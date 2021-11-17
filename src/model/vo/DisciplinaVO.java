package model.vo;

public class DisciplinaVO {

	private Long id;
	private String codigo;
	private String nome;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		if(codigo != null && !codigo.equals(""))
			this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !nome.equals(""))
			this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return getCodigo() + " - " + getNome();
	}
	
}
