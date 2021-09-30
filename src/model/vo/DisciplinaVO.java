package model.vo;

public class DisciplinaVO {

	private Long id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		if(nome != null && !nome.equals(""))
			this.nome = nome;
	}
	
}
