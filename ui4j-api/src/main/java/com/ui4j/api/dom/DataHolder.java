package com.ui4j.api.dom;

public interface DataHolder {

    void removeData(String key);

    Object getData(String key);

    void setData(String key, Object value);
}
