package jackal.gui;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import jackal.support.ContextHolder;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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

    static {
        ContextHolder.getContext();
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        LOG.debug("Starting application main window…");
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainForm mainForm = new MainForm();
                mainForm.pack();
                Dimension dim = mainForm.getToolkit().getScreenSize();
                mainForm.setLocation((dim.width - mainForm.getWidth()) / 2,
                        (dim.height - mainForm.getHeight()) / 3);
                mainForm.setResizable(true);
                mainForm.setVisible(true);
            }
        });
        LOG.debug("Started.");
    }
}
