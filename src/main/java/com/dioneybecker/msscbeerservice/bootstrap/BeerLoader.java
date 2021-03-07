package com.dioneybecker.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.brewery.model.BeerStyleEnum;

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
            
            Beer b1 = Beer.builder().beerName("Mango Bobs").beerStyle(BeerStyleEnum.ALE.name()).quantityToBrew(200)
                    .minOnHand(12).upc(BEER_1_UPC).price(new BigDecimal("12.95")).build();

            beerRepository.save(Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyleEnum.PALE_ALE).quantityToBrew(200)
                    .minOnHand(12).upc(337001000001L).price(new BigDecimal("11.50")).build());
            Beer b2 = Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyleEnum.PALE_ALE.name()).quantityToBrew(200)
                    .minOnHand(12).upc(BEER_2_UPC).price(new BigDecimal("11.50")).build();

            beerRepository.save(Beer.builder().beerName("Tupiniquim").beerStyle(BeerStyleEnum.IPA).quantityToBrew(200).minOnHand(12)
                    .upc(77001000001L).price(new BigDecimal("22.90")).build());
            Beer b3 = Beer.builder().beerName("Pinball Porter").beerStyle(BeerStyleEnum.IPA.name()).quantityToBrew(200)
                    .minOnHand(12).upc(BEER_3_UPC).price(new BigDecimal("22.90")).build();

            this.beerRepository.save(b1);
            this.beerRepository.save(b2);
            this.beerRepository.save(b3);
        }
    }
}