package br.com.uniamerica.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendas", schema = "public")
@NoArgsConstructor
public class Agenda extends AbstractEntity{
    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "paciente_id")
    private Paciente paciente;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id",nullable = false)
    private Medico medico;

    @Getter @Setter
    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

    @Getter @Setter @Column(name = "dateFrom", nullable = false)
    private LocalDateTime dateFrom;

    @Getter @Setter @Column(name = "dateTo", nullable = false)
    private LocalDateTime dateTo;

    @Getter @Setter @Column(name = "encaixe", columnDefinition = "boolean Default false")
    private Boolean encaixe;


}