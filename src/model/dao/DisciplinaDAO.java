package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.DisciplinaVO;

public class DisciplinaDAO extends BaseDAO{
    public void inserir(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "insert into Disciplina (codigo, nome) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, disciplinaVO.getCodigo());
            preparedStatement.setString(2, disciplinaVO.getNome());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerByCodigo(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "delete from Disciplina where values codigo = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, disciplinaVO.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DisciplinaVO> listar() {
        connection = getConnection();
        String sql = "select * from Disciplina";
        Statement statement;
        ResultSet resultSet;
        List<DisciplinaVO> disciplinaVOs = new ArrayList<DisciplinaVO>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                DisciplinaVO disciplinaVO = new DisciplinaVO();
                disciplinaVO.setCodigo(resultSet.getLong("codigo"));
                disciplinaVO.setNome(resultSet.getString("nome"));
                disciplinaVOs.add(disciplinaVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinaVOs;
    }

    public void editar(DisciplinaVO disciplinaVO){
        connection = getConnection();
        String sql = "update Disciplina set codigo = ?, nome = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, disciplinaVO.getCodigo());
            preparedStatement.setString(2, disciplinaVO.getNome());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
