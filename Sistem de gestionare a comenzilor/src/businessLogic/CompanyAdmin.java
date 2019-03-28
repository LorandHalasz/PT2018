package businessLogic;

import dataAccess.BazaDeDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Company;

/**
 *  Clasa pentru administrarea companiilor
 */
public class CompanyAdmin {

    /**
     * Functia returneaza un ObservableList cu continutul din tabelul Company
     * @return o lista de Companii
     */
    public ObservableList<Company> populareTabelaCompany(BazaDeDate bazaDeDate) throws Exception {
        ObservableList<Company> companyObservableList = FXCollections.observableArrayList();
        String statement = "Select * from company";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            companyObservableList.add(new Company(bazaDeDate.rs.getString(1),bazaDeDate.rs.getInt(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getInt(4)));
        }
        return companyObservableList;
    }

    /**
     * Adauga o noua companie in tabelul Company
     * @param companyName numele noii companii
     * @param caenCode codul caen al companiei
     * @param address adresa la care se afla compania
     * @param postalCode codul postal al companiei
     */
    public void addNewCompany(BazaDeDate bazaDeDate, String companyName, Integer caenCode, String address, Integer postalCode) throws Exception {

        String statement = "INSERT INTO company VALUES ('" + companyName + "', " + caenCode + ", '" +address + "', " + postalCode + ")";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

    /**
     * Sterge o companie din tabelul Company
     * @param companyName numele companiei pe care dorim sa o stergem
     */
    public void deleteCompany(BazaDeDate bazaDeDate, String companyName) throws Exception {

        String statement = "DELETE FROM company WHERE companyName = '" + companyName + "'";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

}

