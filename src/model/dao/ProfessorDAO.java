package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enums.NivelDeUsuario;
import model.vo.EnderecoVO;
import model.vo.ProfessorVO;

public class ProfessorDAO extends BaseDAO {
    
    public void inserir(ProfessorVO professor) {
        connection = getConnection();
        String sql = "INSERT INTO Professor (nome, nivel, username, senha, cpf, endereco) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setObject(2, professor.getNivel());
            preparedStatement.setString(3, professor.getUsername());
            preparedStatement.setString(4, professor.getSenha());
            preparedStatement.setString(5, professor.getCpf());
            preparedStatement.setObject(6, professor.getEndereco());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(Long id) {
        connection = getConnection();
        String sql = "DELETE FROM Professor WHERE id=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProfessorVO> listar() {
        connection = getConnection();
        String sql = "SELECT * FROM Professor";
        List<ProfessorVO> result = new ArrayList<ProfessorVO>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ProfessorVO professor = new ProfessorVO();

            while (resultSet.next()) {
                professor.setId(resultSet.getLong("id"));
                professor.setNome(resultSet.getString("nome"));
                professor.setNivel(NivelDeUsuario.valueOf(resultSet.getString("nivel")));
                professor.setUsername(resultSet.getString("username"));
                professor.setSenha(resultSet.getString("senha"));
                professor.setCpf(resultSet.getString("cpf"));
                professor.setEndereco((EnderecoVO) resultSet.getObject("endereco"));

                result.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void editar(ProfessorVO professor){
        connection = getConnection();
        String sql = "UPDATE Professor SET nome=?, nivel=?, username=?, senha=?, cpf=?, endereco=? WHERE id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setObject(2, professor.getNivel());
            preparedStatement.setString(3, professor.getUsername());
            preparedStatement.setString(4, professor.getSenha());
            preparedStatement.setString(5, professor.getCpf());
            preparedStatement.setObject(6, professor.getEndereco());
            
            preparedStatement.setObject(7, professor.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
