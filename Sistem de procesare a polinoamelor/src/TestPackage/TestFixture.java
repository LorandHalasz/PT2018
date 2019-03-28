package TestPackage;
import static org.junit.Assert.*;
import Controller.*;
import Model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFixture {

    private Polinom P1, P2;
    private static int i = 0;

    @Before
    public void setUp(){
        P1 = new Polinom(OperatiiPolinoame.formarePolinom("x^2+x+1"));
        P2 = new Polinom(OperatiiPolinoame.formarePolinom("x-1"));
        System.out.println("Test " + ++i + ", setUp P1: " + OperatiiPolinoame.parsarePolinom(P1) + ", P2: " + OperatiiPolinoame.parsarePolinom(P2));
    }

    @Test
    public void testAdunare(){
        Polinom rezultatPropZis = OperatiiPolinoame.adunarePolinoame(P1, P2);
        Polinom rezultatAsteptat = new Polinom(OperatiiPolinoame.formarePolinom("x^2+2x"));
        assertTrue(OperatiiPolinoame.parsarePolinom(rezultatPropZis).equals(OperatiiPolinoame.parsarePolinom(rezultatAsteptat)));
    }

    @Test
    public void testScadere(){
        Polinom rezultatPropZis = OperatiiPolinoame.scaderePolinoame(P1, P2);
        Polinom rezultatAsteptat = new Polinom(OperatiiPolinoame.formarePolinom("x^2+2"));
        assertTrue(OperatiiPolinoame.parsarePolinom(rezultatPropZis).equals(OperatiiPolinoame.parsarePolinom(rezultatAsteptat)));
    }

    @Test
    public void testInmultire(){
        Polinom rezultatPropZis = OperatiiPolinoame.inmultirePolinoame(P1, P2);
        Polinom rezultatAsteptat = new Polinom(OperatiiPolinoame.formarePolinom("x^3-1"));
        assertTrue(OperatiiPolinoame.parsarePolinom(rezultatPropZis).equals(OperatiiPolinoame.parsarePolinom(rezultatAsteptat)));
    }

    @Test
    public void testImpartire(){
        Polinom cat = new Polinom();
        Polinom rest;
        rest = OperatiiPolinoame.impartirePolinoame(P1, P2, cat);
        Polinom catAst = new Polinom(OperatiiPolinoame.formarePolinom("x+2"));
        Polinom restAst = new Polinom(OperatiiPolinoame.formarePolinom("3"));
        assertTrue(OperatiiPolinoame.parsarePolinom(cat).equals(OperatiiPolinoame.parsarePolinom(catAst)) || OperatiiPolinoame.parsarePolinom(rest).equals(OperatiiPolinoame.parsarePolinom(restAst)));
    }

    @Test
    public void testDerivare(){
        OperatiiPolinoame.derivarePolinom(P1);
        OperatiiPolinoame.derivarePolinom(P2);
        Polinom rezultatAsteptat1 = new Polinom(OperatiiPolinoame.formarePolinom("2x+1"));
        Polinom rezultatAsteptat2 = new Polinom(OperatiiPolinoame.formarePolinom("1"));
        assertTrue(OperatiiPolinoame.parsarePolinom(P1).equals(OperatiiPolinoame.parsarePolinom(rezultatAsteptat1)) && OperatiiPolinoame.parsarePolinom(P2).equals(OperatiiPolinoame.parsarePolinom(rezultatAsteptat2)));
    }

    @Test
    public void testIntegrare(){
        OperatiiPolinoame.integrarePolinom(P1);
        OperatiiPolinoame.integrarePolinom(P2);
        assertTrue(OperatiiPolinoame.parsarePolinomCuCoefD(P1).equals("0.333333x^3+0.500000x^2+x") && OperatiiPolinoame.parsarePolinomCuCoefD(P2).equals("0.500000x^2-x"));
    }

    @After
    public void tearDown(){
        P1.monoame.clear();
        P2.monoame.clear();
        System.out.println("tearDown: size of P1: " + P1.monoame.size() + ", size of P2: " + P2.monoame.size());
    }
}
