package medicos.projeto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import medicos.projeto.dto.MedicoDto;
import medicos.projeto.model.Medicos;
import medicos.projeto.repository.EnderecoRepository;
import medicos.projeto.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Medicos cadastrarMedicos(Medicos medico) {
        medico.setStatus(true);
        enderecoRepository.save(medico.getEndereco());
        return medicoRepository.save(medico);
    }

    public Page<Medicos> listarMedicos(Pageable pageable) {
        return medicoRepository.findAllByStatus(true, pageable);
    }

    public void deletarMedicos(Long id){
        Medicos medicoAserExcluido = encontrarMedicos(id);
        medicoAserExcluido.setStatus(false);
        medicoRepository.save(medicoAserExcluido);
    }

    public Medicos encontrarMedicos(Long id){
        Optional<Medicos> medico = medicoRepository.findByIdAndStatus(id,true);
        if (medico.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Medico não encontrado");
        }
        return medico.get();
    }

    public Medicos atualizarMedicos(Long id, Medicos medico){
        Medicos medicoAserEditado = encontrarMedicos(id);
        medicoAserEditado.setNome(medico.getNome());
        medicoAserEditado.setTelefone(medico.getTelefone());
        medicoAserEditado.setEndereco(medico.getEndereco());
        enderecoRepository.save(medicoAserEditado.getEndereco());
        medicoRepository.save(medicoAserEditado);
        return medicoAserEditado;
    }
}
