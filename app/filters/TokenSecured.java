package filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import util.APIError;
import util.AuthToken;
import util.ErrorHandling;

import static play.libs.Json.toJson;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public class TokenSecured extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        String tokenBearer = ctx.request().getHeader("Authorization");

        //Returns early if no token was provided
        if (tokenBearer == null || tokenBearer.isEmpty()) return null;

        String[] tokenParts = tokenBearer.split("Bearer ");

        //Returns early if the string is malformed or bears no token
        if (tokenParts.length < 2) return null;

        //Decodes the token
        String token = tokenParts[1];
        DecodedJWT authToken = AuthToken.verifyToken(token);

        //Returns early if the token is invalid
        if (authToken == null) return null;

        //Gets the username claim
        return authToken.getClaim("user").asString();
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized(
            toJson(ErrorHandling.makeErrorMessage(APIError.UNAUTHORIZED))
        );
    }

}
