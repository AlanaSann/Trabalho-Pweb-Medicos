package medicos.projeto.rules;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import medicos.projeto.model.Especialidade;
import medicos.projeto.model.Medicos;

public class Rules {
    public void valida(Medicos medicoNovo, Medicos medicoAtual){
        validaEmail(medicoNovo.getEmail(), medicoAtual.getEmail());
        validaCPF(medicoNovo.getCrm(), medicoAtual.getCrm());
        validaEspecialidade(medicoNovo.getEspecialidade(),medicoAtual.getEspecialidade());
    }

    private void validaEmail(String emailNovo, String emailAtual){
        if (!emailNovo.equals(emailAtual)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel modificar o email");
        }
    }
    private void validaCPF(String crmNovo,String crmAtual){
        if (!crmAtual.equals(crmNovo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não é possivel modificar o crm");   
        }
    }

    private void validaEspecialidade(Especialidade especialidadeNova, Especialidade especialidadeAtual){
        if (!especialidadeAtual.equals(especialidadeNova)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não é possivel modificar a especialidade"); 
        }
    }
}
