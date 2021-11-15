package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.vo.DisciplinaVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
import model.vo.UsuarioVO;
import view.Telas;

public class ProfessorController {
    
    @FXML private Button btnTurmas;
    @FXML private Label lblNomePerfil;
    @FXML private Pane panePerfil;

    @FXML private TableView<TurmaVO> tblTurmas;
    @FXML private TableColumn<TurmaVO, String> collumnCodigoTurma;
    @FXML private TableColumn<DisciplinaVO, String> collumnDisciplina;
    @FXML private TableColumn<TurmaVO, String> collumnHorario;
    @FXML private TableColumn<TurmaVO, Integer> collumnAlunos;
    @FXML private TableColumn<TurmaVO, String> collumnStatus;
    @FXML private TableColumn<TurmaVO, String> collumnLocal;
    @FXML private TableColumn<Button, Button> collumnDiario;

    UsuarioVO profVO = new ProfessorVO();

    // -- Metodos Gerais das telas Professor --

    // ==> Método para receber o usuário logado

    public void inicializaController(UsuarioVO professor) throws Exception {
        this.profVO = professor;
        carregaNomePerfil();
    }

    // ==> metodos para manipular tabelas


    // ==> metodos do botao do Perfil

    public void perfilProf() {
        
    }

    public void carregaNomePerfil() {
        lblNomePerfil.setText(profVO.getNome());
        panePerfil.autosize();
        panePerfil.setLayoutX(797 - (panePerfil.getWidth() + 15));
    }

    // ==> metodos do botao Sair

    public void logoff(ActionEvent event) throws Exception {
        Telas.telaLogin();
    }

    // ==> metodos do botao Turmas

    public void telaTurmas(ActionEvent e) throws Exception {
        Telas.telaInicialProfessor(profVO);
    }

    public void acenderBtnTurmas() {
        btnTurmas.setOpacity(0.8);
    }

    public void apagarBtnTurmas() {
        btnTurmas.setOpacity(1);
    }
    
}