package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.bo.AlunoBO;
import model.bo.DisciplinaBO;
import model.bo.ProfessorBO;
import model.bo.TurmaBO;
import model.vo.AlunoVO;
import model.vo.DisciplinaVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
import model.vo.UsuarioVO;
import view.Telas;

public class ProfessorController {
    
   // Objetos 
   UsuarioVO userVO = new UsuarioVO();

   // Inputs
   @FXML private TextField input1;
   @FXML private TextField input2;
   @FXML private TextField input3;
   @FXML private TextField input4;
   @FXML private TextField input5;
   @FXML private TextField input6;

   // Botões
   @FXML private Button btnSair;
   @FXML private Button btnSalvarCadastro;
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
   @FXML private Pane concluidoPane;
   @FXML private Pane painelErro;
   @FXML private Pane removePane;

   // Labels
   @FXML private Label lblTituloTela;
   @FXML private Label lblTituloCadastro;

   // ChoiceBox
   @FXML private ChoiceBox<DisciplinaVO> cbDisciplina;
   @FXML private ChoiceBox<ProfessorVO> cbProfessor;

   // TableViews e Collums

   @FXML private TableView<modelAdmin> tblGeral;
   @FXML private TableColumn<modelAdmin, String> colunm1;
   @FXML private TableColumn<modelAdmin, String> colunm2;
   @FXML private TableColumn<modelAdmin, String> colunm3;
   @FXML private TableColumn<modelAdmin, String> colunm4;
   @FXML private TableColumn<modelAdmin, String> colunm5;
   @FXML private TableColumn<modelAdmin, String> colunm6;
   @FXML private TableColumn<modelAdmin, String> colunm7;
   @FXML private TableColumn<modelAdmin, Boolean> colunmAction;


   // ==> Inicialização das Telas <==

   public void setProfessor(UsuarioVO professor) {
       this.userVO = professor;
   }

   public void carregaTabelas() throws Exception {
       switch (lblTituloTela.getText()) {
           case "Alunos": {
               List<AlunoVO> alunosVO = new ArrayList<AlunoVO>();
               AlunoBO alunoBO = new AlunoBO();
       
               alunosVO = alunoBO.listar();
       
               
               colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
               colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
               colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
               colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
       
               colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
       
               ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
       
               for (AlunoVO aluno : alunosVO) {
                   obsTest.add(new modelAdmin(aluno.getNome(), aluno.getMatricula(), aluno.getEndereco().getEndereco() + ", " + aluno.getEndereco().getCidade() + ", " + aluno.getEndereco().getUf(), "", "", "", ""));
               }
       
               tblGeral.setItems(obsTest);                            
               break;
           }

           case "Professores": {
               List<ProfessorVO> professoresVO = new ArrayList<ProfessorVO>();
               List<TurmaVO> turmasProfessor = new ArrayList<TurmaVO>(); 
               ProfessorBO professorBO = new ProfessorBO();
               TurmaBO turmaBO = new TurmaBO();
       
               professoresVO = professorBO.listar();
               
               
               colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
               colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
               colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
               colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
               colunm4.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna4"));
       
               colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
       
               ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
       
               for (ProfessorVO professor : professoresVO) {
                   turmasProfessor = turmaBO.getTurmasDoProfessor(professor.getId());
                   obsTest.add(new modelAdmin(professor.getNome(), professor.getCpf(), professor.getEndereco().getEndereco() + ", " + professor.getEndereco().getCidade() + ", " + professor.getEndereco().getUf(), turmaBO.turmasProfString(turmasProfessor) , "", "", ""));
               }
       
               tblGeral.setItems(obsTest);                
               break;
           }

           case "Disciplinas": {
               List<DisciplinaVO> disciplinasVO = new ArrayList<DisciplinaVO>();
               DisciplinaBO disciplinaBO = new DisciplinaBO();
       
               disciplinasVO = disciplinaBO.listar();
       
               
               colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
               colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
               colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
       
               colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
       
               ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
       
               for (DisciplinaVO disciplina : disciplinasVO) {
                   obsTest.add(new modelAdmin(disciplina.getCodigo(), disciplina.getNome(), "", "", "", "", ""));
               }
       
               tblGeral.setItems(obsTest);                            
               break;
           }
           case "Turmas": {
               List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
               List<AlunoVO> alunosDaTurma = new ArrayList<AlunoVO>();
               TurmaBO turmaBO = new TurmaBO();
               AlunoBO alunoBO = new AlunoBO();
       
               turmasVO = turmaBO.listar();
               
               
               colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
               colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
               colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
               colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
               colunm4.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna4"));
               colunm5.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna5"));
               colunm6.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna6"));
               colunm7.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna7"));
       
               colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
       
               ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
       
               for (TurmaVO turma : turmasVO) {
                   alunosDaTurma = alunoBO.buscarPorTurma(turma.getCodigo());
                   obsTest.add(new modelAdmin(turma.getCodigo(), turma.getProfessor().getNome(), turma.getDisciplina().getNome(), turma.getHorario() ,String.valueOf(alunosDaTurma.size()) , String.valueOf(turma.isAberta()), turma.getLocal()));
               }
       
               tblGeral.setItems(obsTest);                
               break;
           }
       
           default:
               break;
       }

   }

   

   public void carregaTelaTurmas(ActionEvent e) throws Exception {
       Telas.telaInicialAdmin(userVO);
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

           case "Button[id=btnSalvarCadastro, styleClass=button]'Salvar'":
               btnSalvarCadastro.setOpacity(0.9);
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
           
           case "Button[id=btnSalvarCadastro, styleClass=button]'Salvar'":
               btnSalvarCadastro.setOpacity(1);
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