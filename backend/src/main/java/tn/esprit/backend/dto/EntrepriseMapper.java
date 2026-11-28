package tn.esprit.backend.dto;

import tn.esprit.backend.entity.Entreprise;

public final class EntrepriseMapper {

  private EntrepriseMapper() {}

  public static EntrepriseDTO toDto(Entreprise e) {
    return new EntrepriseDTO(e.getId(), e.getNom(), e.getAdresse());
  }

  public static Entreprise toEntity(EntrepriseDTO d) {
    Entreprise e = new Entreprise();
    e.setId(d.id());
    e.setNom(d.nom());
    e.setAdresse(d.adresse());
    return e;
  }
}
