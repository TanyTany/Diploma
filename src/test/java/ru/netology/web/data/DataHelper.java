package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class PaymentGateInfo {
    }

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
        return new CardInfo("4444 4444 4444 4442", "08", "23", "Ivanov Ivan", "999")
    }

    /// for field card number
    public static CardInfo getInvalidFieldNumberOfEmpty() {
        return new CardInfo("", "08", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidFieldNumberOfSameNumb() {
        return new CardInfo("9999 9999 9999 9999", "08", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidFieldNumberNotCompletely() {
        return new CardInfo("9999 9999 9999 99", "08", "23", "Ivanov Ivan", "999")
    }

    /// for field month
    public static CardInfo getInvalidMonthNonexistentForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "14", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMonthNonexistentForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "14", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMonthOneFigureForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "1", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMonthOneFigureForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "1", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMonthEmptyForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "", "23", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMonthEmptyForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "", "23", "Ivanov Ivan", "999")
    }

    /// for field year
    public static CardInfo getInvalidLastYearForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "19", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidLastYearForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "19", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidYearOneFigureForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "2", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidYearOneFigureForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "2", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidYearEmptyForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidYearEmptyForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMoreFutureYearForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "30", "Ivanov Ivan", "999")
    }

    public static CardInfo getInvalidMoreFutureYearForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "30", "Ivanov Ivan", "999")
    }

    /// for field card holder
    public static CardInfo getInvalidNameWithoutSurnameForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "Ivan", "999")
    }

    public static CardInfo getInvalidNameWithoutSurnameForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "22", "Ivan", "999")
    }

    public static CardInfo getInvalidNameCyrillicForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "Иван Иванов", "999")
    }

    public static CardInfo getInvalidNameCyrillicForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "22", "Иван Иванов", "999")
    }

    public static CardInfo getInvalidNameEmptyForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "", "999")
    }

    public static CardInfo getInvalidNameEmptyForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "22", "", "999")
    }

    public static CardInfo getInvalidNameNumbersForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "22", "4356", "999")
    }

    public static CardInfo getInvalidNameNumbersForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "22", "4356", "999")
    }

    /// for field CVV code
    public static CardInfo getInvalidCodeNonCompletelyForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "23", "Ivanov Ivan", "99");
    }

    public static CardInfo getInvalidCodeNonCompletelyForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "23", "Ivanov Ivan", "99")
    }

    public static CardInfo getInvalidCodeEmptyForSuccessCard() {
        return new CardInfo("4444 4444 4444 4441", "08", "23", "Ivanov Ivan", "");
    }

    public static CardInfo getInvalidCodeEmptyForFailureCard() {
        return new CardInfo("4444 4444 4444 4442", "08", "23", "Ivanov Ivan", "");
    }

}
