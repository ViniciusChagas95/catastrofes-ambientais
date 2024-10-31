package br.com.fiap.catastrofes_ambientais.controller;

import br.com.fiap.catastrofes_ambientais.exception.ResourceNotFoundException;
import br.com.fiap.catastrofes_ambientais.model.Catastrofe;
import br.com.fiap.catastrofes_ambientais.repository.CatastrofeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/catastrofes")
public class CatastrofeController {

    @Autowired
    private CatastrofeRepository catastrofeRepository;

    @GetMapping
    public List<Catastrofe> getAllCatastrofes() {
        return catastrofeRepository.findAll();
    }

    @PostMapping
    public Catastrofe createCatastrofe(@RequestBody Catastrofe catastrofe) {
        return catastrofeRepository.save(catastrofe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Catastrofe> updateCatastrofe(@PathVariable Long id, @RequestBody Catastrofe catastrofeDetails) {
        Catastrofe catastrofe = catastrofeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catástrofe não encontrada com id :" + id));

        catastrofe.setTipo(catastrofeDetails.getTipo());
        catastrofe.setDescricao(catastrofeDetails.getDescricao());
        catastrofe.setData(catastrofeDetails.getData());
        catastrofe.setCidade(catastrofeDetails.getCidade());

        final Catastrofe updatedCatastrofe = catastrofeRepository.save(catastrofe);
        return ResponseEntity.ok(updatedCatastrofe);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCatastrofe(@PathVariable Long id) {
        Catastrofe catastrofe = catastrofeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catástrofe não encontrada com id :" + id));

        catastrofeRepository.delete(catastrofe);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
