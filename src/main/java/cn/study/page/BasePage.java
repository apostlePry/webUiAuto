package cn.study.page;

import cn.study.pojo.PageObjectModel;
import cn.study.pojo.TestCase;
import cn.study.utils.GetLocatorUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BasePage {

    private List<PageObjectModel> pages = new ArrayList<>();

    public void input(By locator, String value) {
//        System.out.println(locator);
//        System.out.println(value);
    }

    public void click(By locator) {
//        System.out.println(locator);
    }

    public void action(Map<String, Object> map) {
        // 如果是page级别的关键字,处理
        if (map.containsKey("page")){
            String action = map.get("action").toString();
            String pageName = map.get("page").toString();
//            pages.forEach(pom-> System.out.println(pom.getPageName()));

            pages.stream().filter(pom -> pom.getPageName().equals(pageName)).findFirst()
                    .get().getMethods().get(action).forEach(step -> action(step));
        }else {
            // 自动化级别
            map.keySet().stream().forEach(key -> {
                if ("click".toLowerCase().contains(key)) {
//                    System.out.println(map.get(key));
                    click(GetLocatorUtil.getByLocator((Map<String, Object>) map.get(key)));
                }

                if ("input".toLowerCase().contains(key)) {
                    String value = map.get("text").toString();
                    input(GetLocatorUtil.getByLocator((Map<String, Object>) map.get(key)), value);
                }

            });
        }

    }

    public void run(TestCase testCase) {
        testCase.getSteps().stream().forEach(map ->
                map.keySet().stream().forEach(key -> {
                    if ("action".toLowerCase().equalsIgnoreCase(key)) {
                        this.action(map);
                    } else {
                        if ("click".toLowerCase().contains(key)) {
                            click(GetLocatorUtil.getByLocator((Map<String, Object>) map.get(key)));
                        }

                        if ("input".toLowerCase().contains(key)) {
                            String value = map.get("text").toString();
                            input(GetLocatorUtil.getByLocator((Map<String, Object>) map.get(key)), value);
                        }
                    }
                })
        );
    }

    /**
     * 加载对应文件内容
     *
     * @author jiaqinwen
     * @creed: Talk is cheap,show me the code
     * @date 2020/6/20 3:37 下午
     */
    public TestCase load(String path) {
        TestCase testCase = null;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            testCase = mapper.readValue(BasePage.class.getResourceAsStream(path), TestCase.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testCase;
    }

    public PageObjectModel loadPage(String path) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PageObjectModel pom = null;
        try {
            pom = mapper.readValue(
                    new File(path),
                    PageObjectModel.class
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pom;
    }

    public void loadPages(String dir) {
        Stream.of(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains("Page");
            }
        })).forEach(path -> {
            path = dir + "/" + path;
            pages.add(this.loadPage(path));
        });
    }
}
