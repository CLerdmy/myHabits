package dev.clerdmy.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public abstract class RoundedField<T extends JComponent> extends JComponent {

    protected String placeholder;
    protected Color placeholderColor = Color.GRAY;
    protected Color textColor = Color.BLACK;
    protected boolean showingPlaceholder = true;

    protected final T field;

    protected RoundedField(T field, String placeholder) {

        this.field = field;
        this.placeholder = placeholder;

        setLayout(new BorderLayout());
        add(field, BorderLayout.CENTER);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setOpaque(false);

        initListeners();
        showPlaceholder();

    }

    private void initListeners() {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    clearPlaceholder();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    showPlaceholder();
                }
            }
        });

        if (field instanceof JTextField textField) {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                public void insertUpdate(DocumentEvent e) { handleTextChange(); }
                public void removeUpdate(DocumentEvent e) { handleTextChange(); }
                public void changedUpdate(DocumentEvent e) { handleTextChange(); }

                private void handleTextChange() {
                    if (!showingPlaceholder) {
                        applyForeground(textColor);
                    }
                }
            });
        }
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        if (getText().isEmpty() || showingPlaceholder) {
            showPlaceholder();
        }
    }

    protected void showPlaceholder() {
        SwingUtilities.invokeLater(() -> {
            setRawText(placeholder);
            applyEchoChar((char) 0);
            applyForeground(placeholderColor);
            showingPlaceholder = true;
            repaint();
        });
    }

    protected void clearPlaceholder() {
        SwingUtilities.invokeLater(() -> {
            setRawText("");
            applyEchoChar(getOriginalEchoChar());
            applyForeground(textColor);
            showingPlaceholder = false;
            repaint();
        });
    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
        if (showingPlaceholder) applyForeground(color);
    }

    public void setForeground(Color color) {
        this.textColor = color;
        if (!showingPlaceholder) applyForeground(color);
    }

    protected void applyForeground(Color color) {
        field.setForeground(color);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        RoundedPainter.paintRounded(graphics, this, getBackground());
        super.paintComponent(graphics);
    }

    @Override
    public boolean contains(int x, int y) {
        return RoundedPainter.containsRounded(this, x, y);
    }

    public abstract void setEchoChar(char c);

    public abstract void setFont(Font font);

    public abstract void setText(String text);

    public abstract String getText();

    protected abstract void setRawText(String text);

    protected abstract void applyEchoChar(char c);

    protected abstract char getOriginalEchoChar();

}