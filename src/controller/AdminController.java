package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import model.vo.EnderecoVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;
import model.vo.UsuarioVO;
import view.Telas;


public class AdminController {

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
    @FXML private Pane alertPane;

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

    public void setUser(UsuarioVO admin) {
        this.userVO = admin;
    }

    public void carregaChoiceBox() {
        List<DisciplinaVO> disciplinas = new ArrayList<DisciplinaVO>();
        DisciplinaBO disciplinaBO = new DisciplinaBO();

        disciplinas = disciplinaBO.listar();

        ObservableList<DisciplinaVO> obsDisciplinas = FXCollections.observableArrayList(disciplinas);

        cbDisciplina.setItems(obsDisciplinas);

        List<ProfessorVO> professores = new ArrayList<ProfessorVO>();
        ProfessorBO professorBO = new ProfessorBO();

        professores = professorBO.listar();

        ObservableList<ProfessorVO> obsProfessores = FXCollections.observableArrayList(professores);

        cbProfessor.setItems(obsProfessores);
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
                    obsTest.add(new modelAdmin(aluno.getNome(), aluno.getMatricula(), aluno.getEndereco().getEndereco() + ", " + aluno.getEndereco().getCidade() + ", " + aluno.getEndereco().getUf(), "", "", "", "", "", ""));
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
                    obsTest.add(new modelAdmin(professor.getNome(), professor.getCpf(), professor.getEndereco().getEndereco() + ", " + professor.getEndereco().getCidade() + ", " + professor.getEndereco().getUf(), turmaBO.turmasProfString(turmasProfessor) , "", "", "", "", ""));
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
                    obsTest.add(new modelAdmin(disciplina.getCodigo(), disciplina.getNome(), "", "", "", "", "", "", ""));
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
                    obsTest.add(new modelAdmin(turma.getCodigo(), turma.getProfessor().getNome(), turma.getDisciplina().getNome(), turma.getHorario() ,String.valueOf(alunosDaTurma.size()) , String.valueOf(turma.isAberta()), turma.getLocal(), "", ""));
                }
        
