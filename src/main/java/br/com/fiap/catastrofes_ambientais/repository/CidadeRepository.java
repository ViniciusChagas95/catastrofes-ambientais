package br.com.fiap.catastrofes_ambientais.repository;

import br.com.fiap.catastrofes_ambientais.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
