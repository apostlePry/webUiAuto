package cn.study.page.web.love_test;

import cn.study.factory.PageFactory;
import cn.study.page.BasePage;
import cn.study.page.web.WebBasePage;
import cn.study.pojo.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class HomePageTest {
    private static BasePage basePage = null;

    @Test
    public void testLoad(){
        basePage = PageFactory.createPage("selenium");
        TestCase load = basePage.load("/cn/study/page/web/TestRun.yaml");

        basePage.run(load);
    }

    @AfterAll
    public static void after(){
        ((WebBasePage) basePage).quit();
    }

}