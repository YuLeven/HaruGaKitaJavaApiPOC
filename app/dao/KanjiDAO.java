package dao;

import javax.inject.Inject;
import models.Kanji;
import play.db.jpa.JPAApi;

import java.util.List;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public class KanjiDAO implements DAO<Kanji> {
    public static final String FIND_BY_KANJI = "FIND_BY_KANJI";
    public static final String FIND_ALL = "FIND_ALL";
    private final JPAApi jpaApi;

    @Inject
    public KanjiDAO(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    public Kanji create(Kanji entity) {
        jpaApi.em().persist(entity);
        return entity;
    }

    @Override
    public void delete(Kanji kanji) {
        kanji = jpaApi.em().merge(kanji);
        jpaApi.em().remove(kanji);
    }

    @Override
    public Kanji update(Kanji entity) {
        return this.jpaApi.em()
                          .merge(entity);
    }

    @Override
    public Kanji find(Long ID) {
        return jpaApi.em()
                     .find(Kanji.class, ID);
    }

    @SuppressWarnings("unchecked")
    public List<Kanji> findAll() {
        return (List<Kanji>) jpaApi.em()
                                   .createNamedQuery(FIND_ALL)
                                   .getResultList();
    }

    public Kanji findOneByKanji(String kanji) {
        return (Kanji) jpaApi.em()
                             .createNamedQuery(FIND_BY_KANJI)
                             .setParameter("kanji", kanji)
                             .getSingleResult();
    }
}
