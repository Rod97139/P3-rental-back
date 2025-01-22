package com.oc.services;

import com.oc.dto.RentalDisplayAllDto;
import com.oc.dto.RentalDisplayDto;
import com.oc.dto.RentalDto;
import com.oc.dto.RentalRequestDto;

import java.util.List;

public interface RentalService {

    RentalDto createRental(RentalRequestDto rentalRequestDto, String ownerEmail);

    RentalDisplayDto getRentalById(Integer rentalId);

    List<RentalDisplayAllDto> getAllRentals();

    RentalRequestDto updateRental(Integer rentalId, RentalRequestDto updatedRental);

    void deleteRental(Integer rentalId);
}
