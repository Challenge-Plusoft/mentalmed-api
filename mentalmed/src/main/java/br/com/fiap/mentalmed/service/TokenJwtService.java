package br.com.fiap.mentalmed.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.mentalmed.models.Credencial;
import br.com.fiap.mentalmed.models.JwtToken;
import br.com.fiap.mentalmed.models.Usuario;
import br.com.fiap.mentalmed.repository.UsuarioRepository;

@Service
public class TokenJwtService {

    @Value("${jwt.secret}")
    String secret;
 
    @Autowired
    UsuarioRepository repository;
 
    public JwtToken generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                    .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                    .withSubject(credencial.email())
                    .withIssuer("MentalMED")
                    .sign(alg)
                    ;
 
        return new JwtToken(token);
    }
 
    public Usuario validate(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                    .withIssuer("MentalMED")
                    .build()
                    .verify(token)
                    .getSubject();
        
        return repository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("MentalMED"));
    }
    
}
