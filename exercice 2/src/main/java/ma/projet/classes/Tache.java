package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tache")
@NamedQuery(name = "taches.byPrice", query = "SELECT t FROM Tache t WHERE t.prix > 1000")
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private Date dateDebutPlanifie;
    private Date dateFinPlanifie;
    private Date dateDebutReelle;
    private Date dateFinReelle;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeTache> employeTaches;

    public Tache() {
    }

    public Tache(String nom, Date dateDebutPlanifie, Date dateFinPlanifie, Date dateDebutReelle, Date dateFinReelle, double prix, Projet projet) {
        this.nom = nom;
        this.dateDebutPlanifie = dateDebutPlanifie;
        this.dateFinPlanifie = dateFinPlanifie;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinReelle = dateFinReelle;
        this.prix = prix;
        this.projet = projet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebutPlanifie() {
        return dateDebutPlanifie;
    }

    public void setDateDebutPlanifie(Date dateDebutPlanifie) {
        this.dateDebutPlanifie = dateDebutPlanifie;
    }

    public Date getDateFinPlanifie() {
        return dateFinPlanifie;
    }

    public void setDateFinPlanifie(Date dateFinPlanifie) {
        this.dateFinPlanifie = dateFinPlanifie;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<EmployeTache> getEmployeTaches() {
        return employeTaches;
    }

    public void setEmployeTaches(List<EmployeTache> employeTaches) {
        this.employeTaches = employeTaches;
    }

    @Override
    public String toString() {
        return "Tache{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDebutPlanifie=" + dateDebutPlanifie +
                ", dateFinPlanifie=" + dateFinPlanifie +
                ", dateDebutReelle=" + dateDebutReelle +
                ", dateFinReelle=" + dateFinReelle +
                ", prix=" + prix +
                '}';
    }
}
