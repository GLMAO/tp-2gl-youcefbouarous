# 🎨 BONUS 1: Diagramme de Classe

## TP Design Patterns - Gestion de l'Emploi du Temps


## 📊 Diagramme de Classe Complet


┌─────────────────────────────────────────────────────────────────────┐
│                         PATTERN OBSERVER                            │
└─────────────────────────────────────────────────────────────────────┘

┌────────────────────┐                     ┌────────────────────┐
│   <<interface>>    │                     │   <<interface>>    │
│      Observer      │                     │      Subject       │
├────────────────────┤                     ├────────────────────┤
│ + update(String)   │                     │ + attach(Observer) │
└──────────▲─────────┘                     │ + detach(Observer) │
           │                               │ + notifyObservers()│
           │                               └──────────▲─────────┘
           │                                          │
    ┌──────┴──────┐                                  │
    │             │                                  │
┌───┴────────┐ ┌──┴──────────┐        ┌─────────────┴──────────────┐
│  Etudiant  │ │ Responsable │        │ GestionnaireEmploiDuTemps  │
├────────────┤ ├─────────────┤        ├────────────────────────────┤
│ - nom      │ │ - nom       │        │ - listeCours: List<ICours> │
├────────────┤ ├─────────────┤        │ - observers: List<Observer>│
│ + update() │ │ + update()  │        ├────────────────────────────┤
└────────────┘ └─────────────┘        │ + ajouterCours(ICours)     │
                                      │ + modifierCours(ICours,...)│
                                      │ + annulerCours(ICours)     │
                                      │ + setChangement(String)    │
                                      │ + attach(Observer)         │
                                      │ + detach(Observer)         │
                                      │ + notifyObservers(String)  │
                                      └────────────────────────────┘


┌─────────────────────────────────────────────────────────────────────┐
│                         PATTERN BUILDER                             │
└─────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────────────┐          ┌─────────────────────┐
│        CoursBuilder                    │ builds   │       Cours         │
├────────────────────────────────────────┤◄─────────┤                     │
│ ~ matiere: String                      │          │ (implémente ICours) │
│ ~ enseignant: String                   │          └─────────────────────┘
│ ~ salle: String                        │
│ ~ date: String                         │
│ ~ heureDebut: String                   │
│ ~ estOptionnel: boolean                │
│ ~ niveau: String                       │
│ ~ necessiteProjecteur: boolean         │
├────────────────────────────────────────┤
│ + setMatiere(String): this             │
│ + setEnseignant(String): this          │
│ + setSalle(String): this               │
│ + setDate(String): this                │
│ + setHeureDebut(String): this          │
│ + setEstOptionnel(boolean): this       │
│ + setNiveau(String): this              │
│ + setNecessiteProjecteur(boolean): this│
│ + build(): Cours                       │
└────────────────────────────────────────┘


┌─────────────────────────────────────────────────────────────────────┐
│                        PATTERN DECORATOR                            │
└─────────────────────────────────────────────────────────────────────┘

                    ┌────────────────────┐
                    │   <<interface>>    │
                    │       ICours       │
                    ├────────────────────┤
                    │ + getDescription() │
                    │ + getDuree()       │
                    └──────────▲─────────┘
                               │
                ┌──────────────┴──────────────┐
                │                             │
     ┌──────────┴───────────┐      ┌──────────┴──────────────┐
     │       Cours          │      │   <<abstract>>          │
     ├──────────────────────┤      │    CoursDecorator       │
     │ - matiere            │      ├─────────────────────────┤
     │ - enseignant         │      │ # coursDecorated: ICours│
     │ - salle              │      ├─────────────────────────┤
     │ - date               │      │ + getDescription()      │
     │ - heureDebut         │      │ + getDuree()            │
     │ - estOptionnel       │      └───────────▲─────────────┘
     │ - niveau             │                  │
     │ - necessiteProjecteur│          ┌──────┴───────┬──────────────┐
     ├──────────────────────┤          │              │              │
     │ + getDescription()   │   ┌──────┴──────┐ ┌─────┴──────┐ ┌────┴────────┐
     │ + getDuree()         │   │CoursEnLigne │ │CoursEnAnglais│ │CoursMagistral│
     │ + getMatiere()       │   ├─────────────┤ ├────────────┤ ├─────────────┤
     │ + getEnseignant()    │   │+getDescription()│+getDescription()│+getDescription()│
     │ + getSalle()         │   │+getDuree()  │ │+getDuree() │ │+getDuree()  │
     │ + getDate()          │   └─────────────┘ └────────────┘ └─────────────┘
     │ + getHeureDebut()    │
     │ + ... (getters)      │
     │ + builder(): Builder │
     └──────────────────────┘


