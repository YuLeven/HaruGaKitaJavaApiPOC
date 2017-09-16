package dao;

import com.google.inject.Inject;
import models.PrepaidPackage;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
public class PrepaidPackageDAO extends AbstractDAO<PrepaidPackage> {


    private final JPAApi jpaAPI;

    @Inject
    public PrepaidPackageDAO(JPAApi jpaAPI) {
        super(PrepaidPackage.class);
        this.jpaAPI = jpaAPI;
    }

    @Override
    protected EntityManager getEM() {
        return jpaAPI.em();
    }
}
