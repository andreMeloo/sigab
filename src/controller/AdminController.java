package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.vo.UsuarioVO;
import view.Telas;


public class AdminController {

    // Objetos

    // Inputs

    // Botões
    @FXML private Button btnSair;
    @FXML private Button btnSalvarTurma;
    @FXML private Button btnVoltar;
    @FXML private Button btnTurmas;
    @FXML private Button btnProfessores;
    @FXML private Button btnDisciplinas;
    @FXML private Button btnAdicionar;
    @FXML private Button btnAlunos;
    @FXML private Button btnSim;
    @FXML private Button btnNao;

    // Panes
    @FXML private Pane centerPane;
    @FXML private Pane topPane;
    @FXML private Pane leftPane;
    @FXML private Pane exitPane;

    // Labels

    @FXML private Label lblTituloTela;
    @FXML private Label lblTituloCadastro;


    // ==> Inicialização das Telas

    UsuarioVO userVO = new UsuarioVO();

    public void setUser(UsuarioVO admin) {
        this.userVO = admin;
    }


    // ==> Métodos dos botões de navegação <==

    // transições de telas

    public void carregaTelaTurmas(ActionEvent e) throws Exception {
        Telas.telaInicialAdmin(userVO);
    }

    public void carregaTelaProfessores(ActionEvent e) throws Exception {
        Telas.telaProfessores(userVO);
    }

    public void carregaTelaDisciplinas(ActionEvent e) throws Exception {
        Telas.telaDisciplinas(userVO);
    }

    public void carregaTelaAlunos(ActionEvent e) throws Exception {
        Telas.telaAlunos(userVO);
    }

    public void telaCadastro(ActionEvent e) throws Exception {
        switch (lblTituloTela.getText()) {
            case "Alunos":
                Telas.telaAdicionaAluno(userVO);
                break;
            case "Professores":
                Telas.telaAdicionaProfessor(userVO);
                break;
            case "Disciplinas":
                Telas.telaAdicionaDisciplina(userVO);
                break;
            case "Turmas":
                Telas.telaAdicionaTurma(userVO);
                break;
        
            default:
                break;
        }
    }

    public void cancelaCadastro(ActionEvent e) throws Exception {
        switch (lblTituloCadastro.getText()) {
            case "Aluno":
                Telas.telaAlunos(userVO);
                break;
            case "Professor":
                Telas.telaProfessores(userVO);
                break; 
            case "Disciplina":
                Telas.telaDisciplinas(userVO);
                break;
            case "Turma":
                Telas.telaInicialAdmin(userVO);
                break;       
            default:
                break;
        }
    }

    public void logoff(ActionEvent event) {
        centerPane.setOpacity(0.5);
        topPane.setOpacity(0.5);
        leftPane.setOpacity(0.5);
        exitPane.setDisable(false);
        exitPane.setVisible(true);
    }

    public void desativaExitPane(ActionEvent event) {
        centerPane.setOpacity(1);
        topPane.setOpacity(1);
        leftPane.setOpacity(1);
        exitPane.setDisable(true);
        exitPane.setVisible(false);
    }

    public void voltaTelaLogin( ActionEvent e) throws Exception {
        Telas.telaLogin();
    }
    
    // Efeitos
    
    public void acenderBtn(MouseEvent e){     
        switch (e.getSource().toString()) {
            case "Button[id=btnTurmas, styleClass=button]'Turmas'":
                btnTurmas.setOpacity(0.7);
                break;

            case "Button[id=btnProfessores, styleClass=button]'Professores'":
                btnProfessores.setOpacity(0.7);
                break;  
                
            case "Button[id=btnAlunos, styleClass=button]'Alunos'":
                btnAlunos.setOpacity(0.7);
                break;

            case "Button[id=btnDisciplinas, styleClass=button]'Disciplinas'":
                btnDisciplinas.setOpacity(0.7);
                break;

            case "Button[id=btnSalvarTurma, styleClass=button]'Salvar'":
                btnSalvarTurma.setOpacity(0.9);
                break;

            case "Button[id=btnVoltar, styleClass=button]'Voltar'":
                btnVoltar.setOpacity(0.9);
                break;

            case "Button[id=btnAdicionar, styleClass=button]'Adicionar'":
                btnAdicionar.setOpacity(0.9);
                break;

            case "Button[id=btnSim, styleClass=button]'SIM'":
                btnSim.setOpacity(0.9);
                break;

            case "Button[id=btnNao, styleClass=button]'NÃO'":
                btnNao.setOpacity(0.9);
                break;
        
            default:
                break;
        }
    }

    public void apagarBtn(MouseEvent e) {
        switch (e.getSource().toString()) {
            case "Button[id=btnTurmas, styleClass=button]'Turmas'":
                btnTurmas.setOpacity(1);
                break;

            case "Button[id=btnProfessores, styleClass=button]'Professores'":
                btnProfessores.setOpacity(1);
                break;  
                
            case "Button[id=btnAlunos, styleClass=button]'Alunos'":
                btnAlunos.setOpacity(1);
                break;

            case "Button[id=btnDisciplinas, styleClass=button]'Disciplinas'":
                btnDisciplinas.setOpacity(1);
                break;
            
            case "Button[id=btnSalvarTurma, styleClass=button]'Salvar'":
                btnSalvarTurma.setOpacity(1);
                break;

            case "Button[id=btnVoltar, styleClass=button]'Voltar'":
                btnVoltar.setOpacity(1);
                break;

            case "Button[id=btnAdicionar, styleClass=button]'Adicionar'":
                btnAdicionar.setOpacity(1);
                break;

            case "Button[id=btnSim, styleClass=button]'SIM'":
                btnSim.setOpacity(1);
                break;

            case "Button[id=btnNao, styleClass=button]'NÃO'":
                btnNao.setOpacity(1);
                break;
        
            default:
                break;
        }
    }

    

}
