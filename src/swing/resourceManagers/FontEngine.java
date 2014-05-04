package swing.resourceManagers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexisvincent
 */
public class FontEngine {

    private static final ArrayList<Font> fonts;
    private static final GraphicsEnvironment graphicsEnvironment;

    static {
        fonts = new ArrayList<>();
        graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

    public static void registerFont(Font font) {
        fonts.add(font);
        graphicsEnvironment.registerFont(font);
    }
    
    public static void registerFont(String internalPath) {
        try {
            registerFont(Font.createFont(Font.TRUETYPE_FONT, FontEngine.class.getResourceAsStream(internalPath)));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(FontEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void registerFont(Path path) {
        try {
            registerFont(Font.createFont(Font.TRUETYPE_FONT, path.toFile()));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(FontEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Font getFont(String name) {
        for (Font font : fonts) {
            if (font.getFontName().equals(name)) {
                return font;
            }
        }
        return null;
    }

}
