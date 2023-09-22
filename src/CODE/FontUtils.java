
package CODE;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontUtils {
    
     public static void registerCustomFont(String fontFilePath) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFilePath));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
     
     public static void main(String [] args){
     
     FontUtils.registerCustomFont("E:\\files\\project\\jayantha_store\\src\\font\\FMBindumathi x.ttf");
     System.out.println("බාහිර");
     }
}
