package domain.controllers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerGeneratorFactoryController {

    public void create(InputStream inputStream, String fileName) throws Exception {
        // read image
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        // create new image in memory with transparency and with new size
        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy the image original for new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);
        // int centralizeString = graphics.getFontMetrics().stringWidth("TOPZERA");

        // configuration font
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 45);
        graphics.setColor(Color.GRAY);
        graphics.setFont(font);

        // write new phase at the new image
        graphics.drawString("TOPZERA", 75, newHeight - 100);

        // write the new image in a file
        ImageIO.write(newImage, "png", new File(fileName));
        // "src/main/resources/output/sticker.jpg"
    }
}
