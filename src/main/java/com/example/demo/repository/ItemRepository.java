package com.example.demo.repository;

import com.example.demo.domain.item.Book;
import com.example.demo.domain.item.Item;
import com.example.demo.domain.item.Lecture;
import jakarta.persistence.EntityManager;
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
    public List<?> findAll(Class<?> entityClass) {
        return em.createQuery("select i from " + entityClass.getSimpleName() + " i", entityClass)
                .getResultList();
    }

    public <T> T findOne(Class<T> entityClass, Long id) {
        return em.find(entityClass, id);
    }

    public void delete(Item item) {
        em.remove(item);
    }
}
