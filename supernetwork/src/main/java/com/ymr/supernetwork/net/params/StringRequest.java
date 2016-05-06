package com.ymr.supernetwork.net.params;

import java.util.Map;

/**
 * Created by ymr on 15/7/8.
 */
public class StringRequest extends SimpleNetParams {

    public StringRequest(String tailUrl) {
        super(tailUrl);
    }

    public StringRequest(String tailUrl, DomainUrl domainUrl) {
        super(tailUrl, domainUrl);
    }

    @Override
    protected Map<String, String> getChildGETParams() {
        return null;
    }

    @Override
    protected Map<String, String> getChildPostParams() {
        return null;
    }
}
