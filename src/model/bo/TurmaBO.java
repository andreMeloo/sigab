package model.bo;

import java.util.List;

import model.dao.TurmaDAO;
import model.vo.TurmaVO;

public class TurmaBO implements EntityBOInterface<TurmaVO>{

    @Override
    public void salvar(TurmaVO turmaVO) {
        TurmaDAO turmaDAO = new TurmaDAO();
        turmaDAO.inserir(turmaVO);
    }

    @Override
    public List<TurmaVO> listar() {
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.listar();
    }

    @Override
    public void remover(TurmaVO turmaVO) {
        TurmaDAO turmaDAO = new TurmaDAO();
        try {
            turmaDAO.getById(turmaVO.getId());
            turmaDAO.remover(turmaVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(TurmaVO turmaVO) {
        TurmaDAO turmaDAO = new TurmaDAO();
        try {
            turmaDAO.getById(turmaVO.getId());
            turmaDAO.editar(turmaVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TurmaVO getById(Long id) {
        TurmaDAO turmaDAO = new TurmaDAO();
        return turmaDAO.getById(id);
    }
    
}