package com.boas.rian.Local.Eats.controller;

import com.boas.rian.Local.Eats.domain.authentication.IAuthenticationFacade;
import com.boas.rian.Local.Eats.domain.enums.UserType;
import com.boas.rian.Local.Eats.domain.partner.Partner;
import com.boas.rian.Local.Eats.domain.partner.PartnerRepository;
import com.boas.rian.Local.Eats.domain.restaurant.CreateRestaurantDto;
import com.boas.rian.Local.Eats.domain.restaurant.CreateRestaurantResponseDto;
import com.boas.rian.Local.Eats.domain.restaurant.Restaurant;
import com.boas.rian.Local.Eats.domain.restaurant.RestaurantRepository;
import com.boas.rian.Local.Eats.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    IAuthenticationFacade authenticationFacade;

    @PostMapping
    public ResponseEntity<CreateRestaurantResponseDto> createRestaurant(@RequestBody CreateRestaurantDto body, UriComponentsBuilder uriBuilder){
        User user = authenticationFacade.retrieveUser(UserType.PARTNER);

        Partner partner = partnerRepository.findByUserId(user.getId());

        Restaurant restaurant = restaurantRepository.save(new Restaurant(body, partner));

        URI uri = uriBuilder.path("/restaurants/{id}").buildAndExpand(restaurant.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateRestaurantResponseDto(restaurant));
    }
}
