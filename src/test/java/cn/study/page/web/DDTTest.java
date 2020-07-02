package cn.study.page.web;

import cn.study.factory.PageFactory;
import cn.study.page.BasePage;
import cn.study.pojo.TestCase;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DDTTest {
    private static BasePage basePage;

    @ParameterizedTest
    @MethodSource
    public void testLoadPage(TestCase testCase) {
        basePage.run(testCase);
    }

    public static List<Arguments> testLoadPage() {
        basePage = PageFactory.createPage("web");
        List<Arguments> all = new ArrayList<>();

        Arrays.asList(
                "/cn/study/page/web/TestRun.yaml"
        ).stream().forEach(path -> {
            List<TestCase> testCases = basePage.load(path);
            testCases.stream().forEach(testCase ->
                    all.add(Arguments.arguments(testCase))
            );
        });
        return all;

    }

    @AfterAll
    public static void after() {
        try {
            Thread.sleep(3000);
            ((WebBasePage)basePage).quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
