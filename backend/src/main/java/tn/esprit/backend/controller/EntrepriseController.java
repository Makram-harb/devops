package tn.esprit.backend.controller;

import tn.esprit.backend.dto.EntrepriseDTO;
import tn.esprit.backend.dto.EntrepriseMapper;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.entity.Entreprise;
import tn.esprit.backend.service.IEntrepriseService;

import java.util.List;

@RestController
@RequestMapping("/entreprise")
@AllArgsConstructor
@CrossOrigin("*")
public class EntrepriseController {

    IEntrepriseService entrepriseService;

    @PostMapping("/add")
    public EntrepriseDTO addEntreprise(@RequestBody EntrepriseDTO dto) {
        return EntrepriseMapper.toDto(entrepriseService.addEntreprise(EntrepriseMapper.toEntity(dto)));
    }

    @PutMapping("/update")
    public EntrepriseDTO updateEntreprise(@RequestBody EntrepriseDTO dto) {
        return EntrepriseMapper.toDto(entrepriseService.updateEntreprise(EntrepriseMapper.toEntity(dto)));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEntreprise(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
    }

    @GetMapping("/get/{id}")
    public Entreprise getEntrepriseById(@PathVariable Long id) {
        return entrepriseService.getEntrepriseById(id);
    }

    @GetMapping("/all")
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }
}