                tblGeral.setItems(obsTest);                
                break;
            }
        
            default:
                break;
        }

    }

        // metodos para preenchimento automatico

    public void matricula() {
        AlunoBO aluno = new AlunoBO();
        input1.setText(aluno.gerarMatricula());
    }

    public void codigoDiscilpina() throws Exception {
        DisciplinaBO disciplinaBO = new DisciplinaBO();
        input2.setText(disciplinaBO.geraCodigoDisciplina());
    }

    public void codigoTurma() throws Exception {
        TurmaBO turmaBO = new TurmaBO();
        input1.setText(turmaBO.geraCodigoTurma());
    }

    public String geraSenha() {
        Random r = new Random();
        return String.valueOf(r.nextInt(1000000)); 
    }

    // ==> Métodos principais <==

    public void salvaCadastro() throws Exception {
        switch (lblTituloCadastro.getText()) {
            case "Aluno":{
                AlunoVO cadastroAluno = new AlunoVO();
                EnderecoVO cadastroEndereco = new EnderecoVO();
                AlunoBO aluno = new AlunoBO();

                try {

                    if (!input1.getText().isBlank() && !input2.getText().isBlank() && !input3.getText().isBlank() && !input4.getText().isBlank() && !input5.getText().isBlank() && !input6.getText().isBlank()) {
                        cadastroAluno.setMatricula(input1.getText());
                        cadastroAluno.setNome(input2.getText());
                        cadastroEndereco.setEndereco(input3.getText() + ", " + input4.getText());
                        cadastroEndereco.setCidade(input5.getText());
                        cadastroEndereco.setUf(input6.getText());
                    } else {
                        throw new Exception();
                    }
            
                    cadastroAluno.setEndereco(cadastroEndereco);
                    cadastroAluno.setUsername(cadastroAluno.getMatricula());
                    cadastroAluno.setSenha(String.valueOf(geraSenha()));

                    aluno.salvar(cadastroAluno);

                    concluidoPane.setDisable(false);
                    concluidoPane.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    painelErro.setDisable(false);
                    painelErro.setVisible(true);
                    
                }
                break;
            }
                 
            case "Professor": {
                ProfessorVO cadastroProfessor = new ProfessorVO();
                EnderecoVO cadastroEndereco = new EnderecoVO();
                ProfessorBO professor = new ProfessorBO();

                try {
                    if (!input1.getText().isBlank() && !input2.getText().isBlank() && !input3.getText().isBlank() && !input4.getText().isBlank() && !input5.getText().isBlank() && !input6.getText().isBlank()) {
                        cadastroProfessor.setCpf(input1.getText());
                        cadastroProfessor.setNome(input2.getText());
                        cadastroEndereco.setEndereco(input3.getText() + ", " + input4.getText());
                        cadastroEndereco.setCidade(input5.getText());
                        cadastroEndereco.setUf(input6.getText());
                    } else {
                        throw new Exception();
                    }

                    cadastroProfessor.setEndereco(cadastroEndereco);
                    cadastroProfessor.setUsername(cadastroProfessor.getCpf());
                    cadastroProfessor.setSenha(String.valueOf(geraSenha()));

                    professor.salvar(cadastroProfessor);

                    concluidoPane.setDisable(false);
                    concluidoPane.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    painelErro.setDisable(false);
                    painelErro.setVisible(true);
                }
                break;
            }
                
            case "Disciplina": {
                DisciplinaVO cadastroDisciplina = new DisciplinaVO();
                DisciplinaBO disciplina = new DisciplinaBO();
                try {
                    cadastroDisciplina.setCodigo(input2.getText());

                    if (!input1.getText().isBlank()) {
                        cadastroDisciplina.setNome(input1.getText());
                    } else {
                        throw new Exception();
                    }

                    disciplina.salvar(cadastroDisciplina);

                    concluidoPane.setDisable(false);
                    concluidoPane.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    painelErro.setDisable(false);
                    painelErro.setVisible(true);
                }

                break;
            }

            case "Turma": {   
                TurmaVO cadastroTurma = new TurmaVO();
                TurmaBO turma = new TurmaBO();
                try {
                    cadastroTurma.setDisciplina(cbDisciplina.getValue());
                    cadastroTurma.setProfessor(cbProfessor.getValue());
                    cadastroTurma.setCodigo(input1.getText());
                    if (!input1.getText().isBlank() && !input2.getText().isBlank() && !input3.getText().isBlank()) {
                        cadastroTurma.setHorario(input2.getText());
                        cadastroTurma.setLocal(input3.getText());
                        cadastroTurma.setAberta(true);
                    } else {
                        throw new Exception();
                    }
                    
                    turma.salvar(cadastroTurma);

                    concluidoPane.setDisable(false);
                    concluidoPane.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    painelErro.setDisable(false);
                    painelErro.setVisible(true);
                }

                break;
            }
            
            default:
                break;
        }
    }

    public void removerItems(ActionEvent e) throws Exception {
        switch (lblTituloTela.getText()) {
            case "Alunos": {
                try {
                    List<String> matriculas = new ArrayList<String>();
                    ObservableList<modelAdmin> obsList = tblGeral.getItems();
                    for (modelAdmin obs : obsList) {
                        if (obs.isAction()) {
                            matriculas.add(obs.getColuna2());
                        }
                    }
        
                    AlunoBO alunoBO = new AlunoBO();
                    List<AlunoVO> alunosVO = new ArrayList<AlunoVO>();
                    for (String matricula : matriculas) {
                        if (alunoBO.buscarPorMatricula(matricula) != null)
                            alunosVO.add(alunoBO.buscarPorMatricula(matricula));
                    }
        
                    for (AlunoVO alunoVO : alunosVO) {
                        alunoBO.remover(alunoVO);
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
                Telas.telaAlunos(userVO);
                break;
            }

            case "Professores": {
                try {
                    List<String> cpfs = new ArrayList<String>();
                    ObservableList<modelAdmin> obsList = tblGeral.getItems();
                    for (modelAdmin obs : obsList) {
                        if (obs.isAction()) {
                            cpfs.add(obs.getColuna2());
                        }
                    }
        
                    ProfessorBO professorBO = new ProfessorBO();
                    List<ProfessorVO> professoresVO = new ArrayList<ProfessorVO>();
                    for (String cpf : cpfs) {
                        if (professorBO.getByCPF(cpf) != null)
                            professoresVO.add(professorBO.getByCPF(cpf));
                    }

                    TurmaBO turmaBO = new TurmaBO();
                    List<TurmaVO> turmasVO = new ArrayList<TurmaVO>(); 
                    
                    for (ProfessorVO professor : professoresVO) {
                        turmasVO = turmaBO.getTurmasDoProfessor(professor.getId());
                        if (turmasVO.size() < 1) {
                            alertPane.setVisible(true);
                        } else {
                            professorBO.remover(professor);
                        }
                    }
            
                } catch (Exception a) {
                    a.printStackTrace();
                }
                Telas.telaProfessores(userVO);
                break;
            }

            case "Disciplinas": {
                try {
                    List<String> codigos = new ArrayList<String>();
                    ObservableList<modelAdmin> obsList = tblGeral.getItems();
                    for (modelAdmin obs : obsList) {
                        if (obs.isAction()) {
                            codigos.add(obs.getColuna1());
                        }
                    }
        
                    DisciplinaBO disciplinaBO = new DisciplinaBO();
                    List<DisciplinaVO> disciplinasVO = new ArrayList<DisciplinaVO>();
                    for (String codigo : codigos) {
                        if (disciplinaBO.buscarDisciplinaPorCod(codigo) != null)
                            disciplinasVO.add(disciplinaBO.buscarDisciplinaPorCod(codigo));
                    }

                    TurmaBO turmaBO = new TurmaBO();
                    Boolean extTurmas = false;

                    for (DisciplinaVO disciplina : disciplinasVO) {
                        extTurmas = turmaBO.getTurmasByDisciplina(disciplina.getId());
                        if (extTurmas) {
                            alertPane.setVisible(true);
                        } else {
                            disciplinaBO.remover(disciplina);
                        }
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
                Telas.telaDisciplinas(userVO);
                break;                
            }

            case "Turmas": {
                try {
                    List<String> codigos = new ArrayList<String>();
                    ObservableList<modelAdmin> obsList = tblGeral.getItems();
                    for (modelAdmin obs : obsList) {
                        if (obs.isAction()) {
                            codigos.add(obs.getColuna1());
                        }
                    }
        
                    TurmaBO turmaBO = new TurmaBO();
                    List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
                    for (String codigo : codigos) {
                        if (turmaBO.getByCodigo(codigo) != null)
                            turmasVO.add(turmaBO.getByCodigo(codigo));
                    }
        
                    for (TurmaVO turma : turmasVO) {
                        turmaBO.remover(turma);
                    }
                } catch (Exception a) {
                    a.printStackTrace();
                }
                Telas.telaInicialAdmin(userVO);
                break;
            }

            default:
                break;
        }


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

    public void telaEditOrSave(ActionEvent e) throws Exception {
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

    public void fechaMsgErro() {
        painelErro.setDisable(true);
        painelErro.setVisible(false);
    }

    public void voltaTelaLogin( ActionEvent e) throws Exception {
        Telas.telaLogin();
    }

    public void perguntaRemover(ActionEvent e) {
        removePane.setVisible(true);
        removePane.setDisable(false);
    }

    public void desativaAlert() {
        alertPane.setVisible(false);
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
