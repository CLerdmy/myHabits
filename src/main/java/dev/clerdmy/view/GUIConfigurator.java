package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class GUIConfigurator {

    private static final Dimension sizeHalf = new Dimension(GUIConstants.DEFAULT_WIDTH / 4 + GUIConstants.SIDEBAR / 2, GUIConstants.SQUARE / 2);
    private static final Dimension sizeHalfLong = new Dimension(GUIConstants.DEFAULT_WIDTH / 3 + GUIConstants.DEFAULT_WIDTH / 4, GUIConstants.SQUARE / 2);
    private static final Dimension sizeFull = new Dimension(GUIConstants.DEFAULT_WIDTH / 4 + GUIConstants.SIDEBAR / 2, GUIConstants.SQUARE);
    private static final Dimension sizeFullLong = new Dimension(GUIConstants.DEFAULT_WIDTH / 3 + GUIConstants.DEFAULT_WIDTH / 4, GUIConstants.SQUARE);
    private static final Dimension sidebar = new Dimension(GUIConstants.SIDEBAR, GUIConstants.SQUARE);

    protected static void configureTitle(JLabel title) {
        title.setForeground(GUIConstants.WHITE);
        title.setFont(GUIConstants.TITLE_FONT);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    protected static void configureTextField(RoundedField<? extends JComponent> textField) {
        textField.setPreferredSize(sizeFull);
        textField.setMaximumSize(sizeFull);
        textField.setForeground(GUIConstants.BLACK);
        textField.setBackground(GUIConstants.WHITE);
        textField.setPlaceholderColor(GUIConstants.GRAY);
        textField.setFont(GUIConstants.MAIN_FONT);
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    protected static void configureTextFieldLong(RoundedField<? extends JComponent> textField) {
        configureTextField(textField);
        textField.setPreferredSize(sizeFullLong);
        textField.setMaximumSize(sizeFullLong);
    }

    protected static void configureText(JLabel text) {
        text.setPreferredSize(sizeHalf);
        text.setMaximumSize(sizeHalf);
        text.setForeground(GUIConstants.WHITE);
        text.setFont(GUIConstants.HINT_FONT);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    protected static void configureTextLong(JLabel text) {
        configureText(text);
        text.setPreferredSize(sizeHalfLong);
        text.setMaximumSize(sizeHalfLong);
    }

    protected static void configureButton(RoundedButton button) {
        button.setPreferredSize(sizeFull);
        button.setMaximumSize(sizeFull);
        button.setHoverColor(GUIConstants.LIGHTER_BLACK);
        button.setBackground(GUIConstants.BLACK);
        button.setForeground(GUIConstants.WHITE);
        button.setFont(GUIConstants.MAIN_FONT_BOLD);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    protected static void configureButtonLong(RoundedButton button) {
        configureButton(button);
        button.setPreferredSize(sizeFullLong);
        button.setMaximumSize(sizeFullLong);
    }

    protected static void configureSquareButton(RoundedButton button) {
        button.setBounds(GUIConstants.TEXT_INSETS / 2, GUIConstants.TEXT_INSETS / 2, GUIConstants.SQUARE, GUIConstants.SQUARE);
        button.setHoverColor(GUIConstants.LIGHTER_BLACK);
        button.setBackground(GUIConstants.BLACK);
        button.setForeground(GUIConstants.WHITE);
        button.setFont(GUIConstants.MAIN_FONT_BOLD);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    protected static void configureSidebarButton(RoundedButton button) {
        configureSquareButton(button);
        button.setPreferredSize(sidebar);
        button.setMaximumSize(sidebar);
    }

    protected static void configureWhiteTextArea(RoundedTextArea textArea) {
        textArea.setBackground(GUIConstants.WHITE);
        textArea.getTextArea().setFont(GUIConstants.MAIN_FONT);
        textArea.getTextArea().setForeground(GUIConstants.BLACK);
        textArea.getTextArea().setBackground(GUIConstants.WHITE);
    }

}