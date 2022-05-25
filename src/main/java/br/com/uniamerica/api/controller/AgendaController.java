package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Controller
@RequestMapping("/api/agendas")
public class AgendaController {

    @Autowired
    public AgendaService agendaService;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<Agenda>> listAllAgendas(Pageable pageable){
        return ResponseEntity.ok().body(this.agendaService.listAll(pageable));
    }

    /**
     *
     * @param agenda
     * @return
     */

    //  @PostMapping
    //public ResponseEntity<?> cadastrar(@RequestBody List<Agenda> agenda){
    //    agendaService.saveAll(agenda);
    //    return new ResponseEntity<>("Registro Cadastrado", HttpStatus.OK);
    //}

    @PutMapping("/update/{idAgenda}")
    public ResponseEntity<?> updateAgenda(
            @RequestBody Agenda agenda,
            @PathVariable Long idAgenda){
        try{
            this.agendaService.updateAgenda(idAgenda,agenda);
            return ResponseEntity.ok().body("agenda atualizada");
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
