package com.assigment.loadingInfo.controller;
import com.assigment.loadingInfo.dto.LoadUpdateDto;
import com.assigment.loadingInfo.dto.LoadWriteDto;
import com.assigment.loadingInfo.entity.LoadDetails;
import com.assigment.loadingInfo.service.LoadService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final Logger logger = LoggerFactory.getLogger(LoadController.class);

    @Autowired
    private LoadService loadService;

    @PostMapping("/addLoad")
    public ResponseEntity<String> addLoadDetails(@RequestBody @Valid LoadWriteDto loadWriteDto) {
        logger.info("Received a request to add load details.");
        loadService.addLoadDetails(loadWriteDto);
        logger.info("Load details added successfully.");
        return new ResponseEntity<>("Loads added successfully", HttpStatus.OK);
    }

    @GetMapping("/listByShippingId/{shippingId}")
    public ResponseEntity<List<LoadDetails>> getLoadsByShippingId(@PathVariable Long shippingId) {
        logger.info("Received a request to get loads by shipping ID: {}", shippingId);
        List<LoadDetails> loads = loadService.getLoadsByShippingId(shippingId);
        logger.info("Found {} loads for shipping ID: {}", loads.size(), shippingId);
        return new ResponseEntity<>(loads, HttpStatus.OK);
    }

    @GetMapping("/getLoadDetails/{loadId}")
    public ResponseEntity<LoadDetails> getLoadDetailsById(@PathVariable Long loadId) {
        logger.info("Received a request to get load details by ID: {}", loadId);
        LoadDetails loadDetails = loadService.getLoadDetailsById(loadId);
        if (loadDetails != null) {
            logger.info("Found load details for ID: {}", loadId);
            return new ResponseEntity<>(loadDetails, HttpStatus.OK);
        } else {
            logger.info("Load details not found for ID: {}", loadId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateLoadDetails/{loadId}")
    public ResponseEntity<LoadDetails> updateLoadDetails(@PathVariable Long loadId, @RequestBody @Valid LoadUpdateDto loadUpdateDto) {
        logger.info("Received a request to update load details for ID: {}", loadId);
        LoadDetails updatedLoadDetails = loadService.updateLoadDetails(loadId, loadUpdateDto);
        if (updatedLoadDetails != null) {
            logger.info("Load details updated successfully for ID: {}", loadId);
            return new ResponseEntity<>(updatedLoadDetails, HttpStatus.OK);
        } else {
            logger.info("Load details not found for update for ID: {}", loadId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteLoad/{loadId}")
    public ResponseEntity<String> deleteLoadById(@PathVariable Long loadId) {
        logger.info("Received a request to delete load details for ID: {}", loadId);
        if (loadService.deleteLoadById(loadId)) {
            logger.info("Load details deleted successfully for ID: {}", loadId);
            return new ResponseEntity<>("Load deleted successfully", HttpStatus.OK);
        } else {
            logger.info("Load details not found for delete for ID: {}", loadId);
            return new ResponseEntity<>("Load not found", HttpStatus.NOT_FOUND);
        }
    }
}
