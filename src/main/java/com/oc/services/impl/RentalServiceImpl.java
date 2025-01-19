package com.oc.services.impl;

import com.oc.dto.RentalDto;
import com.oc.exception.ResourceNotFoundException;
import com.oc.mapper.RentalMapper;
import com.oc.model.Rental;
import com.oc.repository.RentalRepository;
import com.oc.services.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    @Override
    public RentalDto createRental(RentalDto rentalDto) {

        Rental rental = RentalMapper.mapToRental(rentalDto);
        Rental savedRental = rentalRepository.save(rental);

        return RentalMapper.mapToRentalDto(savedRental);
    }

    @Override
    public RentalDto getRentalById(Integer rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(
                () -> new ResourceNotFoundException("Rental not found with id: " + rentalId)
        );

        return RentalMapper.mapToRentalDto(rental);
    }

    @Override
    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map((rental) -> RentalMapper.mapToRentalDto(rental))
                .collect(Collectors.toList());
    }

    @Override
    public RentalDto updateRental(Integer rentalId, RentalDto updatedRental) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));
        rental.setName(updatedRental.getName());
        rental.setSurface(updatedRental.getSurface());
        rental.setPrice(updatedRental.getPrice());
        rental.setPicture(updatedRental.getPicture());
        rental.setDescription(updatedRental.getDescription());
        rental.setOwner(updatedRental.getOwner());
        rental.setUpdatedAt(updatedRental.getUpdatedAt());

        Rental updatedRentalObj = rentalRepository.save(rental);

        return RentalMapper.mapToRentalDto(updatedRentalObj);
    }

    @Override
    public void deleteRental(Integer rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));
        rentalRepository.deleteById(rentalId);

    }
}
