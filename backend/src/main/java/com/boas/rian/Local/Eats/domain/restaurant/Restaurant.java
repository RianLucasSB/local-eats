package com.boas.rian.Local.Eats.domain.restaurant;

import com.boas.rian.Local.Eats.domain.enums.Category;
import com.boas.rian.Local.Eats.domain.partner.Partner;
import com.boas.rian.Local.Eats.domain.utils.Point;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
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

    @ManyToOne()
    @JoinColumn(name = "partner_id")
    private Partner partner;

    public Restaurant(CreateRestaurantDto restaurantDto){
        this.name = restaurantDto.name();
        this.rating = restaurantDto.rating();
        this.categories = restaurantDto.categories();
        this.phoneNumber = restaurantDto.phoneNumber();
        this.instagramUrl = restaurantDto.instagramUrl();
        this.websiteUrl = restaurantDto.websiteUrl();
        this.imagesUrl = restaurantDto.imagesUrl();
        this.coordinates = restaurantDto.coordinates();
    }

    public Restaurant(CreateRestaurantDto restaurantDto, Partner partner) {
        this.name = restaurantDto.name();
        this.rating = restaurantDto.rating();
        this.categories = restaurantDto.categories();
        this.phoneNumber = restaurantDto.phoneNumber();
        this.instagramUrl = restaurantDto.instagramUrl();
        this.websiteUrl = restaurantDto.websiteUrl();
        this.imagesUrl = restaurantDto.imagesUrl();
        this.coordinates = restaurantDto.coordinates();
        this.partner = partner;
    }
}
