package model;

public class Client {
    private Integer arrivingTime;
    private Integer serviceTime;

    public Client(Integer arrivingTime, Integer serviceTime) {
        this.arrivingTime = arrivingTime;
        this.serviceTime = serviceTime;
    }

    public Integer getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Integer arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }
}



