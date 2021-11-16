package controller;

import javafx.beans.property.SimpleStringProperty;

public class modelAdmin {
    private final SimpleStringProperty coluna1;
    private final SimpleStringProperty coluna2;
    private final SimpleStringProperty coluna3;
 
    modelAdmin(String col1, String col2, String col3) {
        this.coluna1 = new SimpleStringProperty(col1);
        this.coluna2 = new SimpleStringProperty(col2);
        this.coluna3 = new SimpleStringProperty(col3);
    }
 
    public String getColuna1() {
        return coluna1.get();
    }
    public void setColuna1(String col1) {
        coluna1.set(col1);
    }
        
    public String getColuna2() {
        return coluna2.get();
    }
    public void setColuna2(String col2) {
        coluna2.set(col2);
    }
    
    public String getColuna3() {
        return coluna3.get();
    }
    public void setColuna3(String col3) {
        coluna3.set(col3);
    }
}
