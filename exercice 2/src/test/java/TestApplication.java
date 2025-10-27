import ma.projet.classes.*;
import ma.projet.service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestApplication {

    public static void main(String[] args) {
        System.out.println("=== Application de Gestion de Projets ===\n");

        EmployeService employeService = new EmployeService();
        ProjetService projetService = new ProjetService();
        TacheService tacheService = new TacheService();
        EmployeTacheService employeTacheService = new EmployeTacheService();

        System.out.println("\n1. Création des employés...");
        employeService.create(new Employe("EMP001", "yasser", "benhadda", "ahmed@example.com",
                "0612345678", "Casablanca", "15/03/1985"));
        employeService.create(new Employe("EMP002", "ali", "naitcheikh", "fatimad@example.com",
                "0612345679", "Rabat", "20/05/1990"));
        employeService.create(new Employe("EMP003", "ghali", "elasri", "mohammed@example.com",
                "0612345680", "Fès", "10/08/1988"));

        System.out.println("\n2. Création des projets...");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Employe chef1 = employeService.findByMatricule("EMP001");
        Employe chef2 = employeService.findByMatricule("EMP002");

        try {
            Projet projet1 = new Projet("Gestion de stock",
                    sdf.parse("14/01/2013"),
                    sdf.parse("31/05/2013"),
                    15000, chef1);
            projetService.create(projet1);

            Projet projet2 = new Projet("Système de facturation",
                    sdf.parse("01/02/2013"),
                    sdf.parse("30/06/2013"),
                    20000, chef2);
            projetService.create(projet2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("\n3. Création des tâches...");
        try {
            Projet p1 = projetService.findById(1);
            Projet p2 = projetService.findById(2);

            Tache tache1 = new Tache("Analyse",
                    sdf.parse("15/01/2013"), sdf.parse("25/01/2013"),
                    sdf.parse("10/02/2013"), sdf.parse("20/02/2013"),
                    1500, p1);
            tacheService.create(tache1);

            Tache tache2 = new Tache("Conception",
                    sdf.parse("26/01/2013"), sdf.parse("15/02/2013"),
                    sdf.parse("10/03/2013"), sdf.parse("15/03/2013"),
                    2000, p1);
            tacheService.create(tache2);

            Tache tache3 = new Tache("Développement",
                    sdf.parse("16/02/2013"), sdf.parse("10/04/2013"),
                    sdf.parse("10/04/2013"), sdf.parse("25/04/2013"),
                    1200, p1);
            tacheService.create(tache3);

            Tache tache4 = new Tache("Analyse",
                    sdf.parse("02/02/2013"), sdf.parse("20/02/2013"),
                    sdf.parse("05/03/2013"), sdf.parse("18/03/2013"),
                    800, p2);
            tacheService.create(tache4);

            Tache tache5 = new Tache("Développement",
                    sdf.parse("21/02/2013"), sdf.parse("25/05/2013"),
                    sdf.parse("20/03/2013"), sdf.parse("15/06/2013"),
                    3000, p2);
            tacheService.create(tache5);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("\n4. Assignment des tâches aux employés...");
        Employe emp1 = employeService.findByMatricule("EMP001");
        Employe emp2 = employeService.findByMatricule("EMP002");
        Employe emp3 = employeService.findByMatricule("EMP003");

        Tache t1 = tacheService.findById(1);
        Tache t2 = tacheService.findById(2);
        Tache t3 = tacheService.findById(3);

        employeTacheService.create(new EmployeTache(40, emp1, t1));
        employeTacheService.create(new EmployeTache(30, emp2, t1));
        employeTacheService.create(new EmployeTache(50, emp3, t2));
        employeTacheService.create(new EmployeTache(60, emp1, t3));
        employeTacheService.create(new EmployeTache(45, emp2, t3));

        System.out.println("\n5. Tâches réalisées par l'employé " + emp1.getNom() + " " + emp1.getPrenom() + ":");
        List<Tache> tachesEmp = employeService.getTachesByEmploye(emp1.getId());
        for (Tache t : tachesEmp) {
            System.out.println("- " + t.getNom() + " (Projet: " + t.getProjet().getNom() + ")");
        }

        System.out.println("\n6. Projets gérés par l'employé " + emp1.getNom() + " " + emp1.getPrenom() + ":");
        List<Projet> projetsEmp = employeService.getProjetsByEmploye(emp1.getId());
        for (Projet p : projetsEmp) {
            System.out.println("- " + p.getNom() + " (Prix: " + p.getPrix() + " DH)");
        }

        System.out.println("\n7. Tâches réalisées du projet 'Gestion de stock':");
        projetService.afficherTachesRealisees(1);

        System.out.println("\n8. Tâches avec prix supérieur à 1000 DH:");
        tacheService.afficherTachesPrixSuperieur();

        System.out.println("\n9. Tâches réalisées entre 01/03/2013 et 30/05/2013:");
        try {
            tacheService.afficherTachesEntreDates(sdf.parse("01/03/2013"), sdf.parse("30/05/2013"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("\n10. Liste de tous les employés:");
        List<Employe> employes = employeService.findAll();
        for (Employe e : employes) {
            System.out.println("- " + e.getMatricule() + " : " + e.getNom() + " " + e.getPrenom());
        }

        ma.projet.util.HibernateUtil.closeSessionFactory();
        System.out.println("\n=== Fin des tests ===");
    }
}
