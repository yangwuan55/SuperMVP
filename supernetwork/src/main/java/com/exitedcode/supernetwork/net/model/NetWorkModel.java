package com.exitedcode.supernetwork.net.model;


import com.exitedcode.supernetwork.net.params.NetRequestParams;

/**
 * Created by ymr on 15/5/14.
 */
public interface NetWorkModel<T> extends IModel {

    void updateDatas(NetRequestParams params, UpdateListener<T> listener);

    void updateDatas(NetRequestParams params, UpdateListener<T> listener, boolean forceFromServer);

    interface UpdateListener<T> {
        void finishUpdate(T result);
        void onError(Error error);
    }

    interface NetworkChangedListener {
        void onNetworkChange();
    }

    class Error {
        private int errorCode = -1;
        private String msg;
        private NetRequestParams netRequestParams;
        private Object tag;

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getNetRequestParams() {
            return netRequestParams;
        }

        public void setNetRequestParams(NetRequestParams netRequestParams) {
            this.netRequestParams = netRequestParams;
        }

        public Object getTag() {
            return tag;
        }

        public void setTag(Object tag) {
            this.tag = tag;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "errorCode=" + errorCode +
                    ", msg='" + msg + '\'' +
                    ", netRequestParams=" + netRequestParams +
                    ", tag=" + tag +
                    '}';
        }
    }
}
