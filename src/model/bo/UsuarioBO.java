package model.bo;


import java.util.List;


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
    UsuarioVO usuVO = usuDAO.getByUsername(vo.getUsername());
    AlunoVO alunoVO = new AlunoVO();
    ProfessorVO profVO = new ProfessorVO();

    try {
        // usuário encontrado
        if(usuVO.getSenha().equals(vo.getSenha())) {
            switch (usuVO.getNivel()) {
                case ALUNO: {
                    alunoVO = alunoDAO.getById(usuVO.getId());
                    return alunoVO;
                }
                case PROFESSOR: {
                    profVO = profDAO.getById(usuVO.getId());
                    return profVO;
                }
                case ADMIN: return usuVO;
                
                default: throw new AuthenticationException();
            }
        } else {
            throw new AuthenticationException();
        }
    }
        catch (Exception e) {
        e.printStackTrace();
        throw new AuthenticationException();
        }
    }
}