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

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DbInteraction.getRecordCountOfPaymentEntity;

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
    void shouldSuccessPayValidData() throws SQLException {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getSuccessPayValidData();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.verifySuccessMsg();
        assertEquals(1, getRecordCountOfPaymentEntity());
        assertEquals("APPROVED", DbInteraction.getPayment().getStatus());
    }

    @Test
    @Description("Заполнение валидными данными с картой DECLINED")
    void shouldFailurePayValidData() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getFailurePayValidData();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertFailureMsg();
        assertEquals(1, getRecordCountOfPaymentEntity());
        assertEquals("DECLINED", DbInteraction.getPayment().getStatus());
    }

    /// for field card number

    @Test
    @Description("Номер карты не заполнен")
    void shouldInvalidFieldNumberOfEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfEmpty();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Номер карты из одинаковых цифр")
    void shouldInvalidFieldNumberOfSameNumb() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfSameNumb();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertFailureMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Номер карты заполнен не полностью")
    void shouldInvalidFieldNumberNotCompletely() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidFieldNumberNotCompletely();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    /// for field month

    @Test
    @Description("Поле Месяц заполнено невалидным значением")
    void shouldInvalidMonthNonexistent() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthNonexistent();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidDatesExpireMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Месяц заполнено однозначным числом")
    void shouldInvalidMonthOneFigure() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthOneFigure();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Месяц не заполнено")
    void shouldInvalidMonthEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMonthEmpty();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Месяц заполнено нулевым значением")
    void shouldInvalidMontZeroValue() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMontZeroValue();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    /// for field year
    @Test
    @Description("Поле Год с истекшим сроком")
    void shouldInvalidLastYear() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidLastYear();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidLastYearMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Год заполнено однозначным числом")
    void shouldInvalidYearOneFigure() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidYearOneFigure();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Год не заполнено")
    void shouldInvalidYearEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidYearEmpty();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Год 30")
    void shouldInvalidMoreFutureYear() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidMoreFutureYear();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidDatesExpireMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    /// for field card holder

    @Test
    @Description("Поле Владелец заполнено только Имя")
    void shouldNameWithoutSurname() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getNameWithoutSurname();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Владелец заполнено кириллицей")
    void shouldInvalidNameCyrillic() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameCyrillic();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Владелец не заполнено")
    void shouldInvalidNameEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameEmpty();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertRequiredFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле Владелец заполнено числовыми значениями")
    void shouldInvalidNameNumbers() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidNameNumbers();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    /// for field CVV code

    @Test
    @Description("Поле CVV заполнено не полностью")
    void shouldInvalidCVVNonCompletely() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidCVVNonCompletely();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

    @Test
    @Description("Поле CVV не заполнено")
    void shouldInvalidCVVEmpty() {
        val startPage = new StartPage();
        val paymentGatePage = startPage.paymentGate();
        val payInfo = DataHelper.getInvalidCVVEmpty();
        paymentGatePage.setDebitCardPayData(payInfo);
        paymentGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfPaymentEntity());
    }

}
