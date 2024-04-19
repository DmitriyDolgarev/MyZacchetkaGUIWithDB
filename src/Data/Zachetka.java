package Data;

import java.util.ArrayList;

public class Zachetka {
    private static ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
    public void deleteData()
    {
        if (disciplines.size() != 0)
        {
            for (int i=0; i<disciplines.size(); ++i)
            {
                disciplines.remove(i);
            }
        }
    }
    public ArrayList<Discipline> getDisciplines()
    {
        return disciplines;
    }
    public String[] getConditions(int index)
    {
        return disciplines.get(index).getConditions();
    }
    public int getHours(int index) {
        return disciplines.get(index).getHours();
    }

    public boolean getSuccess(int index) {
        return disciplines.get(index).getSuccess();
    }

    public boolean getCertification(int index) {
        return disciplines.get(index).getCertification();
    }

    public int doLesson(int index)
    {
        int value= disciplines.get(index).doLesson();
        disciplines.get(index).certify();
        return value;
    }
    public int doTest(int index)
    {
        int value= disciplines.get(index).startTest();
        disciplines.get(index).certify();
        return value;
    }
    public void addDiscipline(Discipline discipline)
    {
        disciplines.add(discipline);
    }
    public int getCountOfDisciplines()
    {
        return disciplines.size();
    }
    public String getNameOfDiscipline(int index)
    {
        return disciplines.get(index).getName();
    }
    public String getNameOfTest(int index)
    {
        return disciplines.get(index).getDifTest(0);
    }
    public String getNameOfWork(int index)
    {
        return disciplines.get(index).getDifTest(1);
    }
    public String getNameOfSecondWork(int index)
    {
        return disciplines.get(index).getDifTest(2);
    }
    public int doWork(int index)
    {
        int result=0;
        switch (getNameOfDiscipline(index))
        {
            case "Математика":
                Matician matician=(Matician) disciplines.get(index);
                result=matician.participateOlympiade();
                break;
            case "Физика":
                Phisicist phisicist=(Phisicist) disciplines.get(index);
                phisicist.participateParadeOfProject();
                break;
            case "Химия":
                Chemist chemist=(Chemist) disciplines.get(index);
                chemist.writeResearchWork();
                break;
            case "Программирование":
                Programmer programmer=(Programmer) disciplines.get(index);
                programmer.joinToProekt();
                break;
            default:
                System.out.println("Неопознанный предмет");
        }
        return result;
    }
    public int doSecondWork(int index)
    {
        int result;
        switch (getNameOfDiscipline(index))
        {
            case "Математика":
                Matician matician=(Matician) disciplines.get(index);
                result= matician.showResultsOfOlympiades();
                break;
            case "Физика":
                Phisicist phisicist=(Phisicist) disciplines.get(index);
                result= phisicist.doLaboratoryWork();
                break;
            case "Химия":
                Chemist chemist=(Chemist) disciplines.get(index);
                result= chemist.doLaboratoryWork();
                break;
            case "Программирование":
                Programmer programmer=(Programmer) disciplines.get(index);
                result= programmer.participateSportProgramming();
                break;
            default:
                System.out.println("Неопознанный предмет");
                result= 0;
        }
        return result;
    }
    public int[] getMarksOfDiscipline(int index)
    {
        return disciplines.get(index).getMarks();
    }
    public int getCountOfMarks(int index)
    {
        return disciplines.get(index).getCountOfMarks();
    }
    public Discipline getDiscipline(int index)
    {
        return disciplines.get(index);
    }
    public void setDisciplines(Zachetka zachetka)
    {
        disciplines=zachetka.getDisciplines();
    }
    public int getIndexOfDiscipline(String disciplineName)
    {
        int a=0;
        for (int i=0; i<disciplines.size(); ++i)
        {
            if (disciplines.get(i).getName() == disciplineName)
            {
                a=i;
            }
        }
        return a;
    }
}
