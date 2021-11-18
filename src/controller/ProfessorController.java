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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.bo.AlunoBO;
import model.bo.DiarioBO;
import model.bo.TurmaBO;
import model.bo.UsuarioBO;
import model.vo.AlunoVO;
import model.vo.DiarioVO;
import model.vo.DisciplinaVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
import view.Telas;

public class ProfessorController {
    
   // Objetos 
   ProfessorVO profVO = new ProfessorVO();
   TurmaVO turmaDiarios = new TurmaVO();

   @FXML private TextField inputPesquisa;

   // Botões
   @FXML private Button btnSair;
   @FXML private Button btnSalvar;
   @FXML private Button btnVoltar;
   @FXML private Button btnTurmas;
   @FXML private Button btnSim;
   @FXML private Button btnNao;
   @FXML private ImageView btnPesquisar;  
   @FXML private Button username;  

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
   @FXML private Label lblDisciplina;

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
   @FXML private TableColumn<modelAdmin, String> colunm8;
   @FXML private TableColumn<modelAdmin, String> colunm9;
   @FXML private TableColumn<modelAdmin, Boolean> colunmAction;


   // ==> Inicialização das Telas <==

    public void setProfessor(ProfessorVO professor) {
       this.profVO = professor;
   }

   public void setTurmaDiarios(TurmaVO turma) {
       this.turmaDiarios = turma;
   }

