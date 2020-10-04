package com.example.msscbeerservice.services;

import com.example.msscbeerservice.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {

    BeerDTO getById(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);
}
