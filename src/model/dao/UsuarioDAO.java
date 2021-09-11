package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enums.NivelDeUsuario;
import model.vo.UsuarioVO;




public class UsuarioDAO extends BaseDAO {
    
    public void inserir(UsuarioVO usuarioVO) {
        connection = getConnection();
        String sql = "insert into Usuario(nome, nivel, username, senha) values (?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuarioVO.getNome());
            preparedStatement.setObject(2, usuarioVO.getNivel());
            preparedStatement.setString(3, usuarioVO.getUsername());
            preparedStatement.setString(4, usuarioVO.getSenha());
            preparedStatement.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void remover(UsuarioVO usuarioVO) {
        connection = getConnection();
        String sql = "delete from Usuario where values id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, usuarioVO.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<UsuarioVO> listar() {
        connection = getConnection();
        String sql = "select * from Usuario";
        Statement statement;
        ResultSet resultSet;
        List<UsuarioVO> usuarioVOs = new ArrayList<UsuarioVO>();
        UsuarioVO usuarioVO = new UsuarioVO();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                usuarioVO.setId(resultSet.getLong("id"));
                usuarioVO.setNome(resultSet.getString("nome"));
                usuarioVO.setNivel(NivelDeUsuario.valueOf(resultSet.getString("nivel")));
                usuarioVO.setUsername(resultSet.getString("username"));
                usuarioVO.setSenha(resultSet.getString("senha"));

                usuarioVOs.add(usuarioVO);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return usuarioVOs;
    }

    public void editar(UsuarioVO usuarioVO){
        connection = getConnection();
        String sql = "UPDATE Usuario SET nome = ?, nivel = ?, username = ?, senha = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuarioVO.getNome());
            preparedStatement.setObject(2, usuarioVO.getNivel());
            preparedStatement.setString(3, usuarioVO.getUsername());
            preparedStatement.setString(4, usuarioVO.getSenha());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
