package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.DbInteraction;
import ru.netology.web.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCredit {

    @BeforeAll
    static void setUp() {
        Configuration.headless = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUpDb() {
        DbInteraction.clearTables();
        open("http://localhost:8080");
    }

    @Test
    @Description("Заполнение валидными данными с картой APPROVED")
    void shouldSuccessPayValidData() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getSuccessPayValidData();
        creditGatePage.payData(payInfo);
        creditGatePage.successMsg();
        DbInteraction.checkRegisterCount("order_entity", 1);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 1);
        assertEquals("APPROVED", DbInteraction.getCredit().getStatus());
    }

    @Test
    @Description("Заполнение валидными данными с картой DECLINED")
    void shouldFailurePayValidData() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getFailurePayValidData();
        creditGatePage.payData(payInfo);
        creditGatePage.failureMsg();
        DbInteraction.checkRegisterCount("order_entity", 1);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 1);
        assertEquals("DECLINED", DbInteraction.getCredit().getStatus());
    }

    /// for field card number

    @Test
    @Description("Номер карты не заполнен")
    void shouldInvalidFieldNumberOfEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfEmpty();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);

    }

    @Test
    @Description("Номер карты из одинаковых цифр")
    void shouldInvalidFieldNumberOfSameNumb() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfSameNumb();
        creditGatePage.payData(payInfo);
        creditGatePage.failureMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Номер карты заполнен не полностью")
    void shouldInvalidFieldNumberNotCompletely() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberNotCompletely();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field month

    @Test
    @Description("Поле Месяц заполнено невалидным значением")
    void shouldInvalidMonthNonexistent() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthNonexistent();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidDatesExpireMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц заполнено однозначным числом")
    void shouldInvalidMonthOneFigure() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthOneFigure();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц не заполнено")
    void shouldInvalidMonthEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthEmpty();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц заполнено нулевым значением")
    void shouldInvalidMontZeroValue() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMontZeroValue();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field year
    @Test
    @Description("Поле Год с истекшим сроком")
    void shouldInvalidLastYear() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidLastYear();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidLastYearMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год заполнено однозначным числом")
    void shouldInvalidYearOneFigure() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidYearOneFigure();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год не заполнено")
    void shouldInvalidYearEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidYearEmpty();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год 30")
    void shouldInvalidMoreFutureYear() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMoreFutureYear();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidDatesExpireMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field card holder

    @Test
    @Description("Поле Владелец заполнено только Имя")
    void shouldNameWithoutSurname() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getNameWithoutSurname();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец заполнено кириллицей")
    void shouldInvalidNameCyrillic() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameCyrillic();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец не заполнено")
    void shouldInvalidNameEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameEmpty();
        creditGatePage.payData(payInfo);
        creditGatePage.requiredFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец заполнено числовыми значениями")
    void shouldInvalidNameNumbers() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameNumbers();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field CVV code

    @Test
    @Description("Поле CVV заполнено не полностью")
    void shouldInvalidCVVNonCompletely() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidCVVNonCompletely();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле CVV не заполнено")
    void shouldInvalidCVVEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidCVVEmpty();
        creditGatePage.payData(payInfo);
        creditGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

}
