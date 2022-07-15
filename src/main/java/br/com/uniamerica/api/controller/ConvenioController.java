package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Convenio;
import br.com.uniamerica.api.service.ConvenioService;
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
@RequestMapping("/api/convenios")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @GetMapping("/{idConvenio}")
    public ResponseEntity<Convenio> findById(
            @PathVariable("idConvenio") Long idConvenio){
        return ResponseEntity.ok().body(this.convenioService.findById(idConvenio).get());
    }

    @GetMapping
    public ResponseEntity<Page<Convenio>> listByAllPage(Pageable pageable){
        return ResponseEntity.ok().body(this.convenioService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Convenio convenio) {
        try {
            this.convenioService.insert(convenio);
            return ResponseEntity.ok().body("convenio cadastrada com sucesso.");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idConvenio}")
    public ResponseEntity<?> update(
            @RequestBody Convenio convenio,
            @PathVariable Long idConvenio){
        try {
            this.convenioService.update(idConvenio,convenio);
            return ResponseEntity.ok().body("convenio atualizada com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{idConvenio}")
    public ResponseEntity<?> updateStatus(
            @RequestBody Convenio convenio,
            @PathVariable Long idConvenio){
        try {
            this.convenioService.updateStatus(idConvenio,convenio);
            return ResponseEntity.ok().body("convenio atualizada com sucesso");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
