package util;

import models.PrepaidPackage;
import models.User;
import org.apache.http.client.utils.URIBuilder;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-23
 */
public class Paypal {

    private final String HARU_PAYMENT_EMAIL = System.getenv("HARU_PAYPAL_EMAIL");
    private final String HARU_PAYMENT_RETURN_URL = System.getenv("HARU_PAYMENT_RETURN_URL");
    private final String HARU_PAYPAL_NOTIFICATION_HOOK = System.getenv("HARU_PAYPAL_NOTIFICATION_HOOK");

    public static String createPaypalURL(User user, PrepaidPackage prepaidPackage) {

        return new URIBuilder()
                .setScheme("https")
                .setHost("www.paypal.com")
                .setPath("/cgi-bin/webscr")
                .addParameter("business", HARU_PAYMENT_EMAIL)
                .addParameter("cmd", "_xclick")
                .addParameter("return", HARU_PAYMENT_RETURN_URL)
                .addParameter("invoice", prepaidPackage.getId().toString())
                .addParameter("amount", user.getPrice().toString())
                .addParameter("currency_code", "BRL")
                .addParameter("item_name", "Pacote de Horas - " + Time.millisecondsToFormattedHourString(prepaidPackage.getHours()))
                .addParameter("notify_url", HARU_PAYPAL_NOTIFICATION_HOOK)
                .toString();
    }
}
