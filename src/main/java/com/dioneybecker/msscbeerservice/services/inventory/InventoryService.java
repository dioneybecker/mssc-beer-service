package com.dioneybecker.msscbeerservice.services.inventory;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
public interface InventoryService {

    Integer getOnhandInventory(UUID beerId);
}
