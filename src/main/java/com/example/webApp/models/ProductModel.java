package com.example.webApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Создает конструкторы, гетеры, сеттеры и т.д
@AllArgsConstructor // Конструктор со всем полями, которые в нем присутствуют
public class ProductModel {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String city;
    private String author;

}
