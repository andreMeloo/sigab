package model.bo;


import java.util.List;

import enums.NivelDeUsuario;
import exception.AuthenticationException;
import model.dao.AlunoDAO;
import model.dao.ProfessorDAO;
import model.dao.UsuarioDAO;
import model.vo.AlunoVO;
import model.vo.ProfessorVO;
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

    @Override
    public UsuarioVO getById(Long id){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.getById(id);
    }


    private static UsuarioDAO usuDAO = new UsuarioDAO();
    private static AlunoDAO alunoDAO = new AlunoDAO();
    private static ProfessorDAO profDAO = new ProfessorDAO();

    public UsuarioVO autenticar (UsuarioVO vo) throws AuthenticationException {
    UsuarioVO usuVO = usuDAO.getByUsername(vo);

    try {
        // usuário encontrado
        if (usuVO.getSenha().equals(vo.getSenha()) && usuVO.getNivel() == NivelDeUsuario.ALUNO) {
            // existe e senha ok. Descobrir se é Aluno, Professor ou Admin
            AlunoVO alunoVO = alunoDAO.getById(usuVO.getId());
            return alunoVO;
        } else if(usuVO.getSenha().equals(vo.getSenha()) && usuVO.getNivel() == NivelDeUsuario.PROFESSOR) {
            ProfessorVO profVO = profDAO.getById(usuVO.getId());
            return profVO;
        } else {
            return usuVO;
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new AuthenticationException();
    }
    
    }
}