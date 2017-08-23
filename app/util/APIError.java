package util;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-30
 */
public enum APIError {

    INTERNAL_SERVER_ERROR,
    RESOURCE_NOT_FOUND,
    USER_NOT_FOUND,
    PACKAGE_NOT_FOUND,
    KANJI_NOT_FOUND,
    UNAUTHORIZED;

    public String getHumanReadableMessage() {
        switch (this) {
            case INTERNAL_SERVER_ERROR:
                return "The server behaved unexpectedly and your request couldn't be processed";
            case RESOURCE_NOT_FOUND:
                return "The requested resource couldn't be found";
            case USER_NOT_FOUND:
                return "The requested user could not be found.";
            case PACKAGE_NOT_FOUND:
                return "The requested pre-paid package could not be found.";
            case KANJI_NOT_FOUND:
                return "The requested kanji could not be found.";
            case UNAUTHORIZED:
                return "The informed credentials are invalid.";
            default:
                return "An error occurred but a message could not be retrieved";
        }
    }
}
