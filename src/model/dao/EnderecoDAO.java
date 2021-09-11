package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.EnderecoVO;

public class EnderecoDAO extends BaseDAO{

    public long inserir(EnderecoVO enderecoVO){

        List<EnderecoVO> list = this.listar();
        long id = 1;
        if (list.size() > 0) {
            long lastId = list.get(list.size() - 1).getId();
            id = lastId + 1;
        }

        connection = getConnection();
        String sql = "INSERT INTO Endereco (id, endereco, cidade, uf) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, enderecoVO.getRua());
            preparedStatement.setString(3, enderecoVO.getCidade());
            preparedStatement.setString(4, enderecoVO.getUf());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void removerById(EnderecoVO enderecoVO){
        connection = getConnection();
        String sql = "DELETE FROM Endereco WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, enderecoVO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnderecoVO> listar() {
        connection = getConnection();
        String sql = "SELECT * FROM Endereco";
        Statement statement;
        ResultSet resultSet;
        List<EnderecoVO> enderecoVOs = new ArrayList<EnderecoVO>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                EnderecoVO enderecoVO = new EnderecoVO();
                enderecoVO.setId(resultSet.getLong("id"));
                enderecoVO.setRua(resultSet.getString("endereco"));

                enderecoVO.setCidade(resultSet.getString("cidade"));
                enderecoVO.setUf(resultSet.getString("uf"));
                enderecoVOs.add(enderecoVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enderecoVOs;
    }

    public void editar(EnderecoVO enderecoVO){
        connection = getConnection();
        String sql = "UPDATE Endereco SET endereco = ?, cidade = ?, uf = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enderecoVO.getRua());
            preparedStatement.setString(2, enderecoVO.getCidade());
            preparedStatement.setString(3, enderecoVO.getUf());
            preparedStatement.setLong(4, enderecoVO.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EnderecoVO getById(long id) {
        connection = getConnection();
        String sql = "SELECT * FROM Endereco WHERE id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        EnderecoVO enderecoVO = new EnderecoVO();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            resultSet.next();
            enderecoVO.setId(resultSet.getLong("id"));
            enderecoVO.setRua(resultSet.getString("endereco"));
            enderecoVO.setCidade(resultSet.getString("cidade"));
            enderecoVO.setUf(resultSet.getString("uf"));
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecoVO;
    }
}
