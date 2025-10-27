package ma.projet.classes;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produit")
@NamedQueries({
        @NamedQuery(name = "Produit.findByPrix", query = "SELECT p FROM Produit p WHERE p.prix > :prix")
})
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reference", unique = true, nullable = false)
    private String reference;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String reference, String designation, double prix, int quantite, Categorie categorie) {
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
}
