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
import static ru.netology.web.data.DbInteraction.getRecordCountOfCreditEntity;
import static ru.netology.web.data.DbInteraction.getRecordCountOfPaymentEntity;

public class TestCredit {
    private static String url = System.getProperty("app.url");

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
        open(url);
    }

    @Test
    @Description("Заполнение валидными данными с картой APPROVED")
    void shouldSuccessPayValidData() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getSuccessPayValidData();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.verifySuccessMsg();
        assertEquals(1, getRecordCountOfCreditEntity());
        assertEquals("APPROVED", DbInteraction.getCredit().getStatus());
    }

    @Test
    @Description("Заполнение валидными данными с картой DECLINED")
    void shouldFailurePayValidData() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getFailurePayValidData();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertFailureMsg();
        assertEquals(1, getRecordCountOfCreditEntity());
        assertEquals("DECLINED", DbInteraction.getCredit().getStatus());
    }

    /// for field card number

    @Test
    @Description("Номер карты не заполнен")
    void shouldInvalidFieldNumberOfEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfEmpty();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());

    }

    @Test
    @Description("Номер карты из одинаковых цифр")
    void shouldInvalidFieldNumberOfSameNumb() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberOfSameNumb();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertFailureMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Номер карты заполнен не полностью")
    void shouldInvalidFieldNumberNotCompletely() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidFieldNumberNotCompletely();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    /// for field month

    @Test
    @Description("Поле Месяц заполнено невалидным значением")
    void shouldInvalidMonthNonexistent() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthNonexistent();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidDatesExpireMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Месяц заполнено однозначным числом")
    void shouldInvalidMonthOneFigure() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthOneFigure();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Месяц не заполнено")
    void shouldInvalidMonthEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMonthEmpty();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Месяц заполнено нулевым значением")
    void shouldInvalidMontZeroValue() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMontZeroValue();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    /// for field year
    @Test
    @Description("Поле Год с истекшим сроком")
    void shouldInvalidLastYear() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidLastYear();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidLastYearMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Год заполнено однозначным числом")
    void shouldInvalidYearOneFigure() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidYearOneFigure();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Год не заполнено")
    void shouldInvalidYearEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidYearEmpty();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Год 30")
    void shouldInvalidMoreFutureYear() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidMoreFutureYear();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidDatesExpireMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    /// for field card holder

    @Test
    @Description("Поле Владелец заполнено только Имя")
    void shouldNameWithoutSurname() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getNameWithoutSurname();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Владелец заполнено кириллицей")
    void shouldInvalidNameCyrillic() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameCyrillic();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Владелец не заполнено")
    void shouldInvalidNameEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameEmpty();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertRequiredFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле Владелец заполнено числовыми значениями")
    void shouldInvalidNameNumbers() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidNameNumbers();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    /// for field CVV code

    @Test
    @Description("Поле CVV заполнено не полностью")
    void shouldInvalidCVVNonCompletely() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidCVVNonCompletely();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

    @Test
    @Description("Поле CVV не заполнено")
    void shouldInvalidCVVEmpty() {
        val startPage = new StartPage();
        val creditGatePage = startPage.creditGate();
        val payInfo = DataHelper.getInvalidCVVEmpty();
        creditGatePage.setCreditCardPayData(payInfo);
        creditGatePage.assertInvalidFieldMsg();
        assertEquals(0, getRecordCountOfCreditEntity());
    }

}
