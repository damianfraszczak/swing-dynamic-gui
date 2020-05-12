package pl.edu.wat.wcy.swingdynamicgui.examples.crud;

import org.jdesktop.swingx.JXFrame;
import org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel;
import pl.edu.wat.wcy.swingdynamicgui.utils.SwingUtils;

import javax.swing.*;

public class CrudAppExample {
    public static void main(String[] args) {

        PersonCrudService crudService = new PersonCrudService();
        initLaf();
        SwingUtils.runInEDT(() -> {
            ExampleCrudTablePanel tablePanel = new ExampleCrudTablePanel(crudService);
            JXFrame frame = new JXFrame();
            frame.setTitle("Crud example with remote LAF");
            frame.add(tablePanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            SwingUtils.centerOnScreen(frame, true);
            frame.setVisible(true);
        });

    }

    private static void initLaf() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
