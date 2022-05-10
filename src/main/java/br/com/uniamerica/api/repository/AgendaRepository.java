package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Eduardo Sganderla
 *
 * @since 1.0.0, 31/03/2022
 * @version 1.0.0
 */
@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Query("from Agenda agenda "+
    "where :dateFrom between agenda.dateFrom and agenda.dateTo " +
    "and :dateTo between agenda.dateFrom and agenda.dateTo")

    public List<Agenda> findAllValidationPaciente(
            @Param("dateFrom")LocalDateTime dateFrom,
            @Param("dateTo")LocalDateTime dateTo
            );

    @Query("from Agenda agenda " +
    "where agenda.id <> :idAgendaSent " +
    "and :dateFrom between agenda.dateFrom and agenda.dateTo " +
    "and :dateTo between agenda.dateFrom and agenda.dateTo")

    public List<Agenda> FindAllValidationUpdate(
            @Param("idAgendaSent") Long idAgendaSent,
            @Param("dateFrom") LocalDateTime dateFrom,
            @Param("dateTo") LocalDateTime dateTo
    );

    @Modifying
    @Query("update Agenda agenda set agenda.excluido = :dataExcluido where agenda.id = :idSent")
    public void updateStatusExcluido(@Param("dataExcluido") LocalDateTime dataExcluido,
                                     @Param("idSent") Long idSent);

    List<Agenda> findAllValidationUpdate(Long id, LocalDateTime dataDe, LocalDateTime dataAte);
}
