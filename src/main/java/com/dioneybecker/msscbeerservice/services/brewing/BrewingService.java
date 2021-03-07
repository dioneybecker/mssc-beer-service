package com.dioneybecker.msscbeerservice.services.brewing;

import java.util.List;

import com.dioneybecker.msscbeerservice.config.JmsConfig;
import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.brewery.model.events.BrewBeerEvent;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.msscbeerservice.services.inventory.InventoryService;
import com.dioneybecker.msscbeerservice.web.mappers.BeerMapper;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("!awsdev")
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {
    private final BeerRepository beerRepository;
    private final InventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;
    

    @Scheduled(fixedRate = 10000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Qty OnHand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
