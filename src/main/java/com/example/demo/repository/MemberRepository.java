package com.example.demo.repository;

import com.example.demo.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    @PersistenceContext
    private final EntityManager em;
    public void save(Member member){
        em.persist(member);
    }

    public <T> T findOne(Class<T> entityClass, Long id) {

        return em.find(entityClass, id);
    }

    public <T> T findByNameOne(Class<T> entityClass, String name) {
        try {
            return em.createQuery("select m from " + entityClass.getSimpleName() + " m where m.name = :name",
                            entityClass)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public List<?> findByName(Class<?> entityClass,String name) {
        return em.createQuery("select m from "+entityClass.getSimpleName() +" m where m.name = :name",
                        entityClass)
                .setParameter("name", name)
                .getResultList();
    }

    public List<?> findAll(Class<?> entityClass) {
        return em.createQuery("select i from " + entityClass.getSimpleName() + " i", entityClass)
                .getResultList();
    }

    public void delete(Class<?> entityClass,Long id) {
        Object member = findOne(entityClass, id);
        em.remove(member);
    }
}
