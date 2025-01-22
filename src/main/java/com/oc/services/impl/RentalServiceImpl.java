package com.oc.services.impl;

import com.oc.dto.RentalDisplayAllDto;
import com.oc.dto.RentalDisplayDto;
import com.oc.dto.RentalDto;
import com.oc.dto.RentalRequestDto;
import com.oc.exception.ResourceNotFoundException;
import com.oc.mapper.RentalMapper;
import com.oc.mapper.UserMapper;
import com.oc.model.Rental;
import com.oc.model.User;
import com.oc.repository.RentalRepository;
import com.oc.services.RentalService;
import com.oc.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final UserService userService;
    private RentalRepository rentalRepository;

    @Override
    public RentalDto createRental(RentalRequestDto rentalRequestDto, String ownerEmail) {


        Rental rental = RentalMapper.mapToRental(rentalRequestDto);
        User owner = UserMapper.mapToUser(userService.getUserByEmail(ownerEmail));
        rental.setOwner(owner);

        Rental savedRental = rentalRepository.save(rental);

        return RentalMapper.mapToRentalDto(savedRental);
    }

    @Override
    public RentalDisplayDto getRentalById(Integer rentalId) {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(
                () -> new ResourceNotFoundException("Rental not found with id: " + rentalId)
        );

        return RentalMapper.mapToRentalDisplayDto(rental);
    }

    @Override
    public List<RentalDisplayAllDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();

        return rentals.stream()
                .map(RentalMapper::mapToRentalDisplayAllDto)
                .collect(Collectors.toList());
    }

    @Override
    public RentalRequestDto updateRental(Integer rentalId, RentalRequestDto updatedRental) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));
        rental.setName(updatedRental.getName());
        rental.setSurface(new BigDecimal(updatedRental.getSurface()));
        rental.setPrice(new BigDecimal(updatedRental.getPrice()));
        rental.setDescription(updatedRental.getDescription());
        rental.setUpdatedAt(Instant.now());

        rentalRepository.save(rental);
        return updatedRental;
    }

    @Override
    public void deleteRental(Integer rentalId) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));
        rentalRepository.deleteById(rentalId);

    }
}
