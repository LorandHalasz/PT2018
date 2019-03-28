package model;

/**
 *  Clasa Product se foloseste pentru a stoca toate produsele.
 *  Clasa contine un constructor iar pentru fiecare atribut al clasei, un getter si un setter
 */
public class Product {

    private Integer idProduct;
    private String type;
    private String name;
    private String company;
    private String description;
    private Integer price;
    private Integer quantity;

    /**
     * Constructorul clasei
     * @param idProduct id-ul produsului
     * @param type tipul din care face parte produsul respectiv
     * @param name numele produsului
     * @param company numele companiei care produce acel produs
     * @param description descrierea produsului
     * @param price pretul unui produs
     * @param quantity cantitatea disponibila
     */
    public Product(Integer idProduct, String type, String name, String company, String description, Integer price, Integer quantity) {
        this.idProduct = idProduct;
        this.type = type;
        this.name = name;
        this.company = company;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
