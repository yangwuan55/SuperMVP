package com.exitedcode.supernetwork;

import com.exitedcode.supernetwork.net.bean.IApiBase;
import com.exitedcode.supernetwork.net.params.CommonParamsGetter;
import com.exitedcode.supernetwork.net.params.DomainUrl;

import java.util.Map;

/**
 * Created by ymr on 16/5/6.
 */
public interface Init {
    DomainUrl getWebUrl();

    Map<String,String> getHeader();

    CommonParamsGetter getCommonParamsGetter();

    Class<? extends IApiBase> getApibaseClass();

    boolean isDebug();
}
