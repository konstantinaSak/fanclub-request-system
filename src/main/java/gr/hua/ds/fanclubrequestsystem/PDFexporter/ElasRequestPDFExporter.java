package gr.hua.ds.fanclubrequestsystem.PDFexporter;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ElasRequestPDFExporter {

    private RequestELAS elasRequest;

    public ElasRequestPDFExporter(RequestELAS elasRequest) {
        this.elasRequest = elasRequest;
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
        Paragraph p = new Paragraph(" ELLHNIKH DHMOKRATIA \n ELLHNIKH ASTYNOMIA \n TMHMA LESXON FILA8LON", bigFont);
        p.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(p);


        //DETAILS
        Paragraph p1 = new Paragraph("\n AT                   : Astunomiko Tmhma " + elasRequest.getELAS().getDepartment() +
                "\n Tax. Dief8unsh: " + elasRequest.getELAS().getAddress() +
                "\nTax. Kodikas   : 17676" +
                "\nThlefono          : 2131316000" +
                "\nEmail               : " + elasRequest.getELAS().getEmail(), smallFont);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);

        Paragraph p2 = new Paragraph("\n Pros:", smallBoldFont);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p2);

        Paragraph p3 = new Paragraph(elasRequest.getFanclub().getName() +
                "\n" + elasRequest.getFanclub().getAddress() +
                "\n" + elasRequest.getFanclub().getEmail(), smallFont);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p3);


        //TITLE
        Paragraph p4 = new Paragraph("\nEGKRISH LEITOYRGIAS KATASTHMATON H/KAI ENTEYKTHRION", bigTitleFont);
        p4.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p4);

        //TEXT
        Paragraph p5 = new Paragraph("\nSas enhmeronoume katopin elegxou ton dikaiologhtikon pou sunodeuoun thn aithsh egkrishs leitourgias " +
                "katasthmaton h/kai enteukthrion lesxhs oti plhrounte oles oi problepomenes peri ths nomimhs leitourgias proypo8eseis" +
                "\nkai se efarmogh ton diatajeon toy ar8rou 3,6, 8 kai 9 tou Nomou 4908/2022 (FEK A' 52/11-03-2022) tou ar8rou 12:" +
                "\nXorhgoume sth lesxh '" + elasRequest.getFanclub().getName() + "' me ID: " + elasRequest.getFanclub().getID() +
                " pou anhkei ston a8lhtiko omilo '" + elasRequest.getFanclub().getSportsClub() +
                "' kai h opoia idru8hke kata thn hmeromhnia " + elasRequest.getFanclub().getDateFoundation() +
                "\nEgkrish nomimhs leitourgias katasthmaton h/kai enteukthrion kai pleon oi xoroi sygkentroshs ths anafer8eisas lesxhs mporoun " +
                "na leitourgoun nomima sumfona me tis isxuouses diatajeis.", smallFont);
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
