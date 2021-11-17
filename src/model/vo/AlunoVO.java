package model.vo;

import enums.NivelDeUsuario;

public class AlunoVO extends UsuarioVO {
	private String matricula;
	private EnderecoVO endereco;

	public AlunoVO() {
		this.setNivel(NivelDeUsuario.ALUNO);
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		if(matricula != null && !matricula.equals(""))
		this.matricula = matricula;
	}

	public EnderecoVO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVO endereco) {
		if (endereco != null) {
            if (!endereco.getEndereco().isBlank() && !endereco.getCidade().isBlank() && !endereco.getUf().isBlank()) {
                this.endereco = endereco;
            }
        }
	}

}
