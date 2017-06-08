/**
 *
 * @author Capitano
 */

import javax.persistence.*;
import java.util.*;
import java.sql.Timestamp;
import java.util.Date;

public class Tilitapahtumat {

    private int koodi;
    private Timestamp aika;
    private String kategoria;
    private float arvo;
    static Tilitapahtumat[] tt = new Tilitapahtumat[500];
    static int i = 1;

    public Tilitapahtumat() {
    }

    public Tilitapahtumat(int koodi, Timestamp aika, String kategoria, float arvo) {
        this.koodi = koodi;
        this.aika = aika;
        this.kategoria = kategoria;
        this.arvo = arvo;
        tt[i] = this;
        i++;
    }

    public int getKoodi() {
        return koodi;
    }

    public void setKoodi(int koodi) {
        this.koodi = koodi;
    }

    public Timestamp getAika() {
        java.util.Date date = new java.util.Date();
        aika = new Timestamp(date.getTime());
        return aika;

    }

    public void setAika(Timestamp aika) {
        this.aika = aika;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public float getArvo() {
        return arvo;
    }

    public void setArvo(float arvo) {
        this.arvo = arvo;
    }

    @Override
    public String toString() {
        return "Poistettu = koodi:" + koodi + " aika:" + aika + " kategoria:" + kategoria + " summa:" + arvo;
    }
}
