package medicos.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import medicos.projeto.model.Medicos;
import medicos.projeto.repository.MedicoRepository;
import medicos.projeto.services.MedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Medicos> cadastrar(@RequestBody Medicos medico) {
        return ResponseEntity.created(null).body(medicoService.cadastrarMedicos(medico));
    }

    @GetMapping("/listar/{page}")
    public Page<Medicos> listar(@PathVariable int page){
       Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "nome"));
        return medicoService.listarMedicos(pageable);
    }

}
