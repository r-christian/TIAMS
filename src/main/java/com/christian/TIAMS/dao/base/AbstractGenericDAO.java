package com.christian.TIAMS.dao.base;

import com.christian.TIAMS.jpa.JPAConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractGenericDAO<T> implements IGenericDAO<T> {

    private final Class<T> type;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractGenericDAO(Class<T> type) {
        super();
        this.type = type;
        this.entityManager = JPAConfiguration.getEntityManager();
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root);
        List<T> result = this.entityManager.createQuery(cq).getResultList();
        return result;
    }

    @Override
    public T update(T object) {
        if(!isActive())
            entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        return object;
    }

    @Override
    public T get(Long id) {
        return this.entityManager.find(type, id);
    }

    @Override
    public void deleteObject(T object)
    {
        if(!isActive())
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id)
    {
        if(!isActive())
            entityManager.getTransaction().begin();
        T object = get(id);
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

    @Override
    public T insert(T object) {
        if(!isActive())
            entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        return object;
    }

    @Override
    public boolean exists(Long id) {
        T entity = this.entityManager.find(this.type, id);
        return entity != null;
    }

    public boolean isActive() {
        return entityManager.getTransaction() != null && entityManager.getTransaction().isActive();
    }
}