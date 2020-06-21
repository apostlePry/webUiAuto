package cn.study.page.web;

import cn.study.factory.PageFactory;
import cn.study.page.BasePage;
import cn.study.pojo.PageObjectModel;
import cn.study.pojo.TestCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

public class BasePageTest {
    private static BasePage basePage;

    @BeforeAll
    public static void beforeAll() {
        basePage = PageFactory.createPage("web");
//        basePage = new BasePage();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    public static void afterAll(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((WebBasePage) basePage).quit();
    }

    @Test
    public void run() {
        TestCase testCase = basePage.load("/cn/study/page/web/TestRun.yaml");
        basePage.run(testCase);
    }

    @Test
    void runPOM() {
        basePage.loadPages("src/main/resources/cn/study/page/web/love_test");
        TestCase testCase = basePage.load("/cn/study/page/web/TestCase.yaml");
        basePage.run(testCase);

    }

    @Test
    void load() throws JsonProcessingException {
        TestCase testCase = basePage.load("src/main/resources/cn/study/page/web/TestCase.yaml");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(testCase));
    }
}
