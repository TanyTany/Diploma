package ru.netology.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private String id;
    private String bank_id;
    private String created;
    private String status;
}
