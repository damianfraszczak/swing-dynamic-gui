package pl.edy.wat.wcy.dynamicgui.utils;

import javax.swing.*;
import java.awt.*;

public class SwingUtils {
    /**
     * executes provided runnable in EDT (event dispatch thread)
     *
     * @param runnable
     */
    public static void runInEDT(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread())
            runnable.run();
        else
            SwingUtilities.invokeLater(runnable);
    }

    /**
     * center provider component on screen
     * @param c
     * @param absolute
     */
    public static void centerOnScreen(final Component c, final boolean absolute) {
        final int width = c.getWidth();
        final int height = c.getHeight();
        final Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        int x = (screenSize.width / 2) - (width / 2);
        int y = (screenSize.height / 2) - (height / 2);
        if (!absolute) {
            x /= 2;
            y /= 2;
        }
        c.setLocation(x, y);
    }

    /**
     * center provided component inside parent or if null provided for parent it will center absolutly on the screen
     * @param wnd
     * @param relcom
     */
    public static void centerWindow(Window wnd, Component relcom) {
        if (relcom == null) {
            centerOnScreen(wnd, true);
        } else {
            Rectangle scrbnd = wnd.getBounds();
            Dimension wndsiz = wnd.getSize();
            Container root = null;
            int px, py;

            if (relcom != null) {
                if (relcom instanceof Window
                        || relcom instanceof java.applet.Applet) {
                    root = (Container) relcom;
                } else {
                    Container parent;
                    for (parent = relcom.getParent(); parent != null; parent = parent
                            .getParent()) {
                        if (parent instanceof Window
                                || parent instanceof java.applet.Applet) {
                            root = parent;
                            break;
                        }
                    }
                }
            }

            if (relcom == null || !relcom.isShowing() || root == null
                    || !root.isShowing()) {
                px = (scrbnd.x + ((scrbnd.width - wndsiz.width) / 2));
                py = (scrbnd.y + ((scrbnd.height - wndsiz.height) / 2));
            } else {
                Point relloc = relcom.getLocationOnScreen();
                Dimension relsiz = relcom.getSize();

                px = (relloc.x + ((relsiz.width - wndsiz.width) / 2));
                py = (relloc.y + ((relsiz.height - wndsiz.height) / 2));
            }

            if ((px + wndsiz.width) > (scrbnd.x + scrbnd.width)) {
                px = ((scrbnd.x + scrbnd.width) - wndsiz.width);
            }
            if ((py + wndsiz.height) > (scrbnd.y + scrbnd.height)) {
                py = ((scrbnd.y + scrbnd.height) - wndsiz.height);
            }
            if (px < scrbnd.x) {
                px = scrbnd.x;
            }
            if (py < scrbnd.y) {
                py = scrbnd.y;
            }
            wnd.setLocation(px, py);
        }
    }

    /**
     * Creates panel with title border which can be used for form groups for example
     *
     * @param component
     * @param text
     * @return
     */
    public static JPanel createTitlePanel(JComponent component, String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(component);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK), text));
        return panel;
    }

}
