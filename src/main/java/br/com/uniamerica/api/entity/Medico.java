package br.com.uniamerica.api.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * @author Eduardo Sganderla
 *
 * @since 1.0.0, 22/03/2022
 * @version 1.0.0
 */
@Entity
@NoArgsConstructor
@Table(name = "medicos", schema = "public")
public class Medico extends Pessoa {

    @Getter @Setter
    @Column(name = "crm", nullable = false, unique = true, length = 20)
    private String crm;

    @Getter @Setter
    @Column(name = "consultorio", nullable = false, length = 20)
    private String consultorio;

    @Getter @Setter
    @Digits(integer = 3, fraction = 3)
    @Column(name = "porcentParticipacao", precision = 3, scale = 3, nullable = false)
    private BigDecimal porcentParticipacao;

    @Getter @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "valor_consulta", precision = 5, scale = 3, length = 50)
    private BigDecimal valorConsulta;

    @Getter @Setter
    @JoinColumn(name = "id_especialidade")
    @ManyToOne(fetch = FetchType.EAGER)
    private Especialidade especialidade;


    public Medico(Long id, String nome, String crm){
        super(id, nome);
        this.crm = crm;
    }

}
