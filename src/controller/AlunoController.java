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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.bo.AlunoBO;
import model.bo.DiarioBO;
import model.bo.TurmaBO;
import model.vo.AlunoVO;
import model.vo.DiarioVO;
import model.vo.DisciplinaVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
import view.Telas;

public class AlunoController {
    
   // Objetos 
   AlunoVO userAluno = new AlunoVO();
   TurmaVO turmaDiarios = new TurmaVO();

   @FXML private TextField inputPesquisa;

   // Botões
   @FXML private Button btnSair;
   @FXML private Button btnRealizarMatriculas;
   @FXML private Button btnVoltar;
   @FXML private Button btnMatriculas;
   @FXML private Button btnHistoricos;
   @FXML private Button btnDisciplinas;
   @FXML private Button btnSim;
   @FXML private Button btnNao;
   @FXML private ImageView btnPesquisar;  

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
   @FXML private Label lblDisciplina;;

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

    public void setAluno(AlunoVO aluno) {
       this.userAluno = aluno;
   }

   public void setTurmaDiarios(TurmaVO turma) {
       this.turmaDiarios = turma;
   }

   public void carregaTabelas(AlunoVO usuario) throws Exception {
       switch (lblTituloTela.getText()) {
           case "Disciplinas Ativas": {
               List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
               AlunoBO alunoBO = new AlunoBO();
               DiarioVO diarioVO = new DiarioVO();
               DiarioBO diarioBO = new DiarioBO();
       
               turmasVO = alunoBO.getTurmasAtivasByAluno(usuario);
               
               colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
               colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
               colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
               colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
               colunm4.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna4"));
               colunm5.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna5"));
               colunm6.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna6"));
               colunm7.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna7"));
               colunm8.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna8"));
               colunm8.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna9"));
       
               colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));
       
               ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
       
                for (TurmaVO turma : turmasVO) {
                    String status = "";
                    diarioVO = diarioBO.buscarPorAlunoETurma(turma.getId(), usuario.getId());
                    if(turma.isAberta()) {
                        status = "Aberta";
                    } else {
                        status = "Fechada";
                    }
                    obsTest.add(new modelAdmin(turma.getDisciplina().getCodigo() , turma.getDisciplina().getNome(), String.valueOf(diarioVO.getNota1()) , String.valueOf(diarioVO.getNota2()) ,String.valueOf(diarioVO.getNota3()) , String.valueOf(diarioVO.getQuartaProva()), String.valueOf(diarioVO.getMedia()), String.valueOf(diarioVO.getFrequencia()), status ) );
                }
       
                tblGeral.setItems(obsTest);                
                break;
           }
           
           case "Disciplinas Concluidas": {
                List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
                AlunoBO alunoBO = new AlunoBO();
                DiarioVO diarioVO = new DiarioVO();
                DiarioBO diarioBO = new DiarioBO();

                turmasVO = alunoBO.getTurmasInativasByAluno(usuario);
                
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

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
                for (TurmaVO turma : turmasVO) {
                    String status = "";
                    diarioVO = diarioBO.buscarPorAlunoETurma(turma.getId(), usuario.getId());
                    if (diarioVO.getMedia() >= 7) {
                        status = "Aprovado";
                    } else {
                        status = "Reprovado";
                    }
                    obsTest.add(new modelAdmin(turma.getDisciplina().getCodigo() , turma.getDisciplina().getNome(), String.valueOf(diarioVO.getNota1()) , String.valueOf(diarioVO.getNota2()) ,String.valueOf(diarioVO.getNota3()) , String.valueOf(diarioVO.getQuartaProva()), String.valueOf(diarioVO.getMedia()), String.valueOf(diarioVO.getFrequencia()), status ) );
                }
                
                tblGeral.setItems(obsTest);                
                break;
        }

        case "Matriculas": {
            List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
            AlunoBO alunoBO = new AlunoBO();

            turmasVO = alunoBO.getTurmasDisponiveisParaMatriculaByAluno(usuario);
            
            colunmAction.setCellValueFactory(new PropertyValueFactory<modelAdmin, Boolean>("action"));
            colunm1.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna1"));
            colunm2.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna2"));
            colunm3.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna3"));
            colunm4.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna4"));
            colunm5.setCellValueFactory(new PropertyValueFactory<modelAdmin, String>("coluna5"));

            colunmAction.setCellFactory(CheckBoxTableCell.forTableColumn(colunmAction));

            ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
            for (TurmaVO turma : turmasVO) {
                obsTest.add(new modelAdmin(turma.getCodigo(), turma.getDisciplina().getCodigo(), turma.getDisciplina().getNome(), turma.getLocal(), turma.getHorario(), "", "", "", ""));
            }
            
            tblGeral.setItems(obsTest);                
            break;
        }
       
           default:
               break;
       }

   }

   public void gerarPdfHistorico(ActionEvent e) {
       AlunoBO alunoBO = new AlunoBO();
       alunoBO.gerarHistorico(userAluno);
   }

   public void matricularAluno(ActionEvent e) {
        try {
            DiarioVO diarioVO = new DiarioVO();
            DiarioBO diarioBO = new DiarioBO();
            TurmaVO turmaVO = new TurmaVO();
            TurmaBO turmaBO = new TurmaBO();

            ObservableList<modelAdmin> obs = tblGeral.getItems();
            for (modelAdmin modelAdmin : obs) {
                if (modelAdmin.isAction() && turmaVO == null) {
                    turmaVO = turmaBO.getByCodigo(modelAdmin.getColuna1());
                }
            }
    
            diarioVO = diarioBO.buscarPorAlunoETurma(userAluno.getId(), turmaVO.getId());
            diarioVO.setAluno(userAluno);
            diarioVO.setTurma(turmaDiarios);
            diarioBO.salvar(diarioVO);
        } catch (Exception a) {
            a.printStackTrace();
        }
   }

   public void pesquisar() throws Exception {
        
        
   }

   // ==> Navegação de telas

   public void carregaTelaDisciplinas(ActionEvent e) throws Exception {
       Telas.telaInicialAluno(userAluno);
   }

   public void abreHistorico(ActionEvent e) throws Exception {
        Telas.telaAlunoHistorico(userAluno, turmaDiarios);
   }

   public void carregaTelaMatricula(ActionEvent e) throws Exception {
        Telas.telaMatriculas(userAluno, turmaDiarios);
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
    
}
