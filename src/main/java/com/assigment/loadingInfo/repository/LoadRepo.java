package com.assigment.loadingInfo.repository;
import com.assigment.loadingInfo.entity.LoadDetails;
import com.assigment.loadingInfo.entity.ShipmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadRepo extends JpaRepository<LoadDetails,Long> {
    List<LoadDetails> findByShipper(ShipmentDetails shipmentDetails);
}
