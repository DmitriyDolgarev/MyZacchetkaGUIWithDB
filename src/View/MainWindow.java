package View;

import Data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private static Matician matician = new Matician();
    private static Phisicist phisicist = new Phisicist();
    private static Chemist chemist = new Chemist();
    private static Programmer programmer = new Programmer();
    private static Zachetka zachetka;
    private JLabel mainLabel;
    private JButton jButton;
    private JButton jButtons;
    private JButton thbutton;
    private JButton deleteButton;
    public MainWindow()
    {
        setTitle("Моя зачетка");
        mainLabel=new JLabel("Главное меню");
        jButton=new JButton("Выбрать предмет");
        jButtons=new JButton("Добавить предмет");
        thbutton=new JButton("Показать общую информацию по всем предметам");
        deleteButton = new JButton("Очистить данные");

        DBWorker.initDB();
        zachetka = DBWorker.getZachetka();

        JPanel jPanel=new JPanel();
        jPanel.add(mainLabel);
        jPanel.add(jButton);
        jPanel.add(jButtons);
        jPanel.add(thbutton);
        jPanel.add(deleteButton);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        thbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(jPanel);

        MyListener myListener=new MyListener(zachetka);
        jButton.addActionListener(myListener);
        jButtons.addActionListener(myListener);
        thbutton.addActionListener(myListener);
        deleteButton.addActionListener(myListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (zachetka.getCountOfDisciplines() > 0)
                {
                    DBWorker.addSubjects(zachetka);
                    DBWorker.closeDB();
                }
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
class MyListener implements ActionListener {
    private static Zachetka zachetka=new Zachetka();
    public MyListener(Zachetka zachetka1)
    {
        zachetka=zachetka1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Выбрать предмет") && Adding.getInstance()==null)
        {
            Chosing chosing= Chosing.checkInstance(zachetka);
        }
        else if (e.getActionCommand().equals("Добавить предмет") && Chosing.getInstance()==null)
        {
            Adding adding= Adding.checkInstance(zachetka);
        }
        else if (e.getActionCommand().equals("Показать общую информацию по всем предметам"))
        {
            if (zachetka.getCountOfDisciplines()==0)
            {
                JOptionPane.showMessageDialog(null, "Прежде чем посмотреть информацию по всем предметам вам нужно иметь в зачетке хотя бы 1 предмет.", "Общая информация", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                GenInfo genInfo = GenInfo.checkInstance(zachetka);
            }
        }
        else if (e.getActionCommand().equals("Очистить данные"))
        {
            DBWorker.deleteData();
            zachetka.deleteData();
        }
    }
}
