package br.com.uniamerica.api.service;

import br.com.uniamerica.api.entity.Paciente;
import br.com.uniamerica.api.entity.TipoAtendimento;
import br.com.uniamerica.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Optional<Paciente> findById(Long id){
        return this.pacienteRepository.findById(id);
    }

    public Page<Paciente> listAll(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }

    public void insert(Paciente paciente){
        this.validarFormulario(paciente);
        this.insertTransactional(paciente);
    }
    @Transactional
    public void insertTransactional(Paciente paciente){
        this.pacienteRepository.save(paciente);
    }

    public void update(Long id, Paciente paciente){
        if(id == paciente.getId()){
            this.validarFormulario(paciente);
            this.saveTransactional(paciente);
        }
    }
    @Transactional
    public void saveTransactional(Paciente paciente){
            this.pacienteRepository.save(paciente);
    }

public void validarFormulario(Paciente paciente){
    if(paciente.getTipoAtendimento().equals(TipoAtendimento.convenio)){
        if(paciente.getConvenio().getId() == null){
            throw new RuntimeException("numero do convenio nao informado");
        }
        if(paciente.getNumeroCartaoConvenio() == null){
            throw new RuntimeException("cartao do convenio nao informado");
        }
        if(paciente.getDataVencimento() == null){
            throw new RuntimeException("data de vencimento nao informado");
        }
        if(paciente.getDataVencimento().compareTo(LocalDateTime.now()) >= 0){
            throw new RuntimeException("cartao de convenio vencido");
        }
    }
    if (paciente.getTipoAtendimento().equals(TipoAtendimento.particular)) {
        paciente.setConvenio(null);
        paciente.setNumeroCartaoConvenio(null);
        paciente.setDataVencimento(null);
    }
}

}
