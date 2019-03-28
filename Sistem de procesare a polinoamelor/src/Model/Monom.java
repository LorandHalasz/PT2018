package Model;

import java.lang.*;

public class Monom implements Comparable<Monom>{
    private Double exponent;
    private Double coeficient;

    public Monom(double exponent, double coeficient) {
        this.exponent = exponent;
        this.coeficient = coeficient;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    @Override
    public int compareTo(Monom o) {
        Integer a = this.exponent.intValue();
        Integer b = (int)o.getExponent();
        return b.compareTo(a);
    }
}


