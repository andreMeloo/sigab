package controller;

import javafx.event.ActionEvent;
import view.Telas;

public class AlunoController {
    

    
    // ==> metodos do botao Sair

    public void logoff(ActionEvent event) throws Exception {
        Telas.telaLogin();
    }
}
