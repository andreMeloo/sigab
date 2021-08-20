package model.vo;

public class DisciplinaVO {
	private long codigo;
	private String nome;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		if(codigo > 0)
			this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome != null && !nome.equals(""))
			this.nome = nome;
	}
	
}
