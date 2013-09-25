package jackal.gui;

import jackal.objects.year2013.Executor;
import jackal.support.ContextHolder;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * User: jackal
 * Date: 20.09.13
 * Time: 15:04
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class MainForm extends JFrame {

    private JLabel label;
    private JButton button1;

    public MainForm() throws HeadlessException {
        super("Конвертация жалоб from 2013 to 2014 базу");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //setLayout(new GridBagLayout());
        Container pane = getContentPane();

        JMenuBar menuBar = new JMenuBar();
        pane.add(menuBar,BorderLayout.NORTH);

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JButton("Test"));
        pane.add(panel);



        /*label = new JLabel("jLabel1");
        pane.add( label, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0 ) );*/

        JButton b1 = new JButton("Сохранить исполнителя");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Executor ex = new Executor();
                ex.setCreated(new Date());
                ex.setDeleted(false);
                ex.setSurname("Запахайло");
                ex.setFirstname("Семён");
                ex.setPatronimyc("Львович");
                ContextHolder.getDBService().addExecutors(ex);
            }
        });
        pane.add(b1);
        /*pane.add( b1, new GridBagConstraints( 2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0 ) );
        JButton b2 = new JButton("Посчитать");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List l = ContextHolder.getDBService().getExecutors();
                System.out.println("Найдено исполнителей: "+l.size());
            }
        });
        pane.add( b2, new GridBagConstraints( 3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0 ) );*/
    }


}
