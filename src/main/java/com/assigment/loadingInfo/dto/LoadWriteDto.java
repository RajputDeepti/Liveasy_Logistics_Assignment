package com.assigment.loadingInfo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadWriteDto {

    @NotBlank(message = "Loading Point is required")
    private String loadingPoint;

    @NotBlank(message = "Unloading Point is required")
    private String unloadingPoint;

    @NotBlank(message = "Product Type is required")
    private String productType;

    @NotBlank(message = "Truck Type is required")
    private String truckType;

    @NotNull(message = "Number of Trucks is required")
    private int noOfTrucks;

    @NotNull(message = "Weight is required")
    private double weight;

    @Size(max = 255, message = "Comment should not exceed 255 characters")
    private String comment;

    @NotNull(message = "Date is required")
    private Date date;

    @NotNull(message = "Shipping ID is required")
    private long shipping_id;
}
