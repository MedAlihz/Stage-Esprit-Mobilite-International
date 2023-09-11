package com.example.ahmed.Service;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import com.example.ahmed.Entity.Candidature;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

@AllArgsConstructor
@Service
public class PdfGenerationService {

    public byte[] generateCandidaturePdf(Candidature candidature) {

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDImageXObject backgroundImage = PDImageXObject.createFromFile("src/main/resources/images/bg2.jpg", document);

            PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/images/logo.png", document);
            float logoWidth = 120f; // Adjust the width as needed
            float logoHeight = 70f; // Adjust the height as needed
            float x = 25f; // Adjust the X position
            float y = PDRectangle.A4.getHeight() - logoHeight - 25f; // Adjust the Y position

            // Draw the background image using its original dimensions
            float imageWidth = backgroundImage.getWidth();
            float imageHeight = backgroundImage.getHeight();
            float pageWidth = PDRectangle.A4.getWidth();
            float pageHeight = PDRectangle.A4.getHeight();

            // Calculate the scaling factors to fit the image onto the page
            float scaleWidth = pageWidth / imageWidth;
            float scaleHeight = pageHeight / imageHeight;
            float scaleFactor = Math.max(scaleWidth, scaleHeight);

            // Calculate the new dimensions of the image to maintain its original aspect ratio
            imageWidth *= scaleFactor;
            imageHeight *= scaleFactor;

            // Calculate the position to center the image on the page
            float W = (pageWidth - imageWidth) / 2;
            float H = (pageHeight - imageHeight) / 2;
            PDFont font = PDType0Font.load(document, new FileInputStream("src/main/resources/fonts/Helvetica-Bold-Font.ttf"));
            PDFont font2 = PDType0Font.load(document, new FileInputStream("src/main/resources/fonts/Quicksand-Bold.ttf"));
            PDFont font3 = PDType0Font.load(document, new FileInputStream("src/main/resources/fonts/Courier-BOLD.ttf"));

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            //contentStream.drawImage(backgroundImage, 0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
            //contentStream.drawImage(backgroundImage, W, H, imageWidth, imageHeight);

            contentStream.drawImage(logo, x, y, logoWidth, logoHeight);

            contentStream.setNonStrokingColor(Color.red);


            contentStream.setFont(font2, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(446, 800); // Adjust the position as needed
            contentStream.showText("Service Mobilité");
            contentStream.endText();
            contentStream.setNonStrokingColor(Color.red);
            contentStream.setFont(font2, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(456, 780); // Adjust the position as needed
            contentStream.showText("Internationale");
            contentStream.endText();
            // Add content to the PDF here, e.g., candidate's information
            contentStream.setNonStrokingColor(Color.black);

            contentStream.setFont(font3, 24);
            contentStream.beginText();
            contentStream.newLineAtOffset(145, 700);
            contentStream.showText("Candidature Information");
            contentStream.endText();

            contentStream.setFont(font, 16);
            contentStream.setNonStrokingColor(Color.black);
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 660);
            contentStream.showText("Nom Complet: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getNomComplet());
            contentStream.setNonStrokingColor(Color.black); // Set the color back to black
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 620);
            contentStream.showText("Identifiant: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getIdentifiant());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 580);
            contentStream.showText("Email: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getEmail());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 540);
            contentStream.showText("Gerne: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText( "" + candidature.getGerne());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 500);
            contentStream.showText("Classe: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getClasse());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 460);
            contentStream.showText("Moyenne en 3éme: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText( ""+candidature.getMoy3());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 420);
            contentStream.showText("Moyenne en 2éme: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText("" + candidature.getMoy2());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 380);
            contentStream.showText("Moyenne en 1re: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText("" + candidature.getMoy1());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 340);
            contentStream.showText("Niveau Francais: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText( candidature.getFrancais().toString());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 300);
            contentStream.showText("Niveau Anglais: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getAnglais().toString());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 260);
            contentStream.showText("Option: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(candidature.getOption().toString());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 220);
            contentStream.showText("Score: " );
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText(""+candidature.getScore());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 180);
            contentStream.showText("Offre: ");
            contentStream.setNonStrokingColor(Color.red); // Set the color to red for the text between quotes
            contentStream.showText( candidature.getOffre().getTitre());
            contentStream.setNonStrokingColor(Color.black);
            contentStream.endText();


            // Add more candidate information as needed

            contentStream.close();

            // Save the PDF document to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately
            return null;
        }
    }
}