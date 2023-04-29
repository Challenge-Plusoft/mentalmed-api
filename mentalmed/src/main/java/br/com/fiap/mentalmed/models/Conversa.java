package br.com.fiap.mentalmed.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Conversa {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long chatId;

   @NotBlank @Size(min = 5)
   private String userMensagem;
   @NotBlank @Size(min = 5)
   private String chatMensagem;
}