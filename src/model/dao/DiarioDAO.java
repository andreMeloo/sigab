package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.AlunoVO;
import model.vo.DiarioVO;


public class DiarioDAO extends BaseDAO implements EntityDAOInterface<DiarioVO>{

    public void inserir(DiarioVO diarioVO) {
        connection = getConnection();
        String sql = "INSERT INTO Diario (nota1, nota2, nota3, quarta_prova, frequencia, aluno_id, turma_id) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, diarioVO.getNota1());
            preparedStatement.setDouble(2, diarioVO.getNota2());
            preparedStatement.setDouble(3, diarioVO.getNota3());
            preparedStatement.setDouble(4, diarioVO.getQuartaProva());
            preparedStatement.setInt(5, diarioVO.getFrequencia());
            preparedStatement.setLong(6, diarioVO.getAluno().getId());
            preparedStatement.setLong(7, diarioVO.getTurma().getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(DiarioVO diarioVO) {
        connection = getConnection();
        String sql = "DELETE FROM Diario WHERE aluno_id=? AND turma_id=?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, diarioVO.getAluno().getId());
            preparedStatement.setLong(2, diarioVO.getTurma().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DiarioVO> listar() {
        connection = getConnection();
        String sql = "SELECT * FROM Diario";
        Statement statement;
        ResultSet resultSet;
        AlunoDAO alunoDAO = new AlunoDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        List<DiarioVO> diarioVOs = new ArrayList<DiarioVO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                DiarioVO diarioVO = new DiarioVO();
                diarioVO.setNota1(resultSet.getDouble("nota1"));
                diarioVO.setNota2(resultSet.getDouble("nota2"));
                diarioVO.setNota3(resultSet.getDouble("nota3"));
                diarioVO.setQuartaProva(resultSet.getDouble("quarta_Prova"));
                diarioVO.setMedia(resultSet.getDouble("media"));
                diarioVO.setFrequencia(resultSet.getInt("frequencia"));
                diarioVO.setAluno(alunoDAO.getById(resultSet.getLong("aluno_id")));
                diarioVO.setTurma(turmaDAO.getById(resultSet.getLong("turma_id")));

                diarioVOs.add(diarioVO);
            }
        } catch (SQLException e) {   
            e.printStackTrace();
        }

        return diarioVOs;
    }

    public void editar(DiarioVO diarioVO) {
        connection = getConnection();
        String sql = "UPDATE Diario SET nota1 = ?, nota2 = ?, nota3 = ?, quarta_prova = ?, frequencia = ? WHERE aluno_id=? AND turma_id=?";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, diarioVO.getNota1());
            preparedStatement.setDouble(2, diarioVO.getNota2());
            preparedStatement.setDouble(3, diarioVO.getNota3());
            preparedStatement.setDouble(4, diarioVO.getQuartaProva());
            preparedStatement.setInt(5, diarioVO.getFrequencia());
            preparedStatement.setLong(6, diarioVO.getAluno().getId());
            preparedStatement.setLong(7, diarioVO.getTurma().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DiarioVO getById(Long id) {
        // Diario não possui ID
        return null;
    }

    public DiarioVO getByAlunoIdAndTurmaId(Long alunoId, Long turmaId) {
        connection = getConnection();
        String sql = "SELECT * FROM Diario WHERE aluno_id =? and turma_id=?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        AlunoDAO alunoDAO = new AlunoDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        DiarioVO diario = new DiarioVO();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, alunoId);
            preparedStatement.setLong(2, turmaId);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            if(resultSet.next()) {
                diario.setNota1(resultSet.getDouble("nota1"));
                diario.setNota2(resultSet.getDouble("nota2"));
                diario.setNota3(resultSet.getDouble("nota3"));
                diario.setQuartaProva(resultSet.getDouble("quarta_prova"));
                diario.setMedia(resultSet.getDouble("media"));
                diario.setFrequencia(resultSet.getInt("frequencia"));
                diario.setAluno(alunoDAO.getById(resultSet.getLong("aluno_id")));
                diario.setTurma(turmaDAO.getById(resultSet.getLong("turma_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return diario;
    }

    // Este método é só para gerar o histórico
    public List<DiarioVO> findAllBoletimByAluno(AlunoVO aluno) {
        connection = getConnection();
        String sql = "SELECT * FROM boletim WHERE aluno_id = ?";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        AlunoDAO alunoDAO = new AlunoDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        List<DiarioVO> boletins = new ArrayList<DiarioVO>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aluno.getId());
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                DiarioVO diarioVO = new DiarioVO();
                diarioVO.setNota1(resultSet.getDouble("nota1"));
                diarioVO.setNota2(resultSet.getDouble("nota2"));
                diarioVO.setNota3(resultSet.getDouble("nota3"));
                diarioVO.setQuartaProva(resultSet.getDouble("quarta_Prova"));
                diarioVO.setMedia(resultSet.getDouble("media"));
                diarioVO.setAluno(alunoDAO.getById(resultSet.getLong("aluno_id")));
                diarioVO.setTurma(turmaDAO.getById(resultSet.getLong("turma_id")));

                boletins.add(diarioVO);
            }
        } catch (SQLException e) {   
            e.printStackTrace();
        }

        return boletins;
    }

}
