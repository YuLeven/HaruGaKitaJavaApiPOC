package util;

import javax.inject.Singleton;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-02-05
 */
@Singleton
public class Constants {
    public final String HARU_RAILS_APP_SERVER_LOCATION = System.getenv("HARU_RAILS_APP_SERVER_LOCATION");
}
