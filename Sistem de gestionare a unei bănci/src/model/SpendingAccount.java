package model;

public class SpendingAccount extends Account implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    private Integer nrAdd;
    private Integer nrWithdraw;

    public SpendingAccount() {

    }

    public SpendingAccount(Integer idAccount, Integer idHolder, Double amount) {
        super(idAccount, idHolder, amount, 0.0);
        this.nrAdd = 0;
        this.nrWithdraw = 0;
    }

    public SpendingAccount(Integer idAccount, Integer idHolder, String type, Double amount) {
        super(idAccount, idHolder, type, amount, 0.0);
    }


    public Integer getNrAdd() {
        return nrAdd;
    }

    public void setNrAdd(Integer nrAdd) {
        this.nrAdd = nrAdd;
    }

    public Integer getNrWithdraw() {
        return nrWithdraw;
    }

    public void setNrWithdraw(Integer nrWithdraw) {
        this.nrWithdraw = nrWithdraw;
    }

    @Override
    /**
     * @pre in cazul suma este negativa
     * @inv suma disponibila este intre 1 si 999.999
     */
    public Double add(Double amount) {
        assert(amount >= 0): "suma este negativa";
        super.setAmount(super.getAmount() + amount);
        Double sum = super.getAmount() + amount;
        nrAdd++;
        assert(sum > 1.0 || sum < 999.999): "Suma nu se afla in intervalul specificat";
        return sum;
    }

    @Override
    /**
     * @pre in cazul in care se incearca retragerea unei sume mai mari decat fondurile disponibile
     * @inv suma disponibila este intre 1 si 999.999
     */
    public Double withdraw(Double amount) {
        assert(super.getAmount() - amount > 0): "Fonduri insuficiente";
        Double sum = 0.0;
        if(super.getAmount() - amount > 0){
            super.setAmount(super.getAmount() - amount);
            sum = super.getAmount() - amount;
            assert(sum > 1.0 || sum < 999.999): "Suma nu se afla in intervalul specificat";
        }
        nrWithdraw++;
        return sum;
    }

    @Override
    public String toString() {
        return super.toString() + " model.SpendingAccount{" +
                "nrAdd=" + nrAdd +
                ", nrWithdraw=" + nrWithdraw +
                '}';
    }
}
