package com.assigment.loadingInfo.repository;

import com.assigment.loadingInfo.entity.ShipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepo extends JpaRepository<ShipmentDetails,Long> {
}
