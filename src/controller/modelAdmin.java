package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class modelAdmin {
    private final SimpleStringProperty coluna1;
    private final SimpleStringProperty coluna2;
    private final SimpleStringProperty coluna3;
    private final SimpleBooleanProperty action;
 
    modelAdmin(String col1, String col2, String col3) {
        this.coluna1 = new SimpleStringProperty(col1);
        this.coluna2 = new SimpleStringProperty(col2);
        this.coluna3 = new SimpleStringProperty(col3);
        this.action = new SimpleBooleanProperty(true);
    }
 
    public String getColuna1() {
        return coluna1.get();
    }

    public SimpleStringProperty coluna1Property() {
        return coluna1;
    }

    public void setColuna1(String col1) {
        this.coluna1.set(col1);
    }
        
    public String getColuna2() {
        return coluna2.get();
    }

    public SimpleStringProperty coluna2Property() {
        return coluna2;
    }

    public void setColuna2(String col2) {
        this.coluna2.set(col2);
    }
    
    public String getColuna3() {
        return coluna3.get();
    }

    public SimpleStringProperty coluna3Property() {
        return coluna3;
    }

    public void setColuna3(String col3) {
        this.coluna3.set(col3);
    }

    public void setAction(Boolean act) {
        this.action.set(act);
    }

    public boolean isAction() {
        return action.get();
    }

    public SimpleBooleanProperty actionProperty() {
        return action;
    }
}
