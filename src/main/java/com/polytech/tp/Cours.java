package com.polytech.tp;

public class Cours implements ICours {
    private String matiere;
    private String enseignant;
    private String salle;
    private String date;
    private String heureDebut;
    private boolean estOptionnel;
    private String niveau;
    private boolean necessiteProjecteur;

    Cours(CoursBuilder builder) {
        this.matiere = builder.matiere;
        this.enseignant = builder.enseignant;
        this.salle = builder.salle;
        this.date = builder.date;
        this.heureDebut = builder.heureDebut;
        this.estOptionnel = builder.estOptionnel;
        this.niveau = builder.niveau;
        this.necessiteProjecteur = builder.necessiteProjecteur;
    }

    public Cours(String matiere, String enseignant, String salle, String date, 
                 String heureDebut, boolean estOptionnel, String niveau, boolean necessiteProjecteur) {
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.salle = salle;
        this.date = date;
        this.heureDebut = heureDebut;
        this.estOptionnel = estOptionnel;
        this.niveau = niveau;
        this.necessiteProjecteur = necessiteProjecteur;
    }

    @Override
    public String getDescription() {
        return "Cours de " + matiere + " avec " + enseignant + " (" + salle + ")";
    }

    @Override
    public double getDuree() {
        return 1.5; 
    }
    
    // Getters
    public String getMatiere() { return matiere; }
    public String getEnseignant() { return enseignant; }
    public String getSalle() { return salle; }
    public String getDate() { return date; }
    public String getHeureDebut() { return heureDebut; }
    public boolean isEstOptionnel() { return estOptionnel; }
    public String getNiveau() { return niveau; }
    public boolean isNecessiteProjecteur() { return necessiteProjecteur; }
    
    // Méthode statique pour obtenir un Builder
    public static CoursBuilder builder() {
        return new CoursBuilder();
    }
}