package com.dioneybecker.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.dioneybecker.msscbeerservice.services.inventory.model.BeerInventoryDto;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Profile("awsdev")
@Service
public class InventoryServiceFeignImpl implements InventoryService {
    
    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    
    @Override
    public Integer getOnhandInventory(UUID beerId) {
        log.debug("Calling Inventory Service - BeerId" + beerId);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnhandInventory(beerId);
                
        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("BeerId" + beerId + " On Hand is: " + onHand); 
        
        return onHand;
    }
}
