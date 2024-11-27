package com.educacional.educacional.controller;

import com.educacional.educacional.model.Matricula;
import com.educacional.educacional.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping
    public List<Matricula> listarTodas() {
        return matriculaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(@PathVariable Long id) {
        return matriculaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> atualizar(@PathVariable Long id, @RequestBody Matricula matriculaAtualizada) {
        return matriculaRepository.findById(id).map(matricula -> {
            matricula.setAluno(matriculaAtualizada.getAluno());
            matricula.setTurma(matriculaAtualizada.getTurma());
            return ResponseEntity.ok(matriculaRepository.save(matricula));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        return matriculaRepository.findById(id).map(matricula -> {
            matriculaRepository.delete(matricula);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
