package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentGatePage {
    private SelenideElement heading = $(byText("Оплата по карте"));
    private SelenideElement cardNumber = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthCard = $$("[tabindex='-1'] input").get(0);
    private SelenideElement yearCard = $$("[tabindex='-1'] input").get(1);
    private SelenideElement cardHolder = $$("[tabindex='-1'] input").get(2);
    private SelenideElement cvvCode = $$("[tabindex='-1'] input").get(3);
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement success = $(byText("Операция одобрена Банком."));
    private SelenideElement failure = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement invalidDates = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expireDates = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement invalidField = $(".input__sub");


    public PaymentGatePage() {
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
        success.shouldBe(Condition.visible, Duration.ofSeconds(12));
    }

    public void failureMsg() {
        failure.shouldBe(Condition.visible, Duration.ofSeconds(12));
    }

    public void invalidDatesExpireMsg() {
        invalidDates.shouldBe(Condition.visible);
    }

    public void invalidLastYearMsg() {
        expireDates.shouldBe(Condition.visible);
    }

    public void invalidFieldMsg() {
        invalidField.shouldBe(Condition.visible);
    }

    public void requiredFieldMsg() {
        requiredField.shouldBe(Condition.visible);
    }

}
