package com.assigment.loadingInfo.controller;

import com.assigment.loadingInfo.entity.ShipmentDetails;
import com.assigment.loadingInfo.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {

    private final Logger logger = LoggerFactory.getLogger(ShipmentController.class);

    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/addShipment")
    public ResponseEntity<String> addShipmentDetails(@RequestBody ShipmentDetails shipmentDetails) {
        logger.info("Received a request to add shipment details.");
        shipmentService.addShipmentDetails(shipmentDetails);
        logger.info("Shipment details added successfully.");
        return new ResponseEntity<>("Details added successfully", HttpStatus.OK);
    }
}
