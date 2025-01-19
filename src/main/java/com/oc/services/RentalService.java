package com.oc.services;

import com.oc.dto.RentalDto;

import java.util.List;

public interface RentalService {

    RentalDto createRental(RentalDto rentalDto);

    RentalDto getRentalById(Integer rentalId);

    List<RentalDto> getAllRentals();

    RentalDto updateRental(Integer rentalId, RentalDto updatedRental);

    void deleteRental(Integer rentalId);
}
