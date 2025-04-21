package dev.clerdmy.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public abstract class ScrollPanel<T> extends JPanel {

    protected final JPanel contentListPanel;
    protected final JScrollPane scrollPane;

    public ScrollPanel() {

        setOpaque(false);
        setBounds(0, 0, GUIConstants.DEFAULT_WIDTH / 2, GUIConstants.DEFAULT_HEIGHT / 2);
        setLayout(new BorderLayout(GUIConstants.TEXT_INSETS, GUIConstants.TEXT_INSETS));
        setBorder(new EmptyBorder(GUIConstants.SQUARE / 2, GUIConstants.SQUARE * 2, GUIConstants.SQUARE, GUIConstants.SQUARE * 2));

        contentListPanel = new JPanel();
        contentListPanel.setOpaque(false);
        contentListPanel.setLayout(new BoxLayout(contentListPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(contentListPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().setBlockIncrement(50);

        add(scrollPane);

        add(scrollPane, BorderLayout.CENTER);

    }

    protected abstract void fillContentList(List<T> items);

}