package com.exitedcode.supernetwork.net.params;

import java.util.Map;

/**
 * Created by ymr on 15/6/15.
 */
public interface NetRequestParams {
    /**
     *
     * @return Method.GET or Method.POST
     */
    int getMethod();

    /**
     * url for request
     * @return
     */
    String getUrl();

    /**
     * for post method
     * @return
     */
    Map<String,String> getPostParams();

    Map<String,String> getHeaders();

    String getCookies();
}
