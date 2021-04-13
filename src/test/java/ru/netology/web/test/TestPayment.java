package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.DbInteraction;
import ru.netology.web.page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayment {
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

    /// Positive Case

    @Test
    @Description("Заполнение валидными данными с картой APPROVED")
    void shouldSuccessPayValidData() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getSuccessPayValidData();
        paymentGatePage.payData(payInfo);
        paymentGatePage.successMsg();
        DbInteraction.checkRegisterCount("order_entity", 1);
        DbInteraction.checkRegisterCount("payment_entity", 1);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
        assertEquals("APPROVED", DbInteraction.getPayment().getStatus());
    }

    @Test
    @Description("Заполнение валидными данными с картой DECLINED")
    void shouldFailurePayValidData() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getFailurePayValidData();
        paymentGatePage.payData(payInfo);
        paymentGatePage.failureMsg();
        DbInteraction.checkRegisterCount("order_entity", 1);
        DbInteraction.checkRegisterCount("payment_entity", 1);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
        assertEquals("DECLINED", DbInteraction.getPayment().getStatus());
    }

    /// for field card number

    @Test
    @Description("Номер карты не заполнен")
    void shouldInvalidFieldNumberOfEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfEmpty();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);

    }

    @Test
    @Description("Номер карты из одинаковых цифр")
    void shouldInvalidFieldNumberOfSameNumb() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfSameNumb();
        paymentGatePage.payData(payInfo);
        paymentGatePage.failureMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Номер карты заполнен не полностью")
    void shouldInvalidFieldNumberNotCompletely() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberNotCompletely();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field month

    @Test
    @Description("Поле Месяц заполнено невалидным значением")
    void shouldInvalidMonthNonexistent() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthNonexistent();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidDatesExpireMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц заполнено однозначным числом")
    void shouldInvalidMonthOneFigure() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthOneFigure();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц не заполнено")
    void shouldInvalidMonthEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthEmpty();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Месяц заполнено нулевым значением")
    void shouldInvalidMontZeroValue() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMontZeroValue();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field year
    @Test
    @Description("Поле Год с истекшим сроком")
    void shouldInvalidLastYear() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidLastYear();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidLastYearMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год заполнено однозначным числом")
    void shouldInvalidYearOneFigure() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidYearOneFigure();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год не заполнено")
    void shouldInvalidYearEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidYearEmpty();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Год 30")
    void shouldInvalidMoreFutureYear() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMoreFutureYear();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidDatesExpireMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field card holder

    @Test
    @Description("Поле Владелец заполнено только Имя")
    void shouldNameWithoutSurname() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getNameWithoutSurname();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец заполнено кириллицей")
    void shouldInvalidNameCyrillic() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameCyrillic();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец не заполнено")
    void shouldInvalidNameEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameEmpty();
        paymentGatePage.payData(payInfo);
        paymentGatePage.requiredFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле Владелец заполнено числовыми значениями")
    void shouldInvalidNameNumbers() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameNumbers();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    /// for field CVV code

    @Test
    @Description("Поле CVV заполнено не полностью")
    void shouldInvalidCVVNonCompletely() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidCVVNonCompletely();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

    @Test
    @Description("Поле CVV не заполнено")
    void shouldInvalidCVVEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidCVVEmpty();
        paymentGatePage.payData(payInfo);
        paymentGatePage.invalidFieldMsg();
        DbInteraction.checkRegisterCount("order_entity", 0);
        DbInteraction.checkRegisterCount("payment_entity", 0);
        DbInteraction.checkRegisterCount("credit_request_entity", 0);
    }

}
