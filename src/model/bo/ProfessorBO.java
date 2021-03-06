package model.bo;

import java.util.ArrayList;
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

    @Override
    public ProfessorVO getById(Long id) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        return professorDAO.getById(id);
    }
    
    public List<ProfessorVO> buscarPorNome(String nome) {
        
        ProfessorDAO usuarioDAO = new ProfessorDAO();
        List<ProfessorVO> listaPorNome = new ArrayList<ProfessorVO>();
        
        listaPorNome = usuarioDAO.listar();
        listaPorNome.removeIf(s -> !s.getNome().contains(nome));

        return listaPorNome;
    }

    public ProfessorVO getByCPF(String cpf) {
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            ProfessorVO professorVO = new ProfessorVO();
            
            professorVO = professorDAO.getByCPF(cpf);
            return professorVO;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
