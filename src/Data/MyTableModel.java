package Data;

import Data.Zachetka;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private Zachetka zachetka;
    public MyTableModel(Zachetka zachetka1)
    {
        zachetka=zachetka1;
    }
    @Override
    public int getRowCount() {
        return zachetka.getCountOfDisciplines();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex)
        {
            case 0:
                /*if (rowIndex==0)
                {
                    return "Предмет";
                }
                else
                {
                    return zachetka.getNameOfDiscipline(rowIndex-1);
                }*/
                return zachetka.getNameOfDiscipline(rowIndex);
            case 1:
                /*if (rowIndex==0)
                {
                    return "Количество часов";
                }
                else
                {
                    return zachetka.getHours(rowIndex-1);
                }*/
                return zachetka.getHours(rowIndex);
            case 2:
                /*if (rowIndex==0)
                {
                    return "Сдача теста";
                }
                else
                {
                    if (zachetka.getSuccess(rowIndex-1))
                    {
                        return "Тест сдан!";
                    }
                    else
                    {
                        return "Тест не сдан.";
                    }
                }*/
                if (zachetka.getSuccess(rowIndex))
                {
                    return "Тест сдан!";
                }
                else
                {
                    return "Тест не сдан.";
                }
            case 3:
                /*if (rowIndex==0)
                {
                    return "Аттестация";
                }
                else
                {
                    if (zachetka.getCertification(rowIndex-1))
                    {
                        return "Вы аттестованы!";
                    }
                    else
                    {
                        return "Вы не аттестованы.";
                    }
                }*/
                if (zachetka.getCertification(rowIndex))
                {
                    return "Вы аттестованы!";
                }
                else
                {
                    return "Вы не аттестованы.";
                }
            default:
                return "default";
        }
    }
    @Override
    public String getColumnName(int column)
    {
        switch (column)
        {
            case 0:
                return "Предмет";
            case 1:
                return "Количество часов";
            case 2:
                return "Сдача теста";
            case 3:
                return "Аттестация";
        }
        return "";
    }
}
