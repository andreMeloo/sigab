package model.bo;

import java.util.List;

import model.vo.EnderecoVO;
import model.vo.UsuarioVO;
import model.dao.EnderecoDAO;
import model.dao.ProfessorDAO;
import model.dao.AlunoDAO;

import enums.NivelDeUsuario;

public class EnderecoBO implements EntityBOInterface<EnderecoVO> {

    @Override
    public void salvar(EnderecoVO endereco) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.inserir(endereco);
    }

    @Override
    public List<EnderecoVO> listar() {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.listar();
    }

    @Override
    public void remover(EnderecoVO endereco) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.remover(endereco);
    }

    @Override
    public void editar(EnderecoVO endereco) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.editar(endereco);
    }

    @Override
    public EnderecoVO getById(Long id) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        return enderecoDAO.getById(id);
    }

    public EnderecoVO getByUsuario(UsuarioVO usuario) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        EnderecoVO endereco = null;
        
        if (usuario.getNivel() == NivelDeUsuario.ALUNO) {
            AlunoDAO alunoDAO = new AlunoDAO();
            endereco = enderecoDAO.getByAluno(alunoDAO.getById(usuario.getId()));
        } else if (usuario.getNivel() == NivelDeUsuario.PROFESSOR) {
            ProfessorDAO professorDAO = new ProfessorDAO();
            endereco = enderecoDAO.getByProfessor(professorDAO.getById(usuario.getId()));
        }

        return endereco;
    }

    
}
