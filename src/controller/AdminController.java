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
import model.bo.EnderecoBO;
import model.bo.ProfessorBO;
import model.bo.TurmaBO;
import model.bo.UsuarioBO;
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
    AlunoVO alunoEdit = new AlunoVO();
    ProfessorVO professorEdit = new ProfessorVO();
    TurmaVO turmaEdit = new TurmaVO();
    DisciplinaVO disciplinaEdit = new DisciplinaVO();

    // Inputs
    @FXML private TextField input1;
    @FXML private TextField input2;
    @FXML private TextField input3;
    @FXML private TextField input4;
    @FXML private TextField input5;
    @FXML private TextField input6;
    @FXML private TextField inputPesquisa;

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
    @FXML private Button btnPesquisar;
    

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

    public void setAlunoEdit(AlunoVO aluno) {
        this.alunoEdit = aluno;
    }
    
    public void setProfessorEdit(ProfessorVO professor) {
        this.professorEdit = professor;
    }

    public void setDisciplinaEdit(DisciplinaVO disciplina) {
        this.disciplinaEdit = disciplina;
    }

    public void setTurmaEdita(TurmaVO turma) {
        this.turmaEdit = turma;
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
                    String status = "";
                    alunosDaTurma = alunoBO.buscarPorTurma(turma.getId());
                    if(turma.isAberta()) {
                        status = "Aberta";
                    } else {
                        status = "Fechada";
                    }
                    obsTest.add(new modelAdmin(turma.getCodigo(), turma.getProfessor().getNome(), turma.getDisciplina().getNome(), turma.getHorario() ,String.valueOf(alunosDaTurma.size()) , status, turma.getLocal(), "", ""));
                }
        
                tblGeral.setItems(obsTest);                
                break;
            }
        
            default:
                break;
        }

    }

    public void carregaEdicaoAluno(AlunoVO aluno) {
        input1.setText(aluno.getMatricula());
        input2.setText(aluno.getNome());
        String endereco = aluno.getEndereco().getEndereco();
        input3.setText(endereco);
        input4.setDisable(true);
        input5.setText(aluno.getEndereco().getCidade());
        input6.setText(aluno.getEndereco().getUf());
    }

    public void carregaEdicaoProfessor(ProfessorVO professor) {
        input1.setText(professor.getCpf());
        input2.setText(professor.getNome());
        String endereco = professor.getEndereco().getEndereco();
        input3.setText(endereco);
        input4.setDisable(true);
        input5.setText(professor.getEndereco().getCidade());
        input6.setText(professor.getEndereco().getUf());
    }

    public void carregaEdicaoDisciplina(DisciplinaVO disciplina) {
        input1.setText(disciplina.getNome());
        input2.setText(disciplina.getCodigo());
        input2.setDisable(true);
    }

    public void carregaEdicaoTurma(TurmaVO turma) {
        cbDisciplina.setValue(turma.getDisciplina());
        cbProfessor.setValue(turma.getProfessor());
        input1.setText(turma.getCodigo());
        input1.setDisable(true);
        input2.setText(turma.getHorario());
        input3.setText(turma.getLocal());
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

    public void saveOrEdit() throws Exception {
        switch (lblTituloCadastro.getText()) {
            case "Aluno":{
                if (alunoEdit.getId() == null) {
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
                } else {
                    AlunoVO cadastroAluno = new AlunoVO();
                    EnderecoVO cadastroEndereco = new EnderecoVO();
                    UsuarioBO aluno = new UsuarioBO();
                    EnderecoBO endereco = new EnderecoBO();
    
                    try {
                        cadastroAluno.setId(alunoEdit.getId());
                        if (!input1.getText().isBlank() && !input2.getText().isBlank() && !input3.getText().isBlank() && !input5.getText().isBlank() && !input6.getText().isBlank()) {
                            cadastroAluno.setMatricula(input1.getText());
                            cadastroAluno.setNome(input2.getText());
                            cadastroEndereco.setEndereco(input3.getText());
                            cadastroEndereco.setCidade(input5.getText());
                            cadastroEndereco.setUf(input6.getText());
                        } else {
                            throw new Exception();
                        }
                        cadastroAluno.setUsername(alunoEdit.getUsername());
                        cadastroAluno.setSenha(alunoEdit.getSenha());
                
                        cadastroAluno.setEndereco(cadastroEndereco);
                        cadastroEndereco.setId(alunoEdit.getEndereco().getId());

                        aluno.editar(cadastroAluno);
                        endereco.editar(cadastroEndereco);
    
                        concluidoPane.setDisable(false);
                        concluidoPane.setVisible(true);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        painelErro.setDisable(false);
                        painelErro.setVisible(true);
                        
                    }
                    break;
                }
                
            }
                 
            case "Professor": {
                if (professorEdit.getId() == null) {
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
                } else {
                    ProfessorVO cadastroProfessor = new ProfessorVO();
                    EnderecoVO cadastroEndereco = new EnderecoVO();
                    UsuarioBO professor = new UsuarioBO();
                    EnderecoBO endereco = new EnderecoBO();

    
                    try {
                        cadastroProfessor.setId(professorEdit.getId());
                        if (!input1.getText().isBlank() && !input2.getText().isBlank() && !input3.getText().isBlank() && !input5.getText().isBlank() && !input6.getText().isBlank()) {
                            cadastroProfessor.setCpf(input1.getText());
                            cadastroProfessor.setNome(input2.getText());
                            cadastroEndereco.setEndereco(input3.getText());
                            cadastroEndereco.setCidade(input5.getText());
                            cadastroEndereco.setUf(input6.getText());
                        } else {
                            throw new Exception();
                        }
                        cadastroProfessor.setUsername(professorEdit.getUsername());
                        cadastroProfessor.setSenha(professorEdit.getSenha());
    
                        cadastroProfessor.setEndereco(cadastroEndereco);
                        cadastroEndereco.setId(professorEdit.getEndereco().getId());
    
                        professor.editar(cadastroProfessor);
                        endereco.editar(cadastroEndereco);
    
                        concluidoPane.setDisable(false);
                        concluidoPane.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                        painelErro.setDisable(false);
                        painelErro.setVisible(true);
                    }
                    break;
                }
            }
                
            case "Disciplina": {
                if (disciplinaEdit.getId() == null) {
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
                } else {
                    DisciplinaVO cadastroDisciplina = new DisciplinaVO();
                    DisciplinaBO disciplina = new DisciplinaBO();
                    try {
                        cadastroDisciplina.setId(disciplinaEdit.getId());
                        cadastroDisciplina.setCodigo(input2.getText());
    
                        if (!input1.getText().isBlank()) {
                            cadastroDisciplina.setNome(input1.getText());
                        } else {
                            throw new Exception();
                        }
    
                        disciplina.editar(cadastroDisciplina);
    
                        concluidoPane.setDisable(false);
                        concluidoPane.setVisible(true);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        painelErro.setDisable(false);
                        painelErro.setVisible(true);
                    }
                    break;
                }

            }

            case "Turma": { 
                if (turmaEdit.getId() == null) {
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
                } else {
                    TurmaVO cadastroTurma = new TurmaVO();
                    TurmaBO turma = new TurmaBO();
                    try {
                        cadastroTurma.setId(turmaEdit.getId());
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
                        
                        turma.editar(cadastroTurma);
    
                        concluidoPane.setDisable(false);
                        concluidoPane.setVisible(true);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                        painelErro.setDisable(false);
                        painelErro.setVisible(true);
                    }
    
                    break;
                }
            
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
                    List<TurmaVO> turmas = new ArrayList<TurmaVO>();

                    for (DisciplinaVO disciplina : disciplinasVO) {
                        turmas = turmaBO.getTurmasByDisciplina(disciplina.getId());
                        if (turmas.size() < 1) {
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
                        if(turma.isAberta()) {
                            turma.setAberta(false);
                            turmaBO.editar(turma);
                        }else {
                            turma.setAberta(true);
                            turmaBO.editar(turma);
                        }
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

    public void pesquisar(MouseEvent e) throws Exception {
        switch (lblTituloTela.getText()) {
            case "Alunos": {
                String palavra = inputPesquisa.getText();

                AlunoBO alunoBO = new AlunoBO();
                List<AlunoVO> alunosVO = new ArrayList<AlunoVO>();

                alunosVO = alunoBO.buscarPorNome(palavra);

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();

                for (AlunoVO aluno : alunosVO) {
                    obsTest.add(new modelAdmin(aluno.getNome(), aluno.getMatricula(), aluno.getEndereco().getEndereco() + ", " + aluno.getEndereco().getCidade() + ", " + aluno.getEndereco().getUf(), "", "", "", "", "", ""));
                }

                tblGeral.setItems(obsTest);
                break;
            }

            case "Professores": {
                String palavra = inputPesquisa.getText();

                ProfessorBO professorBO = new ProfessorBO();
                List<ProfessorVO> professoresVO = new ArrayList<ProfessorVO>();

                professoresVO = professorBO.buscarPorNome(palavra);

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();

                List<TurmaVO> turmasProfessor = new ArrayList<TurmaVO>();
                TurmaBO turmaBO = new TurmaBO();

                for (ProfessorVO professor : professoresVO) {
                    obsTest.add(new modelAdmin(professor.getNome(), professor.getCpf(), professor.getEndereco().getEndereco() + ", " + professor.getEndereco().getCidade() + ", " + professor.getEndereco().getUf(), turmaBO.turmasProfString(turmasProfessor) , "", "", "", "", ""));
                }

                tblGeral.setItems(obsTest);
                break;
            }
            
            case "Disciplinas": {
                String palavra = inputPesquisa.getText();

                DisciplinaBO disciplinaBO = new DisciplinaBO();
                List<DisciplinaVO> disciplinasVO = new ArrayList<DisciplinaVO>();

                disciplinasVO = disciplinaBO.buscarPorNome(palavra);

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
        
                for (DisciplinaVO disciplina : disciplinasVO) {
                    obsTest.add(new modelAdmin(disciplina.getCodigo(), disciplina.getNome(), "", "", "", "", "", "", ""));
                }

                tblGeral.setItems(obsTest);
                break;
            }

            case "Turmas": {
                String palavra = inputPesquisa.getText();

                TurmaBO turmaBO = new TurmaBO();
                List<TurmaVO> turmasVO = new ArrayList<TurmaVO>();
                AlunoBO alunoBO = new AlunoBO();
                List<AlunoVO> alunosDaTurma = new ArrayList<AlunoVO>();

                ProfessorBO professorBO = new ProfessorBO();
                List<ProfessorVO> professoresVO = new ArrayList<ProfessorVO>();
                professoresVO = professorBO.buscarPorNome(palavra);

                for (ProfessorVO professor : professoresVO) {
                    turmasVO.addAll(turmaBO.getTurmasDoProfessor(professor.getId()));
                }

                ObservableList<modelAdmin> obsTest = FXCollections.observableArrayList();
        
                for (TurmaVO turma : turmasVO) {
                    alunosDaTurma = alunoBO.buscarPorTurma(turma.getId());
                    obsTest.add(new modelAdmin(turma.getCodigo(), turma.getProfessor().getNome(), turma.getDisciplina().getNome(), turma.getHorario() ,String.valueOf(alunosDaTurma.size()) , String.valueOf(turma.isAberta()), turma.getLocal(), "", ""));
                }

                tblGeral.setItems(obsTest);
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
            case "Alunos":{
                AlunoBO alunoBO = new AlunoBO();
                AlunoVO alunoVO = new AlunoVO();
                String matricula = "";
                ObservableList<modelAdmin> obsList = tblGeral.getItems();
                for (modelAdmin obs : obsList) {
                    if (obs.isAction() && matricula.equals("")) {
                        matricula = obs.getColuna2();
                    }
                }

                alunoVO = alunoBO.buscarPorMatricula(matricula);

                Telas.telaAdicionaAluno(userVO, alunoVO);
                break;
            }

            case "Professores":{
                ProfessorBO professorBO = new ProfessorBO();
                ProfessorVO professorVO = new ProfessorVO();
                String cpf = "";
                ObservableList<modelAdmin> obsList = tblGeral.getItems();
                for (modelAdmin obs : obsList) {
                    if (obs.isAction() && cpf.equals("")) {
                        cpf = obs.getColuna2();
                    }
                }

                professorVO = professorBO.getByCPF(cpf);

                Telas.telaAdicionaProfessor(userVO, professorVO);
                break;
            }

            case "Disciplinas": {
                DisciplinaBO disciplinaBO = new DisciplinaBO();
                DisciplinaVO disciplinaVO = new DisciplinaVO();
                String codigo = "";
                ObservableList<modelAdmin> obsList = tblGeral.getItems();
                for (modelAdmin obs : obsList) {
                    if (obs.isAction() && codigo.equals("")) {
                        codigo = obs.getColuna1();
                    }
                }

                disciplinaVO = disciplinaBO.buscarDisciplinaPorCod(codigo);

                Telas.telaAdicionaDisciplina(userVO, disciplinaVO);
                break;
            }
        
            case "Turmas": {
                TurmaBO turmaBO = new TurmaBO();
                TurmaVO turmaVO = new TurmaVO();
                String codigo = "";
                ObservableList<modelAdmin> obsList = tblGeral.getItems();
                for (modelAdmin obs : obsList) {
                    if (obs.isAction() && codigo.equals("")) {
                        codigo = obs.getColuna1();
                    }
                }

                turmaVO = turmaBO.getByCodigo(codigo);

                Telas.telaAdicionaTurma(userVO, turmaVO);
                break;
            }

        
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
