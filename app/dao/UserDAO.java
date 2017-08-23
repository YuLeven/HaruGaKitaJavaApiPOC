package dao;

import models.User;
import play.db.jpa.JPAApi;

import javax.inject.Inject;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public class UserDAO implements DAO<User> {
    public static final String FIND_BY_EMAIL = "findByEmail";
    private final JPAApi jpaApi;

    @Inject
    public UserDAO(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public User findByEmail(String email) {
        return (User) jpaApi.em().createNamedQuery(FIND_BY_EMAIL)
                                 .setParameter("email", email)
                                 .getSingleResult();
    }

    @Override
    public User create(User entity) {
        jpaApi.em().persist(entity);
        return entity;
    }

    @Override
    public void delete(User entity) {
        entity = this.jpaApi.em().merge(entity);
        this.jpaApi.em().remove(entity);
    }

    @Override
    public User update(User entity) {
        return this.jpaApi.em().merge(entity);
    }

    @Override
    public User find(Long ID) {
        return this.jpaApi.em().find(User.class, ID);
    }

    public Long countWatchedClasses(Long ID, Boolean onlyUnpayed) {
        String query = "SELECT count(c) from Class c WHERE c.user.id = :user_id";
        if (onlyUnpayed) query += " AND c.payed = true";

        //Counts the number of classes matching the query
        return (Long) this.jpaApi.em()
                                 .createQuery(query)
                                 .setParameter("user_id", ID)
                                 .getSingleResult();

    }
}
