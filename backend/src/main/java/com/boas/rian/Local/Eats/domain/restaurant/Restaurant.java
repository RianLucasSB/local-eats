package com.boas.rian.Local.Eats.domain.restaurant;

import com.boas.rian.Local.Eats.domain.enums.Category;
import com.boas.rian.Local.Eats.domain.utils.Point;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double rating;

    private List<Category> categories;

    private String phoneNumber;

    private String instagramUrl;

    private String websiteUrl;

    @ElementCollection
    private List<String> imagesUrl;

    @Embedded
    private Point coordinates;

}
