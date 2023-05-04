package br.com.fiap.mentalmed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.mentalmed.exceptions.RestNotFoundException;
import br.com.fiap.mentalmed.models.Conversa;
import br.com.fiap.mentalmed.models.RestValidationError;
import br.com.fiap.mentalmed.repository.ConversaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mentalmed/conversa")
public class ConversaController {

   Logger log = LoggerFactory.getLogger(ConversaController.class);

   @Autowired
   ConversaRepository repository;

   @Autowired
   PagedResourcesAssembler<Object> assembler;

   @PostMapping
   public ResponseEntity<Object> create(@RequestBody @Valid Conversa conversa){
      log.info("Criando a conversa " + conversa);
      repository.save(conversa);
      return ResponseEntity.created(conversa.toEntityModel().getRequiredLink("self").toUri()).body(conversa.toEntityModel());
   }

   @GetMapping("{id}")
   public EntityModel<Conversa> show(@PathVariable Long id){
      log.info("Detalhe da conversa" + id);
      return getConversa(id).toEntityModel();
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Conversa> destroy(@PathVariable Long id){
      log.info("Apagando a conversa" + id);
      var conversa = getConversa(id);

      repository.delete(conversa);   

      return ResponseEntity.noContent().build();
   }

   @GetMapping
   public PagedModel<EntityModel<Object>> lista(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
      Page<Conversa> conversas = (busca == null)?
         repository.findAll(pageable):
         repository.findByDescricaoContaining(busca, pageable);

      return assembler.toModel(conversas.map(Conversa::toEntityModel));
   }

   private Conversa getConversa(Long id) {
      return repository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("conversa não encontrado"));
   }

}