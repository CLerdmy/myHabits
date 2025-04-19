package dev.clerdmy.view;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPainter {

    public static void paintRounded(Graphics graphics, Component component, Color color) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(0, 0, component.getWidth(), component.getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE);
        graphics2D.dispose();
    }

    public static boolean containsRounded(Component component, int x, int y) {
        return new RoundRectangle2D.Float(0, 0, component.getWidth(), component.getHeight(), GUIConstants.ANGLE, GUIConstants.ANGLE).contains(x, y);
    }

}