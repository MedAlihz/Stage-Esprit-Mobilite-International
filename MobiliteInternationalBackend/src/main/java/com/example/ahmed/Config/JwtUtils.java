package com.example.ahmed.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {//jwtservice 3ando hoa
    private final String SECRET_KEY = "782F413F4428472B4B6250655368566D5971337436773979244226452948404D";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
            private Key getSignInKey() {
                byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
                return Keys.hmacShaKeyFor(keyBytes);
            }
                public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
                    final Claims claims = extractAllClaims(token);
                    return claimsResolver.apply(claims);
                }
                    public String generateToken(Map<String, Object> extraClaims,
                                                UserDetails userDetails
                    ) {

                        return Jwts
                                .builder()
                                    .setClaims(extraClaims)
                                        .setSubject(userDetails.getUsername())
                                             .setIssuedAt(new Date(System.currentTimeMillis()))
                                                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                                                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                                                        .compact();
                    }
                        public String generateToke(UserDetails userDetails){
                        return generateToken(new HashMap<>(),userDetails);
                        }
                            public Boolean isTokenValid(String token, UserDetails userDetails) {
                                final String username = extractUsername(token);
                                return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
                            }
                                private Boolean isTokenExpired(String token) {
                                    return extractExpiration(token).before(new Date());
                                }

                                    private Date extractExpiration(String token) {
                                        return extractClaim(token, Claims::getExpiration);
                                    }








    public String generateToken(UserDetails userDetails) {
        return generateToken( new HashMap<>(), userDetails);
    }


    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


}
