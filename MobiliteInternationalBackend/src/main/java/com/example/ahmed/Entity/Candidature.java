package com.example.ahmed.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer idCandidature;
    private String NomComplet;
    private String Identifiant;
    private String email;
    @Enumerated(EnumType.STRING)
    private  Genre gerne;
    private LocalDate DateNaissance;
    private String Classe;
    private double Moy3;
    private double Moy2;
    private double Moy1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double score;
    @Enumerated(EnumType.STRING)
    private  Niveau Francais;
    @Enumerated(EnumType.STRING)
    private  Niveau Anglais;
    @Enumerated(EnumType.STRING)
    private  Option option;
    @ManyToOne
    @JsonIgnore
    User userCandidature;
    @ManyToOne
    @JsonIgnore
    Offre offre;




}
