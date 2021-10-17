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

    public void autenticar(ActionEvent event) throws Exception {
        UsuarioVO vo = new UsuarioVO();
        vo.setUsername(login.getText());
        vo.setSenha(senha.getText());

        
        try 
        {
            UsuarioVO autenticado = usuBO.autenticar(vo);
            switch (autenticado.getNivel()) {
                case ALUNO:
                    // Abre janelas de Aluno
                    break;
                case PROFESSOR:
                    // Abre Janelas de professor
                    break;
                case ADMIN:
                    // Abre Janelas do administrador
                    break;
            }

        } 
        catch (AuthenticationException e) {
            erroAut.setText("Usuario ou Senha invalidos!");
            erroAut.setVisible(true);
        }
    }
}
