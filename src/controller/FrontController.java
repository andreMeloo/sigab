package controller;



import exception.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;
import view.Telas;

public class FrontController {

    @FXML private TextField usuario;
    @FXML private PasswordField senha;
    @FXML private Pane painelErro;


    UsuarioBO usuBO = new UsuarioBO();

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
                    Telas.telaAlunoPrincipal();
                    break;
                case PROFESSOR:
                    // Abre Janelas de professor
                    Telas.telaProfessorPrincipal();
                    break;
                case ADMIN:
                    // Abre Janelas do administrador
                    Telas.telaAdminPrincipal();
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
