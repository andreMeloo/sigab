package model.vo;

public class EnderecoVO {
	
	private long id;
	private String rua;
	private String cidade;
	private String uf;

	public EnderecoVO(String rua, String cidade, String uf) {
		this.setRua(rua);
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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if(rua != null && !rua.equals(""))
			this.rua = rua;
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
