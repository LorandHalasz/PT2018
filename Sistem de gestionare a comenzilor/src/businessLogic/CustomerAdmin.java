package businessLogic;

import dataAccess.BazaDeDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.SQLException;

/**
 *  Clasa pentru administrarea clientilor
 */
public class CustomerAdmin {

    /**
     * Functia returneaza un ObservableList cu continutul din tabelul Customer
     * @return o lista de Clienti
     */
    public ObservableList<Customer> populareTabelaCustomer(BazaDeDate bazaDeDate) throws Exception {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String statement = "Select * from customer";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            customerObservableList.add(new Customer(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getString(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getString(4),bazaDeDate.rs.getInt(5)));
        }
        return customerObservableList;
    }

    /**
     * Adauga un nou Client in tabelul Customer
     * @param bazaDeDate
     * @param idCustomer id-ul noului client
     * @param firstName prenumele noului client
     * @param lastName numele de familie al noului client
     * @param address adresa noului client
     * @param age varsta noului client
     * @throws Exception
     */
    public void addNewCustomer(BazaDeDate bazaDeDate, Integer idCustomer, String firstName, String lastName, String address, Integer age) throws Exception {

        String statement = "INSERT INTO customer VALUES (" + idCustomer + ", '" + firstName + "', '" +lastName + "', '" + address + "', " + age + ")";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

    /**
     * Sterge un client din tabelul Customer
     * @param bazaDeDate
     * @param idCustomer id-ul clientului pe care dorim sa il stergem
     * @throws Exception
     */
    public void deleteCustomer(BazaDeDate bazaDeDate, Integer idCustomer) throws Exception {

        String statement = "DELETE FROM customer WHERE idCustomer = " + idCustomer;
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

    /**
     * Permite editarea continutului tabelului Customer
     * @param bazaDeDate
     * @param idCustomer id-ul clientului pe care dorim sa il modificam
     * @param firstName noul prenume
     * @param lastName noul nume de familie
     * @param address noua adresa
     * @param age noua varsta
     */
    public void editCustomer(BazaDeDate bazaDeDate, Integer idCustomer, String firstName, String lastName, String address, Integer age){
        Integer fOk = 0, lOk = 0, adOk = 0, agOk = 0;
        if(firstName != "") fOk = 1;
        if(lastName != "") lOk = 1;
        if(address != "") adOk = 1;
        if(age != 0) agOk = 1;
        try {
            bazaDeDate.st = bazaDeDate.connect.createStatement();
            if(fOk == 1) {
                String statement1 = "UPDATE customer SET firstName = '" + firstName + "' WHERE idCustomer = " + idCustomer;
                bazaDeDate.st.executeUpdate(statement1);
            }
            if(lOk == 1) {
                String statement2 = "UPDATE customer SET lastName = '" + lastName + "' WHERE idCustomer = " + idCustomer;
                bazaDeDate.st.executeUpdate(statement2);
            }
            if(adOk == 1) {
                String statement3 = "UPDATE customer SET address = '" + address + "' WHERE idCustomer = " + idCustomer;
                bazaDeDate.st.executeUpdate(statement3);
            }
            if(agOk == 1) {
                String statement4 = "UPDATE customer SET age = " + age + " WHERE idCustomer = " + idCustomer;
                bazaDeDate.st.executeUpdate(statement4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite afisarea continului din tabelul Customer utilizandu-se un filtru
     * @param bazaDeDate
     * @param age reprezinta varsta dupa care se doreste sa se faca filtrarea
     * @return o lista de clienti
     * @throws SQLException
     */
    public ObservableList<Customer> addFilterCustomer(BazaDeDate bazaDeDate, Integer age) throws SQLException {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String statement = "Select * from customer WHERE age <= " + age;
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            customerObservableList.add(new Customer(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getString(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getString(4),bazaDeDate.rs.getInt(5)));
        }
        return customerObservableList;
    }

}

