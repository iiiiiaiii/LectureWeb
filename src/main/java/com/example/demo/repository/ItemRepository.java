package com.example.demo.repository;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.Lecture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    @PersistenceContext
    private final EntityManager em;
    public void save(Item item){
            em.persist(item);
    }
    public <T> List<T> findAll(Class<T> entityClass) {
        return em.createQuery("select i from " + entityClass.getSimpleName() + " i", entityClass)
                .getResultList();
    }

    public <T> T findOne(Class<T> entityClass, Long id) {
        return em.find(entityClass, id);
    }

    public <T> T findByName(Class<T> entityClass, String name) {
        try {
            return em.createQuery("select m from " + entityClass.getSimpleName() + " m where m.name = :name", entityClass)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
    public void delete(Item item) {
        em.remove(item);
    }
}
