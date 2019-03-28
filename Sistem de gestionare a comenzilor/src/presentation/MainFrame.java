package presentation;

import businessLogic.*;
import dataAccess.BazaDeDate;
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
import model.Company;
import model.Customer;
import model.Order;
import model.Product;

import java.io.*;
import java.sql.Date;

/**
 * Clasa pentru interfata grafica
 */
public class MainFrame extends Application {

    private BazaDeDate bazaDeDate = new BazaDeDate();
    private OrderProcessing orderProcessing = new OrderProcessing();
    @Override
    public void start(Stage primaryStage){

        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab();
        Tab tab2 = new Tab();

        tab1.setText("Tables");
        tab2.setText("Reflection");

        ScrollPane scrollPane1 = new ScrollPane();
        VBox vBox = new VBox();

        /// For customer table

        HBox hBoxCustomer = new HBox();
        Pane tableCustomer= new Pane();
        TableView<Customer> customer = new TableView<Customer>();
        TableColumn idCustomer = new TableColumn("idCustomer");
        TableColumn firstName = new TableColumn("firstName");
        TableColumn lastName = new TableColumn("lastName");
        TableColumn address = new TableColumn("address");
        TableColumn age = new TableColumn("age");

        idCustomer.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("idCustomer"));
        firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        age.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("age"));
        customer.getColumns().addAll(idCustomer, firstName, lastName, address, age);
        customer.setEditable(true);
        customer.autosize();
        customer.setLayoutX(20);

        Button buttonCustomer = new Button("Vizualizare clienti");
        buttonCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CustomerAdmin customerAdmin = new CustomerAdmin();
                try {
                    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
                    customerObservableList = customerAdmin.populareTabelaCustomer(bazaDeDate);
                    Reflection reflection = new Reflection();
                    customer.setItems(customerObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxCustomer = new VBox();
        Button buttonAddCustomer = new Button("Adaugare client");
        TextField textFieldIdCustomer = new TextField();
        textFieldIdCustomer.setPromptText("idCustomer");
        TextField textFieldFirstName = new TextField();
        textFieldFirstName.setPromptText("firstName");
        TextField textFieldLastName = new TextField();
        textFieldLastName.setPromptText("lastName");
        TextField textFieldAddress = new TextField();
        textFieldAddress.setPromptText("address");
        TextField textFieldAge = new TextField();
        textFieldAge.setPromptText("age");

        vBoxCustomer.setSpacing(20);
        vBoxCustomer.getChildren().setAll(buttonAddCustomer, textFieldIdCustomer, textFieldFirstName, textFieldLastName, textFieldAddress, textFieldAge);

        TitledPane titledPaneAddCustomer = new TitledPane();
        titledPaneAddCustomer.setText("Adaugare Client");
        titledPaneAddCustomer.setContent(vBoxCustomer);
        titledPaneAddCustomer.setExpanded(false);

        buttonAddCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CustomerAdmin customerAdmin = new CustomerAdmin();
                try {
                    Integer idCustomer = Integer.valueOf(textFieldIdCustomer.getText());
                    String firstName = textFieldFirstName.getText();
                    String lastName = textFieldLastName.getText();
                    String address = textFieldAddress.getText();
                    Integer age = Integer.valueOf(textFieldAge.getText());
                    customerAdmin.addNewCustomer(bazaDeDate, idCustomer, firstName, lastName, address, age);
                    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
                    customerObservableList = customerAdmin.populareTabelaCustomer(bazaDeDate);

                    customer.setItems(customerObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxDeleteCustomer = new VBox();
        Button buttonDeleteCustomer = new Button("Delete Customer");
        TextField deleteIdCustomer = new TextField();
        deleteIdCustomer.setPromptText("idCustomer");
        vBoxDeleteCustomer.setSpacing(20);
        vBoxDeleteCustomer.getChildren().addAll(buttonDeleteCustomer, deleteIdCustomer);

        TitledPane titledPaneDeleteCustomer = new TitledPane();
        titledPaneDeleteCustomer.setText("Stergere Client");
        titledPaneDeleteCustomer.setContent(vBoxDeleteCustomer);
        titledPaneDeleteCustomer.setExpanded(false);

        buttonDeleteCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CustomerAdmin customerAdmin = new CustomerAdmin();
                try {
                    Integer idCustomer = Integer.valueOf(deleteIdCustomer.getText());
                    customerAdmin.deleteCustomer(bazaDeDate, idCustomer);
                    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
                    customerObservableList = customerAdmin.populareTabelaCustomer(bazaDeDate);

                    customer.setItems(customerObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxEditCustomer = new VBox();
        Label labelIdCustomerEdit = new Label("Id-ul clientului pe care doriti sa il modificati: ");
        Button buttonEditCustomer = new Button("Edit Customer");
        Label labelAtributesCustomer = new Label("Completati ceea ce doriti sa modificati:");
        TextField editIdCustomer = new TextField();
        editIdCustomer.setPromptText("idCustomer");
        TextField editFirstName = new TextField();
        editFirstName.setPromptText("firstName");
        TextField editLastName = new TextField();
        editLastName.setPromptText("lastName");
        TextField editAddress = new TextField();
        editAddress.setPromptText("address");
        TextField editAge = new TextField();
        editAge.setPromptText("age");

        vBoxEditCustomer.getChildren().addAll(buttonEditCustomer, labelIdCustomerEdit, editIdCustomer, labelAtributesCustomer, editFirstName, editLastName, editAddress, editAge);
        vBoxEditCustomer.setSpacing(20);

        TitledPane titledPaneEditCustomer = new TitledPane();
        titledPaneEditCustomer.setText("Editare Client");
        titledPaneEditCustomer.setContent(vBoxEditCustomer);
        titledPaneEditCustomer.setExpanded(false);

        buttonEditCustomer.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
               CustomerAdmin customerAdmin = new CustomerAdmin();
               try {
                   Integer idCustomer = Integer.valueOf(editIdCustomer.getText());
                   String firstName = "", lastName = "", address = "";
                   if(!editFirstName.getText().isEmpty())
                       firstName = editFirstName.getText();
                   if(!editLastName.getText().isEmpty())
                       lastName = editLastName.getText();
                   if(!editAddress.getText().isEmpty())
                    address = editAddress.getText();
                   Integer age = 0;
                   if(!editAge.getText().isEmpty())
                       age = Integer.valueOf(editAge.getText());
                   customerAdmin.editCustomer(bazaDeDate, idCustomer, firstName, lastName, address, age);
                   ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
                   customerObservableList = customerAdmin.populareTabelaCustomer(bazaDeDate);
                   customer.setItems(customerObservableList);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });

        VBox vBoxFilterCustomer = new VBox();
        Button buttonFilterCustomer = new Button("Aplica Filtru");
        TextField textFieldFilterCustomer = new TextField();
        textFieldFilterCustomer.setPromptText("Clientul cu varsta mai mica decat");

        vBoxFilterCustomer.getChildren().addAll(buttonFilterCustomer, textFieldFilterCustomer);
        vBoxFilterCustomer.setSpacing(20);

        TitledPane titledPaneFilterCustomer = new TitledPane();
        titledPaneFilterCustomer.setText("Filtru Clienti");
        titledPaneFilterCustomer.setContent(vBoxFilterCustomer);
        titledPaneFilterCustomer.setExpanded(false);

        buttonFilterCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    CustomerAdmin customerAdmin = new CustomerAdmin();
                    Integer age = Integer.valueOf(textFieldFilterCustomer.getText());
                    ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
                    customerObservableList = customerAdmin.addFilterCustomer(bazaDeDate, age);
                    customer.setItems(customerObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxCustomer.setSpacing(20);
        hBoxCustomer.getChildren().addAll(customer, buttonCustomer, titledPaneAddCustomer, titledPaneDeleteCustomer, titledPaneEditCustomer, titledPaneFilterCustomer);
        hBoxCustomer.setLayoutX(20);
        hBoxCustomer.setLayoutY(20);
        tableCustomer.getChildren().add(hBoxCustomer);

        /// For product table

        HBox hBoxProduct = new HBox();
        Pane tableProduct = new Pane();
        TableView<Product> product = new TableView<Product>();
        TableColumn idProduct = new TableColumn("idProduct");
        TableColumn type = new TableColumn("type");
        TableColumn name = new TableColumn("name");
        TableColumn company = new TableColumn("company");
        TableColumn description = new TableColumn("description");
        TableColumn price = new TableColumn("price");
        TableColumn quantity = new TableColumn("quantity");
        idProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("idProduct"));
        type.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        company.setCellValueFactory(new PropertyValueFactory<Product, String>("company"));
        description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
        price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("quantity"));
        product.getColumns().addAll(idProduct, type, name, company, description, price, quantity);
        product.setEditable(true);
        product.autosize();
        product.setLayoutX(20);

        Button buttonProduct = new Button("Vizualizare produse");
        buttonProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ProductAdmin productAdmin = new ProductAdmin();
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = productAdmin.populareTabelaProduct(bazaDeDate);
                    product.setItems(productObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxProduct = new VBox();
        Button buttonAddProduct = new Button("Adaugare produs");
        TextField textFieldIdProduct = new TextField();
        textFieldIdProduct.setPromptText("idProduct");
        TextField textFieldType = new TextField();
        textFieldType.setPromptText("type");
        TextField textFieldName = new TextField();
        textFieldName.setPromptText("name");
        TextField textFieldCompany = new TextField();
        textFieldCompany.setPromptText("company");
        TextField textFieldDescription = new TextField();
        textFieldDescription.setPromptText("description");
        TextField textFieldPrice = new TextField();
        textFieldPrice.setPromptText("price");
        TextField textFieldQuantity = new TextField();
        textFieldQuantity.setPromptText("quantity");

        vBoxProduct.setSpacing(20);
        vBoxProduct.getChildren().setAll(buttonAddProduct, textFieldIdProduct, textFieldType, textFieldName, textFieldCompany, textFieldDescription, textFieldPrice, textFieldQuantity);

        TitledPane titledPaneAddProduct = new TitledPane();
        titledPaneAddProduct.setText("Adaugare Produs");
        titledPaneAddProduct.setContent(vBoxProduct);
        titledPaneAddProduct.setExpanded(false);

        buttonAddProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProductAdmin productAdmin = new ProductAdmin();
                try {
                    Integer idProduct = Integer.valueOf(textFieldIdProduct.getText());
                    String type = textFieldType.getText();
                    String name = textFieldName.getText();
                    String company = textFieldCompany.getText();
                    String description = textFieldDescription.getText();
                    Integer price = Integer.valueOf(textFieldPrice.getText());
                    Integer quantity = Integer.valueOf(textFieldQuantity.getText());
                    productAdmin.addNewProduct(bazaDeDate, idProduct, type, name, company, description, price, quantity);
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = productAdmin.populareTabelaProduct(bazaDeDate);

                    product.setItems(productObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxDeleteProduct = new VBox();
        Button buttonDeleteProduct = new Button("Delete Product");
        TextField deleteIdProduct = new TextField();
        deleteIdProduct.setPromptText("idProduct");

        vBoxDeleteProduct.setSpacing(20);
        vBoxDeleteProduct.getChildren().addAll(buttonDeleteProduct, deleteIdProduct);

        TitledPane titledPaneDeleteProduct = new TitledPane();
        titledPaneDeleteProduct.setText("Stergere Produs");
        titledPaneDeleteProduct.setContent(vBoxDeleteProduct);
        titledPaneDeleteProduct.setExpanded(false);

        buttonDeleteProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProductAdmin productAdmin = new ProductAdmin();
                try {
                    Integer idProduct = Integer.valueOf(deleteIdProduct.getText());
                    productAdmin.deleteProduct(bazaDeDate, idProduct);
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = productAdmin.populareTabelaProduct(bazaDeDate);

                    product.setItems(productObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxEditProduct = new VBox();
        Label labelIdProductEdit = new Label("Id-ul produsului pe care doriti sa il modificati: ");
        Button buttonEditProduct = new Button("Edit Product");
        Label labelAtributesProduct = new Label("Completati ceea ce doriti sa modificati:");
        TextField editIdProduct = new TextField();
        editIdProduct.setPromptText("idProduct");
        TextField editType = new TextField();
        editType.setPromptText("type");
        TextField editName = new TextField();
        editName.setPromptText("name");
        TextField editCompany = new TextField();
        editCompany.setPromptText("company");
        TextField editDescription = new TextField();
        editDescription.setPromptText("description");
        TextField editPrice = new TextField();
        editPrice.setPromptText("price");
        TextField editQuantity = new TextField();
        editQuantity.setPromptText("quantity");

        vBoxEditProduct.getChildren().addAll(buttonEditProduct, labelIdProductEdit, editIdProduct, labelAtributesProduct, editType, editName, editCompany, editDescription, editPrice, editQuantity);
        vBoxEditProduct.setSpacing(20);

        TitledPane titledPaneEditProduct = new TitledPane();
        titledPaneEditProduct.setText("Editare Produs");
        titledPaneEditProduct.setContent(vBoxEditProduct);
        titledPaneEditProduct.setExpanded(false);

        buttonEditProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProductAdmin productAdmin = new ProductAdmin();
                try {
                    Integer idProduct = Integer.valueOf(editIdProduct.getText());
                    String type = "", name = "", company = "", description = "";
                    Integer price = 0, quantity = 0;
                    if(!editType.getText().isEmpty())
                        type = editType.getText();
                    if(!editName.getText().isEmpty())
                        name = editName.getText();
                    if(!editCompany.getText().isEmpty())
                        company = editCompany.getText();
                    if(!editDescription.getText().isEmpty())
                        description = editDescription.getText();
                    if(!editPrice.getText().isEmpty())
                        price = Integer.valueOf(editPrice.getText());
                    if(!editQuantity.getText().isEmpty())
                        quantity = Integer.valueOf(editQuantity.getText());
                    productAdmin.editProduct(bazaDeDate, idProduct, type, name, company, description, price, quantity);
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = productAdmin.populareTabelaProduct(bazaDeDate);

                    product.setItems(productObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxFilterProduct = new VBox();
        Button buttonFilterProduct = new Button("Aplica Filtru");
        TextField textFieldFilterProduct = new TextField();
        textFieldFilterProduct.setPromptText("Pretul mai mic decat");

        vBoxFilterProduct.getChildren().addAll(buttonFilterProduct, textFieldFilterProduct);
        vBoxFilterProduct.setSpacing(20);

        TitledPane titledPaneFilterProduct = new TitledPane();
        titledPaneFilterProduct.setText("Filtru Produse");
        titledPaneFilterProduct.setContent(vBoxFilterProduct);
        titledPaneFilterProduct.setExpanded(false);

        buttonFilterProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ProductAdmin productAdmin = new ProductAdmin();
                    Integer price = Integer.valueOf(textFieldFilterProduct.getText());
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = productAdmin.addFilterProduct(bazaDeDate, price);
                    product.setItems(productObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxProduct.setSpacing(20);
        hBoxProduct.getChildren().addAll(product, buttonProduct, titledPaneAddProduct, titledPaneDeleteProduct, titledPaneEditProduct, titledPaneFilterProduct);
        hBoxProduct.setLayoutX(20);
        hBoxProduct.setLayoutY(20);
        tableProduct.getChildren().add(hBoxProduct);

        /// For order table

        HBox hBoxOrder = new HBox();
        Pane tableOrders = new Pane();
        TableView<Order> orders = new TableView<Order>();
        TableColumn idCustomerOrder = new TableColumn("idCustomer");
        TableColumn idProductOrder = new TableColumn("idProduct");
        TableColumn date = new TableColumn("date");
        TableColumn quantityOrder = new TableColumn("quantity");
        idCustomerOrder.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idCustomer"));
        idProductOrder.setCellValueFactory(new PropertyValueFactory<Order, Integer>("idProduct"));
        date.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
        quantityOrder.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        orders.getColumns().addAll(idCustomerOrder, idProductOrder, date, quantityOrder);
        orders.setEditable(true);
        orders.autosize();
        orders.setLayoutX(20);
        tableOrders.getChildren().add(orders);

        Button buttonOrders = new Button("Vizualizare comenzi");
        buttonOrders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ObservableList<Order> ordersObservableList = FXCollections.observableArrayList();
                    ordersObservableList = orderProcessing.populareTabelaOrder(bazaDeDate);
                    orders.setItems(ordersObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxOrder = new VBox();
        Button buttonAddOrder = new Button("Adaugare comanda");
        TextField textFieldIdCustomerOrder = new TextField();
        textFieldIdCustomerOrder.setPromptText("idCustomer");
        TextField textFieldIdProductOrder = new TextField();
        textFieldIdProductOrder.setPromptText("idProduct");
        TextField textFieldDate = new TextField();
        textFieldDate.setPromptText("date");
        TextField textFieldQuantityOrder = new TextField();
        textFieldQuantityOrder.setPromptText("quantity");
        Label statusComanda = new Label("");

        vBoxOrder.setSpacing(20);
        vBoxOrder.getChildren().setAll(buttonAddOrder, textFieldIdCustomerOrder, textFieldIdProductOrder, textFieldDate, textFieldQuantityOrder, statusComanda);

        TitledPane titledPaneAddOrder = new TitledPane();
        titledPaneAddOrder.setText("Adaugare Comanda");
        titledPaneAddOrder.setContent(vBoxOrder);
        titledPaneAddOrder.setExpanded(false);

        buttonAddOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Integer idCustomer = Integer.valueOf(textFieldIdCustomerOrder.getText());
                    Integer idProduct = Integer.valueOf(textFieldIdProductOrder.getText());
                    Date date = Date.valueOf(textFieldDate.getText());
                    Integer quantity = Integer.valueOf(textFieldQuantityOrder.getText());
                    ObservableList<Product> productObservableList = FXCollections.observableArrayList();
                    productObservableList = orderProcessing.addNewOrder(bazaDeDate, idCustomer, idProduct, date, quantity);
                    ObservableList<Order> orderObservableList = FXCollections.observableArrayList();
                    orderObservableList = orderProcessing.populareTabelaOrder(bazaDeDate);

                    orders.setItems(orderObservableList);
                    product.setItems(productObservableList);
                    statusComanda.setText(orderProcessing.rez[orderProcessing.getNrComenzi()]);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxOrder.setSpacing(20);
        hBoxOrder.getChildren().addAll(orders, buttonOrders, titledPaneAddOrder);
        hBoxOrder.setLayoutX(20);
        hBoxOrder.setLayoutY(20);
        tableOrders.getChildren().add(hBoxOrder);

        // For company table

        HBox hBoxCompany= new HBox();
        Pane tableCompany = new Pane();
        TableView<Company> companyT = new TableView<Company>();
        TableColumn companyName = new TableColumn("companyName");
        TableColumn caenCode = new TableColumn("caenCode");
        TableColumn companyAddress = new TableColumn("address");
        TableColumn postalCode = new TableColumn("postalCode");
        companyName.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        caenCode.setCellValueFactory(new PropertyValueFactory<Company, Integer>("caenCode"));
        companyAddress.setCellValueFactory(new PropertyValueFactory<Company, String>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<Company, Integer>("postalCode"));
        companyT.getColumns().addAll(companyName, caenCode, companyAddress, postalCode);
        companyT.setEditable(true);
        companyT.autosize();
        companyT.setLayoutX(20);
        tableCompany.getChildren().add(companyT);

        Button buttonCompany = new Button("Vizualizare companii");
        buttonCompany.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CompanyAdmin companyAdmin = new CompanyAdmin();
                try {
                    ObservableList<Company> companyObservableList = FXCollections.observableArrayList();
                    companyObservableList = companyAdmin.populareTabelaCompany(bazaDeDate);
                    companyT.setItems(companyObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxCompany = new VBox();
        Button buttonAddCompany = new Button("Adaugare companie");
        TextField textFieldIdCompanyName = new TextField();
        textFieldIdCompanyName.setPromptText("companyName");
        TextField textFieldCaenCode = new TextField();
        textFieldCaenCode.setPromptText("caenCode");
        TextField textFieldCompanyAddress = new TextField();
        textFieldCompanyAddress.setPromptText("address");
        TextField textFieldPostalCode = new TextField();
        textFieldPostalCode.setPromptText("postalCode");

        vBoxCompany.setSpacing(20);
        vBoxCompany.getChildren().setAll(buttonAddCompany, textFieldIdCompanyName, textFieldCaenCode, textFieldCompanyAddress, textFieldPostalCode);

        TitledPane titledPaneAddCompany = new TitledPane();
        titledPaneAddCompany.setText("Adaugare Companie");
        titledPaneAddCompany.setContent(vBoxCompany);
        titledPaneAddCompany.setExpanded(false);

        buttonAddCompany.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CompanyAdmin companyAdmin = new CompanyAdmin();
                try {
                    String companyName = textFieldIdCompanyName.getText();
                    Integer caenCode = Integer.valueOf(textFieldCaenCode.getText());
                    String address = textFieldCompanyAddress.getText();
                    Integer postalCode = Integer.valueOf(textFieldPostalCode.getText());
                    companyAdmin.addNewCompany(bazaDeDate, companyName, caenCode, address, postalCode);
                    ObservableList<Company> companyObservableList = FXCollections.observableArrayList();
                    companyObservableList = companyAdmin.populareTabelaCompany(bazaDeDate);

                    companyT.setItems(companyObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBoxDeleteCompany = new VBox();
        Button buttonDeleteCompany = new Button("Delete Company");
        TextField deleteCompanyName = new TextField();
        deleteCompanyName.setPromptText("companyName");

        vBoxDeleteCompany.setSpacing(20);
        vBoxDeleteCompany.getChildren().addAll(buttonDeleteCompany, deleteCompanyName);

        TitledPane titledPaneDeleteCompany = new TitledPane();
        titledPaneDeleteCompany.setText("Stergere Companie");
        titledPaneDeleteCompany.setContent(vBoxDeleteCompany);
        titledPaneDeleteCompany.setExpanded(false);

        buttonDeleteCompany.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CompanyAdmin companyAdmin = new CompanyAdmin();
                try {
                    String companyName = deleteCompanyName.getText();

                    companyAdmin.deleteCompany(bazaDeDate, companyName);
                    ObservableList<Company> companyObservableList = FXCollections.observableArrayList();
                    companyObservableList = companyAdmin.populareTabelaCompany(bazaDeDate);

                    companyT.setItems(companyObservableList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hBoxCompany.setSpacing(20);
        hBoxCompany.getChildren().addAll(companyT, buttonCompany, titledPaneAddCompany, titledPaneDeleteCompany);
        hBoxCompany.setLayoutX(20);
        hBoxCompany.setLayoutY(20);
        tableCompany.getChildren().add(hBoxCompany);

        vBox.getChildren().addAll(tableCustomer, tableProduct, tableOrders, tableCompany);
        vBox.setSpacing(20);

        scrollPane1.setContent(vBox);
        tab1.setContent(scrollPane1);

        Pane pane = new Pane();
        Reflection reflection = new Reflection();
        try {
            ObservableList<Order> objList = orderProcessing.populareTabelaOrder(bazaDeDate);
            TableView table = reflection.generateTable(objList);

            table.setLayoutX(20);
            table.setLayoutY(20);
            table.setMaxWidth(296.5);
            table.setMinWidth(200);
            pane.getChildren().add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tab2.setContent(pane);
        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.autosize();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    File file = new File("Orders.doc");
                    FileWriter fWriter = new FileWriter(file);
                    BufferedWriter writer = new BufferedWriter(fWriter);
                    for(int i = 1; i <= orderProcessing.getNrFacturi(); i++)
                       writer.write(orderProcessing.facturi[i]);
                    writer.flush();
                }   catch (IOException e) {
                    e.printStackTrace();
                }
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.setTitle("Database Application");
        primaryStage.setScene(new Scene(tabPane, 1530, 800));
        primaryStage.show();
    }

    /**
     * Functia care lanseaza aplicatia
     */
    public static void main (String []args)
    {
        launch(args);
    }
}