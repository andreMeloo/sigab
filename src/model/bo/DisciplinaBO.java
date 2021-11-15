package model.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.dao.DisciplinaDAO;
import model.vo.DisciplinaVO;

public class DisciplinaBO implements EntityBOInterface<DisciplinaVO>{

    @Override
    public void salvar(DisciplinaVO disciplinaVO) throws Exception {
        try {
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            disciplinaDAO.inserir(disciplinaVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String geraCodigoDisciplina() throws Exception {
        String codigo = "";
        List<DisciplinaVO> disciplinaVO = new ArrayList<DisciplinaVO>();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Random r = new Random();

        try {
            disciplinaVO = disciplinaDAO.listar();

            while (codigo.equals("")) {
                codigo += "SGB" + r.nextInt(10000);
                for (DisciplinaVO disciplina : disciplinaVO) {
                    if (disciplina.getCodigo().equals(codigo)) {
                        codigo = "";
                    }
                }
            }

            return codigo;
        
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
    
}