   public void carregaTabelas(ProfessorVO usuario, TurmaVO selectedTurma) throws Exception {
    username.setText(usuario.getNome());
    
    switch (lblTituloTela.getText()) {
           case "Turmas": {
               List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
               List<AlunoVO> alunosDaTurma = new ArrayList<AlunoVO>();
               TurmaBO turmaBO = new TurmaBO();
               AlunoBO alunoBO = new AlunoBO();
       
               turmasVO = turmaBO.getTurmasDoProfessor(usuario.getId());
               
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
                    String status = "";
                    alunosDaTurma = alunoBO.buscarPorTurma(turma.getId());
                    if(turma.isAberta()) {
                        status = "Aberta";
                    } else {
                        status = "Fechada";
                    }
                    obsTest.add(new modelAdmin(turma.getCodigo(), turma.getProfessor().getNome(), turma.getDisciplina().getNome(), turma.getHorario() ,String.valueOf(alunosDaTurma.size()) , status, turma.getLocal(), "", "" ) );
                }
       
                tblGeral.setItems(obsTest);                
                break;
           }
           
           case "Diário": {
            lblDisciplina.setText(selectedTurma.getCodigo());
                List<DiarioVO> diariosVO = new ArrayList<DiarioVO>();
                DiarioBO diarioBO = new DiarioBO();
                List<AlunoVO> alunosDaTurma = new ArrayList<AlunoVO>();
                AlunoBO alunoBO = new AlunoBO();
                
                colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
                colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
                colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
                colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
                colunm4.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna4"));
                colunm5.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna5"));
                colunm6.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna6"));
                colunm7.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna7"));
                colunm8.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna8"));

                colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
                colunm2.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm3.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm4.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm5.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm6.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm7.setCellFactory(TextFieldTableCell.forTableColumn());
                colunm8.setCellFactory(TextFieldTableCell.forTableColumn());

                alunosDaTurma = alunoBO.buscarPorTurma(selectedTurma.getId());
                for (AlunoVO aluno : alunosDaTurma) {
                    diariosVO.add(diarioBO.buscarPorAlunoETurma(aluno.getId(), selectedTurma.getId()));
                }

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
                for (DiarioVO diario : diariosVO) {
                    String status = "";
                    if (diario.getTurma().isAberta()) {
                        status = "Aberta";
                    } else {
                        status = "Fechada";
                    }
                    obsTest.add(new modelAdmin(diario.getAluno().getNome(), String.valueOf(diario.getNota1()), String.valueOf(diario.getNota2()), String.valueOf(diario.getNota3()), String.valueOf(diario.getQuartaProva()), String.valueOf(diario.getMedia()), String.valueOf(diario.getFrequencia()), "", status));
                }
                
                tblGeral.setItems(obsTest);                
                break;
        }
       
           default:
               break;
       }

   }

   public void salvarDiario(ActionEvent e) {
        DiarioVO diarioVO = new DiarioVO();
        DiarioBO diarioBO = new DiarioBO();
        AlunoBO alunoBO = new AlunoBO();
        try {
            ObservableList<modelAdmin> obsList = tblGeral.getItems();
            for (modelAdmin obs : obsList) {
                if (obs.isAction()) {
                    for (AlunoVO aluno : turmaDiarios.getAlunos()) {
                        if(aluno.getNome().equals(obs.getColuna1()))
                            diarioVO.setAluno(alunoBO.getById(aluno.getId()));
                    }
                    diarioVO = diarioBO.buscarPorAlunoETurma(diarioVO.getAluno().getId(), turmaDiarios.getId());
                    diarioVO.setTurma(turmaDiarios);
                    diarioVO.setNota1(Double.valueOf(obs.getColuna2()));
                    diarioVO.setNota2(Double.valueOf(obs.getColuna3()));
                    diarioVO.setNota3(Double.valueOf(obs.getColuna4()));
                    diarioVO.setQuartaProva(Double.valueOf(obs.getColuna5()));
                    diarioVO.setMedia(Double.valueOf(obs.getColuna6()));
                    diarioVO.setFrequencia(Integer.valueOf(obs.getColuna7()));

                    diarioBO.editar(diarioVO);
                }
            }

            Telas.telaInicialProfessor(profVO);
        } catch (Exception t) {
            t.printStackTrace();
        }
   }

   // ==> Navegação de telas

   public void carregaTelaTurmas(ActionEvent e) throws Exception {
       Telas.telaInicialProfessor(profVO);
   }

   public void abreDiario(ActionEvent e) throws Exception {
        TurmaBO turmaBO = new TurmaBO();
        TurmaVO turmaVO = new TurmaVO();
        AlunoBO alunoBO = new AlunoBO();
        String codigo = "";
        ObservableList<modelAdmin> obsList = tblGeral.getItems();
        for (modelAdmin obs : obsList) {
            if (obs.isAction() && codigo.equals("")) {
                codigo = obs.getColuna1();
            }
        }

        turmaVO = turmaBO.getByCodigo(codigo);

        turmaVO.setAlunos(alunoBO.buscarPorTurma(turmaVO.getId()));

        
        if(turmaVO.isAberta()) {
            Telas.diarioProfessor(profVO, turmaVO);
        } else {
            Telas.telaInicialProfessor(profVO);
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

           case "Button[id=btnVoltar, styleClass=button]'Voltar'":
               btnVoltar.setOpacity(0.9);
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

           case "Button[id=btnVoltar, styleClass=button]'Voltar'":
               btnVoltar.setOpacity(1);
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

   public void pesquisar(MouseEvent e) throws Exception {
    switch (lblTituloTela.getText()) {
        case "Diário": {
            String palavra = inputPesquisa.getText();

            DiarioBO diarioBO = new DiarioBO();
            List<DiarioVO> diariosVO = new ArrayList<DiarioVO>();

            diariosVO = diarioBO.getDiarioByAlunoName(palavra);

            ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();

            for (DiarioVO diario : diariosVO) {
                obsTest.add(new modelAdmin(diario.getAluno().getNome(), String.valueOf(diario.getNota1()), String.valueOf(diario.getNota2()), String.valueOf(diario.getNota3()), String.valueOf(diario.getQuartaProva()), String.valueOf(diario.getMedia()), String.valueOf(diario.getFrequencia()), "", ""));
            }

            tblGeral.setItems(obsTest);
            break;
        }
    }
   }
   
    
}