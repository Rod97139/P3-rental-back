package com.oc.services;

import com.oc.dto.TokenResponseDto;
import com.oc.dto.UserLoginRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;



    public TokenResponseDto generateToken(UserLoginRequestDto user) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(user.getEmail())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        String token = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        TokenResponseDto result = new TokenResponseDto();
        result.setToken(token);
        return result;
    }

    public String getSubjectFromToken(String token) {
        return jwtDecoder.decode(token).getSubject();
    }
}
