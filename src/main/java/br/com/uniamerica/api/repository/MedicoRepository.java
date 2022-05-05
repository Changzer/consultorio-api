package br.com.uniamerica.api.repository;

import br.com.uniamerica.api.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    /**
     * @see Medico#Medico(Long, String, String)
     */
    @Query("SELECT new Medico(medico.id, medico.nome, medico.crm) FROM Medico medico")
    public List<Medico> listTable();

    @Modifying
    @Query ("UPDATE Medico medico set medico.excluido = :excluido WHERE medico.id=:idMedico")
    public void medicoExcluido (@Param("excluido")LocalDateTime dataExcluido, @Param("idMedico")Long idExcluido);
}
