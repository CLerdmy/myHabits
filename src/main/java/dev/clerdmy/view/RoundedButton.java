package dev.clerdmy.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    private boolean hoveredActive = false;
    private Color hoverColor = getBackground();

    public RoundedButton(String text) {

        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoveredActive = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredActive = false;
                repaint();
            }
        });
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Color color = hoveredActive ? getHoverColor() : getBackground();
        RoundedPainter.paintRounded(graphics, this, color);
        super.paintComponent(graphics);
    }

    @Override
    public boolean contains(int x, int y) {
        return RoundedPainter.containsRounded(this, x, y);
    }

}