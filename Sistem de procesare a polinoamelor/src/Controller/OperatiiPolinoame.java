package Controller;
import Model.*;
import java.util.*;

public class OperatiiPolinoame {

    private static double calcNum(String polinom, int i) {          // metoda calcNum returneaza coeficientul monomului
        double numar = 0;                                           // convertit din String
        int ind = 1;
        Character c = polinom.charAt(i);
        while(Character.isDigit(c)) {
            numar = numar + (c - '0') * ind;
            c = polinom.charAt(--i);
            ind *= 10;
        }
        return numar;
    }

    private static int calcInd(String polinom, int i) {             // metoda calcInd returneaza pozitia pe care se afla
        Character c = polinom.charAt(i);                            // primul caracter de dinainte de prima cifra a coeficientului
        while(Character.isDigit(c)) {
            c = polinom.charAt(--i);
        }
        return i;
    }

    private static Monom detTermen(String polinom, int i) {
        Monom monom = new Monom(0, 1);
        double nr = 0, nr1 = 0;
        Character c = polinom.charAt(i);
        if (Character.isLetter(c)) {                            // Daca cel mai din dreapta caracter nu este o cifra atunci inseamana ca
            monom.setExponent(1);                               // primul element este x la puterea 1 si astfel facem calculele necesare
            c = polinom.charAt(--i);                            // ne mutam la stanga cu o pozitie
            if (Character.isDigit(c)) {                         // daca inaintea lui x^1 avem o cifra atunci aceea va fi coeficientul lui x^1
                monom.setCoeficient(calcNum(polinom, i));       // formam coeficientul
                i = calcInd(polinom, i);                        // actualizam indicele
                c = polinom.charAt(i);
            }
            if(c == '-') monom.setCoeficient(monom.getCoeficient() * (-1));
        }
        else {                                                  // in caz contrar inseamna ca monomul este ori x la o putere
            nr1 = nr = calcNum(polinom, i);                     // ori x^0
            i = calcInd(polinom, i);
            c = polinom.charAt(i);
            if (c == '^') {
                monom.setExponent(nr);
                nr1 = calcNum(polinom, i-2);
                i = calcInd(polinom, i-2);
                c = polinom.charAt(i);
                if(nr1 == 0) nr1 = 1;
            }
            if(c == '-') nr1 *= -1;
            monom.setCoeficient(nr1);
        }
        return monom;
    }

    private static int actualizareIndice(String polinom, int i) {
        Character c = polinom.charAt(i);
        if (Character.isLetter(c)) {
            c = polinom.charAt(--i);
            if (Character.isDigit(c))
                i = calcInd(polinom, i);
            i--;
        }
        else {
            i = calcInd(polinom, i);
            c = polinom.charAt(i);
            if (c == '^') {
                i -= 2;
                i = calcInd(polinom, i);
            }
            i--;
        }
        return i;
    }

    public static List<Monom> formarePolinom(String polinom) {
        List<Monom> monoame = new ArrayList<Monom>();
        polinom = "+" + polinom;
        for (int i = polinom.length() - 1; i >= 0; i--) {
            monoame.add(detTermen(polinom, i));
            i = actualizareIndice(polinom, i) + 1;
        }
        for (int i = 0; i < monoame.size() - 1; i++)
            for (int j = i + 1; j < monoame.size(); j++)
                if (monoame.get(i).getExponent() == monoame.get(j).getExponent()) {
                    monoame.get(i).setCoeficient(monoame.get(i).getCoeficient() + monoame.get(j).getCoeficient());
                    monoame.get(j).setCoeficient(0);
                }
        Iterator<Monom> it = monoame.iterator();
        while(it.hasNext())
            if(it.next().getCoeficient() == 0) it.remove();
        monoame.sort(new MyComparator());
        return monoame;
    }

    private static double insumare(Monom m1, Polinom p, int op){
        double numar = m1.getCoeficient();
        Iterator<Monom> it2 = p.monoame.iterator();
        while(it2.hasNext()){
            Monom m2 = it2.next();
            if(m1.getExponent() == m2.getExponent()){
                numar += m2.getCoeficient();
                if(op == 1) {
                    m2.setCoeficient(numar);
                    numar = 0;
                }
            }
        }
        return numar;
    }

    private static Boolean verificareTermeni(Monom m1, Polinom p){
        Iterator<Monom> it2 = p.monoame.iterator();
        while(it2.hasNext()){
            Monom m2 = it2.next();
            if(m1.getExponent() == m2.getExponent())
                return false;
        }
        return true;
    }

    public static Polinom adunarePolinoame(Polinom P1, Polinom P2){
        Iterator<Monom> it = P1.monoame.iterator();
        int i = 0;
        Monom m;
        Polinom rez = new Polinom();
        while(it.hasNext()) {
            m = it.next();
            Monom mon = new Monom(m.getExponent(), insumare(m, P2, 0));
            rez.monoame.add(i++, mon);
        }
        it = P2.monoame.iterator();
        while(it.hasNext()) {
            m = it.next();
            if(verificareTermeni(m, P1)){
                rez.monoame.add(i++, m);
            }
        }
        return rez;
    }

    public static Polinom scaderePolinoame(Polinom P1, Polinom P2) {
        Polinom rez = new Polinom();
        for(Monom i: P2.monoame)
            i.setCoeficient(-1 * i.getCoeficient());
        rez = adunarePolinoame(P1, P2);
        return rez;
    }

