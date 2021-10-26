package model.bo;

import java.util.List;

import model.dao.AlunoDAO;
import model.vo.AlunoVO;

public class AlunoBO implements EntityBOInterface<AlunoVO>{

    @Override
    public void salvar(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.inserir(alunoVO);
    }

    @Override
    public List<AlunoVO> listar() {
        AlunoDAO alunoDAO = new AlunoDAO();
        return  alunoDAO.listar();
    }

    @Override
    public void remover(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoDAO.getById(alunoVO.getId());
            alunoDAO.remover(alunoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void editar(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoDAO.getById(alunoVO.getId());
            alunoDAO.editar(alunoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AlunoVO getById(Long id) {
        AlunoDAO alunoDAO = new AlunoDAO();
        return alunoDAO.getById(id);
    }
    
}
