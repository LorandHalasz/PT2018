package model;

/**
 *  Clasa Company se foloseste pentru a stoca toate companiile.
 *  Clasa contine un constructor iar pentru fiecare atribut al clasei, un getter si un setter
 */
public class Company {

    private String companyName;
    private Integer caenCode;
    private String address;
    private Integer postalCode;

    /**
     * Constructorul clasei
     * @param companyName numele companiei
     * @param caenCode codul caen al companiei
     * @param address adresa sediului companiei
     * @param postalCode codul postal al companiei
     */
    public Company(String companyName, Integer caenCode, String address, Integer postalCode) {
        this.companyName = companyName;
        this.caenCode = caenCode;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCaenCode() {
        return caenCode;
    }

    public void setCaenCode(Integer caenCode) {
        this.caenCode = caenCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

}

