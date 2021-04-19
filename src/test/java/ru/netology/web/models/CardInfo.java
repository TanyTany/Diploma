package ru.netology.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Data
public class CardInfo {
        private String cardNumber;
        private String monthCard;
        private String yearCard;
        private String cardHolder;
        private String cvvCode;
}

