package model.bo;

import java.util.List;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO implements EntityBOInterface<UsuarioVO>{

    @Override
    public void salvar(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(usuarioVO);
    }

    @Override
    public List<UsuarioVO> listar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listar();
    }

    @Override
    public void remover(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.getById(usuarioVO.getId());
            usuarioDAO.remover(usuarioVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(UsuarioVO usuarioVO) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            usuarioDAO.getById(usuarioVO.getId());
            usuarioDAO.editar(usuarioVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}