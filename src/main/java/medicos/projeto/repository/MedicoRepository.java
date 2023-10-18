package medicos.projeto.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import medicos.projeto.model.Medicos;

public interface MedicoRepository extends JpaRepository<Medicos,Long>{
    public Page<Medicos>findAllByStatus(Boolean status,Pageable pageable );
    public Optional<Medicos> findByIdAndStatus(Long id,Boolean status);
}
