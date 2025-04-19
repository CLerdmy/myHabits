package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class RoundedTextField extends RoundedField<JTextField> {

    public RoundedTextField(String placeholder) {

        super(new JTextField(), placeholder);
        setBorder(BorderFactory.createEmptyBorder(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS));

    }

    @Override
    public void setText(String text) {
        field.setText(text);
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : field.getText();
    }

    @Override
    protected void applyEchoChar(char c) {}

    @Override
    protected char getOriginalEchoChar() {
        return 0;
    }

    @Override
    public void setEchoChar(char c) {}

    @Override
    public void setFont(Font font) {
        field.setFont(font);
    }

}