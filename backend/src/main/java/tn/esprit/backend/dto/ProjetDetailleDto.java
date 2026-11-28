package tn.esprit.backend.dto;

import java.time.LocalDate;

import tn.esprit.backend.entity.Projet;
import tn.esprit.backend.entity.ProjetDetaille;

public record ProjetDetailleDto(Long id, String description, String technologie,
                                Double coutProvisoire, LocalDate dateDebut, IdRef projet) {

    public ProjetDetaille toEntity() {
        ProjetDetaille pd = new ProjetDetaille();
        pd.setId(id);
        pd.setDescription(description);
        pd.setTechnologie(technologie);
        pd.setCoutProvisoire(coutProvisoire);
        pd.setDateDebut(dateDebut);
        if (projet != null && projet.id() != null) {
            Projet p = new Projet();
            p.setId(projet.id());
            pd.setProjet(p);
        }
        return pd;
    }
}
