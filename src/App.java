import java.util.List;

import model.dao.AlunoDAO;
import model.dao.DisciplinaDAO;
import model.dao.EnderecoDAO;
import model.vo.AlunoVO;
import model.vo.DisciplinaVO;
import model.vo.EnderecoVO;

public class App {
    public static void main(String[] args) throws Exception {

        // Disciplina
        System.out.println("\nDisciplina\n");

        DisciplinaDAO disciplinaDao = new DisciplinaDAO();

        System.out.println("Adicionando:");
        DisciplinaVO disciplina = new DisciplinaVO();
        disciplina.setCodigo(167);
        disciplina.setNome("POO");

        disciplinaDao.inserir(disciplina);
        List<DisciplinaVO> disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }

        System.out.println("\nEditando:");
        disciplina.setNome("BD");
        disciplinaDao.editar(disciplina);
        disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }
        
        System.out.println("\nDeletando:");
        disciplinaDao.removerByCodigo(disciplina);
        disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }
        
        // Endereco
        System.out.println("\nEndereco\n");

        EnderecoDAO enderecoDao = new EnderecoDAO();

        System.out.println("Adicionando:");
        EnderecoVO endereco = new EnderecoVO();
        endereco.setCidade("Natal");
        endereco.setRua("Rua das Joaninhas");
        endereco.setUf("RN");

        endereco.setId(enderecoDao.inserir(endereco));
        List<EnderecoVO> enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

        System.out.println("\nEditando:");
        endereco.setCidade("Areia Branca");
        enderecoDao.editar(endereco);
        enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

        System.out.println("\nDeletando:");
        enderecoDao.removerById(endereco);
        enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

        // Aluno
        System.out.println("\nAluno\n");

        AlunoDAO alunoDao = new AlunoDAO();

        System.out.println("Adicionando:");
        AlunoVO aluno = new AlunoVO();
        aluno.setEndereco(new EnderecoVO("Rua Aleatoria", "Natal", "RN"));
        aluno.setMatricula("2021010256");
        aluno.setNome("Joaozinho");
        aluno.setSenha("123456");
        aluno.setUsername("jaozin");

        long alunoId = alunoDao.inserir(aluno);
        aluno = alunoDao.getById(alunoId);
        List<AlunoVO> alunos = alunoDao.listar();

        for (var row : alunos) {
            System.out.println(row.getNome() + " - " + row.getMatricula());
        }

        System.out.println("\nEditando:");
        aluno.setNome("João da Silva");
        alunoDao.editar(aluno);
        alunos = alunoDao.listar();

        for (var row : alunos) {
            System.out.println(row.getNome() + " - " + row.getMatricula());
        }

        System.out.println("\nDeletando:");
        alunoDao.remover(aluno);
        alunos = alunoDao.listar();

        for (var row : alunos) {
            System.out.println(row.getNome() + " - " + row.getMatricula());
        }
    }
}
