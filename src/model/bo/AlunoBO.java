package model.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
    
    public List<AlunoVO> buscarPorNome(String nome) {
        
        AlunoDAO usuarioDAO = new AlunoDAO();
        List<AlunoVO> listaPorNome = new ArrayList<AlunoVO>();
        
        listaPorNome = usuarioDAO.listar();
        listaPorNome.removeIf(s -> !s.getNome().contains(nome));

        return listaPorNome;
    }

    public List<AlunoVO> buscarPorTurma(String codigo) {

        AlunoDAO usuarioDAO = new AlunoDAO();
        List<AlunoVO> listaPorTurma = new ArrayList<AlunoVO>();
        
        listaPorTurma = usuarioDAO.listar();
        listaPorTurma.removeIf(s -> !s.getNome().contains(codigo));

        return listaPorTurma;
    }

    public AlunoVO buscarPorMatricula(String matricula) {
        AlunoVO alunoVO = new AlunoVO();
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoVO = alunoDAO.getByMatricula(matricula);
            return alunoVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String gerarMatricula() {
        String matricula = "";
        String finalMatricula = "";
        String inicioMatricula = "";
        // gerando parte inicial da matricula

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int periodo = Calendar.getInstance().get(Calendar.MONTH);

        if (periodo <= 6) {
            periodo = 1;
        } else {
            periodo = 2;
        }
        
        inicioMatricula += + ano + "0" + periodo;

        // gerando parte final da matricula

        try {
            List<AlunoVO> alunos = this.listar();
            Random r = new Random();
            while (finalMatricula.equals("")) {
                finalMatricula += r.nextInt(10000);

                for (AlunoVO aluno : alunos) {
                    if (aluno.getMatricula().equals(inicioMatricula + finalMatricula)) {
                       finalMatricula = "";
                    }
                }
            }
            matricula = inicioMatricula + finalMatricula;

            return matricula;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }

}
