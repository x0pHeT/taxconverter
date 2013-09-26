package jackal.gui;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import jackal.support.ContextHolder;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

/**
 * User: jackal
 * Date: 23.09.13
 * Time: 10:54
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class Converter {

    private static Logger LOG = Logger.getLogger(Converter.class);

    private static MainForm mainForm;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        LOG.debug("Starting application main window…");
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm = new MainForm();
                mainForm.pack();
                Dimension dim = mainForm.getToolkit().getScreenSize();
                mainForm.setLocation((dim.width - mainForm.getWidth()) / 2,
                        (dim.height - mainForm.getHeight()) / 3);
                mainForm.setResizable(true);
                mainForm.setVisible(true);
            }
        });
        LOG.debug("Started.");
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm.addUserMessageFromOuterMethod("Добро пожаловать");
                mainForm.addUserMessageFromOuterMethod("Загрузка контеста приложения…");
            }
        });
        ContextHolder.getContext();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainForm.addUserMessageFromOuterMethod("Загрузка завершена");
            }
        });
    }
}
