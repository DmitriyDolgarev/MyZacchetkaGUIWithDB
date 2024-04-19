package Data;

import java.util.ArrayList;

public class Matician extends Discipline {
    private ArrayList<String> olympiadePositions=new ArrayList<>();
    public Matician()
    {
        super();
        setName("Математика");
        setDifTests("Пройти тест.", "Принять участие в олимпиаде", "Показать информацию об участии в олимпиадах");
    }
    @Override
    public String[] getConditions()
    {
        String[] conditions=new String[5];
        conditions[0]="Условия получения аттестации";
        conditions[1]="Количество часов посещения: от 3 и больше";
        conditions[2]="Оценка за тестирование: больше 2";
        conditions[3]="Количество оценок: от 3 и больше";
        conditions[4]="";
        return conditions;
    }
    @Override
    public int doLesson() {
        setHours(getHours()+1);
        certify();
        return getHours();
    }
    @Override
    public int startTest() {
        int randomMark=(int)(Math.random()*4)+2;
        addMark(String.valueOf(randomMark));
        if (randomMark>2)
        {
            setSuccess(true);
        }
        certify();
        return randomMark;
    }

    @Override
    public void certify() {
        if (getHours()>=3&&getSuccess()&&getCountOfMarks()>=3)
        {
            setCertification(true);
        }
    }
    public ArrayList<String> getOlympiadePositions()
    {
        return this.olympiadePositions;
    }
    public String olympiadePositionsToString()
    {
        String result = "";

        for (int i=0; i<olympiadePositions.size(); ++i)
        {
            result += olympiadePositions.get(i);
            result += " ";
        }
        return result;
    }
    public void setOlympiadePositions(String[] array)
    {
        for (int i=0; i<array.length; ++i)
        {
            this.olympiadePositions.add(array[i]);
        }
    }
    public int getCountPositions()
    {
        return this.olympiadePositions.size();
    }
    public int participateOlympiade()
    {
        int position=(int)(Math.random()*100)+1;
        olympiadePositions.add(String.valueOf(position));
        setCertification(true);
        if (getCountOfMarks()<3)
        {
            while (getCountOfMarks()<3)
            {
                addMark(String.valueOf(5));
            }
        }
        if (getHours()<3)
        {
            setHours(3);
        }
        setSuccess(true);
        return position;
    }
    public int showResultsOfOlympiades()
    {
        int bestPosition=100;
        for (int i=0; i<olympiadePositions.size(); ++i)
        {
            if (Integer.parseInt(olympiadePositions.get(i))<bestPosition)
            {
                bestPosition=Integer.parseInt(olympiadePositions.get(i));
            }
        }
        return bestPosition;
    }
}
