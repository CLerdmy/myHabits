package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class RoundedTextArea extends JPanel {

    private final JTextArea textArea;

    public RoundedTextArea(String text) {

        setLayout(new BorderLayout());
        setOpaque(false);
        setMaximumSize(new Dimension(GUIConstants.DEFAULT_WIDTH / 2, Integer.MAX_VALUE));

        textArea = new JTextArea(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setHighlighter(null);
        textArea.setOpaque(false);
        textArea.setMargin(new Insets(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));

        add(textArea, BorderLayout.CENTER);

    }

    public JTextArea getTextArea() {
        return textArea;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        RoundedPainter.paintRounded(graphics, this, getBackground());
    }

    @Override
    public boolean contains(int x, int y) {
        return RoundedPainter.containsRounded(this, x, y);
    }

}