package br.com.fiap.catastrofes_ambientais.controller;

import br.com.fiap.catastrofes_ambientais.exception.ResourceNotFoundException;
import br.com.fiap.catastrofes_ambientais.model.Cidade;
import br.com.fiap.catastrofes_ambientais.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> getAllCidades() {
        return cidadeRepository.findAll();
    }

    @PostMapping
    public Cidade createCidade(@RequestBody Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> updateCidade(@PathVariable Long id, @RequestBody Cidade cidadeDetails) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com id :" + id));

        cidade.setNome(cidadeDetails.getNome());
        cidade.setEstado(cidadeDetails.getEstado());

        final Cidade updatedCidade = cidadeRepository.save(cidade);
        return ResponseEntity.ok(updatedCidade);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCidade(@PathVariable Long id) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com id :" + id));

        cidadeRepository.delete(cidade);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
