package util;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-22
 */
public class ErrorHandling {
    public static Object makeErrorMessage(APIError error) {
       return  new Object() {
            public String errorCode = error.toString();
            public String message = error.getHumanReadableMessage();
       };
    }
}
