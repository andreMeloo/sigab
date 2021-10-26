package model.bo;

import java.util.List;

import model.dao.DisciplinaDAO;
import model.vo.DisciplinaVO;

public class DisciplinaBO implements EntityBOInterface<DisciplinaVO>{

    @Override
    public void salvar(DisciplinaVO disciplinaVO) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        disciplinaDAO.inserir(disciplinaVO);
    }

    @Override
    public List<DisciplinaVO> listar() {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        return disciplinaDAO.listar();
    }

    @Override
    public void remover(DisciplinaVO disciplinaVO) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.getById(disciplinaVO.getId());
            disciplinaDAO.remover(disciplinaVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(DisciplinaVO disciplinaVO) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        try {
            disciplinaDAO.getById(disciplinaVO.getId());
            disciplinaDAO.editar(disciplinaVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DisciplinaVO getById(Long id) {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        return disciplinaDAO.getById(id);
    }
    
}
