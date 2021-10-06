package model.bo;

import java.util.List;

import model.dao.ProfessorDAO;
import model.vo.ProfessorVO;

public class ProfessorBO implements EntityBOInterface<ProfessorVO>{

    @Override
    public void salvar(ProfessorVO professorVO) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.inserir(professorVO);
    }

    @Override
    public List<ProfessorVO> listar() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        return professorDAO.listar();
    }

    @Override
    public void remover(ProfessorVO professorVO) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            professorDAO.getById(professorVO.getId());
            professorDAO.remover(professorVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(ProfessorVO professorVO) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            professorDAO.getById(professorVO.getId());
            professorDAO.editar(professorVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
