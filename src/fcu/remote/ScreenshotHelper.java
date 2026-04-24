package fcu.remote;

import fcu.remote.ui.RemoteControllerFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

/**
 * Takes screenshots of RemoteController UI for the delivery report.
 */
public class ScreenshotHelper {

    public static void main(String[] args) throws Exception {
        new File("docs/screenshots").mkdirs();

        JFrame[] holder = new JFrame[1];
        SwingUtilities.invokeAndWait(() -> {
            RemoteControllerFrame f = new RemoteControllerFrame();
            f.pack();
            f.setAlwaysOnTop(true);
            f.setLocation(400, 200);
            f.setVisible(true);
            holder[0] = f;
        });

        JFrame frame = holder[0];
        Thread.sleep(800);

        Robot robot = new Robot();

        // Find buttons and combobox
        List<AbstractButton> buttons = new ArrayList<>();
        List<JComboBox<?>> combos = new ArrayList<>();
        collectComponents(frame, buttons, combos);

        // --- TV mode screenshot ---
        // Click ON
        clickComponent(robot, findButton(buttons, "ON"));
        Thread.sleep(300);
        // Click UP twice (CH 7 -> 9)
        clickComponent(robot, findButton(buttons, "UP"));
        Thread.sleep(200);
        clickComponent(robot, findButton(buttons, "UP"));
        Thread.sleep(300);

        capture(robot, frame, "docs/screenshots/ui-tv.png");
        System.out.println("Saved ui-tv.png");

        // --- AC mode screenshot ---
        // Select AirConditioner from combobox
        JComboBox<?> combo = combos.get(0);
        SwingUtilities.invokeAndWait(() -> combo.setSelectedItem("AirConditioner"));
        Thread.sleep(300);
        // Click ON
        clickComponent(robot, findButton(buttons, "ON"));
        Thread.sleep(300);
        // Click DOWN twice (25 -> 23)
        clickComponent(robot, findButton(buttons, "DOWN"));
        Thread.sleep(200);
        clickComponent(robot, findButton(buttons, "DOWN"));
        Thread.sleep(300);

        capture(robot, frame, "docs/screenshots/ui-ac.png");
        System.out.println("Saved ui-ac.png");

        SwingUtilities.invokeLater(frame::dispose);
    }

    @SuppressWarnings("unchecked")
    private static void collectComponents(Container c,
            List<AbstractButton> buttons, List<JComboBox<?>> combos) {
        for (Component comp : c.getComponents()) {
            if (comp instanceof AbstractButton) buttons.add((AbstractButton) comp);
            if (comp instanceof JComboBox) combos.add((JComboBox<?>) comp);
            if (comp instanceof Container) collectComponents((Container) comp, buttons, combos);
        }
    }

    private static AbstractButton findButton(List<AbstractButton> buttons, String text) {
        for (AbstractButton b : buttons) {
            if (text.equals(b.getText())) return b;
        }
        throw new IllegalArgumentException("Button not found: " + text);
    }

    private static void clickComponent(Robot robot, Component comp) throws Exception {
        Point p = comp.getLocationOnScreen();
        int cx = p.x + comp.getWidth() / 2;
        int cy = p.y + comp.getHeight() / 2;
        robot.mouseMove(cx, cy);
        robot.mousePress(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(java.awt.event.InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
    }

    private static void capture(Robot robot, JFrame frame, String path) throws Exception {
        // Bring window to front and wait for repaint
        SwingUtilities.invokeAndWait(() -> {
            frame.toFront();
            frame.requestFocus();
        });
        robot.delay(400);
        Rectangle bounds = frame.getBounds();
        BufferedImage img = robot.createScreenCapture(bounds);
        ImageIO.write(img, "png", new File(path));
    }
}
