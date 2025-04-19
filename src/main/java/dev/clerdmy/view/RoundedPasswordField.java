package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends RoundedField<JPasswordField> {

    private char originalEchoChar;

    public RoundedPasswordField(String placeholder) {

        super(new JPasswordField(), placeholder);
        this.originalEchoChar = field.getEchoChar();
        setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));

    }

    @Override
    public void setText(String text) {
        field.setText(text);
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : new String(field.getPassword());
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
    }

    @Override
    public void setFont(Font font) {
        field.setFont(font);
    }

}