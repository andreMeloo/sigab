package model.vo;

public class EnderecoVO {
	
	private long id;
	private String endereco;
	private String cidade;
	private String uf;

	public EnderecoVO(String endereco, String cidade, String uf) {
		this.setEndereco(endereco);
		this.setCidade(cidade);
		this.setUf(uf);
	}

	public EnderecoVO() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		if(id > 0)
			this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		if(endereco != null && !endereco.equals(""))
			this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if(cidade != null && !cidade.equals(""))
			this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		if(uf != null && uf.length() > 0 && uf.length() <= 2)
			this.uf = uf;
	}
}
