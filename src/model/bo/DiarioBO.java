package model.bo;

import java.util.List;

import model.dao.DiarioDAO;
import model.vo.DiarioVO;

public class DiarioBO implements EntityBOInterface<DiarioVO> {

    @Override
    public void salvar(DiarioVO diario) {
        DiarioDAO diarioDAO = new DiarioDAO();
        diarioDAO.inserir(diario);
    }

    @Override
    public List<DiarioVO> listar() {
        DiarioDAO diarioDAO = new DiarioDAO();
        return diarioDAO.listar();
    }

    @Override
    public void remover(DiarioVO diario) {
        DiarioDAO diarioDAO = new DiarioDAO();
        diarioDAO.remover(diario);
    }

    @Override
    public void editar(DiarioVO diario) {
        DiarioDAO diarioDAO = new DiarioDAO();
        diarioDAO.editar(diario);
    }

    @Override
    public DiarioVO getById(Long id) {
        // Diario não possui id
        return null;
    }
    
}
