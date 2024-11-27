package com.educacional.educacional.controller;

import com.educacional.educacional.model.Disciplina;
import com.educacional.educacional.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id) {
        return disciplinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Disciplina criar(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @RequestBody Disciplina disciplinaAtualizada) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            disciplina.setNome(disciplinaAtualizada.getNome());
            disciplina.setCodigo(disciplinaAtualizada.getCodigo());
            disciplina.setCurso(disciplinaAtualizada.getCurso());
            disciplina.setProfessor(disciplinaAtualizada.getProfessor());
            return ResponseEntity.ok(disciplinaRepository.save(disciplina));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        return disciplinaRepository.findById(id).map(disciplina -> {
            disciplinaRepository.delete(disciplina);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
