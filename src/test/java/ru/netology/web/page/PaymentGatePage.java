package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentGatePage {
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

    public PaymentGatePage() {
        heading.shouldBe(Condition.visible);
    }
}
