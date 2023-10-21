package com.assigment.loadingInfo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Shipment_Details")
public class ShipmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Shipping_Id")
    private Long shipperId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    @Column(name = "Email_Id")
    private String emailAddress;

    @OneToMany(mappedBy = "shipper", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LoadDetails> load = new ArrayList<>();
}
