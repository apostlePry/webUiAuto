package cn.study.page.web;

import cn.study.factory.PageFactory;
import cn.study.page.BasePage;
import cn.study.pojo.TestCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;

import java.util.List;

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
        List<TestCase> testCases = basePage.load("/cn/study/page/web/TestRun.yaml");
        testCases.stream().forEach(
                testCase -> basePage.run(testCase)
        );
    }

    @Test
    public void runPOM() {
//        basePage.loadPages("classes/cn/study/page/web/love_test");
        basePage.loadPages("src/main/resources/cn/study/page/web/love_test");
        List<TestCase> testCases = basePage.load("/cn/study/page/web/TestCase.yaml");
        testCases.stream().forEach(testCase -> basePage.run(testCase));

    }

    @Test
    public void load() {
        List<TestCase> testCases = basePage.load("/cn/study/page/web/TestRun.yaml");
        ObjectMapper mapper = new ObjectMapper();
        testCases.forEach(testCase -> {
            try {
                System.out.println(mapper.writeValueAsString(testCase));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
