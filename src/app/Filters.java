import play.filters.cors.CORSFilter;
import play.filters.gzip.GzipFilter;
import play.http.HttpFilters;
import play.mvc.EssentialFilter;

import javax.inject.Inject;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-30
 */
public class Filters implements HttpFilters {

    @Inject
    private CORSFilter corsFilter;

    @Inject
    private GzipFilter gzipFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] {
                corsFilter.asJava(),
                gzipFilter.asJava()
        };
    }
}