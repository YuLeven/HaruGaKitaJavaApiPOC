package controllers;

import com.google.inject.Inject;
import dao.PrepaidPackageDAO;
import dao.UserDAO;
import filters.TokenSecured;
import models.PrepaidPackage;
import models.User;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.Security;
import util.APIError;
import util.ErrorHandling;
import util.Paypal;

import javax.persistence.NoResultException;
import java.util.List;

import static play.libs.Json.toJson;
import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
@Security.Authenticated(TokenSecured.class)
public class PrepaidPackageController {

    private final PrepaidPackageDAO packageDAO;
    private final UserDAO userDAO;

    @Inject
    public PrepaidPackageController(PrepaidPackageDAO packageDAO, UserDAO userDAO) {
        this.packageDAO = packageDAO;
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public Result getPackage(String ID) {

        //Local variables used in this method
        PrepaidPackage pack;
        User user;

        //Loads the user from the passed token
        try {
            user = userDAO.findByEmail(request().username());
        } catch (NoResultException ex) {
            return badRequest(
                toJson(ErrorHandling.makeErrorMessage(APIError.USER_NOT_FOUND))
            );
        }

        //Loads the package using the informed ID
        pack = packageDAO.find(Long.valueOf(ID));

        //Returns early if no package was found
        if (pack == null) return notFound(
            toJson(ErrorHandling.makeErrorMessage(APIError.PACKAGE_NOT_FOUND))
        );

        //Returns to the caller
        pack.setPaypalPaymentURL(Paypal.createPaypalURL(user, pack));
        return ok(
            toJson(pack)
        );
    }


    @Transactional
    public Result getAllPackages() {

        //Local variables used in this method
        User user;
        List<PrepaidPackage> prepaidPackageList;

        //Loads the user from the passed token
        try {
            user = userDAO.findByEmail(request().username());
        } catch (NoResultException ex) {
            return notFound(
                toJson(ErrorHandling.makeErrorMessage(APIError.USER_NOT_FOUND))
            );
        }

        //Loads the list of packages
        try {
            prepaidPackageList = packageDAO.findAll();
        } catch (NoResultException ex) {
            return ok(
                toJson(new Object[] {})
            );
        }

        //Prepares the Paypal link for each package
        for (PrepaidPackage pack : prepaidPackageList) {
            pack.setPaypalPaymentURL(
                Paypal.createPaypalURL(user, pack)
            );
        }

        return ok(
            toJson(prepaidPackageList)
        );
    }
}
