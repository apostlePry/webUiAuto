package cn.study.factory;

import cn.study.page.BasePage;
import cn.study.page.web.WebBasePage;

public class PageFactory {
    public static BasePage createPage(String driverName){
        if ("web".toLowerCase().equalsIgnoreCase(driverName) || "selenium".toLowerCase().equalsIgnoreCase(driverName)){
            return new WebBasePage();
        }

        if ("app".toLowerCase().equalsIgnoreCase(driverName) || "appium".toLowerCase().equalsIgnoreCase(driverName)){
            return new BasePage();
        }

        return null;
    }

}
