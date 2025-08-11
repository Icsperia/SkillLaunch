package com.example.practica.Config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
private static final String SECRET_KEY = "0658577718f37c9064687233122112cdb7aca72909b391eee508f16f2a6ad499eb58a8755c188154afd92f6ab390fc9d";

    public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
final Claims claims = extractAllClaims(token);
return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        extraClaims = new HashMap<>(extraClaims);
        extraClaims.put("role", userDetails.getAuthorities());
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInkKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    public boolean isTokenvalid(String token, UserDetails userDetails) {
final String username = extractUsername(token);
return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
  return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
   return extractClaim(token, Claims::getExpiration);

    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInkKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
}

private Key getSignInkKey() {
    byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);


    }


}
