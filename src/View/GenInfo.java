package View;

import Data.MyTableModel;
import Data.Zachetka;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GenInfo extends JFrame {
    private JTable table;
    private static GenInfo instance = null;
    public static GenInfo getInstance()
    {
        return instance;
    }
    public static GenInfo checkInstance(Zachetka zachetka)
    {
        if (instance == null)
        {
            instance = new GenInfo(zachetka);
        }
        return instance;
    }
    private GenInfo(Zachetka zachetka)
    {
        setTitle("Общая информация по предметам");


        table = new JTable();
        table.setModel(new MyTableModel(zachetka));

        JScrollPane jScrollPane = new JScrollPane(table);

        add(jScrollPane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                instance = null;
            }
        });

        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }
}
