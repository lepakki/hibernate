/**
 *
 * @author Capitano
 */

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.hibernate.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class Menu {

    static Scanner Lukija = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
    }

    static Configuration configuration = new Configuration().configure();
    static StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    static SessionFactory istuntotehdas = configuration.buildSessionFactory(builder.build());
    static Tilitapahtumat tilit = new Tilitapahtumat();
    static Pankkitili pankki = new Pankkitili();
    static ASP asp = new ASP();
    static Lompakko lompakko = new Lompakko();

    public static void Lisäys(String kategoria, float arvo, int tili) {

        Session istunto = istuntotehdas.openSession();
        Transaction transaktio = null;

        try {

            transaktio = istunto.beginTransaction();

            switch (tili) {
                case 1:
                    Tilitapahtumat tt01 = new Tilitapahtumat(0, tilit.getAika(), kategoria, arvo);
                    ASP asp = new ASP(tt01.getKoodi(), tt01.getArvo());

                    istunto.saveOrUpdate(tt01);
                    istunto.saveOrUpdate(asp);
                    break;
                case 2:
                    Tilitapahtumat tt02 = new Tilitapahtumat(0, tilit.getAika(), kategoria, arvo);
                    Lompakko lmp = new Lompakko(tt02.getKoodi(), tt02.getArvo());

                    istunto.saveOrUpdate(tt02);
                    istunto.saveOrUpdate(lmp);
                    break;
                case 3:
                    Tilitapahtumat tt03 = new Tilitapahtumat(0, tilit.getAika(), kategoria, arvo);
                    Pankkitili pnk = new Pankkitili(tt03.getKoodi(), tt03.getArvo());

                    istunto.saveOrUpdate(tt03);
                    istunto.saveOrUpdate(pnk);
                    break;
            }

            transaktio.commit();

        } catch (Exception e) {
            if (transaktio != null & transaktio.isActive()) {
                try {
                    transaktio.rollback();
                } catch (HibernateException e1) {
                    
                }
            }
            e.printStackTrace();
        } finally {
            istunto.close();
        }
    }

    public static void NaytaTapahtumat() {

        Session istunto = istuntotehdas.openSession();

        Transaction transaktio = null;

        try {

            transaktio = istunto.beginTransaction();
            SQLQuery haku = istunto.createSQLQuery("SELECT * FROM TILITAPAHTUMAT");
            List tulos = haku.list();

            Iterator itr = tulos.iterator();

            while (itr.hasNext()) {
                Object obj[] = (Object[]) itr.next();
                for (int i = 0; i < obj.length; i++) {
                    System.out.println(obj[i]);

                }
                System.out.println("#################");

            }

            transaktio.commit();
        } catch (Exception e) {
            if (transaktio != null & transaktio.isActive()) {
                try {
                    transaktio.rollback();
                } catch (HibernateException e1) {
                    
                }
            }
            e.printStackTrace();
        } finally {
            istunto.close();
        }
    }

    public static void NaytaTilit() {

        Session istunto = istuntotehdas.openSession();

        Transaction transaktio = null;

        try {

            SQLQuery haku = istunto.createSQLQuery("SHOW TABLES");
            List tulos = haku.list();

            System.out.println("Tilit : ");
            System.out.println(tulos.toString());
            System.in.read();

        } catch (Exception e) {
            if (transaktio != null & transaktio.isActive()) {
                try {
                    transaktio.rollback();
                } catch (HibernateException e1) {
                    
                }
            }
            e.printStackTrace();
        } finally {
            istunto.close();
        }
    }

    public static void Poisto() {

        Session istunto = istuntotehdas.openSession();
        Transaction transaktio = null;

        try {
            transaktio = istunto.beginTransaction();

            System.out.println("Anna poistettavan koodi : ");
            String poisto = Lukija.next();

            System.out.println(tilit.tt[Integer.parseInt(poisto)].toString());
            
            istunto.delete(tilit.tt[Integer.parseInt(poisto)]);
            tilit.tt[Integer.parseInt(poisto)] = null;

            transaktio.commit();

        } catch (Exception e) {
            if (transaktio != null & transaktio.isActive()) {
                try {
                    transaktio.rollback();
                } catch (HibernateException e1) {
                    
                }
            }
            e.printStackTrace();
        } finally {
            istunto.close();
        }
    }

    public static void mainMenu() {

        int vl;

        do {

            System.out.println("===== MENU =====");
            System.out.println("1. Näytä tilit");
            System.out.println("2. Näytä tilitapahtumat");
            System.out.println("3. Lisää tapahtuma");
            System.out.println("4. Poista tapahtuma");
            System.out.println("5. Lopeta");

            vl = Lukija.nextInt();

            switch (vl) {

                case 1:
                    NaytaTilit();
                    break;

                case 2:
                    NaytaTapahtumat();
                    break;

                case 3:
                    System.out.println("Anna kategoria : ");
                    String tt02 = Lukija.next();
                    System.out.println("Anna summa : ");
                    float tt03 = Lukija.nextFloat();
                    System.out.println("Anna tilikoodi (1 = ASP, 2 = Lompakko, 3 = Pankki) : ");
                    int tili = Lukija.nextInt();
                    if (tili > 3 || tili < 1) {
                        System.out.println("");
                    } else {

                        Lisäys(tt02, tt03, tili);
                        switch (tili) {
                            case 1:
                                asp.aspsaldo = asp.aspsaldo + tt03;
                                break;
                            case 2:
                                lompakko.lompsaldo = lompakko.lompsaldo + tt03;
                                break;
                            case 3:
                                pankki.pankkisaldo = pankki.pankkisaldo + tt03;
                                break;
                        }
                    }
                    break;

                case 4:
                    Poisto();
                    break;

                case 5:
                    System.err.println("bye");
                    System.exit(0);
                    break;

            }

        } while (vl != 5);
    }

}