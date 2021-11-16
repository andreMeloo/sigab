package model.bo;

import java.util.List;

public interface EntityBOInterface<ClassVO> {
    public void salvar(ClassVO classVO);
    public List<ClassVO> listar();
    public void remover(ClassVO classVO);
    public void editar(ClassVO classVO);
    public ClassVO getById(Long id);
}
