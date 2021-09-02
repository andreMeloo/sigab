package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.EnderecoVO;

public class EnderecoDAO extends BaseDAO{

    public void inserir(EnderecoVO enderecoVO){
        connection = getConnection();
        String sql = "insert into Endereco (rua, cidade, uf) values (?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enderecoVO.getRua());
            preparedStatement.setString(2, enderecoVO.getCidade());
            preparedStatement.setString(3, enderecoVO.getUf());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void removerById(EnderecoVO enderecoVO){
        connection = getConnection();
        String sql = "delete from Disciplina where values codigo = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enderecoVO.getRua());
            preparedStatement.setString(2, enderecoVO.getCidade());
            preparedStatement.setString(3, enderecoVO.getUf());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EnderecoVO> listar() {
        connection = getConnection();
        String sql = "select * from Endereco";
        Statement statement;
        ResultSet resultSet;
        List<EnderecoVO> enderecoVOs = new ArrayList<EnderecoVO>();
        EnderecoVO enderecoVO = new EnderecoVO();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                enderecoVO.setRua(resultSet.getString("rua"));
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
        String sql = "update Endereco set rua = ?, cidade = ?, uf = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enderecoVO.getRua());
            preparedStatement.setString(2, enderecoVO.getCidade());
            preparedStatement.setString(3, enderecoVO.getUf());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
