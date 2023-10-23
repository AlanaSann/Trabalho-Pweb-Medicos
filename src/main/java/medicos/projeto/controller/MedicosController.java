package medicos.projeto.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import medicos.projeto.dto.MedicoDto;
import medicos.projeto.form.MedicoForm;
import medicos.projeto.model.Endereco;
import medicos.projeto.model.Medicos;
import medicos.projeto.repository.MedicoRepository;
import medicos.projeto.services.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private ModelMapper modelMapper; // conversor de classes(adapter)

    @PostMapping("/cadastrar")
    public ResponseEntity<MedicoDto> cadastrar(@Valid @RequestBody MedicoForm medico) {
        Medicos medicoConvertido = modelMapper.map(medico, Medicos.class);
        medicoService.cadastrarMedicos(medicoConvertido);
        MedicoDto medicoDto = modelMapper.map(medicoConvertido, MedicoDto.class);
        return ResponseEntity.created(null).body(medicoDto);

    }
    
    @GetMapping("/listar/{page}")
    public ResponseEntity<Page<MedicoDto>> listar(@PathVariable int page){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nome"));
        return ResponseEntity.ok().body(medicoService.listarMedicos(pageable).map(m-> modelMapper.map(m, MedicoDto.class)));
    }

    @DeleteMapping("/deletar/{id}")
    public Object deletar(@PathVariable Long id){
         medicoService.deletarMedicos(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MedicoDto> atualizar(@PathVariable Long id,@Valid @RequestBody MedicoForm medico){
        Medicos medicoConvertido =  modelMapper.map(medico, Medicos.class);
        medicoService.atualizarMedicos(id, medicoConvertido);
        MedicoDto medicoDto = modelMapper.map(medicoConvertido, MedicoDto.class);
        return ResponseEntity.created(null).body(medicoDto);
    }
}
