package cn.study.page.web;

import cn.study.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebBasePage extends BasePage {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    public WebBasePage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public WebElement getElement(By by) {
        return driver.findElement(by);
    }

    @Override
    public void action(Map<String, Object> map) {
        super.action(map);
        if ("get".equalsIgnoreCase(String.valueOf(map.get("action")))){
            driver.get(map.get("url").toString());
        }
    }

    @Override
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.getElement(locator).click();
    }

    @Override
    public void input(By locator, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = this.getElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public void quit(){
        driver.quit();
    }
}
