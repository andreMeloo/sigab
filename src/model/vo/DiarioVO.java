package model.vo;

public class DiarioVO {

	// O -1 diferencia valores "nulos" de zeros reais. Não é definitivo, pode mudar.
	private double nota1;
	private double nota2;
	private double nota3;
	private double quartaProva;
	private double media;
	private int frequencia;
	private AlunoVO aluno;
	private TurmaVO turma;
	
	public double getNota1() {
		return nota1;
	} 
	
	public void setNota1(double nota1) {
		if(nota1 >= 0 && nota1 <= 100)
		this.nota1 = nota1;
	}
	
	public double getNota2() {
		return nota2;
	}
	
	public void setNota2(double nota2) {
		if(nota2 >= 0 && nota2 <= 100)
		this.nota2 = nota2;
	}
	
	public double getNota3() {
		return nota3;
	}
	
	public void setNota3(double nota3) {
		if (nota3 >= 0 && nota3 <= 100)
		this.nota3 = nota3;
	}
	
	public double getQuartaProva() {
		return quartaProva;
	}
	
	public void setQuartaProva(double quartaProva) {
		if(quartaProva >= 0 && quartaProva <= 100)
		this.quartaProva = quartaProva;
	}
	
	public int getFrequencia() {
		return frequencia;
	}
	
	public void setFrequencia(int frequencia) {
		if(frequencia >= 0 && frequencia <= 100)
		this.frequencia = frequencia;
	}
	
	public AlunoVO getAluno() {
		return aluno;
	}
	
	public void setAluno(AlunoVO aluno) {
		if (aluno != null)
		this.aluno = aluno;
	}
	
	public TurmaVO getTurma() {
		return turma;
	}
	
	public void setTurma(TurmaVO turma) {
		if (turma != null)
		this.turma = turma;
	}
	
	public double getMedia() {
		return media;
	}
	
	public void setMedia(double media) {
		this.media = media;
	}
}
