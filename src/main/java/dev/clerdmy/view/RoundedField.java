package dev.clerdmy.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public abstract class RoundedField<T extends JComponent> extends JComponent {

    protected final String placeholder;
    protected Color placeholderColor;
    protected Color textColor;
    protected boolean showingPlaceholder;

    protected final T field;

    protected RoundedField(T field, String placeholder) {

        this.field = field;
        this.placeholder = placeholder;

        setLayout(new BorderLayout());
        add(field, BorderLayout.CENTER);

        field.setBorder(BorderFactory.createEmptyBorder());
        field.setOpaque(false);

        showPlaceholder();

        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    applyEchoChar(getOriginalEchoChar());
                    applyForeground(getTextColor());
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    showPlaceholder();
                } else {
                    applyForeground(getTextColor());
                }
            }
        });

        if (field instanceof JTextField) {
            ((JTextField) field).getDocument().addDocumentListener(new DocumentListener() {
                @Override public void insertUpdate(DocumentEvent e) { checkForText(); }
                @Override public void removeUpdate(DocumentEvent e) { checkForText(); }
                @Override public void changedUpdate(DocumentEvent e) { checkForText(); }

                private void checkForText() {
                    if (!getText().isEmpty() && showingPlaceholder) {
                        showingPlaceholder = false;
                        applyForeground(getTextColor());
                    } else if (getText().isEmpty() && !field.hasFocus()) {
                        showPlaceholder();
                    } else {
                        applyForeground(getTextColor());
                    }
                }
            });
        }
    }

    protected void applyForeground(Color color) {
        field.setForeground(color);
    }

    public void setForeground(Color color) {
        field.setForeground(color);
        this.textColor = color;
    }

    protected Color getTextColor() {
        return textColor;
    }

    protected void showPlaceholder() {
        if (getText().isEmpty() && !showingPlaceholder) {
            SwingUtilities.invokeLater(() -> {
                setText(placeholder);
                applyEchoChar((char) 0);
                field.setForeground(getPlaceholderColor());
                showingPlaceholder = true;
            });
        }
    }

    public Color getPlaceholderColor() {
        return placeholderColor;
    }

    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
        applyForeground(placeholderColor);
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

    public abstract void setFont(Font font);
    public abstract void setEchoChar(char c);
    public abstract void setText(String text);
    public abstract String getText();
    protected abstract void applyEchoChar(char c);
    protected abstract char getOriginalEchoChar();

}