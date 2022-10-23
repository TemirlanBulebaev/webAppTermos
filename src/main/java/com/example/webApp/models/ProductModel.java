package com.example.webApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data // Создает конструкторы, гетеры, сеттеры и т.д
@AllArgsConstructor // Конструктор со всем полями, которые в нем присутствуют
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text") // Описание может быть длиннее обычный строки и не будет vachar
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "city")
    private String city;

//    @Column(name = "author")
//    private String author;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ImageModel> images = new ArrayList<>();
    private Long previewImageId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private UserModel user;
    private LocalDateTime dateOfCreated;

    @PrePersist // Инициализация бина
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(ImageModel image) {
        image.setProduct(this);
        images.add(image);
    }

}
