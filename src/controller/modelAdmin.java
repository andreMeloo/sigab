package controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class modelAdmin {
    private final SimpleStringProperty coluna1;
    private final SimpleStringProperty coluna2;
    private final SimpleStringProperty coluna3;
    private final SimpleStringProperty coluna4;
    private final SimpleStringProperty coluna5;
    private final SimpleStringProperty coluna6;
    private final SimpleStringProperty coluna7;
    private final SimpleStringProperty coluna8;
    private final SimpleStringProperty coluna9;
    private final SimpleBooleanProperty action;
 
    modelAdmin(String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8, String col9) {
        this.coluna1 = new SimpleStringProperty(col1);
        this.coluna2 = new SimpleStringProperty(col2);
        this.coluna3 = new SimpleStringProperty(col3);
        this.coluna4 = new SimpleStringProperty(col4);
        this.coluna5 = new SimpleStringProperty(col5);
        this.coluna6 = new SimpleStringProperty(col6);
        this.coluna7 = new SimpleStringProperty(col7);
        this.coluna8 = new SimpleStringProperty(col8);
        this.coluna9 = new SimpleStringProperty(col9);
        this.action = new SimpleBooleanProperty(false);
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

    public String getColuna4() {
        return coluna4.get();
    }

    public SimpleStringProperty coluna4Property() {
        return coluna4;
    }

    public void setColuna4(String col4) {
        this.coluna4.set(col4);
    }

    public String getColuna5() {
        return coluna5.get();
    }

    public SimpleStringProperty coluna5Property() {
        return coluna5;
    }

    public void setColuna5(String col5) {
        this.coluna5.set(col5);
    }

    public String getColuna6() {
        return coluna6.get();
    }

    public SimpleStringProperty coluna6Property() {
        return coluna6;
    }

    public void setColuna6(String col6) {
        this.coluna6.set(col6);
    }

    public String getColuna7() {
        return coluna7.get();
    }

    public SimpleStringProperty coluna7Property() {
        return coluna7;
    }

    public void setColuna7(String col7) {
        this.coluna7.set(col7);
    }

    public String getColuna8() {
        return coluna8.get();
    }

    public SimpleStringProperty coluna8Property() {
        return coluna8;
    }

    public void setColuna8(String col8) {
        this.coluna8.set(col8);
    }

    public String getColuna9() {
        return coluna9.get();
    }

    public SimpleStringProperty coluna9Property() {
        return coluna9;
    }

    public void setColuna9(String col9) {
        this.coluna9.set(col9);
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
