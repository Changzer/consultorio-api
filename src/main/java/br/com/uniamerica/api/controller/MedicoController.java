package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Especialidade;
import br.com.uniamerica.api.entity.Medico;
import br.com.uniamerica.api.repository.AgendaRepository;
import br.com.uniamerica.api.repository.MedicoRepository;
import br.com.uniamerica.api.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Eduardo Sganderla
 *
 * @since 1.0.0, 07/04/2022
 * @version 1.0.0
 */
@Controller
@CrossOrigin
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    public MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<Medico>> listByAllPage(Pageable pageable){
        return ResponseEntity.ok().body(this.medicoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Medico medico) {
        try {
            this.medicoService.insert(medico);
            return ResponseEntity.ok().body("medico cadastrado com sucesso.");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
