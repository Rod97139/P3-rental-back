package com.oc.controllers;

import com.oc.dto.*;
import com.oc.services.JWTService;
import com.oc.services.RentalService;
import com.oc.utils.HandleFile;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private RentalService rentalService;
    private JWTService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalDisplayDto> getRentalById(@PathVariable Integer id) {
        return new ResponseEntity<>(rentalService.getRentalById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RentalsDisplayAllResponseDto> getAllRentals() {
        List<RentalDisplayAllDto> rentals = rentalService.getAllRentals();
        RentalsDisplayAllResponseDto result = new RentalsDisplayAllResponseDto(rentals);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ServerResponseMessageDto> create(
            @RequestPart("name") String name,
            @RequestPart("surface") String surface,
            @RequestPart("price") String price,
            @RequestPart("picture") MultipartFile file,
            @RequestPart("description") String description,
            HttpServletRequest request
            ) throws IOException {

        String originalFileName = HandleFile.saveFile(file);

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);

        RentalRequestDto rentalRequestDto = new RentalRequestDto(
                name,
                surface,
                price,
                "http://localhost:3001/assets/images/" + originalFileName,
                description
        );

        ServerResponseMessageDto response = new ServerResponseMessageDto();

        try {
            rentalService.createRental(rentalRequestDto, email);
            response.setMessage("Rental created !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServerResponseMessageDto> updateRental(
            @PathVariable Integer id,
            @RequestPart("name") String name,
            @RequestPart("surface") String surface,
            @RequestPart("price") String price,
            @RequestPart("description") String description,
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);

        RentalRequestDto rentalRequestDto = new RentalRequestDto(name, surface, price, description);

        ServerResponseMessageDto response = new ServerResponseMessageDto();

        try {
            rentalService.updateRental(id, rentalRequestDto, email);
            response.setMessage("Rental updated !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
