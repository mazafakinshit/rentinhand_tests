package cloud.tests;

import cloud.autotests.tests.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.PressEnter;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import cloud.autotests.utils.FileUtils;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;



@Epic("Rent in Hand")
@Story("Rentinhand tests")
@Tag("RentInHandTests")
public class RentInHandTests extends TestBase {

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));

        if (System.getProperty("selenoid_url") != null) {
        Configuration.remote = "http://" + System.getProperty("selenoid_url") + ":4444/wd/hub";
        }

    }

    @Test
    @Description("Проверка входа на сайт проекта")
    void checkSiteCanBeFound(){

        step("Заходим на сайт", () -> {
        open("https://rentinhand.ru/");
        });
        step("Проверяем наличие текста Rent in hand", () -> {
        $("html").shouldHave(text("Rent in Hand"));
        });

    }

    @Test
    @Description("Проверяем работоспособность кнопок навигационной панели")
    void checkButton() {
        step("Заходим на сайт", () -> {
            open("https://rentinhand.ru/");
        });
        step("Проверяем наличие текста Rent in hand", () -> {
            $("html").shouldHave(text("Rent in Hand"));
        });
        step("Нажимаем на кнопку: Возможности", () -> {
            $(byText("Возможности")).click();
        });
        step("Проверяем скролл до раздела Возможности", () -> {
            $(".col-lg-12").shouldHave(text(" это единая система управления вашим прокатным бизнесом"));
        });
        step("Нажимаем на кнопку: Тарифы", () -> {
            $(byText("Тарифы")).click();
        });
        step("Проверяем наличие раздела Тарифы", () -> {
            $(".f-18").shouldHave(text("Мы предлагем минимальные цены для наших пользовтаелей"));
        });
        step("Нажимаем на кнопку: Расширения", () -> {
            $(byText("Расширения")).click();
        });
        step("Проверяем наличие раздела Расширения", () -> {
            $("html").shouldHave(text("Мы предлагем минимальные цены для наших пользовтаелей"));
        });
        step("Нажимаем на кнопку: Контакты", () -> {
            $(byText("Контакты")).click();
        });
        step("Проверяем наличие раздела Контакты", () -> {
            $("html").shouldHave(text("Контактная информация"));
        });

    }

    @Test
    @Description("")
    void checkAuthorizationForm(){

        step("Заходим в раздел авторизации", () -> {
            open("https://rentinhand.ru/");
            $(byText("Вход")).click();
            $("html").shouldHave(text("Авторизация"));
        });
        step("Заполняем форму авторизации", () -> {
            $("#login").setValue("test");
            $("#password").setValue("123123").pressEnter();
        });
        step("Проверяем успешную авторизацию", () -> {
            $("html").shouldHave(text("Директор"));
        });

    }

}
