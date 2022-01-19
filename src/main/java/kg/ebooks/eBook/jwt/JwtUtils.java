//package kg.ebooks.eBook.jwt;
//
//import io.jsonwebtoken.MalformedJwtException;
//import kg.ebooks.eBook.db.service.impl.ClientDetailsImpl;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Service
//public class JwtUtils {
//
//    @Value("${jwt.token.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.token.expired}")
//    private long jwtExpirationMs;
//
//    public String generateJwtToken(Authentication authentication) {
//
//        ClientDetailsImpl clientPrincipal = (ClientDetailsImpl) authentication.getPrincipal();
//
//        return Jwts.builder().
//                setSubject((userPrincipal.getUsername()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//    }
//
//    public boolean validateJwtToken(String jwt) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
//            return true;
//        } catch (MalformedJwtException e) {
//            System.err.println(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.err.println(e.getMessage());
//        }
//
//        return false;
//    }
//
//    public String getUserNameFromJwtToken(String jwt) {
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
//    }
//}