package br.com.sisgr.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBuilder extends AbstractITextPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document doc, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		Reuniao reuniao = (Reuniao) model.get("reuniao");
		
		doc.addTitle("Reuniao_" + reuniao.getId() + "_" + reuniao.getNome() + "_SisGR");

		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD);
		Font fontSubTitle = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD);
		
		Paragraph space = new Paragraph("");
		space.setSpacingAfter(15);
		
		Paragraph titulo = new Paragraph("Sis-GR Gerenciamento de Reuniões", fontTitle);
		titulo.setAlignment(Element.ALIGN_CENTER);
		titulo.setSpacingAfter(20);
		doc.add(titulo);
		
		doc.add(new Paragraph("Resumo da reunião : " + reuniao.getNome()));
		doc.add(new Paragraph("Descrição da reunião : " + reuniao.getDescricao()));
		doc.add(space);

		doc.add(new Paragraph("Dados do agendamento da reunião", fontSubTitle));
		
		PdfPTable reuniaoTable = new PdfPTable(4);
		reuniaoTable.setWidthPercentage(100.0f);	
		reuniaoTable.setWidths(new float[] {3.0f, 3.0f, 3.0f, 3.0f});
        reuniaoTable.setSpacingBefore(10);
     
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Data", font));
        reuniaoTable.addCell(cell);
         
        cell.setPhrase(new Phrase("Início", font));
        reuniaoTable.addCell(cell);
 
        cell.setPhrase(new Phrase("Fim", font));
        reuniaoTable.addCell(cell);
        
        cell.setPhrase(new Phrase("Local", font));
        reuniaoTable.addCell(cell);
        
        SimpleDateFormat dataFormatador = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat horaFormatador = new SimpleDateFormat("HH:mm");
        
        reuniaoTable.addCell(dataFormatador.format(reuniao.getDia()));
        reuniaoTable.addCell(horaFormatador.format(reuniao.getInicio()));
        reuniaoTable.addCell(horaFormatador.format(reuniao.getFim()));
        reuniaoTable.addCell(reuniao.getLocal());
    
        doc.add(reuniaoTable);
        doc.add(space);
        
        doc.add(new Paragraph("Participantes da Reunião", fontSubTitle));
        
		PdfPTable contatosTable = new PdfPTable(3);
		contatosTable.setWidthPercentage(100.0f);	
		contatosTable.setWidths(new float[] {3.0f, 3.0f, 3.0f});
        contatosTable.setSpacingBefore(10);
     
        // define table header cell
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(BaseColor.BLUE);
        cell1.setPadding(5);
         
        // write table header
        cell1.setPhrase(new Phrase("Nome", font));
        contatosTable.addCell(cell1);
         
        cell1.setPhrase(new Phrase("Sobrenome", font));
        contatosTable.addCell(cell1);
 
        cell1.setPhrase(new Phrase("Email", font));
        contatosTable.addCell(cell1);
                  
        // write table row data
        for (Contato contato : reuniao.getContatos()) {
            contatosTable.addCell(contato.getNome());
            contatosTable.addCell(contato.getSobrenome());
            contatosTable.addCell(contato.getEmail());
        }
         
        doc.add(contatosTable);
        doc.add(space);
        
        List<Tarefa> tarefas = new ArrayList<>();
		
		for(Contato contato: reuniao.getContatos()){
			tarefas.addAll(contato.getTarefas());
		}
	
        
        if (tarefas.isEmpty()) {
        	doc.add(new Paragraph("A reunião não possui tarefas alocadas."));
        }else{
            doc.add(new Paragraph("Lista de tarefas", fontSubTitle));
            
    		PdfPTable tarefasTable = new PdfPTable(3);
    		tarefasTable.setWidthPercentage(100.0f);	
    		tarefasTable.setWidths(new float[] {3.0f, 3.0f, 3.0f});
            tarefasTable.setSpacingBefore(10);
         
            // define table header cell
            PdfPCell cell2 = new PdfPCell();
            cell2.setBackgroundColor(BaseColor.BLUE);
            cell2.setPadding(5);
             
            // write table header
            cell2.setPhrase(new Phrase("Tarefa", font));
            tarefasTable.addCell(cell2);
             
            cell2.setPhrase(new Phrase("Prazo", font));
            tarefasTable.addCell(cell2);
     
            cell2.setPhrase(new Phrase("Responsável", font));
            tarefasTable.addCell(cell2);
                      
            // write table row data
            for (Tarefa tarefa : tarefas) {
                tarefasTable.addCell(tarefa.getDescricao());
                tarefasTable.addCell(dataFormatador.format(tarefa.getPrazo()));
                tarefasTable.addCell(tarefa.getContato().getNome());
            }
            
            doc.add(tarefasTable);
        }
	}
}
