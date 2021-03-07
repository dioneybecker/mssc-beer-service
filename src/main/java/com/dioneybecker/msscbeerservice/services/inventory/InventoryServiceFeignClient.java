package com.dioneybecker.msscbeerservice.services.inventory;

import java.util.List;
import java.util.UUID;

import com.dioneybecker.msscbeerservice.services.inventory.model.BeerInventoryDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {
    
    @RequestMapping(method = RequestMethod.GET, value = InventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);
}
