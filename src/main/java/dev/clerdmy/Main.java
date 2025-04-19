package dev.clerdmy;

import dev.clerdmy.view.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(MainFrame::getInstance);

    }

}