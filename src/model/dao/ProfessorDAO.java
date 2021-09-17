package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProfessorVO;

public class ProfessorDAO extends BaseDAO {
    
    public long inserir(ProfessorVO professor) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        long id = usuarioDAO.inserir(professor);

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        long enderecoId = enderecoDAO.inserir(professor.getEndereco());

        connection = getConnection();
        String sql = "INSERT INTO Professor (id, cpf, endereco_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, professor.getCpf());
            preparedStatement.setObject(3, enderecoId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void remover(Long id) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        long enderecoId = enderecoDAO.getByProfessorId(id).getId();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.remover(id);
        enderecoDAO.remover(enderecoId);
    }

    public List<ProfessorVO> listar() {
        connection = getConnection();
        String sql = "SELECT * FROM Professor INNER JOIN Usuario ON Professor.id=Usuario.id";
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        List<ProfessorVO> result = new ArrayList<ProfessorVO>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                ProfessorVO professor = new ProfessorVO();
                professor.setId(resultSet.getLong("id"));
                professor.setNome(resultSet.getString("nome"));
                professor.setUsername(resultSet.getString("username"));
                professor.setSenha(resultSet.getString("senha"));
                professor.setCpf(resultSet.getString("cpf"));
                professor.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));

                result.add(professor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void editar(ProfessorVO professor){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.editar(professor);

        connection = getConnection();
        String sql = "UPDATE Professor SET cpf=?, endereco_id=? WHERE id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, professor.getCpf());
            preparedStatement.setLong(2, professor.getEndereco().getId());
            preparedStatement.setLong(3, professor.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProfessorVO getById(long id) {
        connection = getConnection();
        String sql = "SELECT * FROM Professor INNER JOIN Usuario ON Professor.id=Usuario.id WHERE Professor.id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ProfessorVO professor = new ProfessorVO();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            resultSet.next();
            professor.setId(resultSet.getLong("id"));
            professor.setNome(resultSet.getString("nome"));
            professor.setCpf(resultSet.getString("cpf"));
            professor.setUsername(resultSet.getString("username"));
            professor.setSenha(resultSet.getString("senha"));
            professor.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }
}
