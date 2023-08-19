package Classes;

public class Aluno_Disciplina {
	private int aluno_matr;
	private int disciplina_cod;
	private String periodo;
	private float nota;
	private float frequencia;
	
	public int getAluno_matr() {
		return aluno_matr;
	}
	public void setAluno_matr(int aluno_matr) {
		this.aluno_matr = aluno_matr;
	}
	public int getDisciplina_cod() {
		return disciplina_cod;
	}
	public void setDisciplina_cod(int disciplina_cod) {
		this.disciplina_cod = disciplina_cod;
	}
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public float getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(float frequencia) {
		this.frequencia = frequencia;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
