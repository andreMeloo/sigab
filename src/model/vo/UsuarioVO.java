package model.vo;

class UsuarioVO {
	private long id;
	private String nome;
	private int nivel;
	private String username;
	private String senha;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		if(id > 0)
		this.id = id;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome != null)
		this.nome = nome;
	}


	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		if(nivel >= 0 && nivel <= 2)
		this.nivel = nivel;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username != null && username != (""))
		this.username = username;
	}


	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		if(senha != null && senha != (""))
		this.senha = senha;
	}
}
