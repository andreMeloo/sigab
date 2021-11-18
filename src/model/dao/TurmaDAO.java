package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.TurmaVO;
/* atenção aos requisitos do projeto de vocês
b) Buscar: alunos, professores e disciplinas (por nome). Buscar turmas (por professor). Buscar
alunos (por turma). Mostrar disciplinas concluídas por aluno.
c) Finalizar disciplina: Lançar notas (p1,p2 e p3) e frequência dos alunos (%presença) e calcular
cadê o métodos para buscar turmas por professor, por exemplo??? faltam alguns ainda.
*/
public class TurmaDAO extends BaseDAO implements EntityDAOInterface<TurmaVO> {

    public void inserir(TurmaVO turma) {
        
        connection = getConnection();
        String sql = "INSERT INTO Turma (codigo, horario, local, aberta, disciplina_id, professor_id) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "T" + turma.getCodigo());
            preparedStatement.setString(2, turma.getHorario());
            preparedStatement.setString(3, turma.getLocal());
            preparedStatement.setBoolean(4, turma.isAberta());
            preparedStatement.setLong(5, turma.getDisciplina().getId());
            preparedStatement.setLong(6, turma.getProfessor().getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            keys.next();
            turma.setId(keys.getLong(1));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(TurmaVO turma) {
        connection = getConnection();
        String sql = "DELETE FROM Turma WHERE id=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, turma.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TurmaVO> listar() {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_id, professor_id, Turma.codigo, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_id=Disciplina.id";
        List<TurmaVO> result = new ArrayList<TurmaVO>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                TurmaVO turma = new TurmaVO();
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));
                turma.setAlunos(alunoDAO.getAllByTurmaId(resultSet.getLong("id")));

                result.add(turma);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void editar(TurmaVO turma){
        connection = getConnection();
        String sql = "UPDATE Turma SET codigo=?, horario=?, local=?, aberta=?, disciplina_id=?, professor_id=? WHERE id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, turma.getCodigo());
            preparedStatement.setString(2, turma.getHorario());
            preparedStatement.setString(3, turma.getLocal());
            preparedStatement.setBoolean(4, turma.isAberta());
            preparedStatement.setLong(5, turma.getDisciplina().getId());
            preparedStatement.setLong(6, turma.getProfessor().getId());
            preparedStatement.setLong(7, turma.getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurmaVO getById(Long id) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_id, professor_id, Turma.codigo, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_id=Disciplina.id "
                    + "WHERE Turma.id=?";
        TurmaVO turma = new TurmaVO();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            if(resultSet.next()) {
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turma;
    }

    public TurmaVO getByCodigo(String codigo) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_id, professor_id, Turma.codigo, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_id=Disciplina.id "
                    + "WHERE Turma.codigo=?";
        TurmaVO turma = new TurmaVO();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codigo);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            if(resultSet.next()) {
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turma;
    }

    public List<TurmaVO> getByAlunoId(long id) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_id, professor_id, Turma.codigo, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_id=Disciplina.id "
                    + "RIGHT JOIN Diario ON Turma.id=Diario.turma_id "
                    + "WHERE aluno_id=?";
        TurmaVO turma = new TurmaVO();
        List<TurmaVO> result = new ArrayList<TurmaVO>();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            while (resultSet.next()) {
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));

                result.add(turma);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<TurmaVO> getByProfessorId(long id) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_id, professor_id, Turma.codigo, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_id=Disciplina.id "
                    + "WHERE professor_id=?";
        TurmaVO turma = new TurmaVO();
        List<TurmaVO> result = new ArrayList<TurmaVO>();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            while (resultSet.next()) {
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));

                result.add(turma);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<TurmaVO> getTurmasByDisciplina(long id) {
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turmas WHERE disciplina_id=?";
        TurmaVO turma = new TurmaVO();
        List<TurmaVO> result = new ArrayList<TurmaVO>();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            while (resultSet.next()) {
                turma.setId(resultSet.getLong("id"));
                turma.setCodigo(resultSet.getString("codigo"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getById(resultSet.getLong("disciplina_id")));

                result.add(turma);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
