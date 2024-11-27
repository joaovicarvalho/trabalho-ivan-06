package com.educacional.educacional.controller;

import com.educacional.educacional.model.Nota;
import com.educacional.educacional.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @GetMapping
    public List<Nota> listarTodas() {
        return notaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarPorId(@PathVariable Long id) {
        return notaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Nota criar(@RequestBody Nota nota) {
        return notaRepository.save(nota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizar(@PathVariable Long id, @RequestBody Nota notaAtualizada) {
        return notaRepository.findById(id).map(nota -> {
            nota.setMatricula(notaAtualizada.getMatricula());
            nota.setDisciplina(notaAtualizada.getDisciplina());
            nota.setNota(notaAtualizada.getNota());
            nota.setDataLancamento(notaAtualizada.getDataLancamento());
            return ResponseEntity.ok(notaRepository.save(nota));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        return notaRepository.findById(id).map(nota -> {
            notaRepository.delete(nota);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
