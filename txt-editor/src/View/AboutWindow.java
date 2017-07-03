package View;

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
         return "Projeto desenvolvido por Matheus Felipe Klauberg e Fábio Luiz Fischer."
                + "\n\nEstudantes do 3º Semestre do curso de Bacharelado de Ciêncas da Computação."
                + "\n\nTrabalho com objetivo de criar um editor de texto baseado na aplicação 'Notepad'"
                + "\nReferênte a disciplina de Programação II, orientada por Matheus Carvalho Viana."
                + "\n\n\n2015/I";
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
