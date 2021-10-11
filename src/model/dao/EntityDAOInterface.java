package model.dao;

import java.util.List;

public interface EntityDAOInterface <ClassVO> { // Provavavelmente vocês viram o padrão entity em algum lugar e acham que tudo que se refere a bd dentro do código java é uma entity.
    // essa classe é qualquer coisa, menos uma Entity. Leiam o padrão. Caso ainda fiquem com dúvidas, me chamem no discord que explico a vocês.
    public void inserir(ClassVO objectVO);
    public void remover(ClassVO objectVO);
    public List<ClassVO> listar();
    public void editar(ClassVO objectVO);
    public ClassVO getById(Long id);
}
