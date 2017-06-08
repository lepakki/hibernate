/**
 *
 * @author Capitano
 */

public class Lompakko {
    
    private int koodi;
    private float saldo;
    static float lompsaldo; 

    public Lompakko() {
    }

    public Lompakko(int koodi, float saldo) {
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
        return saldo+lompsaldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }


}
