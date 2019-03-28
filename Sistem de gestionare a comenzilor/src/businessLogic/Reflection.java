package businessLogic;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;

/**
 * @param <Object>
 */
public class Reflection<Object> {

    /**
     * Functia returneaza un tabel generat pe baza unei liste de obiecte, primite ca parametru
     * @param objectList reprezinta o lista de obiecte
     * @return un tabel
     */
    public TableView generateTable(ObservableList<Object> objectList){
        TableView table = new TableView();
        Class cls = objectList.get(0).getClass();
        Field[] parameters = cls.getDeclaredFields();
        TableColumn[] tableColumns = new TableColumn[parameters.length];
        int i= 0;
        for (Field c: parameters) {
            tableColumns[i] = new TableColumn(c.getName());
            tableColumns[i].setCellValueFactory(new PropertyValueFactory<>(c.getName()));
            table.getColumns().add(tableColumns[i++]);
        }
        table.setItems(objectList);
        return table;
    }
}

