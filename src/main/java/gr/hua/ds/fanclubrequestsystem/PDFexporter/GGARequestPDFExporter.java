package gr.hua.ds.fanclubrequestsystem.PDFexporter;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GGARequestPDFExporter {
    private RequestGGA ggaRequest;

    public GGARequestPDFExporter(RequestGGA ggaRequest) {
        this.ggaRequest = ggaRequest;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {

        //Create a new document
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        //FONTS
        Font bigTitleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        bigTitleFont.setSize(20);
        bigTitleFont.setColor(Color.BLACK);

        Font bigFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        bigFont.setSize(17);
        bigFont.setColor(Color.BLACK);

        Font smallFont = FontFactory.getFont(FontFactory.HELVETICA);
        smallFont.setSize(12);
        smallFont.setColor(Color.BLACK);

        Font smallBoldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        smallBoldFont.setSize(12);
        smallBoldFont.setColor(Color.BLACK);


        //START
        Paragraph p = new Paragraph(" ELLHNIKH DHMOKRATIA \n GENIKH GRAMMATEIA A8LHTISMOY \n TMHMA LESXON FILA8LON", bigFont);
        p.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(p);


        //DETAILS
        Paragraph p1 = new Paragraph("\n Tax. Dief8unsh: " + ggaRequest.getGGA().getAddress() +
                                            "\nTax. Kodikas   : 17676" +
                                            "\nThlefono          : 2131316000" +
                                            "\nEmail               : " + ggaRequest.getGGA().getEmail(), smallFont);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);

        Paragraph p2 = new Paragraph("\n Pros:", smallBoldFont);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p2);

        Paragraph p3 = new Paragraph(ggaRequest.getFanclub().getName() +
                                    "\n" + ggaRequest.getFanclub().getAddress() +
                                    "\n" + ggaRequest.getFanclub().getEmail(), smallFont);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p3);


        //TITLE
        Paragraph p4 = new Paragraph("\nBEBAIOSH LEITOYRGIAS LESXHS", bigTitleFont);
        p4.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p4);

        //TEXT
        Paragraph p5 = new Paragraph("\nSas enhmeronoume katopin elegxou ton dikaiologhtikon pou sunodeuoun thn aithsh leitourgias lesxhs oti plhrounte " +
                "oles oi problepomenes peri ths nomimhs leitourgias proypo8eseis\nkai se efarmogh ton diatajeon toy ar8rou 3,6, 8 kai 9 tou " +
                "Nomou 4908/2022 (FEK A' 52/11-03-2022) tou ar8rou 12:" +
                "\nXorhgoume sth lesxh '" + ggaRequest.getFanclub().getName() + "' me ID: " + ggaRequest.getFanclub().getID() +
                " pou anhkei ston a8lhtiko omilo '" + ggaRequest.getFanclub().getSportsClub() +
                "' kai h opoia idru8hke kata thn hmeromhnia " + ggaRequest.getFanclub().getDateFoundation() +
                "\nBebaiosh nomimhs leitourgias kai pleon h anafer8eisa lesxh mporei na leitourgei sumfona me tis isxuouses diatajeis.", smallFont);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p5);


        //DATE
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat ("dd.MM.yyyy");
        Paragraph pDate = new Paragraph("\n\n\n" + format.format(date), smallFont);
        pDate.setAlignment(Paragraph.ALIGN_RIGHT);

        document.add(pDate);

        document.close();
    }

}
