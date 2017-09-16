package dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import models.Kanji;
import play.db.jpa.JPAApi;

import java.util.List;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public class KanjiDAO extends AbstractDAO<Kanji> {

    private final JPAApi jpaAPI;

    @Inject
    public KanjiDAO(JPAApi jpaAPI) {
        super(Kanji.class);
        this.jpaAPI = jpaAPI;
    }

    @SuppressWarnings("unchecked")
    public List<Kanji> findByKanji(String kanji) {
        return getEM().createQuery("FROM Kanji k WHERE k.kanji = :kanji")
                .setParameter("kanji", kanji)
                .getResultList();
    }

    @Override
    protected EntityManager getEM() {
        return jpaAPI.em();
    }
}
