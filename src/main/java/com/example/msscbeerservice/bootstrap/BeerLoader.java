package com.example.msscbeerservice.bootstrap;

import com.example.msscbeerservice.domain.Beer;
import com.example.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner
{
    private final BeerRepository beerRepository;

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";


    @Override
    public void run(String... args) throws Exception {
        loadBeer();
    }

    private void loadBeer() {
        if(beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.96"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cate")
                    .beerStyle("PALE_ALE")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.96"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Windhoek")
                    .beerStyle("PALE_ALE")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.96"))
                    .build());

            System.out.println("Loaded some beers!!!");
        }
    }
}
