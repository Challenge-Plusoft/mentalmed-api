package br.com.fiap.mentalmed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import br.com.fiap.mentalmed.models.Usuario;
import br.com.fiap.mentalmed.repository.UsuarioRepository;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
   
   @Autowired
   UsuarioRepository usuarioRepository;

   @Override
   public void run(String... args) throws Exception {


      usuarioRepository.save(Usuario.builder()
      .nome("Vinícius Yoda")
      .genero("m")
      .email("vyoda4604@gmail.com")
      .senha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTYiLCJuYW1lIjoiVmluaWNpdXMiLCJpYXQiOjEyMzQ1Njc4OX0.PVQ4OPiyjN_fWDAWZOzHeDTHNkiOJ5V5Apb-h8g8ECg")
      .confirmaSenha("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTYiLCJuYW1lIjoiVmluaWNpdXMiLCJpYXQiOjEyMzQ1Njc4OX0.PVQ4OPiyjN_fWDAWZOzHeDTHNkiOJ5V5Apb-h8g8ECg")
      .build()
        );
   }
}




