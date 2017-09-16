package dao;

import models.User;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public class UserDAO extends AbstractDAO<User> {

    public static final String FIND_BY_EMAIL = "findByEmail";
    private final JPAApi jpaAPI;

    @Inject
    public UserDAO(JPAApi jpaAPI) {
        super(User.class);
        this.jpaAPI = jpaAPI;
    }

    public User findByEmail(String email) {
        return (User) getEM().createNamedQuery(FIND_BY_EMAIL)
                             .setParameter("email", email)
                             .getSingleResult();
    }

    public Long countWatchedClasses(Long ID, Boolean onlyUnpayed) {
        String query = "SELECT count(c) from Class c WHERE c.user.id = :user_id";
        if (onlyUnpayed) query += " AND c.payed = true";

        //Counts the number of classes matching the query
        return (Long) getEM().createQuery(query)
                             .setParameter("user_id", ID)
                             .getSingleResult();

    }

    @Override
    protected EntityManager getEM() {
        return jpaAPI.em();
    }
}
