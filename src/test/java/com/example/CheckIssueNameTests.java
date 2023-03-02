package com.example;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class CheckIssueNameTests {

    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final String ISSUE_NAME = "issue_to_test_allure_report";

    @Test
    @Story("Проверка названия задачи в allure-example")
    @Owner("szhuravlev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тест на проверку названия задачи на чистом Selenide")
    public void testCheckIssueNameUsingListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUE_NAME)).should(exist);
    }

    @Test
    @Story("Проверка названия задачи в allure-example")
    @Owner("szhuravlev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тест на проверку названия задачи с использованием лямбда-шагов")
    public void testCheckIssueNameUsingSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открыть главную страницу github", () -> {
            open("https://github.com");
        });
        step("Найти репозиторий " + REPOSITORY + " и открыть его", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
            $(linkText(REPOSITORY)).click();
            $("#issues-tab").click();
        });

        step("Проверить, что задача с названием " + ISSUE_NAME + " находится в репозитории " + REPOSITORY, () -> {
            $(withText(ISSUE_NAME)).should(exist);
        });
    }

    @Test
    @Story("Проверка названия задачи в allure-example")
    @Owner("szhuravlev")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тест на проверку названия задачи с аннотацией @Step")
    public void testCheckIssueNameUsingAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        AnnotatedSteps steps = new AnnotatedSteps();
        steps.openMainPage();
        steps.findRepositoryAndClick(REPOSITORY);
        steps.checkIssueName(ISSUE_NAME);
    }
}