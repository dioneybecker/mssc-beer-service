package com.dioneybecker.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.msscbeerservice.web.models.BeerStyleEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * BeerLoader
 */
@Component
public class BeerLoader implements CommandLineRunner {

    @Autowired
    private BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.loadBeer();
    }

    private void loadBeer() {
        if (this.beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().beerName("Mango Bobs").beerStyle(BeerStyleEnum.ALE).quantityToBrew(200).minOnHand(12)
                    .upc(447001000001L).price(new BigDecimal("12.95")).build());

            beerRepository.save(Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyleEnum.PALE_ALE).quantityToBrew(200)
                    .minOnHand(12).upc(337001000001L).price(new BigDecimal("11.50")).build());

            beerRepository.save(Beer.builder().beerName("Tupiniquim").beerStyle(BeerStyleEnum.IPA).quantityToBrew(200).minOnHand(12)
                    .upc(77001000001L).price(new BigDecimal("22.90")).build());
        }
    }
}