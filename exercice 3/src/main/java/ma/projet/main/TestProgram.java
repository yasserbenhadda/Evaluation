package ma.projet.main;

import ma.projet.beans.*;
import ma.projet.services.*;
import ma.projet.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestProgram {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            HommeService hommeService = new HommeService();
            FemmeService femmeService = new FemmeService();
            MariageService mariageService = new MariageService();

            System.out.println("=== CRÉATION DES DONNÉES ===\n");

            Homme homme1 = new Homme("SAFI", "SAID", sdf.parse("01/01/1965"));
            Homme homme2 = new Homme("ALAMI", "MOHAMED", sdf.parse("15/05/1970"));
            Homme homme3 = new Homme("ROUSSI", "AHMED", sdf.parse("20/03/1968"));
            Homme homme4 = new Homme("TALBI", "HASSAN", sdf.parse("10/07/1972"));
            Homme homme5 = new Homme("BENZID", "OMAR", sdf.parse("25/12/1966"));

            homme1 = hommeService.create(homme1);
            homme2 = hommeService.create(homme2);
            homme3 = hommeService.create(homme3);
            homme4 = hommeService.create(homme4);
            homme5 = hommeService.create(homme5);

            Femme femme1 = new Femme("RAMI", "SALIMA", sdf.parse("02/02/1970"));
            Femme femme2 = new Femme("ALI", "AMAL", sdf.parse("15/06/1975"));
            Femme femme3 = new Femme("ALAOUI", "WAFA", sdf.parse("20/04/1980"));
            Femme femme4 = new Femme("ALAMI", "KARIMA", sdf.parse("10/08/1972"));
            Femme femme5 = new Femme("BENALI", "FATIMA", sdf.parse("05/03/1973"));
            Femme femme6 = new Femme("TOUZA", "NADIRA", sdf.parse("12/09/1974"));
            Femme femme7 = new Femme("BENTALI", "KHADIJA", sdf.parse("22/11/1971"));
            Femme femme8 = new Femme("EL FASSI", "LATIFA", sdf.parse("08/10/1976"));
            Femme femme9 = new Femme("JAZOULI", "SANA", sdf.parse("30/07/1977"));
            Femme femme10 = new Femme("BAHRI", "AMINA", sdf.parse("14/01/1978"));

            femme1 = femmeService.create(femme1);
            femme2 = femmeService.create(femme2);
            femme3 = femmeService.create(femme3);
            femme4 = femmeService.create(femme4);
            femme5 = femmeService.create(femme5);
            femme6 = femmeService.create(femme6);
            femme7 = femmeService.create(femme7);
            femme8 = femmeService.create(femme8);
            femme9 = femmeService.create(femme9);
            femme10 = femmeService.create(femme10);

            System.out.println("10 femmes et 5 hommes créés.\n");

            Mariage m1 = new Mariage(sdf.parse("03/09/1989"), homme1, femme4, 0);
            m1.setDateFin(sdf.parse("03/09/1990"));
            mariageService.create(m1);

            Mariage m2 = new Mariage(sdf.parse("03/09/1990"), homme1, femme1, 4);
            mariageService.create(m2);

            Mariage m3 = new Mariage(sdf.parse("03/09/1995"), homme1, femme2, 2);
            mariageService.create(m3);

            Mariage m4 = new Mariage(sdf.parse("04/11/2000"), homme1, femme3, 3);
            mariageService.create(m4);

            Mariage m5 = new Mariage(sdf.parse("01/01/1995"), homme2, femme5, 2);
            mariageService.create(m5);

            Mariage m6 = new Mariage(sdf.parse("15/03/2000"), homme2, femme6, 1);
            mariageService.create(m6);

            Mariage m7 = new Mariage(sdf.parse("20/05/1998"), homme3, femme7, 3);
            mariageService.create(m7);

            Mariage m8 = new Mariage(sdf.parse("10/06/2002"), homme4, femme8, 2);
            mariageService.create(m8);

            Mariage m9 = new Mariage(sdf.parse("05/07/2005"), homme4, femme9, 1);
            mariageService.create(m9);

            Mariage m10 = new Mariage(sdf.parse("18/04/2003"), homme5, femme10, 2);
            mariageService.create(m10);

            System.out.println("Mariages créés.\n");

            System.out.println("=== TEST 1: AFFICHER LA LISTE DES FEMMES ===\n");
            List<Femme> femmes = femmeService.findAll();
            for (Femme f : femmes) {
                System.out.println("- " + f);
            }
            System.out.println();

            System.out.println("=== TEST 2: AFFICHER LA FEMME LA PLUS ÂGÉE ===\n");
            Femme femmePlusAgee = femmeService.femmeLaPlusAgee();
            System.out.println("Femme la plus âgée : " + femmePlusAgee +
                    " (Née le " + sdf.format(femmePlusAgee.getDateNaissance()) + ")");
            System.out.println();

            System.out.println("=== TEST 3: AFFICHER LES ÉPOUSES D'UN HOMME DONNÉ ENTRE DEUX DATES ===\n");
            List<Mariage> mariages = hommeService.afficherEpousesEntreDates(
                    homme1.getId(),
                    sdf.parse("01/01/1990"),
                    sdf.parse("31/12/2010")
            );
            System.out.println("Épouses de " + homme1 + " entre 1990 et 2010:");
            for (Mariage m : mariages) {
                System.out.println("- " + m.getFemme() + " (Début: " + sdf.format(m.getDateDebut()) + ")");
            }
            System.out.println();

            System.out.println("=== TEST 4: AFFICHER LE NOMBRE D'ENFANTS D'UNE FEMME ENTRE DEUX DATES ===\n");
            Long nbrEnfants = femmeService.nombreEnfantsFemmeEntreDates(
                    femme1.getId(),
                    sdf.parse("01/01/1990"),
                    sdf.parse("31/12/2000")
            );
            System.out.println("Nombre d'enfants de " + femme1 + " entre 1990 et 2000: " + nbrEnfants);
            System.out.println();

            System.out.println("=== TEST 5: AFFICHER LES FEMMES MARIÉES AU MOINS DEUX FOIS ===\n");
            List<Femme> femmesMarieesPlusieursFois = femmeService.femmesMarieesAuMoinsDeuxFois();
            System.out.println("Femmes mariées au moins deux fois:");
            for (Femme f : femmesMarieesPlusieursFois) {
                System.out.println("- " + f);
            }
            System.out.println();

            System.out.println("=== TEST 6: HOMMES MARIÉS À QUATRE FEMMES ENTRE DEUX DATES ===\n");
            Long nbrHommes = femmeService.nombreHommesMariesAQuatreFemmesEntreDates(
                    sdf.parse("01/01/1980"),
                    sdf.parse("31/12/2020")
            );
            System.out.println("Nombre d'hommes mariés à 4 femmes entre 1980 et 2020: " + nbrHommes);
            System.out.println();

            System.out.println("=== TEST 7: AFFICHER LES MARIAGES D'UN HOMME AVEC TOUS LES DÉTAILS ===\n");
            List<Object[]> details = hommeService.afficherMariagesAvecDetails(homme1.getId());
            System.out.println("Nom : " + homme1);
            System.out.println("Mariages En Cours :");
            int i = 1;
            for (Object[] detail : details) {
                Date dateDebut = (Date) detail[0];
                Date dateFin = (Date) detail[1];
                Integer nbrEnfantsMariage = (Integer) detail[2];
                String nom = (String) detail[3];
                String prenom = (String) detail[4];

                if (dateFin == null) {
                    System.out.println(i + ". Femme : " + prenom + " " + nom +
                            "   Date Début : " + sdf.format(dateDebut) +
                            "    Nbr Enfants : " + nbrEnfantsMariage);
                    i++;
                }
            }

            System.out.println("\nMariages échoués :");
            i = 1;
            for (Object[] detail : details) {
                Date dateDebut = (Date) detail[0];
                Date dateFin = (Date) detail[1];
                Integer nbrEnfantsMariage = (Integer) detail[2];
                String nom = (String) detail[3];
                String prenom = (String) detail[4];

                if (dateFin != null) {
                    System.out.println(i + ". Femme : " + prenom + " " + nom +
                            "  Date Début : " + sdf.format(dateDebut) +
                            "    Date Fin : " + sdf.format(dateFin) +
                            "    Nbr Enfants : " + nbrEnfantsMariage);
                    i++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.close();
        }
    }
}

