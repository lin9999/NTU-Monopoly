package myUtil;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

/**
 * This is the class which create a hyperlink to each department.
 * 
 * @author LIN, EN TING
 * @since 2019-06-30
 */

public class LinkLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private String text, url;
    private boolean isSupported;

    /**
     * Constructor, create a hyperlink with JLabel.
     * 
     * @param text Name of departments.
     * @param url URL of departments' website.
     */
    public LinkLabel(String text, String url) {
        this.text = text;
        this.url = url;
        try {
            this.isSupported = Desktop.isDesktopSupported()
                    && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE);
        } catch (Exception e) {
            this.isSupported = false;
        }
        setText(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setText(isSupported);
                if (isSupported)
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e) {
                setText(false);
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(
                            new java.net.URI(LinkLabel.this.url));
                } catch (Exception ex) {}
            }
        });
    }

    /**
     * Set texts' color
     * @param b If true, set the text to red.
     * 			If false, set the text to black.
     */
    private void setText(boolean b) {
        if (!b)
            setText("<html><font color=black><u>" + text);
        else
            setText("<html><font color=red><u>" + text);
    }
}
