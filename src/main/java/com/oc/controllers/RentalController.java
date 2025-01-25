package com.oc.controllers;

import com.oc.dto.RentalDisplayAllDto;
import com.oc.dto.RentalDisplayDto;
import com.oc.dto.RentalRequestDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, List<RentalDisplayAllDto>>> getAllRentals() {

        List<RentalDisplayAllDto> rentals = rentalService.getAllRentals();

        Map<String, List<RentalDisplayAllDto>> result = new HashMap<>();
        result.put("rentals", rentals);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Map<String, String>> create(
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

        Map<String, String> response = new HashMap<>();

        try {
            rentalService.createRental(rentalRequestDto, email);
            response.put("message", "Rental created !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateRental(
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

        Map<String, String> response = new HashMap<>();

        try {
            rentalService.updateRental(id, rentalRequestDto, email);
            response.put("message", "Rental updated !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
