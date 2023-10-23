package medicos.projeto.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;
import medicos.projeto.model.Endereco;
import medicos.projeto.model.Especialidade;

@Getter
@ToString
public class MedicoForm {

    @NotBlank(message = "Campo obrigatório")
    @Pattern(message = "Não inserir numeros",regexp ="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    @Email(message = "Email invalido",regexp ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "Campo obrigatório")
    @Pattern(message = "Não inserir letras",regexp = "^[0-9]+$")
    private String telefone;

    @NotBlank(message = "Campo obrigatório")
    @Pattern(message = "Não inserir caracteres especiais",regexp = "^[ 0-9a-zA-Z\b]+$")
    private String crm;

    @NotNull(message = "Campo obrigatório")
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Valid
    private EnderecoForm endereco;

    @JsonCreator
    public MedicoForm(@JsonProperty("nome") String nome,@JsonProperty("email") String email,@JsonProperty("telefone") String telefone,
    @JsonProperty("crm") String crm,@JsonProperty("especialidade") Especialidade especialidade,@JsonProperty("endereco") EnderecoForm endereco){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
    }
    
}
