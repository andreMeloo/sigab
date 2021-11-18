package controller;



import exception.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.bo.AlunoBO;
import model.bo.ProfessorBO;
import model.bo.UsuarioBO;
import model.vo.ProfessorVO;
import model.vo.UsuarioVO;
import view.Telas;

public class FrontController {

    @FXML private TextField usuario;
    @FXML private PasswordField senha;
    @FXML private Pane painelErro;


    UsuarioBO usuBO = new UsuarioBO();
    AlunoBO alunoBO = new AlunoBO();
    ProfessorBO professorBO = new ProfessorBO();

    public void autenticar(ActionEvent event) throws Exception {
        UsuarioVO vo = new UsuarioVO();
        vo.setUsername(usuario.getText());
        vo.setSenha(senha.getText());

        try 
        {
            UsuarioVO autenticado = usuBO.autenticar(vo);
            switch (autenticado.getNivel()) {
                case ALUNO:
                    // Abre janelas de Aluno

                    Telas.telaInicialAluno(autenticado);
                    
                    break;
                case PROFESSOR:
                    // Abre Janelas de professor
                    ProfessorVO professorVO = new ProfessorVO();
                    professorVO = professorBO.getById(autenticado.getId());
                    Telas.telaInicialProfessor(professorVO);
                    break;
                case ADMIN:
                    // Abre Janelas do administrador
                    Telas.telaInicialAdmin(autenticado);
                    break;
            }

        } 
        catch (AuthenticationException e) {
            painelErro.setDisable(false);
            painelErro.setVisible(true);
        }
    }

    public void fechaMsgErro() {
        painelErro.setDisable(true);
        painelErro.setVisible(false);
    }

}