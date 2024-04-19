package Data;

public class Programmer extends Discipline {
    private boolean sportProgramming;
    public Programmer()
    {
        super();
        setName("Программирование");
        setDifTests("Сдать курсовой проект.", "Принять участие в создании проекта по программированию.",
                "Принять участие в соревнованиях по спортивному программированию");
    }
    @Override
    public String[] getConditions()
    {
        String[] conditions=new String[5];
        conditions[0]="Условия получения аттестации";
        conditions[1]="Количество часов посещения: больше 0";
        conditions[2]="Оценка за тестирование: больше 2";
        conditions[3]="Количество оценок: от 3 и больше";
        conditions[4]="Принять участие в проекте или в соревнованиях по спортивному программированию";
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
        if (getHours()>0&&getSuccess()&&getCountOfMarks()>=3&&sportProgramming)
        {
            setCertification(true);
        }
    }
    public boolean getSportProgramming()
    {
        return sportProgramming;
    }
    public void setSportProgramming(boolean sp)
    {
        this.sportProgramming = sp;
    }
    public void joinToProekt()
    {
        setCertification(true);
        if (getCountOfMarks()<3)
        {
            while (getCountOfMarks()<3)
            {
                addMark(String.valueOf(5));
            }
        }
        if (getHours()==0)
        {
            setHours(1);
        }
        setSuccess(true);
        this.sportProgramming=true;
    }
    public int participateSportProgramming()
    {
        int position=(int)(Math.random()*100)+1;
        sportProgramming=true;
        certify();
        return position;
    }
}
