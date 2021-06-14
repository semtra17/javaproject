package com.trabajo.Grupo16OO22021.pdf;

import java.awt.Color;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.trabajo.Grupo16OO22021.entities.UserRole;

public class ProfilePDFExporter {
	 private List<UserRole> listUserRole;
     
	    public ProfilePDFExporter(List<UserRole> listUserRole) {
	        this.listUserRole = listUserRole;
	    }
		private void writeTableHeader(PdfPTable table) {
		    PdfPCell cell = new PdfPCell();
		    cell.setBackgroundColor(Color.BLACK);
		    cell.setPadding(3);
		     
		    Font font = FontFactory.getFont(FontFactory.HELVETICA);
		    font.setColor(Color.WHITE);
		     
		   
			Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD ,12,Color.BLACK);
		    cell = new PdfPCell(new Phrase("ROLE", fuenteTituloColumnas));
			cell.setBackgroundColor(Color.lightGray);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(5);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("DESCRIPCION", fuenteTituloColumnas));
			cell.setBackgroundColor(Color.lightGray);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPadding(5);
			table.addCell(cell);
			
		     
		}
		 
		private void writeTableData(PdfPTable table) {
			Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER ,10,Color.BLACK);
		    PdfPCell cell = new PdfPCell();

		    for (UserRole UserRole : listUserRole) {
		    	cell = new PdfPCell(new Phrase(UserRole.getName(), fuenteDataCeldas));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(5);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(UserRole.getDescription(), fuenteDataCeldas));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(5);
				table.addCell(cell);    
		    }
		}
		 
		public void export(HttpServletResponse response) throws DocumentException, IOException {
		    Document document = new Document(PageSize.A4);
		    PdfWriter.getInstance(document, response.getOutputStream());
		     
		    document.open();
		    Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLACK);
		    font.setSize(18);

		    Paragraph p = new Paragraph("LISTADO DE PERFILES", font);
		    p.setAlignment(Paragraph.ALIGN_CENTER);
		     
		    document.add(p);
		     
		    PdfPTable table = new PdfPTable(2);
		    table.setWidthPercentage(100f);
		    
		    table.setSpacingBefore(10);
		     
		    writeTableHeader(table);
		    writeTableData(table);
		     
		    document.add(table);
		     
		    document.close();
		     
		}
}
