package model;

import java.util.Objects;

public class Person implements java.io.Serializable, Observer {

    private static final long serialVersionUID = 1L;

    private Integer idPerson;
    private String name;
    private String cnp;
    private String address;
    private Integer age;

    public Person(Integer idPerson, String name, String cnp, String address, Integer age) {
        this.idPerson = idPerson;
        this.name = name;
        this.cnp = cnp;
        this.address = address;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    public Person() {
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "model.Person{" +
                "idPerson=" + idPerson +
                ", name='" + name + '\'' +
                ", cnp='" + cnp + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void update(Integer idAccount) {
        System.out.println("In contul clientului cu id-ul: " + idAccount + " s-a efectuat o adaugare/retragere de numerar");
    }
}
