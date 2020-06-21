package cn.study.pojo;

import java.util.List;
import java.util.Map;

public class PageObjectModel {
    private String pageName;
    private Map<String, List<Map<String, Object>>> methods;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Map<String, List<Map<String, Object>>> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, List<Map<String, Object>>> methods) {
        this.methods = methods;
    }
}
