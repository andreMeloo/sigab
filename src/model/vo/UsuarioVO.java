package model.vo;

import enums.NivelDeUsuario;

class UsuarioVO {

	private long id;
	private String nome;
	private NivelDeUsuario nivel;
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

	public NivelDeUsuario getNivel() {
		return nivel;
	}

	public void setNivel(NivelDeUsuario nivel) {
		this.nivel = nivel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null && username.equals(""))
			this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if(senha != null && senha.length() >= 6)
			this.senha = senha;
	}
}
