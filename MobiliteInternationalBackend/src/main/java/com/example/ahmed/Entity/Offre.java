package com.example.ahmed.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idOffre;
    private String titre;
    private String description;
    private String lieu;
    private LocalDate deadline;
    private Integer nombreplaces;
    private String link;
    @Lob
    private String image;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "offre")
    @JsonIgnore
    private Set<Candidature> candidatures;
}