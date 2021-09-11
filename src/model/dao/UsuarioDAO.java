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
    
    public long inserir(UsuarioVO usuarioVO) {

        List<UsuarioVO> list = this.listar();
        long id = 1;
        if (list.size() > 0) {
            long lastId = list.get(list.size() - 1).getId();
            id = lastId + 1;
        }

        connection = getConnection();
        String sql = "insert into Usuario(id, nome, nivel, username, senha) values (?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, usuarioVO.getNome());
            preparedStatement.setString(3, usuarioVO.getNivel().name());
            preparedStatement.setString(4, usuarioVO.getUsername());
            preparedStatement.setString(5, usuarioVO.getSenha());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void remover(UsuarioVO usuarioVO) {
        connection = getConnection();
        String sql = "DELETE FROM Usuario WHERE id = ?";

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
        String sql = "SELECT * FROM Usuario";
        Statement statement;
        ResultSet resultSet;
        List<UsuarioVO> usuarioVOs = new ArrayList<UsuarioVO>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                UsuarioVO usuarioVO = new UsuarioVO();
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
        String sql = "UPDATE Usuario SET nome = ?, username = ?, senha = ? WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuarioVO.getNome());
            preparedStatement.setString(2, usuarioVO.getUsername());
            preparedStatement.setString(3, usuarioVO.getSenha());
            preparedStatement.setLong(4, usuarioVO.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
