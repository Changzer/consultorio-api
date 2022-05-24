package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Especialidade;
import br.com.uniamerica.api.repository.AgendaRepository;
import br.com.uniamerica.api.repository.EspecialidadeRepository;
import br.com.uniamerica.api.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/{idEspecialidade}")
    public ResponseEntity<Especialidade> findById(
            @PathVariable("idEspecialidade") Long idEspecialidade){
        return ResponseEntity.ok().body(this.especialidadeService.findById(idEspecialidade).get());
    }

    @GetMapping
    public ResponseEntity<Page<Especialidade>> listByAllPage(Pageable pageable){
        return ResponseEntity.ok().body(this.especialidadeService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Especialidade especialidade) {
        try {
            this.especialidadeService.insert(especialidade);
            return ResponseEntity.ok().body("especialidade cadastrada com sucesso.");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idEspecialidade}")
    public ResponseEntity<?> update(
            @RequestBody Especialidade especialidade,
            @PathVariable Long idEspecialidade){
        try {
            this.especialidadeService.update(idEspecialidade,especialidade);
            return ResponseEntity.ok().body("especialidade atualizada com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{idEspecialidade}")
    public ResponseEntity<?> updateStatus(
            @RequestBody Especialidade especialidade,
            @PathVariable Long idEspecialidade){
        try {
            this.especialidadeService.updateStatus(idEspecialidade,especialidade);
            return ResponseEntity.ok().body("especialidade atualizada com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
