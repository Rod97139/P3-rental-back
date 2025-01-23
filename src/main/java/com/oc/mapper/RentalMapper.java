package com.oc.mapper;

import com.oc.dto.RentalDisplayAllDto;
import com.oc.dto.RentalDisplayDto;
import com.oc.dto.RentalDto;
import com.oc.dto.RentalRequestDto;
import com.oc.model.Rental;
import com.oc.model.User;

import java.math.BigDecimal;
import java.time.Instant;

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
                rental.getUpdatedAt()
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
                rentalDto.getUpdatedAt()
        );
    }

    public static Rental mapToRental(RentalRequestDto rentalRequestDto) {
        return new Rental(
                null,
                rentalRequestDto.getName(),
                new BigDecimal(rentalRequestDto.getSurface()),
                new BigDecimal(rentalRequestDto.getPrice()),
                rentalRequestDto.getPicture(),
                rentalRequestDto.getDescription(),
                new User(),
                Instant.now(),
                Instant.now()
        );
    }

    public static RentalDisplayDto mapToRentalDisplayDto(Rental rental) {
        return new RentalDisplayDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                new String[]{rental.getPicture()},
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }


    public static RentalDisplayAllDto mapToRentalDisplayAllDto(Rental rental) {
        return new RentalDisplayAllDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }
}
