package controller;

import model.*;
import model.Observer;
import model.Person;

import java.io.*;
import java.util.*;

public class Bank implements BankProc, Subject {

    public Hashtable<Person, ArrayList<Account>> bank;
    public List<Observer> observers = new ArrayList<Observer>();

    public Bank() {
        this.bank = new Hashtable<Person, ArrayList<Account>>();
    }

    @Override
    public void addPerson(Person person) {
        assert(person != null): "Persoana este nula";
        bank.put(person, new ArrayList<Account>());
    }

    @Override
    public void removePerson(Integer idPerson) {
        assert(idPerson != null): "Id-ul este invalid";
        Set set = bank.keySet();
        Person deletePerson = new Person();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            if(p.getIdPerson().equals(idPerson)){
                deletePerson = p;
            }
        }
        bank.remove(deletePerson);
        assert(!deletePerson.equals(new Person())): "Persoana nu se afla in lista";
    }

    @Override
    public void addHolderAssociatedAccounts(Integer idPerson, Account account) {
        assert(idPerson != null): "Id-ul este invalid";
        Set set = bank.keySet();
        Person selectedPerson = new Person();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            if(p.getIdPerson().equals(idPerson)){
                selectedPerson = p;
            }
        }
        bank.get(selectedPerson).add(account);
    }

    @Override
    public void removeHolderAssociatedAccounts(Integer idPerson) {
        assert(idPerson != null): "Id-ul este invalid";
        Set set = bank.keySet();
        Person selectedPerson = new Person();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            if(p.getIdPerson().equals(idPerson)){
                selectedPerson = p;
            }
        }
        bank.replace(selectedPerson, new ArrayList<Account>());
    }

    public void printAccount(Integer idPerson){
        Set set = bank.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            if(p.getIdPerson().equals(idPerson)){
                ArrayList<Account> accounts = bank.get(p);
                System.out.println(accounts.toString());
            }
        }
    }

    public void printBankData(){
        Set set = bank.keySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            System.out.println(p.toString());
            ArrayList<Account> accounts = bank.get(p);
            for(Account i : accounts) {
                System.out.println(i.toString());
            }
            System.out.println();
        }
    }

    @Override
    public void readAccountsData() {
        try {
            FileInputStream fileIn = new FileInputStream("bank.ser");
            assert(fileIn.available() > 0 ): "Fisierul este invalid";
            ObjectInputStream in = new ObjectInputStream(fileIn);
            bank = (Hashtable<Person, ArrayList<Account>>) in.readObject();
            Set set = bank.keySet();
            Iterator<Person> iterator = set.iterator();
            while(iterator.hasNext()){
                Person p = iterator.next();
                ArrayList<Account> accounts = bank.get(p);
                for (Account i: accounts) {
                    if(i instanceof SavingAccount){
                        ((SavingAccount) i).setOkA(0);
                        ((SavingAccount) i).setOkW(0);
                    }
                }
            }

            assert(bank != null): "Citirea nu s-a realizat cu succes";

            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("controller.Bank class not found");
            c.printStackTrace();
            return;
        }
    }

    @Override
    public void writeAccountsData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("bank.ser");
            assert(fileOut != null): "Fisierul este invalid";
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(bank);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Integer idAccount) {
        for (Observer i:
                observers) {
            Person p = ((Person)i);
            ArrayList<Account> accounts = bank.get(p);
            for (Account account:
                    accounts) {
                if(account.getIdAccount().equals(idAccount))
                    i.update(idAccount);
            }
        }
    }
}
