package model;

import java.util.Date;

/**
 *  Clasa Order se foloseste pentru a stoca toate comenzile.
 *  Clasa contine un constructor iar pentru fiecare atribut al clasei, un getter si un setter
 */
public class Order {
    private Integer idCustomer;
    private Integer idProduct;
    private Date date;
    private Integer quantity;

    /**
     * Constructorul clasei
     * @param idCustomer id-ul clientului care face comanda
     * @param idProduct id-ul produsului pe care clientul il comanda
     * @param date data in care se face comanda
     * @param quantity cantitatea cumparata de client
     */
    public Order(Integer idCustomer, Integer idProduct, Date date, Integer quantity) {
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
        this.date = date;
        this.quantity = quantity;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
