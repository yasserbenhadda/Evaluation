package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mariage")
public class Mariage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "nbr_enfants")
    private Integer nombreEnfants = 0;

    @ManyToOne
    @JoinColumn(name = "homme_id", nullable = false)
    private Homme homme;

    @ManyToOne
    @JoinColumn(name = "femme_id", nullable = false)
    private Femme femme;

    public Mariage() {
    }

    public Mariage(Date dateDebut, Homme homme, Femme femme, Integer nombreEnfants) {
        this.dateDebut = dateDebut;
        this.homme = homme;
        this.femme = femme;
        this.nombreEnfants = nombreEnfants;
    }

    public Mariage(Date dateDebut, Date dateFin, Homme homme, Femme femme, Integer nombreEnfants) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.homme = homme;
        this.femme = femme;
        this.nombreEnfants = nombreEnfants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNombreEnfants() {
        return nombreEnfants;
    }

    public void setNombreEnfants(Integer nombreEnfants) {
        this.nombreEnfants = nombreEnfants;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    public boolean estEnCours() {
        return dateFin == null;
    }
}

