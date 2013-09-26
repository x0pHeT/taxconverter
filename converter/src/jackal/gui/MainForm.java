package jackal.gui;

import jackal.services.DBService;
import jackal.support.ContextHolder;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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

    private static final long serialVersionUID = 6070558522489739095L;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private JTextArea userInfoTextArea;

    JButton createTablesBtn;
    JButton initDicBtn;
    JButton importUsersBtn;
    JButton importExecutorsBtn;
    JButton importHolidaysBtn;
    JButton importComplaintsBtn;

    public MainForm() throws HeadlessException {
        super("Конвертация жалоб from 2013 to 2014 базу");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = getContentPane();

        JMenuBar menuBar = new JMenuBar();
        pane.add(menuBar,BorderLayout.NORTH);

        /*---Создание главного меню программы---*/
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        /*---*/

        JPanel mainPanel = new JPanel(new GridBagLayout());
        JPanel topButtonsPanel = new JPanel(new GridLayout(2,0,5,5));
        mainPanel.add( topButtonsPanel, new GridBagConstraints( 1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1), 0, 0 ) );
        userInfoTextArea = new JTextArea();
        userInfoTextArea.setEditable(false);
        JScrollPane textAreaScrolPanel = new JScrollPane(userInfoTextArea);
        textAreaScrolPanel.setPreferredSize(new Dimension(1024,400));
        textAreaScrolPanel.setMinimumSize(new Dimension(480, 320));
        mainPanel.add( textAreaScrolPanel, new GridBagConstraints( 1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1), 0, 0 ) );
        pane.add(mainPanel);

        createTablesBtn = new JButton("Создать таблицы"); createTablesBtn.addActionListener(new CreateTableListener());
        initDicBtn = new JButton("Заполнить справочники"); initDicBtn.addActionListener(new InitDicBtnListener());
        importUsersBtn = new JButton("Импортировать пользователей"); importUsersBtn.addActionListener(new ImportUsersBtnListener());
        importExecutorsBtn = new JButton("Импортировать исполнителей"); importExecutorsBtn.addActionListener(new ImportExecutorsBtnListener());
        importHolidaysBtn = new JButton("Импортировать праздники"); importHolidaysBtn.addActionListener(new ImportHolidaysBtnListener());
        importComplaintsBtn = new JButton("Импортировать жалобы"); importComplaintsBtn.addActionListener(new ImportComplaintsBtnListener());
        topButtonsPanel.add(createTablesBtn);
        topButtonsPanel.add(initDicBtn);
        topButtonsPanel.add(importUsersBtn);
        topButtonsPanel.add(importExecutorsBtn);
        topButtonsPanel.add(importHolidaysBtn);
        topButtonsPanel.add(importComplaintsBtn);

    }

    private void insertLogMessageRow(String message) {
        userInfoTextArea.append(sdf.format(new Date())+" "+message+"\n");
    }

    private void appendToUserLog(String message) {
        userInfoTextArea.append(message);
    }

    private class CreateTableListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] scripts = ((LocalSessionFactoryBean) ContextHolder.getBean("&hibernateSessionFactory2014"))
                    .getConfiguration().generateSchemaCreationScript(new Oracle10gDialect());
            DBCreator creator = new DBCreatorImpl(new UICallbackImpl(),scripts);
            creator.createTables();
        }
    }
    private class InitDicBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Инициализируем справочники");
        }
    }
    private class ImportUsersBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем пользователей из старой схемы");
        }
    }
    private class ImportExecutorsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем исполнителей из старой схемы");
        }
    }
    private class ImportHolidaysBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем справочник праздников");
        }
    }
    private class ImportComplaintsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Переносим жалобы в новую схему");
        }
    }

    public void addUserMessageFromOuterMethod(String messageFromOuterMethod) {
        insertLogMessageRow(messageFromOuterMethod);
    }

    private class UICallbackImpl implements UICallback {

        @Override
        public void addUserMessageFromOuterMethod(String message) {
            if (SwingUtilities.isEventDispatchThread()){
                System.out.println("is EventDispatchThread");
            } else {
                System.out.println("is not EventDispatchThread");
            }
            insertLogMessageRow(message);
        }

        @Override
        public void disableCreateButton() {
            createTablesBtn.setEnabled(false);
        }

        @Override
        public void enableCreateButton() {
            createTablesBtn.setEnabled(true);
        }
    }
}
