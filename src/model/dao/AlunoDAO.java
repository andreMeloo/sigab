package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AlunoVO;

public class AlunoDAO extends BaseDAO implements EntityDAOInterface<AlunoVO>{
    
    public void inserir(AlunoVO alunoVO){

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.inserir(alunoVO);

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.inserir(alunoVO.getEndereco());

        long enderecoId = alunoVO.getEndereco().getId();

        connection = getConnection();
        
        String sql = "INSERT INTO Aluno (id ,matricula, endereco_id) VALUES (?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, alunoVO.getId());;
            preparedStatement.setString(2, alunoVO.getMatricula());
            preparedStatement.setLong(3, enderecoId);
            preparedStatement.execute();

            ResultSet keys = preparedStatement.getGeneratedKeys();

            keys.next();
            alunoVO.setId(keys.getLong(1));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(AlunoVO alunoVO){
        // EnderecoDAO enderecoDAO = new EnderecoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.remover(usuarioDAO.getById(alunoVO.getId()));
        // enderecoDAO.remover(enderecoDAO.getByAluno(alunoVO));
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
                connection.close();
            }
        } catch (SQLException e) {
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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public AlunoVO getById(Long id) {
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

            if(resultSet.next()) {
                alunoVO.setId(resultSet.getLong("id"));
                alunoVO.setNome(resultSet.getString("nome"));
                alunoVO.setMatricula(resultSet.getString("matricula"));
                alunoVO.setUsername(resultSet.getString("username"));
                alunoVO.setSenha(resultSet.getString("senha"));
                alunoVO.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alunoVO;
    }

    public List<AlunoVO> getAllByTurmaId(long id) {

        connection = getConnection();
        String sql = "SELECT * FROM Aluno INNER JOIN Usuario ON Aluno.id=Usuario.id "
                    + "RIGHT JOIN Diario ON Aluno.id=Diario.aluno_id WHERE Diario.turma_id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        List<AlunoVO> alunos = new ArrayList<AlunoVO>();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                AlunoVO aluno = new AlunoVO();
                aluno.setId(resultSet.getLong("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setUsername(resultSet.getString("username"));
                aluno.setSenha(resultSet.getString("senha"));
                aluno.setMatricula(resultSet.getString("matricula"));
                aluno.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));

                alunos.add(aluno);
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public AlunoVO getByMatricula(String matricula) {
        connection = getConnection();
        String sql = "SELECT * FROM Aluno INNER JOIN Usuario ON Aluno.id=Usuario.id WHERE Aluno.matricula=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        AlunoVO alunoVO = new AlunoVO();
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, matricula);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            if(resultSet.next()) {
                alunoVO.setId(resultSet.getLong("id"));
                alunoVO.setNome(resultSet.getString("nome"));
                alunoVO.setMatricula(resultSet.getString("matricula"));
                alunoVO.setUsername(resultSet.getString("username"));
                alunoVO.setSenha(resultSet.getString("senha"));
                alunoVO.setEndereco(enderecoDAO.getById(resultSet.getLong("endereco_id")));
            }
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return alunoVO;
    }
}

    
