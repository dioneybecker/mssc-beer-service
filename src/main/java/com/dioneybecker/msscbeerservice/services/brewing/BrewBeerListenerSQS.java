package com.dioneybecker.msscbeerservice.services.brewing;

import com.dioneybecker.brewery.model.BeerDto;
import com.dioneybecker.brewery.model.events.BrewBeerEvent;
import com.dioneybecker.brewery.model.events.NewInventoryEvent;
import com.dioneybecker.msscbeerservice.config.AwsSqsConfig;
import com.dioneybecker.msscbeerservice.config.JmsConfig;
import com.dioneybecker.msscbeerservice.domain.Beer;
import com.dioneybecker.msscbeerservice.repositories.BeerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Profile("awsdev")
@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListenerSQS {
    private final BeerRepository beerRepository;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    @SqsListener(AwsSqsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) throws MessagingException, JsonProcessingException{
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Bewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        queueMessagingTemplate.send(JmsConfig.NEW_INVENTORY_QUEUE,
                MessageBuilder.withPayload(objectMapper.writeValueAsString(newInventoryEvent)).build());

    }
}