┌─────────────────────────────────────────────────────────────────────┐
│                    RELATIONS ENTRE PATTERNS                         │
└─────────────────────────────────────────────────────────────────────┘

GestionnaireEmploiDuTemps ──────> ICours (agrégation: gère une liste)
                          gère
                          
CoursBuilder ──────────────────> Cours (association: construit)
                 construit

CoursDecorator ────────────────> ICours (composition: décore)
                 décore
                 
Observer <──────────────────── Etudiant (réalisation)
         implements
         
Observer <──────────────────── Responsable (réalisation)
         implements
         
Subject <───────────────────── GestionnaireEmploiDuTemps (réalisation)
        implements
```

---
#UML

' ============================================================
' INTERFACES
' ============================================================
interface Observer {
  +update(message: String): void
}

interface Subject {
  +attach(o: Observer): void
  +detach(o: Observer): void
  +notifyObservers(message: String): void
}

interface ICours {
  +getDescription(): String
  +getDuree(): double
}

' ============================================================
' PATTERN OBSERVER - Classes concrètes
' ============================================================
class Etudiant {
  -nom: String
  +Etudiant(nom: String)
  +update(message: String): void
  +getNom(): String
}

class Responsable {
  -nom: String
  +Responsable(nom: String)
  +update(message: String): void
  +getNom(): String
}

class GestionnaireEmploiDuTemps {
  -listeCours: List<ICours>
  -observers: List<Observer>
  +GestionnaireEmploiDuTemps()
  +attach(o: Observer): void
  +detach(o: Observer): void
  +notifyObservers(message: String): void
  +ajouterCours(cours: ICours): void
  +modifierCours(cours: ICours, message: String): void
  +annulerCours(cours: ICours): void
  +setChangement(message: String): void
  +getListeCours(): List<ICours>
}

' ============================================================
' PATTERN BUILDER
' ============================================================
class Cours {
  -matiere: String
  -enseignant: String
  -salle: String
  -date: String
  -heureDebut: String
  -estOptionnel: boolean
  -niveau: String
  -necessiteProjecteur: boolean
  +Cours(builder: CoursBuilder)
  +Cours(matiere, enseignant, salle, date, heureDebut, estOptionnel, niveau, necessiteProjecteur)
  +getDescription(): String
  +getDuree(): double
  +getMatiere(): String
  +getEnseignant(): String
  +getSalle(): String
  +getDate(): String
  +getHeureDebut(): String
  +isEstOptionnel(): boolean
  +getNiveau(): String
  +isNecessiteProjecteur(): boolean
  +{static} builder(): CoursBuilder
}

class CoursBuilder {
  ~matiere: String
  ~enseignant: String
  ~salle: String
  ~date: String
  ~heureDebut: String
  ~estOptionnel: boolean
  ~niveau: String
  ~necessiteProjecteur: boolean
  +CoursBuilder()
  +setMatiere(matiere: String): CoursBuilder
  +setEnseignant(enseignant: String): CoursBuilder
  +setSalle(salle: String): CoursBuilder
  +setDate(date: String): CoursBuilder
  +setHeureDebut(heureDebut: String): CoursBuilder
  +setEstOptionnel(estOptionnel: boolean): CoursBuilder
  +setNiveau(niveau: String): CoursBuilder
  +setNecessiteProjecteur(necessiteProjecteur: boolean): CoursBuilder
  +build(): Cours
}

' ============================================================
' PATTERN DECORATOR
' ============================================================
abstract class CoursDecorator {
  #coursDecorated: ICours
  +CoursDecorator(cours: ICours)
  +getDescription(): String
  +getDuree(): double
}

class CoursEnLigne {
  +CoursEnLigne(cours: ICours)
  +getDescription(): String
  +getDuree(): double
}

class CoursEnAnglais {
  +CoursEnAnglais(cours: ICours)
  +getDescription(): String
  +getDuree(): double
}

class CoursMagistral {
  +CoursMagistral(cours: ICours)
  +getDescription(): String
  +getDuree(): double
}

