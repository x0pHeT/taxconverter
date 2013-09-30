package jackal.gui;

import jackal.gui.threads.*;
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

    private static final long serialVersionUID = 7000321746372483939L;
    private MainForm mainForm;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private JTextArea userInfoTextArea;

    private JDialog dropTablesDialog;
    private JOptionPane dropTablesOptionPane;

    private JButton createTablesBtn;
    private JButton initDicBtn;
    private JButton importUsersBtn;
    private JButton importExecutorsBtn;
    private JButton importHolidaysBtn;
    private JButton importComplaintsBtn;
    private JButton interruptCurrentThreadBtn;

    private LongOperations currentTread = null;

    public MainForm() throws HeadlessException {
        super("Конвертация жалоб from 2013 to 2014 базу");
        mainForm = this;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container pane = getContentPane();

        JMenuBar menuBar = new JMenuBar();
        pane.add(menuBar,BorderLayout.NORTH);

        /*---Создание главного меню программы---*/
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);
        /*---*/

        /*---Создание основной панели в центре---*/
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

        createTablesBtn = new JButton("Создать таблицы"); createTablesBtn.addActionListener(new CreateTablesListener());
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
        /*---*/

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        interruptCurrentThreadBtn = new JButton("Отмена");
        interruptCurrentThreadBtn.addActionListener(new InterrupyThreadBtnListener());
        interruptCurrentThreadBtn.setEnabled(false);
        bottomPanel.add(interruptCurrentThreadBtn);
        pane.add(bottomPanel, BorderLayout.SOUTH);

    }

    private void insertLogMessageRow(String message) {
        userInfoTextArea.append(sdf.format(new Date())+" "+message+"\n");
    }

    private class CreateTablesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ContextHolder.getDBService().isTablesYear2014Exist()) {
                String message = "Выполнить скрипты для удаления всех таблиц в схеме?";
                int answer = JOptionPane.showConfirmDialog(mainForm,message,"Удалить таблицы?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION) {
                    System.out.println("value == JOptionPane.YES_OPTION");
                    String[] scripts = ((LocalSessionFactoryBean) ContextHolder.getBean("&hibernateSessionFactory2014"))
                            .getConfiguration().generateDropSchemaScript(new Oracle10gDialect());
                    currentTread = new TablesCreationThreadImpl(new UICallbackImpl(),scripts);
                    currentTread.execute();
                }
            } else {
                String[] scripts = ((LocalSessionFactoryBean) ContextHolder.getBean("&hibernateSessionFactory2014"))
                        .getConfiguration().generateSchemaCreationScript(new Oracle10gDialect());
                currentTread = new TablesCreationThreadImpl(new UICallbackImpl(),scripts);
                currentTread.execute();
            }

        }
    }
    private class InitDicBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Инициализируем справочники");
            LongOperations initDicThead = new InitDicTheadImpl(new UICallbackImpl());
            initDicThead.execute();
        }
    }
    private class ImportUsersBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем пользователей из старой схемы");
            LongOperations importUsersThread = new ImportUsersThreadImpl(new UICallbackImpl());
            importUsersThread.execute();
        }
    }
    private class ImportExecutorsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем исполнителей из старой схемы");
            LongOperations importExecutorsThread = new ImportExecutorsThreadImpl(new UICallbackImpl());
            importExecutorsThread.execute();
        }
    }
    private class ImportHolidaysBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Импортируем справочник праздников");
            LongOperations importHolidaysThread = new ImportHolidaysThreadImpl(new UICallbackImpl());
            importHolidaysThread.execute();
        }
    }
    private class ImportComplaintsBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            insertLogMessageRow("Переносим жалобы в новую схему");
        }
    }

    private class InterrupyThreadBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentTread!=null) {
                currentTread.cancel();
            }
        }
    }

    public void addUserMessageFromOuterMethod(String messageFromOuterMethod) {
        insertLogMessageRow(messageFromOuterMethod);
    }

    private class UICallbackImpl implements UICallback {

        @Override
        public void addUserMessageFromOuterMethod(final String message) {
            if (SwingUtilities.isEventDispatchThread()){
                insertLogMessageRow(message);
            } else {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        insertLogMessageRow(message);
                    }
                });
            }

        }

        @Override
        public void disableButtons() {
            createTablesBtn.setEnabled(false);
            initDicBtn.setEnabled(false);
            importUsersBtn.setEnabled(false);
            importExecutorsBtn.setEnabled(false);
            importHolidaysBtn.setEnabled(false);
            importComplaintsBtn.setEnabled(false);
            interruptCurrentThreadBtn.setEnabled(true);
        }

        @Override
        public void enableButtons() {
            createTablesBtn.setEnabled(true);
            initDicBtn.setEnabled(true);
            importUsersBtn.setEnabled(true);
            importExecutorsBtn.setEnabled(true);
            importHolidaysBtn.setEnabled(true);
            importComplaintsBtn.setEnabled(true);
            interruptCurrentThreadBtn.setEnabled(false);
        }
    }
}
