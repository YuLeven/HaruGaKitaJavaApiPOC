package controllers;

import com.google.inject.Inject;
import dao.UserDAO;
import filters.TokenSecured;
import models.User;
import models.Clazz;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.APIError;
import util.ErrorHandling;

import javax.persistence.NoResultException;
import java.util.List;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
@Security.Authenticated(TokenSecured.class)
public class UserController extends Controller {

    private final UserDAO userDAO;

    @Inject
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public Result getUserStats() {

        //Local variables
        User user;
        Object responseObject;

        //Loads the use whose token was used to make the request
        try {
            user = userDAO.findByEmail(request().username());
        } catch (NoResultException notFoundEx) {
            //Returns early if no user was found
            return badRequest(
                toJson(ErrorHandling.makeErrorMessage(APIError.USER_NOT_FOUND))
            );
        }

        //Mounts the Object which will be mapped to JSON
        responseObject = new Object() {
            //Base Information
            public String name = user.getName();
            public String email = request().username();
            public Integer hoursBalance = user.getUserHour().getAmount();
            public List<Clazz> classes = user.getClasses();
        };

        //Returns to the caller
        return ok(
            toJson(responseObject)
        );
    }
}
