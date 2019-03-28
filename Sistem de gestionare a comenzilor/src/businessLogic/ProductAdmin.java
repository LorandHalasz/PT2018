package businessLogic;

import dataAccess.BazaDeDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;

import java.sql.SQLException;

/**
 * Clasa pentru administrarea produselor
 */
public class ProductAdmin {

    /**
     * Functia returneaza un ObservableList cu continutul din tabelul Product
     * @return o lista de produse
     */
    public ObservableList<Product> populareTabelaProduct(BazaDeDate bazaDeDate) throws Exception {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        String statement = "Select * from product";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            productObservableList.add(new Product(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getString(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getString(4),bazaDeDate.rs.getString(5),bazaDeDate.rs.getInt(6),bazaDeDate.rs.getInt(7)));
        }
        return productObservableList;
    }

    /**
     * Adauga un nou produs in tabelul Product
     * @param idProduct id-ul noului produs
     * @param type tipul noului produs
     * @param name numele noului produs
     * @param company compania care fabrica noul produs
     * @param description descrierea noului produs
     * @param price pretul noului produs
     * @param quantity cantitatea disponibila
     */
    public void addNewProduct(BazaDeDate bazaDeDate, Integer idProduct, String type, String name, String company, String description, Integer price, Integer quantity) throws Exception {

        String statement = "INSERT INTO product VALUES (" + idProduct + ", '" + type + "', '" +name + "', '" + company + "', '" + description + "', " + price + ", " + quantity + ")";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

    /**
     * Sterge un produs din tabelul Product
     * @param idProduct id-ul produsului pe care dorim sa il stergem
     */
    public void deleteProduct(BazaDeDate bazaDeDate, Integer idProduct) throws Exception {

        String statement = "DELETE FROM product WHERE idProduct = " + idProduct;
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.st.executeUpdate(statement);
    }

    /**
     * Permite editarea continutului tabelului Product
     * @param idProduct id-ul produsului pe care dorim sa il modificam
     * @param type noul tip al produsului
     * @param name noul nume
     * @param company noua companie
     * @param description noua descriere
     * @param price noul pret
     * @param quantity noua cantitate
     */
    public void editProduct(BazaDeDate bazaDeDate, Integer idProduct, String type, String name, String company, String description, Integer price, Integer quantity){
        Integer tOk = 0, nOk = 0, cmpOk = 0, dOk = 0, prOk = 0, qOk = 0;
        if(type != "") tOk = 1;
        if(name != "") nOk = 1;
        if(company != "") cmpOk = 1;
        if(description != "") dOk = 1;
        if(price != 0) prOk = 1;
        if(quantity != 0) qOk = 1;
        try {
            bazaDeDate.st = bazaDeDate.connect.createStatement();
            if(tOk == 1) {
                String statement1 = "UPDATE product SET type = '" + type + "' WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement1);
            }
            if(nOk == 1) {
                String statement2 = "UPDATE product SET name = '" + name + "' WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement2);
            }
            if(cmpOk == 1) {
                String statement3 = "UPDATE product SET company = '" + company + "' WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement3);
            }
            if(dOk == 1) {
                String statement4 = "UPDATE product SET description = '" + description + "' WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement4);
            }
            if(prOk == 1) {
                String statement5 = "UPDATE product SET price = " + price + " WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement5);
            }
            if(qOk == 1) {
                String statement6 = "UPDATE product SET quantity = " + quantity + " WHERE idProduct = " + idProduct;
                bazaDeDate.st.executeUpdate(statement6);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite afisarea continului din tabelul Product utilizandu-se un filtru
     * @param price reprezinta pretul dupa care se doreste sa se faca filtrarea
     * @return o lista de produse
     */
    public ObservableList<Product> addFilterProduct(BazaDeDate bazaDeDate, Integer price) throws SQLException {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        String statement = "Select * from product WHERE price <= " + price;
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            productObservableList.add(new Product(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getString(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getString(4),bazaDeDate.rs.getString(5),bazaDeDate.rs.getInt(6),bazaDeDate.rs.getInt(7)));
        }
        return productObservableList;
    }
}

