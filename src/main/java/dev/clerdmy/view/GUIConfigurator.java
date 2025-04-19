package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;

public class GUIConfigurator {

    private static final Dimension sizeHalf = new Dimension(GUIConstants.MIN_WIDTH / 2, GUIConstants.SQUARE / 2);
    private static final Dimension sizeFull = new Dimension(GUIConstants.MIN_WIDTH / 2, GUIConstants.SQUARE);
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

    protected static void configureText(JLabel text) {
        text.setPreferredSize(sizeHalf);
        text.setMaximumSize(sizeHalf);
        text.setForeground(GUIConstants.WHITE);
        text.setFont(GUIConstants.HINT_FONT);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
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

}