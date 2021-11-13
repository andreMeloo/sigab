import java.util.List;

import model.dao.AlunoDAO;
import model.dao.DiarioDAO;
import model.dao.DisciplinaDAO;
import model.dao.EnderecoDAO;
import model.dao.ProfessorDAO;
import model.dao.TurmaDAO;
import model.vo.AlunoVO;
import model.vo.DiarioVO;
import model.vo.DisciplinaVO;
import model.vo.EnderecoVO;
import model.vo.ProfessorVO;
import model.vo.TurmaVO;

public class App {
    public static void main(String[] args) throws Exception {

        // Disciplina
        // System.out.println("\nDisciplina\n");

        // DisciplinaDAO disciplinaDao = new DisciplinaDAO();

        // System.out.println("Adicionando:");
        // DisciplinaVO disciplina = new DisciplinaVO();
        // disciplina.setCodigo(167);
        // disciplina.setNome("POO");

        // disciplinaDao.inserir(disciplina);
        // List<DisciplinaVO> disciplinas = disciplinaDao.listar();

        // for (var row : disciplinas) {
        //     System.out.println(row.getCodigo() + " - " + row.getNome());
        // }

        // System.out.println("\nEditando:");
        // disciplina.setNome("BD");
        // disciplinaDao.editar(disciplina);
        // disciplinas = disciplinaDao.listar();

        // for (var row : disciplinas) {
        //     System.out.println(row.getCodigo() + " - " + row.getNome());
        // }
        
        // // System.out.println("\nDeletando:");
        // // disciplinaDao.removerByCodigo(disciplina);
        // // disciplinas = disciplinaDao.listar();

        // // for (var row : disciplinas) {
        // //     System.out.println(row.getCodigo() + " - " + row.getNome());
        // // }
        
        // // Endereco
        // System.out.println("\nEndereco\n");

        // EnderecoDAO enderecoDao = new EnderecoDAO();

        // System.out.println("Adicionando:");
        // EnderecoVO endereco = new EnderecoVO();
        // endereco.setCidade("Natal");
        // endereco.setEndereco("R. das Joaninhas");
        // endereco.setUf("RN");

        // enderecoDao.inserir(endereco);
        // List<EnderecoVO> enderecos = enderecoDao.listar();

        // for (var row : enderecos) {
        //     System.out.println(row.getId() + " - " + row.getEndereco() + ". " + row.getCidade() + ", " + row.getUf());
        // }

        // System.out.println("\nEditando:");
        // endereco.setCidade("Areia Branca");
        // enderecoDao.editar(endereco);
        // enderecos = enderecoDao.listar();

        // for (var row : enderecos) {
        //     System.out.println(row.getId() + " - " + row.getEndereco() + ". " + row.getCidade() + ", " + row.getUf());
        // }

        // System.out.println("\nDeletando:");
        // enderecoDao.remover(endereco.getId());
        // enderecos = enderecoDao.listar();

        // for (var row : enderecos) {
        //     System.out.println(row.getId() + " - " + row.getEndereco() + ". " + row.getCidade() + ", " + row.getUf());
        // }

        // // Aluno
        // System.out.println("\nAluno\n");

        // AlunoDAO alunoDao = new AlunoDAO();

        // System.out.println("Adicionando:");
        // AlunoVO aluno = new AlunoVO();
        // aluno.setEndereco(new EnderecoVO("R. Aleatoria", "Natal", "RN"));
        // aluno.setMatricula("2021010256");
        // aluno.setNome("Andre");
        // aluno.setSenha("123456");
        // aluno.setUsername("andre");
        // alunoDao.inserir(aluno);

        // for (var row : alunos) {
        //     System.out.println(row.getNome() + " - " + row.getMatricula());
        // }

        // System.out.println("\nEditando:");
        // aluno.setNome("João da Silva");
        // alunoDao.editar(aluno);
        // alunos = alunoDao.listar();

        // for (var row : alunos) {
        //     System.out.println(row.getNome() + " - " + row.getMatricula());
        // }

        // // System.out.println("\nDeletando:");
        // // alunoDao.remover(aluno.getId());
        // // alunos = alunoDao.listar();

        // // for (var row : alunos) {
        // //     System.out.println(row.getNome() + " - " + row.getMatricula());
        // // }

        // // Professor
        // System.out.println("\nProfessor\n");

        // ProfessorDAO professorDao = new ProfessorDAO();

        // System.out.println("Adicionando:");
        // ProfessorVO professor = new ProfessorVO();
        // professor.setEndereco(new EnderecoVO("R. do Amigo", "Poços de Caldas", "MG"));
        // professor.setCpf("12398741242");
        // professor.setNome("Davi");
        // professor.setSenha("1234567");
        // professor.setUsername("davi");

        // professorDao.inserir(professor);
        // professor = professorDao.getById(professorId);
        // List<ProfessorVO> professores = professorDao.listar();

        // for (var row : professores) {
        //     System.out.println(row.getNome() + " - " + row.getCpf());
        // }

        // System.out.println("\nEditando:");
        // professor.setNome("Linderbergson");
        // professor.setCpf("12345678900");
        // professorDao.editar(professor);
        // professores = professorDao.listar();

        // for (var row : professores) {
        //     System.out.println(row.getNome() + " - " + row.getCpf());
        // }

        // // System.out.println("\nDeletando:");
        // // professorDao.remover(professor.getId());
        // // professores = professorDao.listar();

        // // for (var row : professores) {
        // //     System.out.println(row.getNome() + " - " + row.getCpf());
        // // }

        // // Turma
        // System.out.println("\nTurma\n");

        // TurmaDAO turmaDAO = new TurmaDAO();

        // System.out.println("Adicionando:");
        // TurmaVO turma = new TurmaVO();
        // turma.setDisciplina(disciplina);
        // turma.setHorario("12M34");
        // turma.setLocal("Remoto");
        // turma.setProfessor(professor);

        // turmaDAO.inserir(turma);
        // List<TurmaVO> turmas = turmaDAO.listar();

        // for (var row : turmas) {
        //     System.out.println(row.getHorario() + " - " + row.getDisciplina().getNome() + " - " + row.getProfessor().getNome());
        // }

        // System.out.println("\nEditando:");
        // turma.setHorario("34T12");
        // turmaDAO.editar(turma);
        // turmas = turmaDAO.listar();

        // for (var row : turmas) {
        //     System.out.println(row.getHorario() + " - " + row.getDisciplina().getNome() + " - " + row.getProfessor().getNome());
        // }

        // // System.out.println("\nDeletando:");
        // // turmaDAO.remover(turma.getId());
        // // turmas = turmaDAO.listar();

        // // for (var row : turmas) {
        // //     System.out.println(row.getHorario() + " - " + row.getDisciplina().getNome() + " - " + row.getProfessor().getNome());
        // // }

        // // Diario
        // System.out.println("\nTurma\n");

        // DiarioDAO diarioDao = new DiarioDAO();

        // System.out.println("Adicionando:");
        // DiarioVO diario = new DiarioVO();
        // diario.setNota1((double)5);
        // diario.setNota2(8.9);
        // diario.setNota3((double)10);
        // diario.setFrequencia(75);
        // diario.setAluno(aluno);
        // diario.setTurma(turma);

        // diarioDao.inserir(diario);
        // List<DiarioVO> diarios = diarioDao.listar();

        // for (var row : diarios) {
        //     System.out.println(row.getAluno().getNome() + " - " + row.getTurma().getDisciplina().getNome() + " - " + row.getFrequencia());
        // }

        // System.out.println("\nEditando:");
        // diario.setFrequencia(100);
        // diarioDao.editar(diario);
        // diarios = diarioDao.listar();

        // for (var row : diarios) {
        //     System.out.println(row.getAluno().getNome() + " - " + row.getTurma().getDisciplina().getNome() + " - " + row.getFrequencia());
        // }

        // System.out.println("\nDeletando:");
        // diarioDao.remover(diario.getAluno().getId(), diario.getTurma().getId());
        // diarios = diarioDao.listar();

        // for (var row : diarios) {
        //     System.out.println(row.getAluno().getNome() + " - " + row.getTurma().getDisciplina().getNome() + " - " + row.getFrequencia());
        // }
    }
}
