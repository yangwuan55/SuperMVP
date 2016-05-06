package com.ymr.supernetwork.net.volley;

import java.io.File;
import java.util.Map;

/**
 * @author ZhiCheng Guo
 * @version 2014年10月7日 上午11:04:36
 */
public interface MultiPartRequest {

    public void addFileUpload(Map<File, String> fileMap);
    
    public void addStringUpload(Map<String, String> fileParams);
    
    public Map<File, String> getFileUploads();
    
    public Map<String,String> getStringUploads(); 
}