package dao;

import com.google.inject.Inject;
import models.PrepaidPackage;
import play.db.jpa.JPAApi;

import java.util.List;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
public class PrepaidPackageDAO implements DAO<PrepaidPackage> {

    public static final String FIND_ALL_PACKAGES = "findAllPackages";

    private final JPAApi jpaApi;

    @Inject
    public PrepaidPackageDAO(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    public PrepaidPackage create(PrepaidPackage entity) {
        jpaApi.em().persist(entity);
        return entity;
    }

    @Override
    public void delete(PrepaidPackage entity) {
        entity = this.jpaApi.em().merge(entity);
        this.jpaApi.em().remove(entity);
    }

    @Override
    public PrepaidPackage update(PrepaidPackage entity) {
        return this.jpaApi.em().merge(entity);
    }

    @Override
    public PrepaidPackage find(Long ID) {
        return this.jpaApi.em().find(PrepaidPackage.class, ID);
    }

    @SuppressWarnings("unchecked")
    public List<PrepaidPackage> findAll() {
        return (List<PrepaidPackage>) this.jpaApi.em()
                                                 .createNamedQuery(FIND_ALL_PACKAGES)
                                                 .getResultList();
    }
}
