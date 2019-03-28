package testPackage;
import static org.junit.Assert.*;

import controller.Bank;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class UnitTest {
    private Bank banca = new Bank();
    private Person p1 = new Person(1, "Marin", "1910508257841", "Str. Campina", 27);
    private Person p2 =new Person(2, "Carmen", "2950204284125", "Str. Bucegi", 23);

    @Before
    public void setUp(){
        banca.bank.put(p1, new ArrayList<Account>());
        banca.bank.put(p2, new ArrayList<Account>());
        banca.bank.get(p1).add(new SavingAccount(1, 1, 2500.0, 24, 0.015));
        banca.bank.get(p1).add(new SavingAccount(2, 1, 3400.0, 10, 0.02));
        banca.bank.get(p2).add(new SpendingAccount(3, 2, 2500.0));
    }

    @Test
    public void testAdaugarePersoana(){
        banca.addPerson(new Person(3, "Gica", "1791012872930", "Str. Viorilor", 38));
        Set set = banca.bank.keySet();
        Person per = new Person();
        Iterator<Person> iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = iterator.next();
            if(p.getIdPerson().equals(3)){
                per = p;
            }
        }
        assertTrue(per.getName().equals("Gica"));
    }

    @Test
    public void testParcurgere(){
        Integer ok = 0;
        ArrayList<Account> accounts = banca.bank.get(p1);
        for (Account a:
                accounts) {
            if(a.getAmount().equals(2500.0))
                ok = 1;
        }
        assertTrue(ok == 1);
    }

    @Test
    public void testInterest(){
        banca.bank.get(p1).add(new SavingAccount(4, 1, 3400.0, 2, 0.02));
        Integer ok = 0;
        ArrayList<Account> accounts = banca.bank.get(p1);
        for (Account a:
                accounts) {
            if(a.getIdAccount().equals(4)) {
                ((SavingAccount)a).addInterest();
                if(a.getAmount().equals(3537.36))
                    ok = 1;
            }
        }
        assertTrue(ok == 1);
    }

    @Test
    public void testMoreWithdraw() {
        Integer ok = 0;
        ArrayList<Account> accounts = banca.bank.get(p1);
        for (Account a :
                accounts) {
            if (a.getIdAccount().equals(1)) {
                ((SavingAccount) a).withdraw(100.0);
                ((SavingAccount) a).withdraw(100.0);
                if (a.getAmount().equals(2400.0))
                    ok = 1;
            }
        }
        assertTrue(ok == 1);
    }

    @Test
    public void testAdd() {
        Integer ok = 0;
        ArrayList<Account> accounts = banca.bank.get(p2);
        for (Account a :
                accounts) {
            if (a.getIdAccount().equals(3)) {
                ((SpendingAccount) a).add(100.0);
                ((SpendingAccount) a).add(150.0);
                if (a.getAmount().equals(2750.0))
                    ok = 1;
            }

        }
        assertTrue(ok == 1);
    }

    @Test
    public void testAddAccount() {
        Integer ok = 0;
        banca.addHolderAssociatedAccounts(2, new SpendingAccount(5, 2, 4870.2));
        Set set = banca.bank.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            if(p.getIdPerson().equals(2)){
                ArrayList<Account> accounts = banca.bank.get(p2);
                for (Account a :
                        accounts) {
                    if (a.getIdAccount().equals(5)) {
                        ok = 1;
                    }
                }
            }
        }
        assertTrue(ok == 1);
    }

    @After
    public void tearDown(){
        banca.bank.clear();
    }
}
