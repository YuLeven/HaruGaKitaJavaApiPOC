package controllers;

import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.mvc.Controller;
import play.mvc.Result;
import util.APIError;
import util.Constants;
import util.ErrorHandling;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-27
 */
public class SessionController extends Controller {

    @Inject
    private Constants constants;

    @Inject
    private WSClient ws;

    public CompletionStage<Result> getToken() {

        //Handles errors when the env variable needed to access the legacy server
        //is not set.
        if (constants.HARU_RAILS_APP_SERVER_LOCATION == null) {
            Logger.error("Mandatory environment variable HARU_RAILS_APP_SERVER_LOCATION isn't set");
            return CompletableFuture.completedFuture(
                internalServerError(
                    toJson(ErrorHandling.makeErrorMessage(APIError.INTERNAL_SERVER_ERROR))
                )
            );
        }

        //Perform an async call to the legacy Rails server to retrieve a new token
        //which can be used by the caller to connect to secure resources in this API
        WSRequest request = ws.url(constants.HARU_RAILS_APP_SERVER_LOCATION + "/api/sign_in");
        return request.post(request().body().asJson()).thenApply(wsResponse -> {
            switch (wsResponse.getStatus()) {
                case 200:
                    return ok(
                        wsResponse.asJson()
                    );
                default:
                    return unauthorized(
                        toJson(ErrorHandling.makeErrorMessage(APIError.UNAUTHORIZED))
                    );
            }
        //Handles errors connecting to the legacy API
        }).exceptionally(error -> {
            Logger.error(error.getMessage());
            return unauthorized(
                toJson(ErrorHandling.makeErrorMessage(APIError.INTERNAL_SERVER_ERROR))
            );
        });
    }
}
