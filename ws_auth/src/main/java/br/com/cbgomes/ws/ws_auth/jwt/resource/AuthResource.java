package br.com.cbgomes.ws.ws_auth.jwt.resource;

import br.com.cbgomes.ws.ws_auth.jwt.provider.JwtTokenProvider;
import br.com.cbgomes.ws.ws_auth.user.entity.dto.UserDTO;
import br.com.cbgomes.ws.ws_auth.user.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/login")
public class AuthResource {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthResource(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @RequestMapping("/testSecurity")
    public String test(){
        return "testing";
    }

    @ResponseStatus( HttpStatus.OK)
    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            var username = userDTO.getUserName();
            var password = userDTO.getPassword();

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = this.userService.getUserName(username);
            var token = "";

            if (user != null){
                token = jwtTokenProvider.createToken(username,user.getRoles());
            }else {
                throw new UsernameNotFoundException("User name not found");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);

        }catch (AuthenticationException ex){
            throw new BadCredentialsException(ex.getMessage());
        }
    }

}
