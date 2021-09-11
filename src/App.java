import java.util.List;

import model.dao.DisciplinaDAO;
import model.dao.EnderecoDAO;
import model.vo.DisciplinaVO;
import model.vo.EnderecoVO;

public class App {
    public static void main(String[] args) throws Exception {

        // Disciplina
        System.out.println("\nDisciplina\n");

        DisciplinaDAO disciplinaDao = new DisciplinaDAO();

        System.out.println("Adicionando:");
        DisciplinaVO disciplina = new DisciplinaVO();
        disciplina.setCodigo(167);
        disciplina.setNome("POO");

        disciplinaDao.inserir(disciplina);
        List<DisciplinaVO> disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }

        System.out.println("\nEditando:");
        disciplina.setNome("BD");
        disciplinaDao.editar(disciplina);
        disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }
        
        System.out.println("\nDeletando:");
        disciplinaDao.removerByCodigo(disciplina);
        disciplinas = disciplinaDao.listar();

        for (var row : disciplinas) {
            System.out.println(row.getCodigo() + " - " + row.getNome());
        }
        
        // Endereco
        System.out.println("\nEndereco\n");

        EnderecoDAO enderecoDao = new EnderecoDAO();

        System.out.println("Adicionando:");
        EnderecoVO endereco = new EnderecoVO();
        endereco.setCidade("Natal");
        endereco.setRua("Rua das Joaninhas");
        endereco.setUf("RN");

        endereco.setId(enderecoDao.inserir(endereco));
        List<EnderecoVO> enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

        System.out.println("\nEditando:");
        endereco.setCidade("Areia Branca");
        enderecoDao.editar(endereco);
        enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

        System.out.println("\nDeletando:");
        enderecoDao.removerById(endereco);
        enderecos = enderecoDao.listar();

        for (var row : enderecos) {
            System.out.println(row.getId() + " - " + row.getRua() + ". " + row.getCidade() + ", " + row.getUf());
        }

    }
}
