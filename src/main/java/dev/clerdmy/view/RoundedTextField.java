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
        if (text == null || text.isEmpty()) {
            showPlaceholder();
        } else {
            showingPlaceholder = false;
            setRawText(text);
            applyForeground(textColor);
        }
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : field.getText();
    }

    @Override
    protected void setRawText(String text) {
        field.setText(text);
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