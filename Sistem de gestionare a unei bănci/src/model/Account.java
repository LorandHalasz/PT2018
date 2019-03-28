package model;

public abstract class Account implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Integer idAccount;
    private Integer idHolder;
    private String type;
    private Double amount;
    private Double interest;

    public Account() {
    }

    public Account(Integer idAccount, Integer idHolder, Double amount, Double interest) {
        this.idAccount = idAccount;
        this.idHolder = idHolder;
        this.amount = amount;
        this.interest = interest;
    }

    public Account(Integer idAccount, Integer idHolder, String type, Double amount, Double interest) {
        this.idAccount = idAccount;
        this.idHolder = idHolder;
        this.type = type;
        this.amount = amount;
        this.interest = interest;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public Integer getIdHolder() {
        return idHolder;
    }

    public void setIdHolder(Integer idHolder) {
        this.idHolder = idHolder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public abstract Double add(Double amount);

    public abstract Double withdraw(Double amount);

    @Override
    public String toString() {
        return "model.Account{" +
                "idAccount=" + idAccount +
                ", idHolder=" + idHolder +
                ", amount=" + amount +
                '}';
    }
}

