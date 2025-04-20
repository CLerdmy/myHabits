package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends RoundedField<JPasswordField> {

    private char originalEchoChar;

    public RoundedPasswordField(String placeholder) {

        super(new JPasswordField(), placeholder);
        originalEchoChar = field.getEchoChar();
        setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));

    }

    @Override
    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            showPlaceholder();
        } else {
            showingPlaceholder = false;
            setRawText(text);
            applyEchoChar(originalEchoChar);
            applyForeground(textColor);
        }
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : new String(field.getPassword());
    }

    @Override
    protected void setRawText(String text) {
        field.setText(text);
    }

    @Override
    protected void applyEchoChar(char c) {
        field.setEchoChar(c);
    }

    @Override
    protected char getOriginalEchoChar() {
        return originalEchoChar;
    }

    @Override
    public void setEchoChar(char c) {
        this.originalEchoChar = c;
        if (!showingPlaceholder) field.setEchoChar(c);
    }

    @Override
    public void setFont(Font font) {
        field.setFont(font);
    }

}