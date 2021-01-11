package br.com.cbgomes.ws.ws_auth.jwt.provider;

import io.jsonwebtoken.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Qualifier("UserService")
    private final UserDetailsService userDetailsService;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private Long expire;

    @Autowired
    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init(){
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles){
        var claims = Jwts.claims().setSubject(username);
        claims.put("role",roles);
        Date now = new Date();
        Date valid = new Date(now.getTime()+expire);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(valid)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAutentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        final var bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public Boolean validateToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(claimsJws.getBody().getExpiration().before(new Date())){
                return false;
            }else {
                return true;
            }
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
