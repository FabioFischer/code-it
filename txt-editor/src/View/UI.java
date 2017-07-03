package View;

import Controller.FileManager;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.Element;

public final class UI extends JFrame implements ActionListener {

    private final DocumentListener lineCountListener;
    private final MenuListener mainListener;
    private JMenu fileMenu, editMenu;
    private JMenuItem newMenu, openMenu, saveMenu, saveHowMenu, closeMenu, aboutMenu;
    private final JMenuBar menuPrincipal;
    private final JTextArea textContent, lineCount;
    private final JLabel fileStatus;
    private final JScrollPane scroll;
    private final KeyStroke ctrlN, ctrlO, ctrlS, ctrlShiftS, ctrlW, ctrlA;

    private AboutWindow about;

    private final String FILE_EDITOR_NAME = "AstolfoTextEditor 0.0.1.1";
    private final String PLACEHOLDER_TEXT = "Escreva seu texto aqui...";

    private final String FILE_NOT_SAVED_MESSAGE = "O texto atual não foi salvo, deseja salvar as alterações?";
    private final String[] FILE_NOT_SAVED_OPTIONS = {"Salvar", "Não Salvar", "Cancelar"};

    private final String[] FILE_ALREADY_EXISTS_OPTIONS = {"Sim", "Não"};

    private final String[] FILE_NOT_FOUND_OPTIONS = {"Escolher outro arquivo", "Cancelar"};

    private String originalTextContent = "";
    private String currentPath;
    private boolean conteudoAlterado = false;

    public MenuListener setMainListener() {
        MenuListener listener = new MenuListener() {
            @Override
            public void menuCanceled(MenuEvent event) {
            }

            @Override
            public void menuDeselected(MenuEvent event) {
            }

            @Override
            public void menuSelected(MenuEvent event) {
            }
        };

        return listener;
    }

    public JMenu createFileMenu(MenuListener listener) {
        this.fileMenu = new JMenu();

        this.fileMenu = new JMenu("Arquivo");
        this.fileMenu.addMenuListener(listener);

        this.newMenu = createMenuItem(this.fileMenu, "Novo", this.ctrlN);
        this.openMenu = createMenuItem(this.fileMenu, "Abrir", this.ctrlO);

        this.fileMenu.addSeparator();

        this.saveMenu = createMenuItem(this.fileMenu, "Salvar", this.ctrlS);
        this.saveHowMenu = createMenuItem(this.fileMenu, "Salvar como...", this.ctrlShiftS);

        this.fileMenu.addSeparator();

        this.closeMenu = createMenuItem(this.fileMenu, "Fechar", this.ctrlW);

        return fileMenu;
    }

    public JMenu createEditMenu(MenuListener listener) {
        this.editMenu = new JMenu();

        this.editMenu = new JMenu("Editar");
        this.editMenu.addMenuListener(listener);

        this.aboutMenu = createMenuItem(this.editMenu, "Sobre", this.ctrlA);

        return editMenu;
    }

    public JMenuItem createMenuItem(JMenu menu, String name, KeyStroke shortCut) {
        JMenuItem item = new JMenuItem(name);
        item.addActionListener(this);
        item.setAccelerator(shortCut);
        menu.add(item);

        return item;
    }

    public KeyStroke getKeyShortCut(int a, int b) {
        KeyStroke shortCut = KeyStroke.getKeyStroke(a, b);

        return shortCut;
    }

