package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.Historico;
import br.com.uniamerica.api.entity.Secretaria;
import br.com.uniamerica.api.entity.StatusAgendamento;
import br.com.uniamerica.api.repository.HistoricoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class HistorioService {
    @Autowired
    private HistoricoPaciente historicoPaciente;

    @Transactional
    public void insert(Historico historico) {
        this.historicoPaciente.save(historico);
    }


}