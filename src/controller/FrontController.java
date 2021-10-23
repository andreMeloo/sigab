package controller;

import exception.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class FrontController {

    @FXML private TextField usuario;
    @FXML private PasswordField senha;
    @FXML private Label erroAut;

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
                    erroAut.setText("Usuario Aluno Logado");
                    erroAut.setVisible(true);
                    break;
                case PROFESSOR:
                    // Abre Janelas de professor
                    erroAut.setText("Usuario Professor Logado");
                    erroAut.setVisible(true);
                    break;
                case ADMIN:
                    // Abre Janelas do administrador
                    erroAut.setText("Usuario Admin Logado");
                    erroAut.setVisible(true);
                    break;
            }

        } 
        catch (AuthenticationException e) {
            erroAut.setText("Usuario ou Senha invalidos!");
            erroAut.setVisible(true);
        }
    }
}
