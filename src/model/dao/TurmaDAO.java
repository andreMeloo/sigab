package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.TurmaVO;

public class TurmaDAO extends BaseDAO {

    public long inserir(TurmaVO turma) {
        
        connection = getConnection();
        String sql = "INSERT INTO Turma (horario, local, aberta, disciplina_codigo, professor_id) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, turma.getHorario());
            preparedStatement.setString(2, turma.getLocal());
            preparedStatement.setBoolean(3, turma.isAberta());
            preparedStatement.setLong(4, turma.getDisciplina().getCodigo());
            preparedStatement.setLong(5, turma.getProfessor().getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            keys.next();
            turma.setId(keys.getLong(1));
        
            return turma.getId();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void remover(long id) {
        connection = getConnection();
        String sql = "DELETE FROM Turma WHERE id=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TurmaVO> listar() {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_codigo, professor_id, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_codigo=Disciplina.codigo";
        List<TurmaVO> result = new ArrayList<TurmaVO>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                TurmaVO turma = new TurmaVO();
                turma.setId(resultSet.getLong("id"));
                turma.setHorario(resultSet.getString("horario"));
                turma.setLocal(resultSet.getString("local"));
                turma.setAberta(resultSet.getBoolean("aberta"));
                turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
                turma.setDisciplina(disciplinaDAO.getByCodigo(resultSet.getLong("disciplina_codigo")));

                result.add(turma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void editar(TurmaVO turma){
        connection = getConnection();
        String sql = "UPDATE Turma SET horario=?, local=?, aberta=?, disciplina_codigo=?, professor_id=? WHERE id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, turma.getHorario());
            preparedStatement.setString(2, turma.getLocal());
            preparedStatement.setBoolean(3, turma.isAberta());
            preparedStatement.setLong(4, turma.getDisciplina().getCodigo());
            preparedStatement.setLong(5, turma.getProfessor().getId());
            preparedStatement.setLong(6, turma.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TurmaVO getById(long id) {

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        connection = getConnection();
        String sql = "SELECT Turma.id, disciplina_codigo, professor_id, horario, local, aberta FROM Turma "
                    + "JOIN Professor ON Turma.professor_id=Professor.id "
                    + "JOIN Disciplina ON Turma.disciplina_codigo=Disciplina.codigo "
                    + "WHERE Turma.id=?";
        TurmaVO turma = new TurmaVO();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            
            resultSet.next();
            turma.setId(resultSet.getLong("id"));
            turma.setHorario(resultSet.getString("horario"));
            turma.setLocal(resultSet.getString("local"));
            turma.setAberta(resultSet.getBoolean("aberta"));
            turma.setProfessor(professorDAO.getById(resultSet.getLong("professor_id")));
            turma.setDisciplina(disciplinaDAO.getByCodigo(resultSet.getLong("disciplina_codigo")));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turma;
    }
}
