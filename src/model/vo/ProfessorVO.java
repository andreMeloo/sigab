package model.vo;

public class ProfessorVO extends UsuarioVO {
    
    private String cpf;
    private EnderecoVO endereco;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            this.cpf = cpf;
        }
    }

    public EnderecoVO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoVO endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        }
    }

}