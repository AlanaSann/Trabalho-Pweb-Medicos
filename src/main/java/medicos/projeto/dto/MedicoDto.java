package medicos.projeto.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import medicos.projeto.model.Especialidade;

@Getter
@Setter
public class MedicoDto {
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;
}
