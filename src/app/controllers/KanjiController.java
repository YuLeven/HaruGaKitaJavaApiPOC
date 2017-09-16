package controllers;

import dao.KanjiDAO;
import filters.TokenSecured;
import models.Kanji;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import scala.Option;
import util.APIError;
import util.ErrorHandling;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
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
    public Result getAll() {
        return ok(
            toJson(kanjiDAO.findAll())
        );
    }

    @Transactional(readOnly = true)
    public Result getAll(Option<String> kanji) {

        //TODO: Find a way to do this using method overload?
        //Returns a complete list if the parameter isn't present
        if (kanji.isEmpty()) return getAll();

        return ok(
            toJson(
                kanjiDAO.findByKanji(kanji.get())
            )
        );
    }

    @Transactional(readOnly = true)
    public Result get(Long ID) {

        //Loads the kanji, returning early if not found
        try {
            //Returns to the caller
            return ok(
                toJson(kanjiDAO.find(ID))
            );
        } catch (NoResultException ex) {
            return notFound(
                toJson(ErrorHandling.makeErrorMessage(APIError.KANJI_NOT_FOUND))
            );
        }

    }

}
