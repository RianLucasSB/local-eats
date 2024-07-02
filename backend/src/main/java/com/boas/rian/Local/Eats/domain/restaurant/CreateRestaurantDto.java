package com.boas.rian.Local.Eats.domain.restaurant;

import com.boas.rian.Local.Eats.domain.enums.Category;
import com.boas.rian.Local.Eats.domain.partner.Partner;
import com.boas.rian.Local.Eats.domain.utils.Point;

import java.util.List;

public record CreateRestaurantDto(
         String name,

         Double rating,

         List<Category>categories,

         String phoneNumber,

         String instagramUrl,

         String websiteUrl,

         List<String> imagesUrl,

         Point coordinates

) {
}
