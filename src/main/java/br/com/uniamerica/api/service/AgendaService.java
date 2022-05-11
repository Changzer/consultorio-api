package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Agenda;
import br.com.uniamerica.api.entity.StatusAgendamento;
import br.com.uniamerica.api.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    public Optional<Agenda> selectById(Long id) {
        return this.agendaRepository.findById(id);
    }

    public Page<Agenda> selectAll(Pageable pageable) {
        return this.agendaRepository.findAll(pageable);
    }

    public List<Agenda> selectAllValidation(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return this.agendaRepository.findAllCrossSchedulePaciente(dateFrom, dateTo);
    }

    public List<Agenda> selectAllValidationUpdate(Long id, LocalDateTime dateFrom, LocalDateTime dateTo) {
        return this.agendaRepository.findAllCrossSchedule(id, dateFrom, dateTo);
    }


    public void insert(Agenda agenda) {
        this.validationInsert(agenda);
        this.insertTransactional(agenda);
    }
    @Transactional
    public void insertTransactional(Agenda agenda) {
        this.agendaRepository.save(agenda);
    }

    public void update(Long id, Agenda agenda){
        if (id == agenda.getId()) {
            this.validationUpdate(agenda);
            this.insertTransactional(agenda);
        }
    }

    @Transactional
    public void updateStatusExcluido(Agenda agenda) {
        this.agendaRepository.updateStatusExcluido(LocalDateTime.now(), agenda.getId());
    }

    public void validationUpdate(Agenda agenda) {
        //não pode existir agendamentos com status de pendente no passado
        if (agenda.getStatusAgendamento().equals(StatusAgendamento.PENDENTE)) {
            if (agenda.getDateTo().compareTo(LocalDateTime.now()) == -1 || agenda.getDateFrom().compareTo(LocalDateTime.now()) == -1) {
                throw new RuntimeException();
            }
        }
        //não pode existir agendamentos com status de compareceu ou não compareceu no futuro
        if (agenda.getStatusAgendamento().equals(StatusAgendamento.COMPARECEU) || agenda.getStatusAgendamento().equals(StatusAgendamento.NAO_COMPARECEU)){
            if (agenda.getDateTo().compareTo(LocalDateTime.now()) == 1 || agenda.getDateFrom().compareTo(LocalDateTime.now()) == 1) {
                throw new RuntimeException();
            }
        }
       List<Agenda> validador = this.selectAllValidationUpdate(agenda.getId(), agenda.getDateFrom(), agenda.getDateTo());
       if (validador.size() > 0) {

        }
    }

    public void validationInsert(Agenda agenda) {
        //não pode existir agendamentos com status de pendente no passado
        if (agenda.getStatusAgendamento().equals(StatusAgendamento.PENDENTE)) {
            if (agenda.getDateTo().compareTo(LocalDateTime.now()) == -1 || agenda.getDateFrom().compareTo(LocalDateTime.now()) == -1) {
                throw new RuntimeException();
            }
        }
        //não pode existir agendamentos com status de compareceu ou não compareceu no futuro
        if (agenda.getStatusAgendamento().equals(StatusAgendamento.COMPARECEU) || agenda.getStatusAgendamento().equals(StatusAgendamento.NAO_COMPARECEU)){
            if (agenda.getDateTo().compareTo(LocalDateTime.now()) == 1 || agenda.getDateFrom().compareTo(LocalDateTime.now()) == 1) {
                throw new RuntimeException();
            }
        }
        //paciente não pode ter dois agendamentos no mesmo horario ou o medico nao pode ter dois agendamentos no mesmo horario
        List<Agenda> verificador = this.selectAllValidation(agenda.getDateFrom(), agenda.getDateTo());
        if (verificador.size() > 0) {
            throw new RuntimeException();
        }

    }

}