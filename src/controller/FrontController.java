package controller;

import enums.NivelDeUsuario;
import exception.AuthenticationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.bo.UsuarioBO;
import model.vo.UsuarioVO;

public class FrontController {

    @FXML private TextField login;
    @FXML private TextField senha;
    @FXML private Label erroAut;

    UsuarioBO usuBO = new UsuarioBO();

    public void autenticar(ActionEvent event) {
        UsuarioVO vo = new UsuarioVO();
        vo.setUsername(login.getText());
        vo.setSenha(senha.getText());

        
        try 
        {
            UsuarioVO autenticado = usuBO.autenticar(vo);
            switch (autenticado.getNivel()) {
                case ALUNO:
                    // chama tela aluno
                    break;
                case PROFESSOR:
                    // chama tela professor
                    break;
                case ADMIN:
                    // chama tela do admin
                    break;
                default:
                    break;
            }

        } 
        catch (AuthenticationException e) {
            erroAut.setText("Usuario ou Senha invalidos!");
            erroAut.setVisible(true);
        }
    }
}
