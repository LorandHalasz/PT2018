package controller;

import model.CasaDeMarcat;
import model.Client;

import java.util.Random;

public class Supermarket implements  Runnable {

    public CasaDeMarcat[]caseDeMarcat;
    private Integer nrCozi;
    private Integer minimumArrivingTime;
    private Integer maximumArrivingTime;
    private Integer minimumServiceTime;
    private Integer maximumServiceTime;
    private Integer simulationTime;
    private Integer time;
    private Integer arrivingTotalTime;
    public String[] rez;
    public String[][] printCasa;

    public Supermarket(Integer nrCozi, Integer minimumArrivingTime, Integer maximumArrivingTime, Integer minimumServiceTime, Integer maximumServiceTime, Integer simulationTime) {
        this.nrCozi = nrCozi;
        this.caseDeMarcat = new CasaDeMarcat[nrCozi];
        printCasa = new String[nrCozi][simulationTime+1];
        for (int i = 0; i < nrCozi; i++){
            caseDeMarcat[i] = new CasaDeMarcat(simulationTime,i+1);
            printCasa[i] = new String[simulationTime+1];
        }
        this.minimumArrivingTime = minimumArrivingTime;
        this.maximumArrivingTime = maximumArrivingTime;
        this.minimumServiceTime = minimumServiceTime;
        this.maximumServiceTime = maximumServiceTime;
        this.simulationTime = simulationTime;
        this.time = 0;
        this.arrivingTotalTime = 0;
    }

    public void deschideCozi(Integer nrCozi){
        Thread[] t = new Thread[nrCozi];
        for(int i = 0; i < nrCozi; i++){
            t[i] = new Thread(caseDeMarcat[i]);
            t[i].start();
        }
    }

    public String printeazaCozi(Integer nrCozi){
        String rez = "";
        for(int i = 0; i < nrCozi; i++){
            rez += caseDeMarcat[i].afisareDate();
        }
        return rez;
    }

    public void afisCoadaCasa(Integer i, Integer sTime){
        printCasa[i][sTime] = caseDeMarcat[i].siruriCase();
    }

    public void adaugaClient(Client client){
        Integer minim = 9999999;
        Integer coadaAleasa = 0;
        for(int i = 0; i < nrCozi; i++)
            if(caseDeMarcat[i].getWaitingTime() < minim){
                minim = caseDeMarcat[i].getWaitingTime();
                coadaAleasa = i;
            }
        caseDeMarcat[coadaAleasa].coada.add(client);
        caseDeMarcat[coadaAleasa].setAvgServiceTime(caseDeMarcat[coadaAleasa].getAvgServiceTime() + client.getServiceTime());
        caseDeMarcat[coadaAleasa].setNrClienti(caseDeMarcat[coadaAleasa].getNrClienti() + 1);
        caseDeMarcat[coadaAleasa].setWaitingTime(caseDeMarcat[coadaAleasa].getWaitingTime() + client.getServiceTime());
        caseDeMarcat[coadaAleasa].setAvgWaitingTime(caseDeMarcat[coadaAleasa].getAvgWaitingTime() + caseDeMarcat[coadaAleasa].getWaitingTime());
    }

    @Override
    public void run() {
        rez = new String[simulationTime + 1];
        for(int i = 0; i <= simulationTime; i++)
            rez[i] = "";
        Integer arrivingTime = 0;
        Random rand = new Random();
        deschideCozi(nrCozi);
        while(time <= simulationTime){

            if(arrivingTotalTime == 0) {
                long range = (long)maximumArrivingTime - (long)minimumArrivingTime + 1;
                long fraction = (long)(range * rand.nextDouble());
                arrivingTime =  (int)(fraction + minimumArrivingTime);
                arrivingTotalTime += arrivingTime;
                if(arrivingTime == 1)
                    rez[time] = "Clientul urmator va ajunge intr-o secunda, adica in secunda " + arrivingTotalTime + "\n";
                else
                    rez[time] = "Clientul urmator va ajunge in " + arrivingTime + " secunde, adica in secunda " + arrivingTotalTime + "\n";
            }
            if(arrivingTotalTime == time){
                long range = (long)maximumServiceTime - (long)minimumServiceTime + 1;
                long fraction = (long)(range * rand.nextDouble());
                Integer serviceTime =  (int)(fraction + minimumServiceTime);

                adaugaClient(new Client(arrivingTime, serviceTime));

                range = (long)maximumArrivingTime - (long)minimumArrivingTime + 1;
                fraction = (long)(range * rand.nextDouble());
                arrivingTime =  (int)(fraction + minimumArrivingTime);
                arrivingTotalTime += arrivingTime;
                if(arrivingTime == 1)
                    rez[time] = "Clientul urmator va ajunge intr-o secunda, adica in secunda " + arrivingTotalTime + "\n";
                else
                    rez[time] = "Clientul urmator va ajunge in " + arrivingTime + " secunde, adica in secunda " + arrivingTotalTime + "\n";
                for(int i = 0; i < nrCozi; i++)
                    if(caseDeMarcat[i].getMaxNrClienti() < caseDeMarcat[i].coada.size()){
                        caseDeMarcat[i].setPeakHour(time);
                        caseDeMarcat[i].setMaxNrClienti(caseDeMarcat[i].coada.size());
                    }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            time++;
        }
    }

}
