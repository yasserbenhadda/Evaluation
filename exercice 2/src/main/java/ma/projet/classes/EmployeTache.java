package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employe_tache")
public class EmployeTache implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double nbHeures;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    private Tache tache;

    public EmployeTache() {
    }

    public EmployeTache(double nbHeures, Employe employe, Tache tache) {
        this.nbHeures = nbHeures;
        this.employe = employe;
        this.tache = tache;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(double nbHeures) {
        this.nbHeures = nbHeures;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @Override
    public String toString() {
        return "EmployeTache{" +
                "id=" + id +
                ", nbHeures=" + nbHeures +
                ", employe=" + employe.getNom() + " " + employe.getPrenom() +
                ", tache=" + tache.getNom() +
                '}';
    }
}
