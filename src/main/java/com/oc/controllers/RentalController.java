package com.oc.controllers;

import com.oc.dto.RentalDisplayAllDto;
import com.oc.dto.RentalDisplayDto;
import com.oc.dto.RentalDto;
import com.oc.dto.RentalRequestDto;
import com.oc.services.JWTService;
import com.oc.services.RentalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public ResponseEntity<?> getAllRentals() {

        List<RentalDisplayAllDto> rentals = rentalService.getAllRentals();

        Map<String, List<RentalDisplayAllDto>> result = new HashMap<>();
        result.put("rentals", rentals);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<RentalDto> create(
            @RequestPart("name") String name,
            @RequestPart("surface") String surface,
            @RequestPart("price") String price,
            @RequestPart("picture") MultipartFile file,
            @RequestPart("description") String description,
            HttpServletRequest request
            ) throws IOException {
        String rootDir = System.getProperty("user.dir") + "/src/main/resources/static/assets/images/";

        // Créer le répertoire s'il n'existe pas
        Path directoryPath = Paths.get(rootDir);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        String originalFileName = file.getOriginalFilename();
        assert originalFileName != null;
        Path filePath = directoryPath.resolve(originalFileName);
        Files.write(filePath, file.getBytes());

        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.getSubjectFromToken(token);

        RentalRequestDto rentalRequestDto = new RentalRequestDto(
                name,
                surface,
                price,
                "http://localhost:3001/assets/images/" + originalFileName,
                description
        );

        return new ResponseEntity<>(rentalService.createRental(rentalRequestDto, email), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalRequestDto> updateRental(
            @PathVariable Integer id,
            @RequestPart("name") String name,
            @RequestPart("surface") String surface,
            @RequestPart("price") String price,
            @RequestPart("description") String description
    ) {
        RentalRequestDto rentalRequestDto = new RentalRequestDto(name, surface, price, description);
        return new ResponseEntity<>(rentalService.updateRental(id, rentalRequestDto), HttpStatus.OK);
    }
}
