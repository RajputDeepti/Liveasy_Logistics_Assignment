package com.assigment.loadingInfo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Load_Details")
public class LoadDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Load_Id")
    private Long loadId;

    @Column(name = "Loading_Point")
    private String loadingPoint;

    @Column(name = "Unloading_Point")
    private String unloadingPoint;

    @Column(name = "Product_Type")
    private String productType;

    @Column(name = "Truck_Type")
    private String truckType;

    @Column(name = "No_Of_Trucks")
    private int noOfTrucks;

    @Column(name = "Weight")
    private double weight;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    @JsonIgnore
    private ShipmentDetails shipper;
}
