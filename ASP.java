/**
 *
 * @author Capitano
 */

public class ASP {
    
    private int koodi;
    private float saldo;
    static float aspsaldo; 

    public ASP() {
    }

    public ASP(int koodi, float saldo) {
        this.koodi = koodi;
        this.saldo = saldo;
    }

    public int getKoodi() {
        return koodi;
    }

    public void setKoodi(int koodi) {
        this.koodi = koodi;
    }

    public float getSaldo() {
        return saldo+aspsaldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


}
