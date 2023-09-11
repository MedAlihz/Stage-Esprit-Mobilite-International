import { offre } from "./offre";
import { user } from "./user";
export enum Genre {
    Masculin,
    Féminin
    }
    export enum Niveau {
        A1,A2,B1,B2,C1,C2,Null
        }
        export enum option {
            ERPBI,arcTIC,SAE,SIM,SLEAM,NIDS,TWIN,InFini,DataScience,SE,IoSyS,Wins,OGI,Mecatrononique,StructureOuvrage,PontsEtchaussées,RessourcesNaturellesEtétudesEnergétique,Autre
        }
export class Candidature {
    constructor(
      public idCandidature: number,
      public nomComplet: string,
      public identifiant: string,
      public email: string,
      public gerne: Genre,
      public dateNaissance: Date,
      public classe: string,
      public moy3: number,
      public moy2: number,
      public moy1: number,
      public score: number,
      public francais: Niveau,
      public anglais: Niveau,
      public option: option,

      public offre: offre, 
        public userCandidature: user
    ) {}
  }