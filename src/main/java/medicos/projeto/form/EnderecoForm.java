package medicos.projeto.form;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnderecoForm {

    @NotBlank(message = "Campo obrigatório")
    private String logradouro;

    private int numero;
    
    private String complemento;

    @Pattern(message = "Não inserir numeros",regexp ="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
    @NotBlank(message = "Campo obrigatório")
    private String bairro;

    @Pattern(message = "Não inserir numeros",regexp ="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
    @NotBlank(message = "Campo obrigatório")
    private String cidade;

    @Pattern(message = "Não inserir numeros",regexp ="[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")
    @Size (message = "formato de sigla incorreta",max = 2,min = 2)
    @NotBlank(message = "Campo obrigatório")
    private String uf;

    @NotBlank(message = "Campo obrigatório")
    @Pattern(message = "Não inserir letras",regexp = "^[0-9]+$")
    @Size(message = "tamanho invalido", min = 8,max = 8)
    private String cep;


    public EnderecoForm(String logradouro, int numero, String complemento, String bairro, String cidade, String uf,
            String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
    } 

    
    
}
