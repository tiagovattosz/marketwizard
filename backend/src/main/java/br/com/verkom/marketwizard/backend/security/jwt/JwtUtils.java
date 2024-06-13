//package br.com.verkom.marketwizard.backend.security.jwt;
//
//import br.com.verkom.marketwizard.backend.service.UserDetailsImpl;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
////    @Value("${Backend.jwtSecret}")
//    private final String jwtSecret = "bxOksa8BHgdAhR80Y3pEYvS5M+MnF2sheFDqprkTqQ4odqoszJLW1ikw64/nT/dTvlgrcBTq7HfK1B9Gai2h5A==";
//
////    @Value("${Backend.jwtExpirationMs}")
//    private final int jwtExpirationMs = 900000;
//
//    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetails){
//        return Jwts.builder().setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + + jwtExpirationMs))
//                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
//    }
//
//    public Key getSigninKey(){
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//    }
//
//    public String getUsernameToken(String token){
//        return Jwts.parser().setSigningKey(getSigninKey()).build()
//                .parseClaimsJws(token)
//                .getBody().getSubject();
//    }
//
//    public boolean validadeJwtToken(String authToken){
//        try {
//            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
//            return true;
//        } catch (MalformedJwtException e){
//            System.out.println("Token invalido " + e.getMessage());
//        } catch (ExpiredJwtException e){
//            System.out.println("Token expirado " + e.getMessage());
//        } catch (UnsupportedJwtException e){
//            System.out.println("Token não suportado " + e.getMessage());
//        } catch (IllegalArgumentException e){
//            System.out.println("Token argumento invalido " + e.getMessage());
//        }
//        return false;
//    }
//}