    public static Polinom inmultirePolinoame(Polinom p1, Polinom p2){
        Polinom rez = new Polinom();
        double coef = 0;
        Monom m;
        for (Monom i: p1.monoame)
            for (Monom j: p2.monoame)
                if(i.getCoeficient() != 0 && j.getCoeficient() != 0) {
                    m = new Monom(i.getExponent() + j.getExponent(), i.getCoeficient() * j.getCoeficient());
                    coef = insumare(m, rez, 1);
                    if(coef != 0){
                        m.setCoeficient(coef);
                        rez.monoame.add(m);
                    }
                }
        return rez;
    }

    public static Polinom impartirePolinoame(Polinom P1, Polinom P2, Polinom cat){
        List<Monom> list = new ArrayList<Monom>();
        list.add(new Monom(0,0));
        if(P2.monoame.get(0).getExponent() != 0)
            while(P1.monoame.size() != 0 && P2.monoame.size() != 0 && P1.monoame.get(0).getExponent() >= P2.monoame.get(0).getExponent()) {
                double exp = P1.monoame.get(0).getExponent() - P2.monoame.get(0).getExponent();
                double coef = P1.monoame.get(0).getCoeficient() / P2.monoame.get(0).getCoeficient();
                Monom mon = new Monom(exp, coef);
                cat.monoame.add(mon);
                list.set(0, mon);
                Polinom imp = new Polinom(inmultirePolinoame(new Polinom(list), P2).monoame);
                imp.monoame.sort(new MyComparator());
                P1 = scaderePolinoame(P1, imp);
                Iterator<Monom> it = P1.monoame.iterator();
                while(it.hasNext()) {
                    Monom m = it.next();
                    if(m.getCoeficient() == 0) it.remove();
                }
                P1.monoame.sort(new MyComparator());
            }
        else {
            Iterator<Monom> it = P1.monoame.iterator();
            while(it.hasNext()) {
                Monom m = it.next();
                cat.monoame.add(new Monom(m.getExponent(), m.getCoeficient() / P2.monoame.get(0).getCoeficient()));
            }
            P1.monoame.clear();
        }
        return P1;
    }

    public static void derivarePolinom(Polinom p){
        for (Monom i: p.monoame) {
            i.setCoeficient(i.getCoeficient() * i.getExponent());
            i.setExponent(i.getExponent() - 1);
        }
    }

    public static void integrarePolinom(Polinom p){
        for (Monom i: p.monoame) {
            i.setExponent(i.getExponent() + 1);
            i.setCoeficient(i.getCoeficient() / i.getExponent());
        }
    }

    private static String parsareMonom(Monom m) {
        String rezultat = "";
        if(m.getExponent() > 1) {
            if (m.getCoeficient() == -1) rezultat = "-x^" + String.format("%.0f", m.getExponent());
            else if (m.getCoeficient() == 1) rezultat = "x^" + String.format("%.0f", m.getExponent());
            else if (m.getCoeficient() != 0) rezultat = String.format("%.0f", m.getCoeficient()) +"x^" + String.format("%.0f", m.getExponent());
        }
        else
        if(m.getExponent() == 1) {
            if (m.getCoeficient() == -1) rezultat = "-x";
            else if (m.getCoeficient() == 1) rezultat = "x";
            else if (m.getCoeficient() != 0) rezultat = String.format("%.0fx", m.getCoeficient());
        }
        else
        if (m.getCoeficient() != 0) rezultat = String.format("%.0f", m.getCoeficient());
        return rezultat;
    }

    private static String parsareMonomCuCoefD(Monom m){
        String rezultat = "";
        if(m.getExponent() > 1) {
            if (m.getCoeficient() == -1) rezultat = String.format("-x^%.0f", m.getExponent());
            else if (m.getCoeficient() == 1) rezultat = String.format("x^%.0f", m.getExponent());
            else if (m.getCoeficient() != 0) {
                if (m.getCoeficient() == (int)(m.getCoeficient())) rezultat = String.format("%.0fx^%.0f", m.getCoeficient(), m.getExponent());
                else rezultat = String.format("%fx^%.0f", m.getCoeficient(), m.getExponent());
            }
        }
        else
        if(m.getExponent() == 1) {
            if (m.getCoeficient() == -1) rezultat = "-x";
            else if (m.getCoeficient() == 1) rezultat = "x";
            else if (m.getCoeficient() != 0) {
                if (m.getCoeficient() == (int)(m.getCoeficient())) rezultat = String.format("%.0fx", m.getCoeficient());
                else rezultat = String.format("%fx", m.getCoeficient());
            }
        }
        else
        if (m.getCoeficient() != 0) {
            if (m.getCoeficient() == (int)(m.getCoeficient())) rezultat = String.format("%.0f", m.getCoeficient());
            else rezultat = String.format("%f", m.getCoeficient());
        }
        return rezultat;
    }

    public static String parsarePolinom(Polinom p){
        String rezultat = "";
        p.monoame.sort(new MyComparator());
        Iterator<Monom> it = p.monoame.iterator();
        try{
            Monom m = it.next();
            rezultat += parsareMonom(m);
            while(it.hasNext()) {
                m = it.next();
                if (m.getCoeficient() > 0)
                    rezultat += "+";
                rezultat += parsareMonom(m);
            }
        }catch(Exception e){
            rezultat = "0";
        }
        if(rezultat.equals("") ) rezultat = "0";
        return rezultat;
    }

    public static String parsarePolinomCuCoefD(Polinom p){
        p.monoame.sort(new MyComparator());
        Iterator<Monom> it = p.monoame.iterator();
        String rezultat = "";
        try{
            Monom m = it.next();
            rezultat += parsareMonomCuCoefD(m);
            while(it.hasNext()){
                m = it.next();
                if(m.getCoeficient() > 0)
                   rezultat += "+";
                rezultat += parsareMonomCuCoefD(m);
            }
        }catch (Exception exception){
            rezultat = "0";
        }
        if(rezultat == "") rezultat = "0";
        return rezultat;
    }
}