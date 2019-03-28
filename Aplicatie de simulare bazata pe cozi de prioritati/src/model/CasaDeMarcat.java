package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CasaDeMarcat implements Runnable {
    public BlockingQueue<Client> coada;
    private Integer nrCasa;
    private Integer waitingTime;
    private Integer nrClienti;
    private Integer simulationTime;
    private Double avgServiceTime;
    private Double avgWaitingTime;
    private Integer emptyQueueTime;
    private Integer peakHour;
    private Integer maxNrClienti;


    public CasaDeMarcat(Integer simulationTime, Integer nrCasa) {
        this.coada = new LinkedBlockingQueue<>();
        this.waitingTime = 0;
        this.nrClienti = 0;
        this.simulationTime = simulationTime;
        this.nrCasa = nrCasa;
        this.avgServiceTime = 0.0;
        this.avgWaitingTime = 0.0;
        this.emptyQueueTime = 0;
        this.peakHour = 0;
        this.maxNrClienti = 0;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Integer getNrClienti() {
        return nrClienti;
    }

    public void setNrClienti(Integer nrClienti) {
        this.nrClienti = nrClienti;
    }

    public Double getAvgServiceTime() {
        return avgServiceTime;
    }

    public void setAvgServiceTime(Double avgServiceTime) {
        this.avgServiceTime = avgServiceTime;
    }

    public Double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(Double avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public Integer getEmptyQueueTime() {
        return emptyQueueTime;
    }

    public Integer getPeakHour() {
        return peakHour;
    }

    public void setPeakHour(Integer peakHour) {
        this.peakHour = peakHour;
    }

    public Integer getMaxNrClienti() {
        return maxNrClienti;
    }

    public void setMaxNrClienti(Integer maxNrClienti) {
        this.maxNrClienti = maxNrClienti;
    }

    public String afisareDate(){
        String rez = "";
        if(this.coada.size() == 1){
            rez = "La casa de marcat numarul " + this.nrCasa + " este " + this.coada.size() + " client la coada. Timpul de asteptare este : " + this.waitingTime + "\n";
            rez += "Clientul avand urmatorul timp de servire: ";
        }
        else {
            rez = "La casa de marcat numarul " + this.nrCasa + " sunt " + this.coada.size() + " clienti la coada. Timpul de asteptare este : " + this.waitingTime + "\n";
            rez += "Clientii avand urmatorii timpi de servire: ";
        }
        for (Client aCoada : coada) {
            rez += aCoada.getServiceTime() + " ";
        }
        rez += "\n";
        return rez;
    }

    public String siruriCase(){
        String rez = "";
        for (Client aCoada : coada) {
            rez += "\uC6C3 " + aCoada.getServiceTime() + "\n";
        }
        return rez;
    }


    @Override
    public void run() {
        Integer time = 0;
        while(time <= simulationTime){
            if(coada.size() != 0) {
                coada.peek().setServiceTime(coada.peek().getServiceTime() - 1);
                waitingTime--;
                if (coada.peek().getServiceTime() == 0) {
                    coada.poll();
                }
            }
            else
                this.emptyQueueTime ++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }
}
