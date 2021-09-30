package model.dao;

import java.util.List;

public interface EntityDAOInterface <ClassVO> {
    public void inserir(ClassVO objectVO);
    public void remover();
    public List<ClassVO> listar();
    public void editar();
    public ClassVO getById();
}
