package View;

import Data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Adding extends JFrame {
    private static Zachetka zachetka;
    private JLabel mainLabel;
    private JButton mathematics;
    private JButton phisics;
    private JButton programming;
    private JButton chemistry;
    private static Adding instance=null;
    public static Adding getInstance()
    {
        return instance;
    }
    public static Adding checkInstance(Zachetka zachetka1)
    {
        if (instance == null)
        {
            instance= new Adding(zachetka1);
        }
        return instance;
    }
    private Adding(Zachetka zachetka1)
    {
        zachetka=zachetka1;
        setTitle("Выбор предмета");
        mainLabel=new JLabel("Выберите предмет");
        mathematics=new JButton("Математика");
        phisics=new JButton("Физика");
        programming=new JButton("Программирование");
        chemistry=new JButton("Химия");

        makeEnabled(zachetka);

        JPanel jPanel=new JPanel();
        jPanel.add(mainLabel);
        jPanel.add(mathematics);
        jPanel.add(phisics);
        jPanel.add(programming);
        jPanel.add(chemistry);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        add(jPanel);

        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mathematics.setAlignmentX(Component.CENTER_ALIGNMENT);
        phisics.setAlignmentX(Component.CENTER_ALIGNMENT);
        programming.setAlignmentX(Component.CENTER_ALIGNMENT);
        chemistry.setAlignmentX(Component.CENTER_ALIGNMENT);

        AddingListener addingListener=new AddingListener(zachetka1);
        mathematics.addActionListener(addingListener);
        mathematics.addActionListener(e -> mathematics.setEnabled(false));
        phisics.addActionListener(addingListener);
        phisics.addActionListener(e -> phisics.setEnabled(false));
        programming.addActionListener(addingListener);
        programming.addActionListener(e -> programming.setEnabled(false));
        chemistry.addActionListener(addingListener);
        chemistry.addActionListener(e -> chemistry.setEnabled(false));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance=null;
            }
        });

        setLocationByPlatform(true);
        pack();
        setResizable(false);
        setVisible(true);
    }
    public void makeEnabled(Zachetka zachetka)
    {
        mathematics.setEnabled(true);
        phisics.setEnabled(true);
        programming.setEnabled(true);
        chemistry.setEnabled(true);
        String disciplineName=new String();
        if (zachetka.getCountOfDisciplines()>0)
        {
            for (int i=0; i<zachetka.getCountOfDisciplines(); ++i)
            {
                disciplineName= zachetka.getNameOfDiscipline(i);
                switch (disciplineName)
                {
                    case "Математика":
                        mathematics.setEnabled(false);
                        break;
                    case "Физика":
                        phisics.setEnabled(false);
                        break;
                    case "Программирование":
                        programming.setEnabled(false);
                        break;
                    case "Химия":
                        chemistry.setEnabled(false);
                        break;
                }
            }
        }
    }
}
class AddingListener implements ActionListener {
    private static Matician matician = new Matician();
    private static Phisicist phisicist = new Phisicist();
    private static Chemist chemist = new Chemist();
    private static Programmer programmer = new Programmer();
    private static Zachetka zachetka=new Zachetka();
    public AddingListener(Zachetka zachetka1)
    {
        zachetka=zachetka1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Математика":
                zachetka.addDiscipline(matician);
                break;
            case "Физика":
                zachetka.addDiscipline(phisicist);
                break;
            case "Программирование":
                zachetka.addDiscipline(programmer);
                break;
            case "Химия":
                zachetka.addDiscipline(chemist);
                break;
        }
    }
}
