package com.example.msscbeerservice.web.controllers;

import com.example.msscbeerservice.bootstrap.BeerLoader;
import com.example.msscbeerservice.services.BeerService;
import com.example.msscbeerservice.web.model.BeerDTO;
import com.example.msscbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBeerById() throws Exception {
        given(beerService.getById(any())).willReturn(getValidBeerDTO());
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDTO beerDTO = getValidBeerDTO();
        String beerToJson = objectMapper.writeValueAsString(beerDTO);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDTO());
        mockMvc.perform(post("/api/v1/beer/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws  Exception{
        given(beerService.updateBeer(any(),any())).willReturn(getValidBeerDTO());
        BeerDTO beerDTO = getValidBeerDTO();
        String beerToJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(put("/api/v1/beer/"+ UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isNoContent());
    }

    BeerDTO getValidBeerDTO(){
        return BeerDTO.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal(19.95))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}