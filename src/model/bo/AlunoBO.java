package model.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import model.dao.AlunoDAO;
import model.vo.AlunoVO;
import model.vo.DiarioVO;

public class AlunoBO implements EntityBOInterface<AlunoVO>{

    @Override
    public void salvar(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.inserir(alunoVO);
    }

    @Override
    public List<AlunoVO> listar() {
        AlunoDAO alunoDAO = new AlunoDAO();
        return  alunoDAO.listar();
    }

    @Override
    public void remover(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoDAO.getById(alunoVO.getId());
            alunoDAO.remover(alunoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void editar(AlunoVO alunoVO) {
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoDAO.getById(alunoVO.getId());
            alunoDAO.editar(alunoVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public AlunoVO getById(Long id) {
        AlunoDAO alunoDAO = new AlunoDAO();
        return alunoDAO.getById(id);
    }
    
    public List<AlunoVO> buscarPorNome(String nome) {
        
        AlunoDAO usuarioDAO = new AlunoDAO();
        List<AlunoVO> listaPorNome = new ArrayList<AlunoVO>();
        
        listaPorNome = usuarioDAO.listar();
        listaPorNome.removeIf(s -> !s.getNome().contains(nome));

        return listaPorNome;
    }

    public List<AlunoVO> buscarPorTurma(String codigo) {

        AlunoDAO usuarioDAO = new AlunoDAO();
        List<AlunoVO> listaPorTurma = new ArrayList<AlunoVO>();
        
        listaPorTurma = usuarioDAO.listar();
        listaPorTurma.removeIf(s -> !s.getNome().contains(codigo));

        return listaPorTurma;
    }

    public AlunoVO buscarPorMatricula(String matricula) {
        AlunoVO alunoVO = new AlunoVO();
        AlunoDAO alunoDAO = new AlunoDAO();
        try {
            alunoVO = alunoDAO.getByMatricula(matricula);
            return alunoVO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String gerarMatricula() {
        String matricula = "";
        String finalMatricula = "";
        String inicioMatricula = "";
        // gerando parte inicial da matricula

        int ano = Calendar.getInstance().get(Calendar.YEAR);
        int periodo = Calendar.getInstance().get(Calendar.MONTH);

        if (periodo <= 6) {
            periodo = 1;
        } else {
            periodo = 2;
        }
        
        inicioMatricula += + ano + "0" + periodo;

        // gerando parte final da matricula

        try {
            List<AlunoVO> alunos = this.listar();
            Random r = new Random();
            while (finalMatricula.equals("")) {
                finalMatricula += r.nextInt(10000);

                for (AlunoVO aluno : alunos) {
                    if (aluno.getMatricula().equals(inicioMatricula + finalMatricula)) {
                       finalMatricula = "";
                    }
                }
            }
            matricula = inicioMatricula + finalMatricula;

            return matricula;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }

    public boolean gerarHistorico(AlunoVO aluno) {
        DiarioBO diarioBO = new DiarioBO();
        List<DiarioVO> boletins = diarioBO.getAllBoletimByAluno(aluno);
        Date date = new Date();
        String nomeDoAluno = aluno.getNome().toLowerCase().strip();
        String documentName = "historico_" + nomeDoAluno + date.getTime() + ".pdf";

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(documentName));
            document.open();
            document.addTitle("Histórico");

            Paragraph header = new Paragraph("Histórico", new Font(FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(0, 0, 0)) );
            Chunk linebreak = new Chunk(new DottedLineSeparator());
            document.add(header);
            document.add(linebreak);

            PdfPTable aPTable = new PdfPTable(7);
            aPTable.addCell(new Paragraph("Disciplina", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Nota 1", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Nota 2", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Nota 3", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Quarta Prova", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Média", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.addCell(new Paragraph("Status", new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(0, 0, 0)) ));
            aPTable.completeRow();

            for (var boletim : boletins) {
                aPTable.addCell(new Paragraph(boletim.getTurma().getDisciplina().getNome(), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(normalizeNota(boletim.getNota1()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(normalizeNota(boletim.getNota2()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(normalizeNota(boletim.getNota3()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(normalizeNota(boletim.getQuartaProva()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(normalizeNota(boletim.getMedia()), new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.addCell(new Paragraph(boletim.getMedia() > 7 ? "Aprovado" : "Reprovado", new Font(FontFamily.HELVETICA, 8, Font.NORMAL, new BaseColor(0, 0, 0)) ));
                aPTable.completeRow();
            }

            document.add(aPTable);
            
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return false;
        } finally {
            document.close();
        }

        return true;
    }

    private String normalizeNota(Double nota) {
        return nota == null ? "-" : String.valueOf(nota);
    }

}
