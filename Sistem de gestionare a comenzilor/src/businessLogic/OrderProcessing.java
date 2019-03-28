package businessLogic;

import dataAccess.BazaDeDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.Product;

import java.sql.Date;

/**
 *  Clasa pentru procesarea comenzilor
 */
public class OrderProcessing {

    public String[] rez;
    public String[] facturi;
    private Integer nrComenzi;
    private Integer nrFacturi;
    private Integer ok = 0;

    /**
     * Constructorul clasei
     */
    public OrderProcessing() {
        this.rez = new String[1000];
        this.facturi = new String[1000];
        this.nrComenzi = 0;
        this.nrFacturi = 0;
    }

    /**
     * getter pentru numarul de comenzi
     * @return numarul comenzii
     */
    public Integer getNrComenzi() {
        return nrComenzi;
    }

    /**
     * getter pentru numarul de facturi
     * @return numarul comenzii
     */
    public Integer getNrFacturi() {
        return nrFacturi;
    }

    /**
     * Functia returneaza un ObservableList cu continutul din tabelul Orders
     * @return o lista de Comenzi
     */
    public ObservableList<Order> populareTabelaOrder(BazaDeDate bazaDeDate) throws Exception {
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
        String statement = "Select * from orders";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()){
            orderObservableList.add(new Order(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getInt(2),bazaDeDate.rs.getDate(3),bazaDeDate.rs.getInt(4)));
        }
        return orderObservableList;
    }

    /**
     * Permite procesarea unei noi comenzi
     * @param idCustomer id-ul clientului care face comanda
     * @param idProduct id-ul produsului pe care il comanda
     * @param date data in care se face comanda
     * @param quantity cantitatea de produse comandata
     * @return o lista de Comenzi
     */
    public ObservableList<Product> addNewOrder(BazaDeDate bazaDeDate, Integer idCustomer, Integer idProduct, Date date, Integer quantity) throws Exception{
        String statement = "Select * from customer";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()) if(bazaDeDate.rs.getInt(1) == idCustomer) ok = 1;
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        statement = "Select * from product";
        bazaDeDate.st = bazaDeDate.connect.createStatement();
        bazaDeDate.rs = bazaDeDate.st.executeQuery(statement);
        while(bazaDeDate.rs.next()) productObservableList.add(new Product(bazaDeDate.rs.getInt(1),bazaDeDate.rs.getString(2),bazaDeDate.rs.getString(3),bazaDeDate.rs.getString(4),bazaDeDate.rs.getString(5),bazaDeDate.rs.getInt(6),bazaDeDate.rs.getInt(7)));
        if(ok == 1){
            for (Product i : productObservableList)
                if (i.getIdProduct() == idProduct)
                    if (i.getQuantity() >= quantity) {
                        i.setQuantity(i.getQuantity() - quantity);
                        rez[++nrComenzi] = "Comanda " + nrComenzi + ": Clientul cu id-ul " + idCustomer + " a comandat in data de " + date + ", " + quantity + " produs/e cu id-ul " + idProduct + "\n\n";
                        facturi[++nrFacturi] = "Comanda " + nrFacturi + ": Clientul cu id-ul " + idCustomer + " a comandat in data de " + date + ", " + quantity + " produs/e cu id-ul " + idProduct + "\n\n";
                        statement = "INSERT INTO orders VALUES (" + idCustomer + ", " + idProduct + ", '" + date + "', " + quantity + ")";
                        bazaDeDate.st = bazaDeDate.connect.createStatement();
                        bazaDeDate.st.executeUpdate(statement);
                        statement = "UPDATE product SET quantity = " + i.getQuantity() + " WHERE idProduct = " + idProduct;
                        bazaDeDate.st = bazaDeDate.connect.createStatement();
                        bazaDeDate.st.executeUpdate(statement);
                    } else
                        rez[++nrComenzi] = "Comanda " + nrComenzi + ", efectuata de clientul cu id-ul " + idCustomer + " nu s-a putut efectua, din cauza stocului prea mic\n";
        }
        else
            rez[++nrComenzi] = "Comanda " + nrComenzi + ", nu poate fi efectuata deoarece nu exista clientul cu id-ul " + idCustomer + "\n";
        return productObservableList;
    }
}


