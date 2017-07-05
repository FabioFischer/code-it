package main.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public final class AboutWindow extends JFrame {

    private final JTextPane aboutPane;
    private final StyledDocument docStyle;
    private final SimpleAttributeSet aSet;

    public String aboutTextContent() {
         return "";
    }

    public AboutWindow(String titulo) {
        setLayout(new FlowLayout());
        setSize(500, 200);
        setLocation(450, 50);
        setTitle(titulo);

        aSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(aSet, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontFamily(aSet, "");
        StyleConstants.setFontSize(aSet, 14);

        docStyle = new DefaultStyledDocument();
        Style defaultStyle = docStyle.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);

        aboutPane = new JTextPane(docStyle);
        aboutPane.setText(aboutTextContent());
        aboutPane.setEditable(false);
        aboutPane.setBackground(Color.LIGHT_GRAY);

        setContentPane(aboutPane);
    }
}
