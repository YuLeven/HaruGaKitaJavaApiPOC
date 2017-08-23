package controllers;

import dao.KanjiDAO;
import filters.TokenSecured;
import models.Kanji;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.APIError;
import util.ErrorHandling;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
@Security.Authenticated(TokenSecured.class)
public class KanjiController extends Controller {

    private final KanjiDAO kanjiDAO;

    @Inject
    public KanjiController(KanjiDAO kanjiDAO) {
        this.kanjiDAO = kanjiDAO;
    }

    @Transactional(readOnly = true)
    public Result getAllKanji() {
        return ok(
            toJson(kanjiDAO.findAll())
        );
    }

    @Transactional(readOnly = true)
    public Result getKanjiByKanji(String kanjiString) {

        //Local variables used throught the method
        Kanji kanji;

        //Loads the kanji, returning early if not found
        try {
            kanji = kanjiDAO.findOneByKanji(kanjiString);
        } catch (NoResultException ex) {
            return notFound(
                toJson(ErrorHandling.makeErrorMessage(APIError.KANJI_NOT_FOUND))
            );
        }

        //Returns to the caller
        return ok(
            toJson(kanji)
        );
    }

}
