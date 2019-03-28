package view;

import controller.Supermarket;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainFrame extends Application implements EventHandler<ActionEvent> {

    private Button button = new Button("Start");
    private TextField text1 = new TextField();
    private TextField text2 = new TextField();
    private TextField text3 = new TextField();
    private TextField text4 = new TextField();
    private TextField text5 = new TextField();
    private TextField text6 = new TextField();
    private TextArea textCasa1, textCasa2, textCasa3, textCasa4, textCasa5;
    private final TextArea textArea = new TextArea();
    private final TextArea textAreaRez = new TextArea();
    private Integer time = 0;
    private Integer ok = 0;

    public static void main(String []args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Supermarket");

        text1.setPromptText("Numarul de cozi");
        text2.setPromptText("Minim arriving time");
        text3.setPromptText("Maxim arriving time");
        text4.setPromptText("Minim service time");
        text5.setPromptText("Minim service time");
        text6.setPromptText("Simulation time");

        Pane pane = new Pane();
        HBox hBox = new HBox();
        HBox hBoxCozi = new HBox();

        VBox casa1 = new VBox();
        VBox casa2 = new VBox();
        VBox casa3 = new VBox();
        VBox casa4 = new VBox();
        VBox casa5 = new VBox();
        VBox vAfis = new VBox();

        Label casaDeMarcat1 = new Label("Casa 1");
        Label casaDeMarcat2 = new Label("Casa 2");
        Label casaDeMarcat3 = new Label("Casa 3");
        Label casaDeMarcat4 = new Label("Casa 4");
        Label casaDeMarcat5 = new Label("Casa 5");
        Label evolutie = new Label("Evolutia clientilor");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVmax(300);
        scrollPane.setHmax(300);
        scrollPane.setContent(textArea);

        vAfis.getChildren().add(scrollPane);

        textCasa1 = new TextArea();
        textCasa2 = new TextArea();
        textCasa3 = new TextArea();
        textCasa4 = new TextArea();
        textCasa5 = new TextArea();

        casa1.getChildren().add(textCasa1);
        casa2.getChildren().add(textCasa2);
        casa3.getChildren().add(textCasa3);
        casa4.getChildren().add(textCasa4);
        casa5.getChildren().add(textCasa5);
        casa1.setPrefWidth(15);
        casa2.setPrefWidth(15);
        casa3.setPrefWidth(15);
        casa4.setPrefWidth(15);
        casa5.setPrefWidth(15);

        Region reg1 = new Region();
        HBox leg = new HBox();

        reg1.setPrefWidth(350);
        leg.getChildren().addAll(casaDeMarcat1, casaDeMarcat2, casaDeMarcat3, casaDeMarcat4, casaDeMarcat5, evolutie, reg1);
        leg.setSpacing(58);
        leg.setAlignment(Pos.TOP_RIGHT);

        hBoxCozi.getChildren().addAll(casa1, casa2, casa3, casa4, casa5, vAfis);
        hBoxCozi.setAlignment(Pos.TOP_CENTER);
        hBoxCozi.setSpacing(50);
        hBox.setLayoutX(10);
        hBox.setLayoutY(10);
        hBox.setSpacing(10);
        hBox.getChildren().add(text1);
        hBox.getChildren().add(text2);
        hBox.getChildren().add(text3);
        hBox.getChildren().add(text4);
        hBox.getChildren().add(text5);
        hBox.getChildren().add(text6);
        hBox.getChildren().add(button);
        VBox panou = new VBox();

        Pane p = new Pane();

        Label rezLabel = new Label("Rezultate:");
        VBox hBoxRez = new VBox();
        hBoxRez.getChildren().add(rezLabel);
        hBoxRez.getChildren().add(textAreaRez);
        hBoxRez.setAlignment(Pos.CENTER);
        hBoxRez.setSpacing(10);

        panou.setSpacing(15);
        panou.getChildren().add(hBox);
        panou.getChildren().add(leg);
        panou.getChildren().add(hBoxCozi);
        panou.getChildren().add(hBoxRez);
        panou.setLayoutX(10);
        panou.setLayoutY(10);
        panou.setSpacing(10);
        pane.getChildren().add(panou);
        button.setOnAction(this);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setScene(new Scene(pane, 1020, 480));
        primaryStage.show();
    }

    private String[] everySec(Integer simTime, Supermarket supermarket, Integer nrCozi){
        String[] rez = new String[simTime + 1];
        time = 0;
        while(time <= simTime){
            rez[time] = "\nLa secunda " + time + "\n\n" + supermarket.printeazaCozi(nrCozi) + "\n";
            switch(nrCozi) {
                case 1: supermarket.afisCoadaCasa(0, time);
                    break;
                case 2: supermarket.afisCoadaCasa(0, time);
                    supermarket.afisCoadaCasa(1, time);
                    break;
                case 3: supermarket.afisCoadaCasa(0, time);
                    supermarket.afisCoadaCasa(1, time);
                    supermarket.afisCoadaCasa(2, time);
                    break;
                case 4: supermarket.afisCoadaCasa(0, time);
                    supermarket.afisCoadaCasa(1, time);
                    supermarket.afisCoadaCasa(2, time);
                    supermarket.afisCoadaCasa(3, time);
                    break;
                case 5: supermarket.afisCoadaCasa(0, time);
                    supermarket.afisCoadaCasa(1, time);
                    supermarket.afisCoadaCasa(2, time);
                    supermarket.afisCoadaCasa(3, time);
                    supermarket.afisCoadaCasa(4, time);
                    break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
        return rez;
    }

    private void putTextField(String[] rez, Integer simulationTime, Supermarket supermarket, Integer nrCozi) {
        time = 0;
        Timeline updater = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.appendText(supermarket.rez[time]);
                textArea.appendText(rez[time]);

                switch(nrCozi) {
                    case 1: textCasa1.setText(supermarket.printCasa[0][time]);
                        break;
                    case 2: textCasa1.setText(supermarket.printCasa[0][time]);
                        textCasa2.setText(supermarket.printCasa[1][time]);
                        break;
                    case 3: textCasa1.setText(supermarket.printCasa[0][time]);
                        textCasa2.setText(supermarket.printCasa[1][time]);
                        textCasa3.setText(supermarket.printCasa[2][time]);
                        break;
                    case 4: textCasa1.setText(supermarket.printCasa[0][time]);
                        textCasa2.setText(supermarket.printCasa[1][time]);
                        textCasa3.setText(supermarket.printCasa[2][time]);
                        textCasa4.setText(supermarket.printCasa[3][time]);
                        break;
                    case 5: textCasa1.setText(supermarket.printCasa[0][time]);
                        textCasa2.setText(supermarket.printCasa[1][time]);
                        textCasa3.setText(supermarket.printCasa[2][time]);
                        textCasa4.setText(supermarket.printCasa[3][time]);
                        textCasa5.setText(supermarket.printCasa[4][time]);
                        break;
                }
                if(time == simulationTime)
                    ok = 1;
                if(ok == 1)
                    for(int i = 0; i < nrCozi; i++) {
                        if(supermarket.caseDeMarcat[i].getNrClienti() != 0){
                            textAreaRez.appendText("Coada " + i + "\nAverage Service Time " + supermarket.caseDeMarcat[i].getAvgServiceTime() / supermarket.caseDeMarcat[i].getNrClienti() + "\n");
                            textAreaRez.appendText("Average Waiting Time " + supermarket.caseDeMarcat[i].getAvgWaitingTime() / supermarket.caseDeMarcat[i].getNrClienti() + "\n");
                        }
                        else{
                            textAreaRez.appendText("Average Service Time 0\n");
                            textAreaRez.appendText("Average Waiting Time 0\n");
                        }
                        textAreaRez.appendText("Empty Queue time " + (supermarket.caseDeMarcat[i].getEmptyQueueTime() - 1) + "\n");
                        textAreaRez.appendText("Peak Hour " + supermarket.caseDeMarcat[i].getPeakHour() + "\n\n");
                        ok = 0;
                    }
                time++;
            }
        }));
        updater.setCycleCount(simulationTime + 1);
        updater.play();
    }

    @Override
    public void handle(ActionEvent event) {
        Integer nrCozi, arTimeMin, arTimeMax, serTimeMin, serTimeMax, simTime;
        if(event.getSource() == button){
            textArea.clear();
            textAreaRez.clear();
            nrCozi = Integer.valueOf(text1.getText());
            arTimeMin = Integer.valueOf(text2.getText());
            arTimeMax = Integer.valueOf(text3.getText());
            serTimeMin = Integer.valueOf(text4.getText());
            serTimeMax = Integer.valueOf(text5.getText());
            simTime = Integer.valueOf(text6.getText());
            if(nrCozi > 0 && nrCozi < 6) {
                Supermarket supermarket = new Supermarket(nrCozi, arTimeMin, arTimeMax, serTimeMin, serTimeMax, simTime);
                Thread t1 = new Thread(supermarket);
                t1.start();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                putTextField(everySec(simTime, supermarket, nrCozi), simTime, supermarket, nrCozi);
            }
            else
                textArea.setText("Numar de case de marcat indisponibil");
        }
    }
}
