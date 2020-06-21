package cn.study.utils;

import org.openqa.selenium.By;

import java.util.Map;

public class GetLocatorUtil {

    public static By getByLocator(Map<String, Object> map) {
        String key = map.keySet().toArray()[0].toString();
        String value = map.get(key).toString();
        if ("id".equalsIgnoreCase(key)) {
            return By.id(value);
        }
        if ("xpath".equalsIgnoreCase(key)) {
            return By.xpath(value);
        }
        if ("css".equalsIgnoreCase(key)) {
            return By.cssSelector(value);
        }
        if ("linkText".toLowerCase().equalsIgnoreCase(key)) {
            return By.linkText(value);
        }
        if ("partialLinkText".toLowerCase().equalsIgnoreCase(key)) {
            return By.partialLinkText(value);
        }
        throw new RuntimeException(" locator type not exist in this framework：" + key);
    }
}