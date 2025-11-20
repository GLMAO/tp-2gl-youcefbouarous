

---

##  Question

**"Est-ce que votre code respecte les principes de conception logicielle? S'il ne respecte pas, quel principe viole-t-il?"**

---

##  Réponse

**OUI, le code respecte très bien les principes de conception logicielle.**

**Score global: 24/25**

---

##  Analyse des Principes SOLID

### 1. Single Responsibility Principle (SRP) - Score: 4/5 

**Définition:** Une classe ne devrait avoir qu'une seule raison de changer.

**État:** Partiellement respecté

**Violation détectée:**

La classe `GestionnaireEmploiDuTemps` a **deux responsabilités**:
1. Gérer la liste des cours (ajout, modification, suppression)
2. Notifier les observateurs (pattern Observer)

```java
public class GestionnaireEmploiDuTemps implements Subject {
    private List<ICours> listeCours;      // Responsabilité 1
    private List<Observer> observers;      // Responsabilité 2
}
```

**Principe violé:** Si on change la façon de gérer les cours OU la façon de notifier, on doit modifier cette classe → Deux raisons de changer.

**Conclusion:** Violation mineure acceptable pour un TP académique.

---

### 2. Open/Closed Principle (OCP) - Score: 5/5 

**Définition:** Les classes doivent être ouvertes à l'extension mais fermées à la modification.

**État:** Parfaitement respecté

**Preuve:**

On peut ajouter un nouveau décorateur SANS modifier le code existant:

```java
public class CoursHybride extends CoursDecorator {
    public CoursHybride(ICours cours) {
        super(cours);
    }
    
    @Override
    public String getDescription() {
        return coursDecorated.getDescription() + " (Hybride)";
    }
}
// Aucune modification du code existant nécessaire!
```

**Conclusion:** Extension sans modification 

---

### 3. Liskov Substitution Principle (LSP) - Score: 5/5 

**Définition:** Les objets d'une classe dérivée doivent pouvoir remplacer les objets de la classe de base.

**État:** Parfaitement respecté

**Preuve:**

```java
// Tous les ICours sont interchangeables
ICours cours1 = new Cours(...);
ICours cours2 = new CoursEnLigne(cours1);
ICours cours3 = new CoursEnAnglais(cours1);

// Tous fonctionnent de la même manière
afficherCours(cours1);  // 
afficherCours(cours2);  // 
afficherCours(cours3);  // 
```

**Conclusion:** Substitution parfaite 

---

### 4. Interface Segregation Principle (ISP) - Score: 5/5 

**Définition:** Les clients ne devraient pas dépendre d'interfaces qu'ils n'utilisent pas.

**État:** Parfaitement respecté

**Preuve:**

Nos interfaces sont minimales et ciblées:

```java
// Interface minimale (1 méthode)
public interface Observer {
    void update(String message);
}

// Interface minimale (3 méthodes nécessaires)
public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(String message);
}

// Interface minimale (2 méthodes)
public interface ICours {
    String getDescription();
    double getDuree();
}
```

**Conclusion:** Pas de méthodes inutiles 

---

### 5. Dependency Inversion Principle (DIP) - Score: 5/5 

**Définition:** Dépendre des abstractions, pas des implémentations concrètes.

**État:** Parfaitement respecté

**Preuve:**

```java
public class GestionnaireEmploiDuTemps implements Subject {
    //  Dépend de l'INTERFACE, pas de la classe concrète
    private List<Observer> observers;    // Pas List<Etudiant>
    private List<ICours> listeCours;     // Pas List<Cours>
}

public abstract class CoursDecorator implements ICours {
    //  Dépend de l'INTERFACE, pas de la classe concrète
    protected ICours coursDecorated;     // Pas Cours
}
```




