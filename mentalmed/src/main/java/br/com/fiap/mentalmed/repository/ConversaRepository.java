package br.com.fiap.mentalmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.mentalmed.models.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
   
}