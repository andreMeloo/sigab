package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.bo.ProfessorBO;
import model.vo.ProfessorVO;
import model.vo.UsuarioVO;
import view.Telas;

public class ProfessorController {
    
    @FXML private Button btnTurmas;
    @FXML private Button btnPerfilProfessor;

    ProfessorBO profBO = new ProfessorBO();
    UsuarioVO profVO = new ProfessorVO();

    // -- Metodos Globais das telas Professor --

    // ==> Método para receber o usuário logado

    public void inicializaController(UsuarioVO professor) {
        this.profVO = professor;
        carregaNomePerfil();
    }

    // ==> metodos do botao do Perfil

    public void perfilProf(ActionEvent event) {
        System.out.println(profVO.getNome());
    }

    public void carregaNomePerfil() {
        btnPerfilProfessor.setText(profVO.getNome());
    }

    // ==> metodos do botao Sair

    public void logoff(ActionEvent event) throws Exception {
        Telas.telaLogin();
    }

    // ==> metodos do botao Turmas

    public void acenderBtnTurmas() {
        btnTurmas.setOpacity(0.8);
    }
    public void apagarBtnTurmas() {
        btnTurmas.setOpacity(1);
    }
    
}

