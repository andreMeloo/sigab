package model.vo;

import enums.NivelDeUsuario;

public class ProfessorVO extends UsuarioVO {
    
    private String cpf;
    private EnderecoVO endereco;

    public ProfessorVO() {
		this.setNivel(NivelDeUsuario.PROFESSOR);
	}

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