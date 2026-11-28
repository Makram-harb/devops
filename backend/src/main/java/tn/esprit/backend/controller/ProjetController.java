package tn.esprit.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.dto.ProjetDto;
import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.service.IProjetService;

import java.util.List;

@RestController
@RequestMapping("/projet")
@AllArgsConstructor
public class ProjetController {

    IProjetService projetService;

    @PostMapping("/add")
    public Projet addProjet(@RequestBody ProjetDto dto) {
        return projetService.addProjet(dto.toEntity());
    }

    @PutMapping("/update")
    public Projet updateProjet(@RequestBody ProjetDto dto) {
        return projetService.updateProjet(dto.toEntity());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
    }

    @GetMapping("/get/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id);
    }

    @GetMapping("/all")
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }
}
