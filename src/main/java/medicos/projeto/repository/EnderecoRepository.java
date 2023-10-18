package medicos.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import medicos.projeto.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{
    
}
