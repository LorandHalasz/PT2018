package controller;

import model.Account;
import model.Person;

public interface BankProc {

    /**
     * @pre persoana sa nu fie nula
     */
    void addPerson(Person person);

    /**
     * @pre id-ul persoanei care se doreste a fi stearsa trebuie sa fie valid
     * @post persoana care se doreste a fi stearsa trebuie sa se afle in lista
     */
    void removePerson(Integer idPerson);

    /**
     * @pre id-ul personanei trebuie sa fie valid
     */
    void addHolderAssociatedAccounts(Integer idPerson, Account account);

    /**
     * @pre id-ul personanei trebuie sa fie valid
     */
    void removeHolderAssociatedAccounts(Integer idPerson);

    /**
     * @pre fisierul trebuie sa fie valid
     * @post in cazul in care citirea nu se realizeaza in mod corect
     */
    void readAccountsData();

    /**
     * @pre fisierul trebuie sa fie valid
     */
    void writeAccountsData();
}
