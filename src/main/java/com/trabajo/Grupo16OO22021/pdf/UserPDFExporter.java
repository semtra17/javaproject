package com.trabajo.Grupo16OO22021.pdf;



import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.trabajo.Grupo16OO22021.entities.User;
 
 
public class UserPDFExporter {
    private List<User> listUsers;
     
    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLACK);
		
		
		PdfPCell celda = null;
		
        celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		
		celda = new PdfPCell(new Phrase("DOCUMENTO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		
		celda = new PdfPCell(new Phrase("USUARIO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		
		celda = new PdfPCell(new Phrase("ROLE", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(5);
		table.addCell(celda);
		           
    }
     
    private void writeTableData(PdfPTable table) {
    	PdfPCell celda = null;
		Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		
        for (User user : listUsers) {
        	celda = new PdfPCell(new Phrase(user.getName(), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getLastname(), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
			celda = new PdfPCell(new Phrase(Integer.toString(user.getDocument()), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getEmail(), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getUsername(), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getUserRole().getName(), fuenteDataCeldas));
			celda.setPadding(5);
			table.addCell(celda);
			
		            
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
        font.setSize(18);
         
        Paragraph p = new Paragraph("LISTADO DE USUARIOS", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.2f, 1.4f, 1.8f, 2.5f, 1.5f , 1.3f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
