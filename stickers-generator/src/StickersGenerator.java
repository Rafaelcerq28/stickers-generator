import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class StickersGenerator {

    public void create(InputStream inputStream, String fileName) throws IOException{
        //reading the image
        //InputStream inputStream = new FileInputStream(new File("imgin/movie.jpg")); 
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);

        //load this image in memory with transparency and new size
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newSize = height + 200;

        BufferedImage newImage = new BufferedImage(width, newSize, BufferedImage.TRANSLUCENT);

        //copy original image to new image (in memory)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Text configuration
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);

        //insert a message inside of the image
        graphics.drawString("AMAZING!", 500, newSize - 60);


        //save the image in archive
        ImageIO.write(newImage, "png", new File("imgout/"+fileName));
    }

}   
