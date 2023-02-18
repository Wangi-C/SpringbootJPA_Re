package org.swclass.jpashop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.swclass.jpashop.domain.OrderSearch;
import org.swclass.jpashop.domain.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Orders orders) {
        em.persist(orders);
    }

    public Orders findOne(Long orderId) {
        return em.find(Orders.class, orderId);
    }

    public List<Orders> findAll() {
        return null;
    }

    public List<Orders> findAllByString(OrderSearch orderSearch) {
        String jpql = "select o from Orders o join o.member m";
        boolean isFirstCondition = true;

        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }

            jpql += " o.orderStatus = :status";
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += "where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }

            jpql += " m.name like :name";
        }

        TypedQuery<Orders> query = em.createQuery(jpql, Orders.class).setMaxResults(100);

        if (orderSearch.getOrderStatus() != null) {
            query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }
}
