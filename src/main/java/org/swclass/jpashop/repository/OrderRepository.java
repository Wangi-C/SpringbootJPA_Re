package org.swclass.jpashop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.swclass.jpashop.domain.OrderSearch;
import org.swclass.jpashop.domain.OrderStatus;
import org.swclass.jpashop.domain.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    public List<Orders> FindAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Orders> query = cb.createQuery(Orders.class);
        Root<Orders> o = query.from(Orders.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if(orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        query.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Orders> typedQuery = em.createQuery(query).setMaxResults(100);

        return typedQuery.getResultList();
    }

    /**QueryDSL**/
    public List<Orders> findAllByQueryDSL(OrderSearch orderSearch) {
        QOrders order = QOrders.order;
        QMember member = QMember.member;

        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName()))
                .limit(100)
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if(statusCond == null) {
            return null;
        }
        return order.status.eq(statusCond);
    }

    private BooleanExpression nameLike(String nameCond) {
        if(!StringUtils.hasText(nameCond)) {
            return null;
        }
        return member.name.like(nameCond);
    }
}
