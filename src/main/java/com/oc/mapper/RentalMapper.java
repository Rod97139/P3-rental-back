package com.oc.mapper;

import com.oc.dto.RentalDto;
import com.oc.model.Rental;

public class RentalMapper {
    public static RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner(),
                rental.getCreatedAt(),
                rental.getUpdatedAt(),
                rental.getMessages()
        );
    }

    public static Rental mapToRental(RentalDto rentalDto) {
        return new Rental(
                rentalDto.getId(),
                rentalDto.getName(),
                rentalDto.getSurface(),
                rentalDto.getPrice(),
                rentalDto.getPicture(),
                rentalDto.getDescription(),
                rentalDto.getOwner(),
                rentalDto.getCreatedAt(),
                rentalDto.getUpdatedAt(),
                rentalDto.getMessages()
        );
    }
}
