package br.com.fiap.mentalmed.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 4, max = 20)
    private String nome;
    @NotBlank @Size(min = 1, max = 1)
    private String genero;
    @Email
    private String email;
    @NotBlank @Size(min = 8, max = 16)
    private String senha;
    @NotBlank @Size(min = 8, max = 16)
    private String confirmaSenha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(() -> "ROLE_USUARIO");
    }
 
    @Override
    public String getPassword() {
       return senha;
    }
 
    @Override
    public String getUsername() {
       return email;
    }
 
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }
 
    @Override
    public boolean isEnabled() {
       return true;
    
    }
}