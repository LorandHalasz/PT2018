package model;

public class SavingAccount extends Account implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Integer period;
    private transient Integer okA;
    private transient Integer okW;

    public SavingAccount(){
        this.okA = 0;
        this.okW = 0;
    }

    public SavingAccount(Integer idAccount, Integer idHolder, Double amount, Integer period, Double interest) {
        super(idAccount, idHolder, amount, interest);
        this.period = period;
        this.okA = 0;
        this.okW = 0;
    }

    public SavingAccount(Integer idAccount, Integer idHolder, String type, Double amount, Double interest) {
        super(idAccount, idHolder, type, amount, interest);
        this.okA = 0;
        this.okW = 0;
    }


    public void addInterest(){
        for(int i = 1; i <= period; i++)
            setAmount(getAmount() + getAmount() * getInterest());
    }

    @Override
    /**
     * @pre in cazul perioada este nula
     * @inv suma disponibila este intre 1 si 999.999
     * @post In cazul in care okA nu s-a modificat
     */
    public Double add(Double amount) {
        assert(period != null): "Perioada este 0";
        Double sum = 0.0;
        if(this.okA.equals(0)){
            super.setAmount(super.getAmount() + amount);
            addInterest();
            sum = super.getAmount() + amount;
            assert(sum > 1.0 || sum < 999.999): "Suma nu se afla in intervalul specificat";
            okA = 1;
        }
        assert(okA != 0): "Atribuirea nu s-a executat in mod corect";
        return sum;
    }

    @Override
    /**
     * @pre in cazul in care se incearca retragerea unei sume mai mari decat fondurile disponibile
     * @inv suma disponibila este intre 1 si 999.999
     * @post in cazul in care okW nu s-a modificat
     */
    public Double withdraw(Double amount) {
        assert(super.getAmount() - amount > 0): "Fonduri insuficiente";
        Double sum = 0.0;
        if(this.okW.equals(0)){
            if(super.getAmount() - amount > 0){
                super.setAmount(super.getAmount() - amount);
                sum = super.getAmount() - amount;
                assert(sum > 1.0 || sum < 999.999): "Suma nu se afla in intervalul specificat";
            }
            okW = 1;
        }
        assert(okW != 0): "Atribuirea nu s-a executat in mod corect";
        return sum;
    }

    public void setOkA(Integer okA) {
        this.okA = okA;
    }

    public void setOkW(Integer okW) {
        this.okW = okW;
    }

    @Override
    public String toString() {
        return super.toString() + " model.SavingAccount{" +
                "period=" + period +
                ", interest=" + getInterest() +
                '}';
    }
}
