package model.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public TurmaVO getByCodigo(String codigo) {
        TurmaDAO turmaDAO = new TurmaDAO();
        TurmaVO turmaVO = new TurmaVO();

        try {
            turmaVO = turmaDAO.getByCodigo(codigo);
            return turmaVO;
    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TurmaVO> getTurmasDoProfessor(Long id) throws Exception  {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            List<TurmaVO> turmas = turmaDAO.getByProfessorId(id);
            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TurmaVO> getTurmasByDisciplina(Long id) throws Exception  {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            List<TurmaVO> turmas = turmaDAO.getTurmasByDisciplina(id);
            return turmas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String geraCodigoTurma() throws Exception {
        String codigo = "";
        List<TurmaVO> turmas = new ArrayList<TurmaVO>();
        TurmaDAO turmaDAO = new TurmaDAO();
        Random r = new Random();

        try {
            turmas = turmaDAO.listar();

            while (codigo.equals("")) {
                codigo = String.valueOf(r.nextInt(100));
                for (TurmaVO turmaVO : turmas) {
                    if (turmaVO.getCodigo().equals(codigo)) {
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

    public String turmasProfString(List<TurmaVO> turmas) {
        String turmasProf = "";
        for (TurmaVO turma : turmas) {
            turmasProf += turma.getCodigo() + " ";
        }
        return turmasProf;
    }
    
}