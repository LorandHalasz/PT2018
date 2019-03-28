package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.*;
import model.Person;
import controller.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


public class MainFrame extends Application  {

    private static Bank bank = new Bank();

    @Override
    public void start(Stage primaryStage){

        bank.readAccountsData();

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();

        tab1.setText("Tables");

        ScrollPane scrollPane1 = new ScrollPane();
        VBox vBox = new VBox();

        /// For Person table

        HBox hBoxPerson = new HBox();
        Pane tablePerson= new Pane();
        TableView<Person> person = new TableView<Person>();
        TableColumn idPerson = new TableColumn("idPerson");
        TableColumn name = new TableColumn("name");
        TableColumn cnp = new TableColumn("cnp");
        TableColumn address = new TableColumn("address");
        TableColumn age = new TableColumn("age");

        idPerson.setCellValueFactory(new PropertyValueFactory<Person, Integer>("idPerson"));
        name.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        cnp.setCellValueFactory(new PropertyValueFactory<Person, String>("cnp"));
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
        age.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        person.getColumns().addAll(idPerson, name, cnp, address, age);
        person.setEditable(true);
        person.autosize();
        person.setLayoutX(20);

        Button buttonPerson = new Button("Vizualizare clienti");
        buttonPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ObservableList<Person> personObservableList = FXCollections.observableArrayList();
                    Set set = bank.bank.keySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = (Person) iterator.next();
                        personObservableList.add(p);
                    }
                    person.setItems(personObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxPerson = new VBox();
        Button buttonAddPerson = new Button("Adaugare Persoana");
        TextField textFieldIdPerson = new TextField();
        textFieldIdPerson.setPromptText("idPerson");
        TextField textFieldName = new TextField();
        textFieldName.setPromptText("name");
        TextField textFieldCnp = new TextField();
        textFieldCnp.setPromptText("cnp");
        TextField textFieldAddress = new TextField();
        textFieldAddress.setPromptText("address");
        TextField textFieldAge = new TextField();
        textFieldAge.setPromptText("age");

        vBoxPerson.setSpacing(20);
        vBoxPerson.getChildren().setAll(buttonAddPerson, textFieldIdPerson, textFieldName, textFieldCnp, textFieldAddress, textFieldAge);

        TitledPane titledPaneAddPerson = new TitledPane();
        titledPaneAddPerson.setText("Adaugare Persoana");
        titledPaneAddPerson.setContent(vBoxPerson);
        titledPaneAddPerson.setExpanded(false);

        buttonAddPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer idPerson = Integer.valueOf(textFieldIdPerson.getText());
                    String name = textFieldName.getText();
                    String cnp = textFieldCnp.getText();
                    String address = textFieldAddress.getText();
                    Integer age = Integer.valueOf(textFieldAge.getText());
                    ObservableList<Person> personObservableList = FXCollections.observableArrayList();
                    bank.addPerson(new Person(idPerson, name, cnp, address, age));
                    bank.addObserver(new Person(idPerson, name, cnp, address, age));
                    Set set = bank.bank.keySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = (Person) iterator.next();
                        personObservableList.add(p);
                    }

