package com.example.msscbeerservice.services;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.repositories.BeerRepository;
import com.example.msscbeerservice.web.controllers.NotFoundException;
import com.example.msscbeerservice.web.mappers.BeerMapper;
import com.example.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDTO getById(UUID beerId) {
        return beerMapper.BeerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        Beer beer = beerMapper.BeerDtoToBeer(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.BeerToBeerDto(savedBeer);
    }

    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.BeerToBeerDto(beerRepository.save(beer));
    }
}
