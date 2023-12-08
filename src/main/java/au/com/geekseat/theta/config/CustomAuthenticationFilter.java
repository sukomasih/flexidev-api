package au.com.geekseat.theta.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.geekseat.theta.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("Email is: {}", email);
        log.info("password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        Person person = (Person) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String access_token = JWT.create()
                .withSubject(person.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // 30 minutes
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", person.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withClaim("principal", person.toMap())
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(person.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 60 minutes
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);

        LinkedHashMap<String, Object> tokens = new LinkedHashMap<>();
        tokens.put("id", person.getId());
        tokens.put("name", person.getName());
        tokens.put("email", person.getEmail());
        tokens.put("token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