                    person.setItems(personObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        VBox vBoxDeletePerson = new VBox();
        Button buttonDeletePerson = new Button("Delete Person");
        TextField deleteIdPerson = new TextField();
        deleteIdPerson.setPromptText("idPerson");
        vBoxDeletePerson.setSpacing(20);
        vBoxDeletePerson.getChildren().addAll(buttonDeletePerson, deleteIdPerson);

        TitledPane titledPaneDeletePerson = new TitledPane();
        titledPaneDeletePerson.setText("Stergere Client");
        titledPaneDeletePerson.setContent(vBoxDeletePerson);
        titledPaneDeletePerson.setExpanded(false);

        buttonDeletePerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer idPerson = Integer.valueOf(deleteIdPerson.getText());
                    ObservableList<Person> personObservableList = FXCollections.observableArrayList();
                    bank.removePerson(idPerson);
                    Set set = bank.bank.keySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = (Person) iterator.next();
                        personObservableList.add(p);
                    }
                    person.setItems(personObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        VBox vBoxEditPerson = new VBox();
        Label labelIdPersonEdit = new Label("Id-ul persoanei pe care doriti sa o modificati: ");
        Button buttonEditPerson = new Button("Edit Person");
        Label labelAtributesPerson = new Label("Completati ceea ce doriti sa modificati:");
        TextField editIdPerson = new TextField();
        editIdPerson.setPromptText("idPerson");
        TextField editName = new TextField();
        editName.setPromptText("name");
        TextField editCnp = new TextField();
        editCnp.setPromptText("cnp");
        TextField editAddress = new TextField();
        editAddress.setPromptText("address");
        TextField editAge = new TextField();
        editAge.setPromptText("age");

        vBoxEditPerson.getChildren().addAll(buttonEditPerson, labelIdPersonEdit, editIdPerson, labelAtributesPerson, editName, editCnp, editAddress, editAge);
        vBoxEditPerson.setSpacing(20);

        TitledPane titledPaneEditProduct = new TitledPane();
        titledPaneEditProduct.setText("Editare Persoana");
        titledPaneEditProduct.setContent(vBoxEditPerson);
        titledPaneEditProduct.setExpanded(false);

        Button personReset = new Button("Reset");
        personReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                person.setItems(null);
            }
        });

        buttonEditPerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Person selectedPerson = new Person();
                    Integer idPerson = Integer.valueOf(editIdPerson.getText());


                    Set set = bank.bank.keySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = (Person) iterator.next();
                        if(p.getIdPerson().equals(idPerson))
                            selectedPerson = p;
                    }

                    String name = selectedPerson.getName(), cnp = selectedPerson.getCnp(), address = selectedPerson.getAddress();
                    if(!editName.getText().isEmpty())
                        name = editName.getText();
                    if(!editCnp.getText().isEmpty())
                        cnp = editCnp.getText();
                    if(!editAddress.getText().isEmpty())
                        address = editAddress.getText();
                    Integer age = selectedPerson.getAge();
                    if(!editAge.getText().isEmpty())
                        age = Integer.valueOf(editAge.getText());

                    set = bank.bank.keySet();
                    Iterator<Person> iterator1 = set.iterator();
                    while(iterator1.hasNext()){
                        Person p = iterator1.next();
                        if(p.getIdPerson().equals(idPerson)) {
                            p.setName(name);
                            p.setCnp(cnp);
                            p.setAddress(address);
                            p.setAge(age);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxPerson.setSpacing(20);
        hBoxPerson.getChildren().addAll(person, buttonPerson, titledPaneAddPerson, titledPaneDeletePerson, titledPaneEditProduct, personReset);
        hBoxPerson.setLayoutX(20);
        hBoxPerson.setLayoutY(20);
        tablePerson.getChildren().add(hBoxPerson);

        /// For account table

        HBox hBoxAccount = new HBox();
        Pane tableAccount = new Pane();
        TableView<Account> account = new TableView<Account>();
        TableColumn idAccount = new TableColumn("idAccount");
        TableColumn idHolder = new TableColumn("idHolder");
        TableColumn type = new TableColumn("type");
        TableColumn amount = new TableColumn("amount");
        TableColumn interest = new TableColumn("interest");

        idAccount.setCellValueFactory(new PropertyValueFactory<Account, Integer>("idAccount"));
        idHolder.setCellValueFactory(new PropertyValueFactory<Account, Integer>("idHolder"));
        type.setCellValueFactory(new PropertyValueFactory<Account, String>("type"));
        amount.setCellValueFactory(new PropertyValueFactory<Account, Double>("amount"));
        interest.setCellValueFactory(new PropertyValueFactory<Account, Double>("interest"));
        account.getColumns().addAll(idAccount, idHolder, type, amount, interest);
        account.setEditable(true);
        account.autosize();
        account.setLayoutX(20);

        Button buttonAccount = new Button("Vizualizare conturi");
        buttonAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    Set set = bank.bank.keySet();

                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = (Person) iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving model.Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending model.Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }
                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        VBox vBoxAccount = new VBox();
        Button buttonAddAccount = new Button("Adaugare cont");
        TextField textFieldIdAccount = new TextField();
        textFieldIdAccount.setPromptText("idProduct");
        TextField textFieldIdHolder = new TextField();
        textFieldIdHolder.setPromptText("IdHolder");
        TextField textFieldAmount = new TextField();
        textFieldAmount.setPromptText("amount");
        TextField textFieldInterest = new TextField();
        textFieldInterest.setPromptText("interest");
        TextField textFieldPeriod = new TextField();
        textFieldPeriod.setPromptText("period");

        vBoxAccount.setSpacing(20);
        vBoxAccount.getChildren().setAll(buttonAddAccount, textFieldIdAccount, textFieldIdHolder, textFieldAmount, textFieldInterest, textFieldPeriod);

        TitledPane titledPaneAddAccount = new TitledPane();
        titledPaneAddAccount.setText("Adaugare Cont");
        titledPaneAddAccount.setContent(vBoxAccount);
        titledPaneAddAccount.setExpanded(false);

        buttonAddAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    Integer idAccount = 0, idHolder = 0, period = 0;
                    Double amount = 0.0, interest = 0.0;

                    if(!textFieldIdAccount.getText().isEmpty())
                        idAccount = Integer.valueOf(textFieldIdAccount.getText());
                    if(!textFieldIdHolder.getText().isEmpty())
                        idHolder = Integer.valueOf(textFieldIdHolder.getText());
                    if(!textFieldAmount.getText().isEmpty())
                        amount = Double.valueOf(textFieldAmount.getText());
                    if(!textFieldInterest.getText().isEmpty())
                        interest = Double.valueOf(textFieldInterest.getText());
                    if(!textFieldPeriod.getText().isEmpty())
                        period = Integer.valueOf(textFieldPeriod.getText());

                    Person selectedPerson = new Person();
                    Set set = bank.bank.keySet();
                    Iterator<Person> iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        if(p.getIdPerson().equals(idHolder))
                            selectedPerson = p;
                    }

                    if(interest != 0.0) {
                        for(int i = 1; i <= period; i++)
                            amount += amount * interest;
                        Account newAccount = new SavingAccount(idAccount, idHolder, amount, period, interest);
                        bank.bank.get(selectedPerson).add(newAccount);
                    } else {
                        Account newAccount = new SpendingAccount(idAccount, idHolder, amount);
                        bank.bank.get(selectedPerson).add(newAccount);
                    }

                    iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving model.Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending model.Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }
                    account.setItems(accountsObservableList);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxDeleteAccount = new VBox();
        Button buttonDeleteAccount = new Button("Delete model.Account");
        TextField deleteIdAccount = new TextField();
        deleteIdAccount.setPromptText("idAccount");

        vBoxDeleteAccount.setSpacing(20);
        vBoxDeleteAccount.getChildren().addAll(buttonDeleteAccount, deleteIdAccount);

        TitledPane titledPaneDeleteAccount = new TitledPane();
        titledPaneDeleteAccount.setText("Stergere cont");
        titledPaneDeleteAccount.setContent(vBoxDeleteAccount);
        titledPaneDeleteAccount.setExpanded(false);

        buttonDeleteAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try{
                    Integer IdAccount = Integer.valueOf(deleteIdAccount.getText());
                    Set set = bank.bank.keySet();
                    Person selectedPerson = new Person();
                    Account selectedSpendingAccount = new SpendingAccount();
                    Account selectedSavingAccount = new SavingAccount();
                    Double in = 0.0;
                    Iterator<Person> iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i.getIdAccount().equals(IdAccount)){
                                in = i.getInterest();
                                selectedPerson = p;
                                if(in != 0.0)
                                    selectedSavingAccount = i;
                                else
                                    selectedSpendingAccount = i;
                            }
                        }
                    }

                    if(in != 0.0)
                        bank.bank.get(selectedPerson).remove(selectedSavingAccount);
                    else
                        bank.bank.get(selectedPerson).remove(selectedSpendingAccount);

                    set = bank.bank.keySet();
                    iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }

                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxEditAccount = new VBox();
        Label labelIdAccountEdit = new Label("Id-ul contului pe care doriti sa il modificati: ");
        Button buttonEditAccount = new Button("Edit cont");
        Label labelAtributesAccount = new Label("Completati ceea ce doriti sa modificati:");
        TextField editIdAccount = new TextField();
        editIdAccount.setPromptText("idAccount");
        TextField editAmount = new TextField();
        editAmount.setPromptText("amount");
        TextField editInterest = new TextField();
        editInterest.setPromptText("interest");

        vBoxEditAccount.getChildren().addAll(buttonEditAccount, labelIdAccountEdit, editIdAccount, labelAtributesAccount, editAmount, editInterest);
        vBoxEditAccount.setSpacing(20);

        TitledPane titledPaneEditAccount = new TitledPane();
        titledPaneEditAccount.setText("Editare Cont");
        titledPaneEditAccount.setContent(vBoxEditAccount);
        titledPaneEditAccount.setExpanded(false);


        buttonEditAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer IdAccount = Integer.valueOf(editIdAccount.getText());
                    Double amount = 0.0, interest = 0.0;
                    if(!editAmount.getText().isEmpty())
                        amount = Double.valueOf(editAmount.getText());
                    if(!editInterest.getText().isEmpty())
                        interest = Double.valueOf(editInterest.getText());
                    Set set = bank.bank.keySet();
                    Iterator<Person> iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i.getIdAccount().equals(IdAccount)){
                                if(amount != 0.0)
                                    i.setAmount(amount);
                                if(interest != 0.0)
                                    i.setInterest(interest);
                            }
                        }
                    }

                    set = bank.bank.keySet();
                    iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving model.Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending model.Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }

                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxAccount.setSpacing(20);
        hBoxAccount.getChildren().addAll(account, buttonAccount, titledPaneAddAccount, titledPaneDeleteAccount, titledPaneEditAccount);
        hBoxAccount.setLayoutX(20);
        hBoxAccount.setLayoutY(20);
        tableAccount.getChildren().add(hBoxAccount);

        Pane operation = new Pane();

        VBox vBoxAdd = new VBox();
        Label labelIdAccountAdd = new Label("Id-ul contului pe care doriti sa introduceti bani este: ");
        Button buttonAddAmountAccount = new Button("Add Money");
        Label labelAtributesAddAccount = new Label("Completati suma pe care doriti sa o introduceti:");
        TextField addIdAccount = new TextField();
        addIdAccount.setPromptText("idAccount");
        TextField addAmount = new TextField();
        addAmount.setPromptText("amount");

        vBoxAdd.getChildren().addAll(buttonAddAmountAccount, labelIdAccountAdd, addIdAccount, labelAtributesAddAccount, addAmount);
        vBoxAdd.setSpacing(20);
        vBoxAdd.setLayoutX(20);
        vBoxAdd.setLayoutY(20);

        TitledPane titledPaneAddAmountAccount = new TitledPane();
        titledPaneAddAmountAccount.setText("Adaugare suma in cont");
        titledPaneAddAmountAccount.setContent(vBoxAdd);
        titledPaneAddAmountAccount.setExpanded(false);



        buttonAddAmountAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer IdAccount = Integer.valueOf(addIdAccount.getText());
                    bank.notifyObserver(IdAccount);
                    Double amount = 0.0;
                    if(!addAmount.getText().isEmpty())
                        amount = Double.valueOf(addAmount.getText());
                    Set set = bank.bank.keySet();
                    Iterator<Person> iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i.getIdAccount().equals(IdAccount)){
                                if(amount != 0.0){
                                    i.add(amount);
                                }
                            }
                        }
                    }

                    set = bank.bank.keySet();
                    iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving model.Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending model.Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }

                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxWithdraw = new VBox();
        Label labelIdAccountWithdraw = new Label("Id-ul contului de pe care doriti sa retrageti bani, este: ");
        Button buttonWithdrawAmountAccount = new Button("Withdraw Money");
        Label labelAtributesWithdrawAccount = new Label("Completati suma pe care doriti sa o retrageti:");
        TextField withdrawIdAccount = new TextField();
        withdrawIdAccount.setPromptText("idAccount");
        TextField withdrawAmount = new TextField();
        withdrawAmount.setPromptText("amount");

        vBoxWithdraw.getChildren().addAll(buttonWithdrawAmountAccount, labelIdAccountWithdraw, withdrawIdAccount, labelAtributesWithdrawAccount, withdrawAmount);
        vBoxWithdraw.setSpacing(20);
        vBoxWithdraw.setLayoutX(20);
        vBoxWithdraw.setLayoutY(20);

        TitledPane titledPaneWithdrawAmountAccount = new TitledPane();
        titledPaneWithdrawAmountAccount.setText("Retragere suma din cont");
        titledPaneWithdrawAmountAccount.setContent(vBoxWithdraw);
        titledPaneWithdrawAmountAccount.setExpanded(false);



        buttonWithdrawAmountAccount.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer IdAccount = Integer.valueOf(withdrawIdAccount.getText());
                    bank.notifyObserver(IdAccount);
                    Double amount = 0.0;
                    if(!withdrawAmount.getText().isEmpty())
                        amount = Double.valueOf(withdrawAmount.getText());
                    Set set = bank.bank.keySet();
                    Iterator<Person> iterator = set.iterator();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i.getIdAccount().equals(IdAccount)){
                                if(amount != 0.0){
                                    i.withdraw(amount);
                                }
                            }
                        }
                    }

                    set = bank.bank.keySet();
                    iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }

                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxRemoveHolder= new VBox();
        Label labelIdPersonRemoveHolder = new Label("Sterge toate conturile persoanei cu id-ul: ");
        Button buttonRemoveHolder = new Button("Sterge");
        TextField RemoveHolderIdPerson = new TextField();
        RemoveHolderIdPerson.setPromptText("idPerson");

        vBoxRemoveHolder.getChildren().addAll(buttonRemoveHolder, labelIdPersonRemoveHolder, RemoveHolderIdPerson);
        vBoxRemoveHolder.setSpacing(20);
        vBoxRemoveHolder.setLayoutX(20);
        vBoxRemoveHolder.setLayoutY(20);

        TitledPane titledPaneHolder = new TitledPane();
        titledPaneHolder.setText("Stergere conturi");
        titledPaneHolder.setContent(vBoxRemoveHolder);
        titledPaneHolder.setExpanded(false);



        buttonRemoveHolder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Integer IdAccount = Integer.valueOf(RemoveHolderIdPerson.getText());
                    bank.removeHolderAssociatedAccounts(IdAccount);

                    Set set = bank.bank.keySet();
                    Iterator<Person> iterator = set.iterator();
                    ObservableList<Account> accountsObservableList = FXCollections.observableArrayList();
                    while(iterator.hasNext()){
                        Person p = iterator.next();
                        ArrayList<Account> accounts = bank.bank.get(p);
                        for(Account i : accounts) {
                            if(i instanceof SavingAccount) {
                                Account ac = new SavingAccount(i.getIdAccount(), i.getIdHolder(), "Saving Account", i.getAmount(), i.getInterest());
                                accountsObservableList.add(ac);
                            } else {
                                Account ac = new SpendingAccount(i.getIdAccount(), i.getIdHolder(), "Spending Account", i.getAmount());
                                accountsObservableList.add(ac);
                            }
                        }
                    }

                    account.setItems(accountsObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        HBox accountOperation = new HBox();
        accountOperation.setSpacing(20);
        accountOperation.getChildren().addAll(titledPaneAddAmountAccount, titledPaneWithdrawAmountAccount, titledPaneHolder);
        accountOperation.setLayoutX(20);
        accountOperation.setLayoutY(20);
        operation.getChildren().add(accountOperation);

        vBox.getChildren().addAll(tablePerson, tableAccount, operation);
        vBox.setSpacing(20);

        scrollPane1.setContent(vBox);
        tab1.setContent(scrollPane1);

        tabPane.getTabs().addAll(tab1);
        tabPane.autosize();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                bank.writeAccountsData();
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setTitle("Bank Application");
        primaryStage.setScene(new Scene(tabPane, 1530, 800));
        primaryStage.show();
    }

    public static void main(String[] args){

        launch(args);
    }

}