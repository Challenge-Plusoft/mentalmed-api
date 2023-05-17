package br.com.fiap.mentalmed.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mentalmed.exceptions.RestNotFoundException;
import br.com.fiap.mentalmed.models.Conversa;
import br.com.fiap.mentalmed.repository.ConversaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.springframework.hateoas.Link;
import org.springframework.validation.BindingResult;

import br.com.fiap.mentalmed.models.RestValidationError;





@RestController
@RequestMapping("/mentalmed/conversa")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "conversa")
public class ConversaController {

   @Autowired
   ConversaRepository repository;

   @Autowired
   PagedResourcesAssembler<Object> assembler;

   @GetMapping
   @Operation(
      summary = "Listar conversas",
      description = "Endpoint que retorna todas as conversas criadas"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "conversas listadas com sucesso"),
      @ApiResponse(responseCode = "400", description = "conversas não encontrado")
   })
   public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable) {
      Page<Conversa> conversas = (busca == null)?
         repository.findAll(pageable):
         repository.findByDescricaoContaining(busca, pageable);

      return assembler.toModel(conversas.map(Conversa::toEntityModel));
   }

   @PostMapping
   @Operation(
      summary = "Criar conversa",
      description = "Endpoint que cria a conversa"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "conversa criada com sucesso"),
      @ApiResponse(responseCode = "400", description = "não foi possível salvar a conversa")
   })
   public ResponseEntity<Object> create(@RequestBody @Valid Conversa conversa){
      log.info("Criando a conversa " + conversa);
      repository.save(conversa);
      return ResponseEntity.status(HttpStatus.CREATED).body(conversa.toEntityModel());
   }

   @GetMapping("{id}")
   @Operation(
      summary = "Mostrar conversa",
      description = "Endpoint que mostra a conversa"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "conversa retornada com sucesso"),
      @ApiResponse(responseCode = "400", description = "não foi possível retornar a conversa")
   })
   public EntityModel<Conversa> show(@PathVariable Long id){
      log.info("Detalhe da conversa" + id);
      return getConversa(id).toEntityModel();
   }

   @DeleteMapping("{id}")
   @Operation(
      summary = "Deletar conversa",
      description = "Endpoint que deleta a conversa"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "conversa deletada com sucesso"),
      @ApiResponse(responseCode = "400", description = "não foi possível deletar a conversa")
   })
   public ResponseEntity<Conversa> destroy(@PathVariable Long id){
      log.info("Apagando a conversa" + id);
      var conversa = getConversa(id);

      repository.delete(conversa);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   @Operation(
      summary = "Alterar conversa",
      description = "Endpoint que altera a conversa"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "conversa alterada com sucesso"),
      @ApiResponse(responseCode = "400", description = "não foi possível alterar a conversa")
   })
   public EntityModel<Conversa> update(@PathVariable Long id, @RequestBody @Valid Conversa conversa){
      log.info("Alterando a conversa" + id);
      conversa = getConversa(id);

      conversa.setUserId(id);
      conversa.setChatId(id);
      repository.save(conversa);

      return conversa.toEntityModel();
   }

   private Conversa getConversa(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("conversa não encontrado"));
   }

   

}