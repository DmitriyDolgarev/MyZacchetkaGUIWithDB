package View;

import Data.Matician;
import Data.Zachetka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisciplineMenu extends JFrame {
    private JLabel header;
    private JLabel firstCondition;
    private JLabel secondCondition;
    private JLabel thirdCondition;
    private JLabel fourthCondition;
    private JLabel hours;
    private JLabel success;
    private JLabel certification;
    private JLabel marks;
    private JButton lesson;
    private JButton testing;
    private JButton work;
    private JButton secondWork;
    private static DisciplineMenu instance = null;
    public static DisciplineMenu checkInstance(Zachetka zachetka1, String disciplineName)
    {
        if (instance == null)
        {
            instance= new DisciplineMenu(zachetka1, disciplineName);
        }
        return instance;
    }
    private DisciplineMenu(Zachetka zachetka, String disciplineName)
    {
        setTitle(disciplineName);
        int index = zachetka.getIndexOfDiscipline(disciplineName);
        String[] conditions=zachetka.getConditions(index);

        header=new JLabel(conditions[0]);
        firstCondition=new JLabel(conditions[1]);
        secondCondition=new JLabel(conditions[2]);
        thirdCondition=new JLabel(conditions[3]);
        fourthCondition=new JLabel(conditions[4]);
        hours=new JLabel("Количество часов: "+zachetka.getHours(index));
        success = new JLabel("");
        certification = new JLabel("");
        marks = new JLabel("");
        lesson = new JButton("Посетить занятие");
        testing = new JButton(zachetka.getNameOfTest(index));
        work = new JButton(zachetka.getNameOfWork(index));
        secondWork = new JButton(zachetka.getNameOfSecondWork(index));

        makeSuccess(zachetka, index);
        makeCertification(zachetka, index);
        printMarks(zachetka, index);

        JPanel jPanel=new JPanel();
        jPanel.add(header);
        jPanel.add(firstCondition);
        jPanel.add(secondCondition);
        jPanel.add(thirdCondition);
        jPanel.add(fourthCondition);
        jPanel.add(hours);
        jPanel.add(success);
        jPanel.add(certification);
        jPanel.add(marks);
        jPanel.add(lesson);
        jPanel.add(testing);
        jPanel.add(work);
        jPanel.add(secondWork);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        add(jPanel);

        lesson.addActionListener(e -> {
            zachetka.doLesson(index);

            hours.setText("Количество часов: "+zachetka.getHours(index));
            zachetka.getDiscipline(index).certify();
            makeSuccess(zachetka, index);
            makeCertification(zachetka, index);
        });

        testing.addActionListener(e -> {
            zachetka.doTest(index);

            makeSuccess(zachetka, index);
            zachetka.getDiscipline(index).certify();
            makeSuccess(zachetka, index);
            makeCertification(zachetka, index);
            printMarks(zachetka, index);
        });

        work.addActionListener(e -> {
            doWork(zachetka, index);

            makeCertification(zachetka, index);
            printMarks(zachetka, index);
        });

        secondWork.addActionListener(e -> {
            doSecondWork(zachetka, index);
            printMarks(zachetka, index);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });

        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstCondition.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondCondition.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdCondition.setAlignmentX(Component.CENTER_ALIGNMENT);
        fourthCondition.setAlignmentX(Component.CENTER_ALIGNMENT);
        hours.setAlignmentX(Component.CENTER_ALIGNMENT);
        success.setAlignmentX(Component.CENTER_ALIGNMENT);
        certification.setAlignmentX(Component.CENTER_ALIGNMENT);
        marks.setAlignmentX(Component.CENTER_ALIGNMENT);
        lesson.setAlignmentX(Component.CENTER_ALIGNMENT);
        testing.setAlignmentX(Component.CENTER_ALIGNMENT);
        work.setAlignmentX(Component.CENTER_ALIGNMENT);
        secondWork.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLocationByPlatform(true);
        pack();
        setResizable(false);
        setVisible(true);
    }
    public void printMarks(Zachetka zachetka, int index)
    {
        String message = new String("Оценки:");
        int[] marksOfDiscipline = zachetka.getMarksOfDiscipline(index);
        for (int i=0; i<marksOfDiscipline.length; ++i)
        {
            System.out.println(" "+marksOfDiscipline[i]);
            message+=" "+marksOfDiscipline[i];
        }
        marks.setText(message);
    }
    public void makeSuccess(Zachetka zachetka, int index)
    {
        if (zachetka.getSuccess(index))
        {
            success.setText("Тест сдан!");
        }
        else
        {
            success.setText("Тест не сдан");
        }
    }
    public void makeCertification(Zachetka zachetka, int index)
    {
        if (zachetka.getCertification(index))
        {
            certification.setText("Вы аттестованы!");
        }
        else
        {
            certification.setText("Вы не аттестованы");
        }
    }

    public void doWork(Zachetka zachetka, int index)
    {
        int value;
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                JOptionPane.showMessageDialog(null, "Вы приняли участие в олимпиаде. Вы заняли " + zachetka.doWork(index) + " место! Теперь вы аттестованы!", "Участие в олимпиаде", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Физика":
                value = zachetka.doWork(index);
                JOptionPane.showMessageDialog(null, "Вы приняли участие в параде проектов, теперь вам необязательно сдавать лабораторную работу", "Парад проектов", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Химия":
                value = zachetka.doWork(index);
                JOptionPane.showMessageDialog(null, "Вы написали исследовательскую работу, теперь вы аттестованы по химии!", "Исследовательская работа", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Программирование":
                value = zachetka.doWork(index);
                JOptionPane.showMessageDialog(null, "Вы приняли участие в создании проекта, за это вы аттестованы!", "Участие в создании проекта", JOptionPane.INFORMATION_MESSAGE);
            default:
                System.out.println("Неопознанный предмет");
        }
    }
    public void doSecondWork(Zachetka zachetka, int index)
    {
        switch (zachetka.getNameOfDiscipline(index)) {
            case "Математика":
                Matician matician1 = (Matician) zachetka.getDiscipline(index);
                JOptionPane.showMessageDialog(null, "Вы приняли участие в " + matician1.getCountPositions() + " олимпиадах. Лучший результат- " + zachetka.doSecondWork(index) + " место.", "Участие в олимпиадах", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Физика":
                if (zachetka.doSecondWork(index) > 2) {
                    JOptionPane.showMessageDialog(null, "Вы сдали лабораторную работу на "+zachetka.doSecondWork(index), "Лабораторная работа", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Вы не смогли сдать лабораторную работу, попробуйте еще раз.", "Лабораторная работа", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Химия":
                if (zachetka.doSecondWork(index) > 2) {
                    JOptionPane.showMessageDialog(null, "Вы сдали лабораторную работу на "+zachetka.doSecondWork(index), "Лабораторная работа", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Вы не смогли сдать лабораторную работу, попробуйте еще раз.", "Лабораторная работа", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Программирование":
                JOptionPane.showMessageDialog(null, "Вы приняли участие в соревнованиях по спортивному программированию. Вы заняли "+zachetka.doSecondWork(index) + " место.", "Спортивное программирование", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                System.out.println("Неопознанный предмет");
        }
    }
}