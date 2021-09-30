package model.vo;

import java.util.List;

public class TurmaVO {
    
    private Long id;
    private String horario;
    private String local;
    private boolean aberta = true;

    private DisciplinaVO disciplina;
    private List<AlunoVO> alunos;
    private ProfessorVO professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        if (horario != null && !horario.equals("")) {
            this.horario = horario;
        }
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        if (local != null && !local.equals("")) {
            this.local = local;
        }
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public DisciplinaVO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaVO disciplina) {
        if (disciplina != null) {
            this.disciplina = disciplina;
        }
    }

    public List<AlunoVO> getAlunos() {
        return alunos;
    }
    
    public void setAlunos(List<AlunoVO> alunos) {
        this.alunos = alunos;
    }

    public ProfessorVO getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorVO professor) {
        if (professor != null) {
            this.professor = professor;
        }
    }
    
}
