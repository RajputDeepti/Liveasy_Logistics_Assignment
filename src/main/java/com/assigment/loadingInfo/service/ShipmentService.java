package com.assigment.loadingInfo.service;

import com.assigment.loadingInfo.entity.ShipmentDetails;
import com.assigment.loadingInfo.repository.ShipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepo shipmentRepo;

    public void addShipmentDetails(ShipmentDetails shipmentDetails) {
        shipmentRepo.save(shipmentDetails);
    }
}
