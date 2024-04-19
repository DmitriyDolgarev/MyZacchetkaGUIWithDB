package View;

import Data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Chosing extends JFrame {
    private static Zachetka zachetka;
    private JLabel mainLabel;
    private JButton mathematics;
    private JButton phisics;
    private JButton programming;
    private JButton chemistry;
    private static Chosing instance = null;
    public static Chosing getInstance()
    {
        return instance;
    }
    public static Chosing checkInstance(Zachetka zachetka1)
    {
        if (instance == null)
        {
            instance= new Chosing(zachetka1);
        }
        return instance;
    }
    private Chosing(Zachetka zachetka1)
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

        ChoosingListener choosingListener = new ChoosingListener(zachetka1);
        mathematics.addActionListener(choosingListener);
        phisics.addActionListener(choosingListener);
        programming.addActionListener(choosingListener);
        chemistry.addActionListener(choosingListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });

        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mathematics.setAlignmentX(Component.CENTER_ALIGNMENT);
        phisics.setAlignmentX(Component.CENTER_ALIGNMENT);
        programming.setAlignmentX(Component.CENTER_ALIGNMENT);
        chemistry.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLocationByPlatform(true);
        pack();
        setResizable(false);
        setVisible(true);
    }
    public void makeEnabled(Zachetka zachetka)
    {
        mathematics.setEnabled(false);
        phisics.setEnabled(false);
        programming.setEnabled(false);
        chemistry.setEnabled(false);
        String disciplineName=new String();
        if (zachetka.getCountOfDisciplines()>0)
        {
            for (int i=0; i<zachetka.getCountOfDisciplines(); ++i)
            {
                disciplineName= zachetka.getNameOfDiscipline(i);
                switch (disciplineName)
                {
                    case "Математика":
                        mathematics.setEnabled(true);
                        break;
                    case "Физика":
                        phisics.setEnabled(true);
                        break;
                    case "Программирование":
                        programming.setEnabled(true);
                        break;
                    case "Химия":
                        chemistry.setEnabled(true);
                        break;
                }
            }
        }
    }
}
class ChoosingListener implements ActionListener {
    private static Zachetka zachetka=new Zachetka();
    public ChoosingListener(Zachetka zachetka1)
    {
        zachetka=zachetka1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "Математика":
                System.out.println("матеша");
                DisciplineMenu maticianMenu = DisciplineMenu.checkInstance(zachetka, "Математика");
                break;
            case "Физика":
                System.out.println("физик");
                DisciplineMenu phisicMenu = DisciplineMenu.checkInstance(zachetka, "Физика");
                break;
            case "Программирование":
                System.out.println("прога");
                DisciplineMenu progMenu = DisciplineMenu.checkInstance(zachetka, "Программирование");
                break;
            case "Химия":
                System.out.println("химик");
                DisciplineMenu chemistryMenu = DisciplineMenu.checkInstance(zachetka, "Химия");
                break;
        }
    }
}
