import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickerGeneratorFactory {

    void create(InputStream inputStream, String fileName) throws Exception {
        // read image
        // InputStream inputStream = new FileInputStream(new File("src/main/resources/entry/movie-large.jpg"));
        // InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        //        .openStream();
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        // create new image in memory with transparency and with new size
        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copy the image original for new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0, 0, null);

        // configuration font
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        // write new phase at the new image
        graphics.drawString("Created with Java this Sticker! lol", 75, newHeight - 100);

        // write the new image in a file
        ImageIO.write(newImage, "png", new File(fileName));
        // "src/main/resources/output/sticker.jpg"
    }

//    public static void main(String[] args) throws Exception {
//        StickerGeneratorFactory generatorFactory = new StickerGeneratorFactory();
//        generatorFactory.create();
//    }
}
