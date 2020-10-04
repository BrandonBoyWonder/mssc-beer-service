package com.example.msscbeerservice.web.controllers;

import com.example.msscbeerservice.services.BeerService;
import com.example.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerService.getById(beerId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDTO beerDTO){
        return new ResponseEntity(beerService.saveNewBeer(beerDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,@Validated @RequestBody BeerDTO beerDTO){

        return  new ResponseEntity(beerService.updateBeer(beerId,beerDTO),HttpStatus.NO_CONTENT);
    }
}