    public void getCloseWarning() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent warning) {
                try {
                    close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public DocumentListener setDocumentListener() {
        DocumentListener listener = new DocumentListener() {
            public String getText() {
                int position = textContent.getDocument().getLength();
                Element root = textContent.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(position) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lineCount.setText(getText());
                changeLabelContent();
                flagIfContentHasBeenChanged();
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lineCount.setText(getText());
                changeLabelContent();
                flagIfContentHasBeenChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lineCount.setText(getText());
                changeLabelContent();
                flagIfContentHasBeenChanged();
            }
        };

        return listener;
    }

    private void flagIfContentHasBeenChanged() {
        if (!originalTextContent.equals(textContent.getText())) {
            conteudoAlterado = true;
        }
    }

    public UI() {
        setLayout(new FlowLayout());
        setSize(600, 600);
        setLocation(400, 50);
        setTitle("Sem título - " + FILE_EDITOR_NAME);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getCloseWarning();

        ctrlN = getKeyShortCut(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        ctrlO = getKeyShortCut(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        ctrlS = getKeyShortCut(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        ctrlShiftS = getKeyShortCut(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        ctrlW = getKeyShortCut(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
        ctrlA = getKeyShortCut(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK);

        lineCountListener = setDocumentListener();

        lineCount = new JTextArea("1", 10, 2);
        lineCount.setEditable(false);
        lineCount.setBackground(Color.LIGHT_GRAY);

        textContent = new JTextArea("Insira seu texto...", 10, 10);
        textContent.getDocument().addDocumentListener(lineCountListener);
        textContent.setLineWrap(true);

        scroll = new JScrollPane(textContent);
        scroll.getViewport().add(textContent);
        scroll.setRowHeaderView(lineCount);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainListener = setMainListener();

        createFileMenu(mainListener);
        createEditMenu(mainListener);

        fileStatus = new JLabel("");
        fileStatus.setEnabled(false);
        changeLabelContent();

        menuPrincipal = new JMenuBar();

        menuPrincipal.add(fileMenu);
        menuPrincipal.add(editMenu);
        menuPrincipal.add(Box.createGlue());
        menuPrincipal.add(fileStatus);

        setContentPane(scroll);
        setJMenuBar(menuPrincipal);
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.setVisible(true);
        ui.textContent.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            if (event.getSource() == newMenu) {
                newFile();

            } else if (event.getSource() == openMenu) {
                openFile();

            } else if (event.getSource() == saveMenu) {
                saveFile();

            } else if (event.getSource() == saveHowMenu) {
                saveFileAs();

            } else if (event.getSource() == closeMenu) {
                close();

            } else if (event.getSource() == aboutMenu) {
                about();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void newFile() throws IOException {
        if (conteudoAlterado) {
            newFileDialog();
            changeLabelContent();
        } else {
            clear();
            changeLabelContent();
        }
    }

    private void newFileDialog() throws IOException {
        int response = createWarningOptionDialog(FILE_NOT_SAVED_MESSAGE, FILE_NOT_SAVED_OPTIONS);

        if (response == 0) {
            if (currentPath == null || currentPath.isEmpty()) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showSaveDialog(this);

                boolean fileSaved = false;
                alreadyExistingFile(returnValue, fileChooser, fileSaved);
            } else {
                FileManager.saveFile(currentPath, getCurrentTextContent());
            }

            clear();
            changeLabelContent();
        } else if (response == 1) {
            clear();
            changeLabelContent();
        }
    }

    private void openFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().toPath().toString();
            boolean fileOpen = false;

            String fileContent = openFile(fileChooser);
            currentPath = fileChooser.getSelectedFile().toPath().toString();
            originalTextContent = fileContent;
            textContent.setText(fileContent);
            conteudoAlterado = false;
            changeLabelContent();

            String fileName = FileManager.getFileName(currentPath);
            this.setTitle(fileName + " - " + FILE_EDITOR_NAME);
        }
    }

    private String openFile(JFileChooser fileChooser) throws IOException {
        while (true) {
            try {
                String filePath = fileChooser.getSelectedFile().toPath().toString();
                return FileManager.getFileContent(filePath);
            } catch (FileNotFoundException exception) {
                int warningDialogResponse = createWarningOptionDialog(exception.getMessage(), FILE_NOT_FOUND_OPTIONS);

                if (warningDialogResponse == 0) {
                    fileChooser.showOpenDialog(this);
                } else {
                    break;
                }
            }
        }

        return "error";
    }

    private void saveFile() throws IOException {
        if (currentPath == null || currentPath.isEmpty()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showSaveDialog(this);

            boolean fileSaved = false;
            alreadyExistingFile(returnValue, fileChooser, fileSaved);
            changeLabelContent();
        } else {
            FileManager.saveFile(currentPath, getCurrentTextContent());
            conteudoAlterado = false;
            originalTextContent = textContent.getText();
            changeLabelContent();

        }
    }

    public void saveFileAs() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showSaveDialog(this);

        boolean fileSaved = false;
        alreadyExistingFile(returnValue, fileChooser, fileSaved);
        changeLabelContent();
    }

    public void close() throws IOException {
        if (conteudoAlterado) {
            int response = createWarningOptionDialog(FILE_NOT_SAVED_MESSAGE, FILE_NOT_SAVED_OPTIONS);

            if (response == 0) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showSaveDialog(this);

                boolean fileSaved = false;
                alreadyExistingFile(returnValue, fileChooser, fileSaved);
            } else if (response == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    public void about() {
        this.about = new AboutWindow("Sobre - " + FILE_EDITOR_NAME);
        this.about.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private String getCurrentTextContent() {
        return textContent.getText();
    }

    private void alreadyExistingFile(int returnValue, JFileChooser fileChooser, boolean fileSaved) throws IOException {
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String currentTextContent = getCurrentTextContent();
        while (returnValue != JFileChooser.APPROVE_OPTION || !fileSaved) {
            String filePath = fileChooser.getSelectedFile().toPath().toString();

            try {
                FileManager.createNewFile(filePath, currentTextContent, false);
                fileSaved = true;

                String fileName = FileManager.getFileName(filePath);
                this.setTitle(fileName + " - " + FILE_EDITOR_NAME);

                conteudoAlterado = false;
                currentPath = filePath;
                originalTextContent = textContent.getText();
            } catch (IllegalArgumentException exception) {
                int overrideResponse = createWarningOptionDialog(exception.getMessage(), FILE_ALREADY_EXISTS_OPTIONS);

                if (overrideResponse == 0) {
                    FileManager.createNewFile(filePath, currentTextContent, true);
                    fileSaved = true;
                    returnValue = JFileChooser.APPROVE_OPTION;
                } else {
                    returnValue = fileChooser.showSaveDialog(this);

                    if (returnValue == JFileChooser.CANCEL_OPTION) {
                        break;
                    }
                }
            }
        }
    }

    private void clear() {
        this.setTitle("Sem título - " + FILE_EDITOR_NAME);
        textContent.setText(PLACEHOLDER_TEXT);
        textContent.requestFocus();
        originalTextContent = "";
        conteudoAlterado = false;
        currentPath = "";
        changeLabelContent();
    }

    private void changeLabelContent() {
        String content = "Arquivo: "
                + ((currentPath == null || currentPath.equals("")) ? "Nenhum arquivo selecionado" : currentPath)
                + "   |   Conteúdo alterado: "
                + (conteudoAlterado ? "Sim   " : "Não   ");
        fileStatus.setText(content);
    }

    private int createWarningOptionDialog(String message, String[] options) {
        return JOptionPane.showOptionDialog(this, message, FILE_EDITOR_NAME, 0, JOptionPane.WARNING_MESSAGE, null, options,
                options[0]);
    }
}
