package com.ymr.supernetwork.net.params;

import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.ymr.supernetwork.Env;
import com.ymr.supernetwork.net.util.NetUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ymr on 15/6/18.
 */
public abstract class SimpleNetParams implements NetRequestParams, Serializable {
    private String tailUrl;
    private Map<String, String> mChildGETParams;
    private String mUrl;
    private Map<String, String> mPostParams;

    private boolean getedUrl = false;
    private boolean getedPost = false;
    private DomainUrl domainUrl;

    public SimpleNetParams(String tailUrl) {
        this.tailUrl = tailUrl;
        domainUrl = Env.sWebUrl;
    }

    public SimpleNetParams(String tailUrl,DomainUrl domainUrl) {
        this.tailUrl = tailUrl;
        this.domainUrl = domainUrl;
    }

    public void setTailUrl(String tailUrl) {
        this.tailUrl = tailUrl;
    }

    @Override
    public int getMethod() {
        return getPostParams() == null ? Request.Method.GET : Request.Method.POST;
    }

    @Override
    public String getUrl() {
        if (!getedUrl) {
            getedUrl = true;
            mUrl = getRealUrl();
        }
        return mUrl;
    }

    private String getRealUrl() {
        if (mChildGETParams == null) {
            mChildGETParams = getChildGETParams();
        }
        Map<String, String> sendMap = new HashMap<>();
        if (mChildGETParams != null && mChildGETParams.size() > 0) {
            sendMap.putAll(mChildGETParams);
        }
        if (Env.sCommonParamsGetter != null) {
            sendMap.putAll(Env.sCommonParamsGetter.getCommonParams());
        }

        String url = null;
        if (domainUrl != null) {
            if (Env.isDebug()) {
                url = domainUrl.getDebugUrl();
            } else {
                url = domainUrl.getReleaseUrl();
            }
        }
        return NetUtil.getUrl(generateByTailUrl(url),sendMap);
    }

    @NonNull
    private String generateByTailUrl(String url) {
        return url + tailUrl + "/";
    }

    /**
     * for method GET
     *
     * @return
     */
    protected abstract Map<String, String> getChildGETParams();

    protected abstract Map<String, String> getChildPostParams();

    @Override
    public Map<String, String> getPostParams() {
        if (!getedPost) {
            getedPost = true;
            mPostParams = getChildPostParams();
        }
        return mPostParams;
    }

    @Override
    public String toString() {
        return "SimpleNetParams{" +
                "url = " + getUrl() + " post = " + getPostParams() +
                '}';
    }

    @Override
    public Map<String, String> getHeaders() {
        return null;
    }

    @Override
    public String getCookies() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleNetParams) {
            SimpleNetParams other = (SimpleNetParams) o;
            if (getMethod() == other.getMethod() && getMethod() == Request.Method.POST) {
                return getUrl().equals(other.getUrl()) && getPostParams().equals(other.getPostParams());
            }
            return getUrl().equals(other.getUrl());
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        if (getMethod() == Request.Method.POST) {
            return getUrl().hashCode() + getPostParams().hashCode();
        }
        return getUrl().hashCode();
    }
}
