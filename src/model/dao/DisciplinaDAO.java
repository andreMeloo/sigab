package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.DisciplinaVO;

public class DisciplinaDAO extends BaseDAO implements EntityDAOInterface<DisciplinaVO> {
    public void inserir(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "INSERT INTO Disciplina (codigo, nome) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, disciplinaVO.getCodigo());
            preparedStatement.setString(2, disciplinaVO.getNome());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "DELETE FROM Disciplina WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, disciplinaVO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DisciplinaVO> listar() {
        connection = getConnection();
        String sql = "SELECT * FROM Disciplina";
        Statement statement;
        ResultSet resultSet;
        List<DisciplinaVO> disciplinaVOs = new ArrayList<DisciplinaVO>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                DisciplinaVO disciplinaVO = new DisciplinaVO();
                disciplinaVO.setCodigo(resultSet.getString("codigo"));
                disciplinaVO.setNome(resultSet.getString("nome"));
                disciplinaVO.setId(resultSet.getLong("id"));
                disciplinaVOs.add(disciplinaVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinaVOs;
    }

    public void editar(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "UPDATE Disciplina SET nome = ?, codigo = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, disciplinaVO.getNome());
            preparedStatement.setString(2, disciplinaVO.getCodigo());
            preparedStatement.setLong(3, disciplinaVO.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DisciplinaVO getById(Long id) {
        connection = getConnection();
        String sql = "SELECT * FROM Disciplina WHERE id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        DisciplinaVO disciplina = new DisciplinaVO();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            resultSet.next();
            disciplina.setCodigo(resultSet.getString("codigo"));
            disciplina.setNome(resultSet.getString("nome"));
            disciplina.setId(resultSet.getLong("id"));
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplina;
    }

    public List<DisciplinaVO> getByNome(String nome) {
        connection = getConnection();
        String sql = "SELECT * FROM Disciplina WHERE name=?";
        List<DisciplinaVO> disciplinaVOs = new ArrayList<DisciplinaVO>();
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                DisciplinaVO disciplinaVO = new DisciplinaVO();
                disciplinaVO.setCodigo(resultSet.getString("codigo"));
                disciplinaVO.setNome(resultSet.getString("nome"));
                disciplinaVO.setId(resultSet.getLong("id"));
                disciplinaVOs.add(disciplinaVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinaVOs;
    }
}
