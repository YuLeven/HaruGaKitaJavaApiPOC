import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;
import util.APIError;
import util.ErrorHandling;

import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-31
 */
@Singleton
public class ErrorHandler implements HttpErrorHandler {


    @Override
    public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {

        //Intercepts the calls for specific status codes
        switch (statusCode) {
            case 404:
                return CompletableFuture.completedFuture(
                    Results.status(statusCode, toJson(ErrorHandling.makeErrorMessage(APIError.RESOURCE_NOT_FOUND)))
                );
        }

        //Returns a generic message
        return CompletableFuture.completedFuture(
            Results.status(statusCode, message)
        );
    }

    @Override
    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {

        //Prints the exception
        exception.printStackTrace();

        return CompletableFuture.completedFuture(
            Results.internalServerError(
                toJson(ErrorHandling.makeErrorMessage(APIError.INTERNAL_SERVER_ERROR))
            )
        );
    }
}
