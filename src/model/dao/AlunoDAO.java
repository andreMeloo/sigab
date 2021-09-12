package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AlunoVO;

public class AlunoDAO extends BaseDAO{
    
    public long inserir(AlunoVO alunoVO){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        long id = usuarioDAO.inserir(alunoVO);

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        long enderecoId = enderecoDAO.inserir(alunoVO.getEndereco());

        connection = getConnection();
        String sql = "INSERT INTO Aluno (id, matricula, endereco_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, alunoVO.getMatricula());
            preparedStatement.setLong(3, enderecoId);
            preparedStatement.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;
    }

    public void remover(long id){
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        long enderecoId = enderecoDAO.getByAlunoId(id).getId();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.remover(id);
        enderecoDAO.remover(enderecoId);
    }

    public List<AlunoVO> listar(){
        connection = getConnection();
        String sql = "SELECT * FROM Aluno INNER JOIN Usuario ON Aluno.id=Usuario.id";
        Statement statement;
        ResultSet resultSet;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        List<AlunoVO> alunoVOs = new ArrayList<AlunoVO>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                AlunoVO alunoVO = new AlunoVO();
                alunoVO.setId(resultSet.getLong("id"));
                alunoVO.setNome(resultSet.getString("nome"));
                alunoVO.setUsername(resultSet.getString("username"));
                alunoVO.setSenha(resultSet.getString("senha"));
                alunoVO.setMatricula(resultSet.getString("matricula"));
                alunoVO.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));

                alunoVOs.add(alunoVO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return alunoVOs;
    }

    public void editar(AlunoVO alunoVO){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.editar(alunoVO);

        connection = getConnection();
        String sql = "UPDATE Aluno SET matricula = ?, endereco_id = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alunoVO.getMatricula());
            preparedStatement.setLong(2, alunoVO.getEndereco().getId());
            preparedStatement.setLong(3, alunoVO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public AlunoVO getById(long id) {
        connection = getConnection();
        String sql = "SELECT * FROM Aluno INNER JOIN Usuario ON Aluno.id=Usuario.id WHERE Aluno.id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        AlunoVO alunoVO = new AlunoVO();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            resultSet.next();
            alunoVO.setId(resultSet.getLong("id"));
            alunoVO.setNome(resultSet.getString("nome"));
            alunoVO.setMatricula(resultSet.getString("matricula"));
            alunoVO.setUsername(resultSet.getString("username"));
            alunoVO.setSenha(resultSet.getString("senha"));
            alunoVO.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));
        
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunoVO;
    }
}
