package model.vo;

public class AlunoVO {
	private String matricula;
	private EnderecoVO endereco;
	
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
		if(endereco != null)
		this.endereco = endereco;
	}
}
