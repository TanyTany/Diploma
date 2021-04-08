package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $(byText("Путешествие дня"));
    private SelenideElement paymentGateButton = $(byText("Купить"));
    private SelenideElement creditGateButton = $(byText("Купить в кредит"));

    public DashboardPage() {
        heading.shouldBe(Condition.visible);
    }
}
