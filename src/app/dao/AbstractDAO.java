package dao;

import models.Kanji;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    private Class<T> entitySubclass;

    public AbstractDAO(Class<T> entitySubclass) {
        this.entitySubclass = entitySubclass;
    }

    protected abstract EntityManager getEM();

    @Override
    public T create(T entity) {
        getEM().persist(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        getEM().remove(getEM().merge(entity));
    }

    @Override
    public T update(T entity) {
        getEM().merge(entity);
        return entity;
    }

    @Override
    public T find(Long ID) {
        return getEM().find(entitySubclass, ID);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = getEM().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entitySubclass));
        return getEM().createQuery(cq).getResultList();
    }
}
