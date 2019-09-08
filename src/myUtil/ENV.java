package myUtil;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This is the class which declares some global variable.
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class ENV {
	public static final int WIDTH = 1152, HEIGHT = 720;
	
	public static Font font;
	public static void installFont() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("fonts/kenvector_future_thin.ttf")));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}  
	}
}
