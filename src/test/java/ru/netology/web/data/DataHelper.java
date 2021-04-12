package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    @AllArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String monthCard;
        private String yearCard;
        private String cardHolder;
        private String cvvCode;
    }

    /// Positive Case
    public static CardInfo getSuccessPayValidData() {
        return new CardInfo("4444 4444 4444 4441", "08", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getFailurePayValidData() {
        return new CardInfo("4444 4444 4444 4442", "08", "23", "Ivanov Ivan", "999");
    }

    /// for field card number
    public static CardInfo getInvalidFieldNumberOfEmpty() {
        return new CardInfo("", "08", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidFieldNumberOfSameNumb() {
        return new CardInfo("9999 9999 9999 9999", "08", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidFieldNumberNotCompletely() {
        return new CardInfo("9999 9999 9999 99", "08", "23", "Ivanov Ivan", "999");
    }

    /// for field month
    public static CardInfo getInvalidMonthNonexistent() {
        return new CardInfo("4444 4444 4444 4441", "14", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidMonthOneFigure() {
        return new CardInfo("4444 4444 4444 4441", "1", "23", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidMonthEmpty() {
        return new CardInfo("4444 4444 4444 4441", "", "23", "Ivanov Ivan", "999");
    }


    /// for field year
    public static CardInfo getInvalidLastYear() {
        return new CardInfo("4444 4444 4444 4441", "08", "19", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidYearOneFigure() {
        return new CardInfo("4444 4444 4444 4441", "08", "2", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidYearEmpty() {
        return new CardInfo("4444 4444 4444 4441", "08", "", "Ivanov Ivan", "999");
    }

    public static CardInfo getInvalidMoreFutureYear() {
        return new CardInfo("4444 4444 4444 4441", "08", "30", "Ivanov Ivan", "999");
    }

    /// for field card holder
    public static CardInfo getNameWithoutSurname() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "Ivan", "999");
    }

    public static CardInfo getInvalidNameCyrillic() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "Иван Иванов", "999");
    }

    public static CardInfo getInvalidNameEmpty() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "", "999");
    }

    public static CardInfo getInvalidNameNumbers() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "4356", "999");
    }


    /// for field CVV code
    public static CardInfo getInvalidCVVNonCompletely() {
        return new CardInfo("4444 4444 4444 4441", "08", "23", "Ivanov Ivan", "99");
    }

    public static CardInfo getInvalidCVVEmpty() {
        return new CardInfo("4444 4444 4444 4441", "08", "23", "Ivanov Ivan", "");
    }

}
