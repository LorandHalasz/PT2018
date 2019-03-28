package Model;

import java.util.ArrayList;
import java.util.List;

public class Polinom {
    public List<Monom> monoame;

    public Polinom(List<Monom> monoame) {
        this.monoame = monoame;
    }

    public Polinom(){
        this.monoame = new ArrayList<Monom>();
    }

    public List<Monom> getPolinom() {
        return monoame;
    }

    public void setPolinom(ArrayList<Monom> monoame) {
        this.monoame = monoame;
    }
}



