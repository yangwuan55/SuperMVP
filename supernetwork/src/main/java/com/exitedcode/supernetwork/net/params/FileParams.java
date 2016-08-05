package com.exitedcode.supernetwork.net.params;

import java.io.File;
import java.util.Map;

/**
 * Created by ymr on 15/8/21.
 */
public abstract class FileParams extends SimpleNetParams {

    private final Map<File, String> mFileMap;

    public FileParams(String tailUrl,Map<File,String> fileMap) {
        super(tailUrl);
        mFileMap = fileMap;
    }

    public FileParams(String tailUrl,Map<File,String> fileMap, DomainUrl domainUrl) {
        super(tailUrl, domainUrl);
        mFileMap = fileMap;
    }

    public Map<File,String> getFileMap() {
        return mFileMap;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FileParams && mFileMap != null && ((FileParams) o).getFileMap() != null) {
            return super.equals(o) && mFileMap.equals(((FileParams) o).getFileMap());
        }
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        if (mFileMap != null) {
            return super.hashCode()+mFileMap.hashCode();
        }
        return super.hashCode();
    }
}
