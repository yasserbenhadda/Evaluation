package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "femme")
@NamedQueries({
        @NamedQuery(
                name = "femmesMarieesAuMoinsDeuxFois",
                query = "SELECT f FROM Femme f WHERE f.id IN " +
                        "(SELECT DISTINCT m.femme.id FROM Mariage m GROUP BY m.femme.id HAVING COUNT(m.id) >= 2)"
        )
})
public class Femme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariages;

    public Femme() {
    }

    public Femme(String nom, String prenom, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}

