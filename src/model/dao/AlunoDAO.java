package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enums.NivelDeUsuario;
import model.vo.AlunoVO;
import model.vo.EnderecoVO;

public class AlunoDAO extends BaseDAO{
    
    public long inserir(AlunoVO alunoVO){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        long id = usuarioDAO.inserir(alunoVO);

        connection = getConnection();
        String sql = "INSERT INTO Aluno (id, nome, nivel, username, senha, matricula, endereco) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, alunoVO.getNome());
            preparedStatement.setObject(3, alunoVO.getNivel());
            preparedStatement.setString(4, alunoVO.getUsername());
            preparedStatement.setString(5, alunoVO.getSenha());
            preparedStatement.setString(6, alunoVO.getMatricula());
            preparedStatement.setObject(7, alunoVO.getEndereco());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;
    }

    public void remover(AlunoVO alunoVO){
        connection = getConnection();
        String sql ="delete from Aluno where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, alunoVO.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        connection = getConnection();
        String sql = "update Aluno set nome = ?, nivel = ?, username = ?, senha = ?, matricula = ?, endereco = ?";
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alunoVO.getNome());
            preparedStatement.setObject(2, alunoVO.getNivel());
            preparedStatement.setString(3, alunoVO.getUsername());
            preparedStatement.setString(4, alunoVO.getSenha());
            preparedStatement.setString(5, alunoVO.getMatricula());
            preparedStatement.setObject(6, alunoVO.getEndereco());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
