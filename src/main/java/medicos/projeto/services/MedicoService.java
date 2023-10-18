package medicos.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import medicos.projeto.model.Medicos;
import medicos.projeto.repository.EnderecoRepository;
import medicos.projeto.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Medicos cadastrarMedicos(Medicos medico){
        medico.setStatus(true);
        enderecoRepository.save(medico.getEndereco());
        return medicoRepository.save(medico);
    }
}
