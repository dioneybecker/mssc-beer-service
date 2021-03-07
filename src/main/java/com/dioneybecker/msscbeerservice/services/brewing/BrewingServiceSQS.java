package com.dioneybecker.msscbeerservice.services.brewing;

import java.util.List;

import com.dioneybecker.brewery.model.events.BrewBeerEvent;
import com.dioneybecker.msscbeerservice.config.AwsSqsConfig;
import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.dioneybecker.msscbeerservice.services.inventory.InventoryService;
import com.dioneybecker.msscbeerservice.web.mappers.BeerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("awsdev")
@Slf4j
@RequiredArgsConstructor
public class BrewingServiceSQS {
    private final BeerRepository beerRepository;
    private final InventoryService beerInventoryService;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final BeerMapper beerMapper;
    private final ObjectMapper objectMapper;


    @Value("${cloud.aws.endpoint.brewing-request-url}")
    private String endpoint;
    

    @Scheduled(fixedRate = 10000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Qty OnHand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                //jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
                BrewBeerEvent event = new BrewBeerEvent(beerMapper.beerToBeerDto(beer));
                try {
                    queueMessagingTemplate.send(AwsSqsConfig.BREWING_REQUEST_QUEUE,
                            MessageBuilder.withPayload(objectMapper.writeValueAsString(event)).build());
                } catch (MessagingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
