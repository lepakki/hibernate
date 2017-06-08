/**
 *
 * @author Capitano
 */

public class Pankkitili {

    private int koodi;
    private float saldo;
    static float pankkisaldo;


    public Pankkitili() {
    }

    public Pankkitili(int koodi, float saldo) {
        this.koodi = koodi;
        this.saldo = saldo;
        pt[i] = this;
        i++;
    }

    public int getKoodi() {
        return koodi;
    }

    public void setKoodi(int koodi) {
        this.koodi = koodi;
    }

    public float getSaldo() {
        return saldo + pankkisaldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    

}