package com.assigment.loadingInfo.service;

import com.assigment.loadingInfo.dto.LoadUpdateDto;
import com.assigment.loadingInfo.dto.LoadWriteDto;
import com.assigment.loadingInfo.entity.LoadDetails;
import com.assigment.loadingInfo.entity.ShipmentDetails;
import com.assigment.loadingInfo.exceptions.ResourceNotFoundException;
import com.assigment.loadingInfo.repository.LoadRepo;
import com.assigment.loadingInfo.repository.ShipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadService {
    @Autowired
    private LoadRepo loadRepo;

    @Autowired
    private ShipmentRepo shipmentRepo;

    private LoadWriteDto convertToWriteDto(LoadDetails loadDetails){
        LoadWriteDto writeDto=new LoadWriteDto();
        writeDto.setShipping_id(loadDetails.getShipper().getShipperId());
        writeDto.setLoadingPoint(loadDetails.getLoadingPoint());
        writeDto.setUnloadingPoint(loadDetails.getUnloadingPoint());
        writeDto.setProductType(loadDetails.getProductType());
        writeDto.setTruckType(loadDetails.getTruckType());
        writeDto.setNoOfTrucks(loadDetails.getNoOfTrucks());
        writeDto.setWeight(loadDetails.getWeight());
        writeDto.setComment(loadDetails.getComment());
        writeDto.setDate(loadDetails.getDate());
        return writeDto;
    }

    public LoadWriteDto addLoadDetails(LoadWriteDto loadWriteDto) {
        ShipmentDetails shipmentDetails=shipmentRepo.findById(loadWriteDto.getShipping_id()).orElseThrow(()->new ResourceNotFoundException("Shipper not found with this shipping id: "+loadWriteDto.getShipping_id()));
        LoadDetails newLoad = new LoadDetails();
        newLoad.setLoadingPoint(loadWriteDto.getLoadingPoint());
        newLoad.setUnloadingPoint(loadWriteDto.getUnloadingPoint());
        newLoad.setProductType(loadWriteDto.getProductType());
        newLoad.setTruckType(loadWriteDto.getTruckType());
        newLoad.setNoOfTrucks(loadWriteDto.getNoOfTrucks());
        newLoad.setWeight(loadWriteDto.getWeight());
        newLoad.setComment(loadWriteDto.getComment());
        newLoad.setDate(loadWriteDto.getDate());
        newLoad.setShipper(shipmentDetails);
        newLoad=loadRepo.save(newLoad);

        return convertToWriteDto(newLoad);
    }

    public List<LoadDetails> getLoadsByShippingId(Long shippingId) {
        ShipmentDetails shipmentDetails = shipmentRepo.findById(shippingId).orElseThrow(() -> new ResourceNotFoundException("Shipper not found with this id: " + shippingId));
        return loadRepo.findByShipper(shipmentDetails);
    }

    public LoadDetails getLoadDetailsById(Long loadId) {
        LoadDetails loadDetails= loadRepo.findById(loadId).orElseThrow(()->new ResourceNotFoundException("No load details found with this id: "+loadId));
        return loadRepo.findById(loadId).orElse(null);
    }

    public LoadDetails updateLoadDetails(Long loadId, LoadUpdateDto loadUpdateDto) {
        LoadDetails existingLoadDetails = loadRepo.findById(loadId).orElseThrow(()->new ResourceNotFoundException("No load details found with this id: "+loadId));
        if (existingLoadDetails != null) {
            existingLoadDetails.setLoadingPoint(loadUpdateDto.getLoadingPoint());
            existingLoadDetails.setUnloadingPoint(loadUpdateDto.getUnloadingPoint());
            existingLoadDetails.setProductType(loadUpdateDto.getProductType());
            existingLoadDetails.setTruckType(loadUpdateDto.getTruckType());
            existingLoadDetails.setNoOfTrucks(loadUpdateDto.getNoOfTrucks());
            existingLoadDetails.setWeight(loadUpdateDto.getWeight());
            existingLoadDetails.setComment(loadUpdateDto.getComment());
            existingLoadDetails.setDate(loadUpdateDto.getDate());

            return loadRepo.save(existingLoadDetails);
        }
        return null;
    }

    public boolean deleteLoadById(Long loadId) {
        LoadDetails loadDetails = loadRepo.findById(loadId).orElseThrow(()->new ResourceNotFoundException("No load details found with this id: "+loadId));
        if (loadDetails != null) {
            loadRepo.delete(loadDetails);
            return true;
        }
        return false;
    }
}
