package model.dao;

import java.util.List;

public interface EntityDAOInterface <ClassVO> {
    public void inserir(ClassVO objectVO);
    public void remover(ClassVO objectVO);
    public List<ClassVO> listar();
    public void editar(ClassVO objectVO);
    public ClassVO getById(Long id);
}
