package model;

/**
 *  Clasa Customer se foloseste pentru a stoca toti clientii.
 *  Clasa contine un constructor iar pentru fiecare atribut al clasei, un getter si un setter
 */
public class Customer {
    private Integer idCustomer;
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;

    /**
     * Constructorul clasei
     * @param idCustomer id-ul clientului
     * @param firstName prenumele clientului
     * @param lastName numele de familie al clientului
     * @param address adresa de domiciliu al clientului
     * @param age varsta clientului
     */
    public Customer(Integer idCustomer, String firstName, String lastName, String address, Integer age) {
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
