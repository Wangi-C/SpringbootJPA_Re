package org.swclass.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.swclass.jpashop.domain.Busket;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface BusketRepository extends JpaRepository<Busket, Long> {

}
