package medicos.projeto.services;

import java.util.List;
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
import medicos.projeto.rules.Rules;


@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    
    public Medicos cadastrarMedico(Medicos medico) {
        medico.setStatus(true);
        enderecoRepository.save(medico.getEndereco());
        return medicoRepository.save(medico);
    }

    public Page<Medicos> listarMedicos(Pageable pageable) {
        return medicoRepository.findAllByStatus(true, pageable);
    }

    public void deletarMedico(Long id){
        Medicos medicoAserExcluido = encontrarMedico(id);
        medicoAserExcluido.setStatus(false);
        medicoRepository.save(medicoAserExcluido);
    }

    public Medicos encontrarMedico(Long id){
        Optional<Medicos> medico = medicoRepository.findByIdAndStatus(id,true);
        if (medico.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Medico não encontrado");
        }
        return medico.get();
    }

    public Medicos encontrarMedico(String crm){
        Optional<Medicos> medico = medicoRepository.findByCrmAndStatus(crm,true);
        if (medico.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Medico não encontrado");
        }
        return medico.get();
    }

    
    public Medicos atualizarMedico(Long id, Medicos medico){
        Rules regra = new Rules();
        Medicos medicoAserEditado = encontrarMedico(id);
        regra.valida(medicoAserEditado,medico);
        medicoAserEditado.setNome(medico.getNome());
        medicoAserEditado.setTelefone(medico.getTelefone());
        medicoAserEditado.setEndereco(medico.getEndereco());
        enderecoRepository.save(medicoAserEditado.getEndereco());
        medicoRepository.save(medicoAserEditado);
        return medicoAserEditado;
    }

    public List<Medicos> listarMedicos(){
        return medicoRepository.findAll();
    }
}
