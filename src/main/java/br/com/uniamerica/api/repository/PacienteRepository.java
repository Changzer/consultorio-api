package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Modifying
    @Query("update Paciente paciente set paciente.excluido = :dataExclusao where paciente.id = :idPassado")
    public void updateStatusExcluido(@Param("dataExclusao") LocalDateTime dataExclusao, @Param("idPassado") Long idPassado);
}