package com.boas.rian.Local.Eats.domain.restaurant;

import com.boas.rian.Local.Eats.domain.enums.Category;
import com.boas.rian.Local.Eats.domain.utils.Point;

import java.util.List;

public record CreateRestaurantResponseDto(
        Long id,
        String name,

        Double rating,

        List<Category> categories,

        String phoneNumber,

        String instagramUrl,

        String websiteUrl,

        List<String> imagesUrl,

        Point coordinates
) {
    public CreateRestaurantResponseDto(Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getRating(), restaurant.getCategories(), restaurant.getPhoneNumber(), restaurant.getInstagramUrl(), restaurant.getWebsiteUrl(), restaurant.getImagesUrl(), restaurant.getCoordinates());
    }
}
