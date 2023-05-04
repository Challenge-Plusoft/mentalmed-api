package br.com.fiap.mentalmed.models;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import br.com.fiap.mentalmed.controller.ConversaController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

   public EntityModel<Conversa> toEntityModel(){
      return EntityModel.of(
          this, 
          linkTo(methodOn(ConversaController.class).show(userId)).withSelfRel(),
          linkTo(methodOn(ConversaController.class).show(chatId)).withSelfRel(),
          linkTo(methodOn(ConversaController.class).destroy(userId)).withRel("delete"),
          linkTo(methodOn(ConversaController.class).destroy(chatId)).withRel("delete"),
          linkTo(methodOn(ConversaController.class).lista(null, Pageable.unpaged())).withRel("all")
      );
  }
}