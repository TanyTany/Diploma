package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CreditGatePage {
    private SelenideElement heading = $(byText("Оплата по карте")).parent();
    private SelenideElement cardNumber = $(byText("Номер карты"));
    private SelenideElement monthCard = $(byText("Месяц"));
    private SelenideElement yearCard = $(byText("Год"));
    private SelenideElement cardHolder = $(byText("Владелец"));
    private SelenideElement cvvCode = $(byText("CVC/CVV"));
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement success = $(byText("Операция одобрена Банком."));
    private SelenideElement failure = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement invalidDates = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expireDates = $(byText("Истёк срок действия карты"));
    private SelenideElement invalidUser = $(byText("Неверный формат имени и фамилии")); //bag
    private SelenideElement invalidCvv = $(byText("Неверный формат"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));

    public CreditGatePage() {
        heading.shouldBe(Condition.visible);
    }

    public void payData(DataHelper.CardInfo info) {
        cardNumber.setValue(info.getCardNumber());
        monthCard.setValue(info.getMonthCard());
        yearCard.setValue(info.getYearCard());
        cardHolder.setValue(info.getCardHolder());
        cvvCode.setValue(info.getCvvCode());
        continueButton.click();
    }

    public void successMsg() {
        success.shouldBe(Condition.visible, Duration.ofSeconds(8));
    }
    public void failureMsg() {
        failure.shouldBe(Condition.visible, Duration.ofSeconds(8));
    }
    public void invalidYearMsg() {
        invalidDates.shouldBe(Condition.visible);
    }
    public void invalidLastYearMsg() {
        expireDates.shouldBe(Condition.visible);
    }
    public void invalidUserMsg() {
        invalidUser.shouldBe(Condition.visible);
    }
    public void invalidCvvMsg() {
        invalidCvv.shouldBe(Condition.visible);
    }
    public void requiredFieldMsg() {
        requiredField.shouldBe(Condition.visible);
    }
}
