package Data;

public class Phisicist extends Discipline {
    private boolean laboratoryWork;
    public Phisicist()
    {
        super();
        setName("Физика");
        setDifTests("Сдать контрольную работу.", "Принять участиe в параде проектов.", "Сдать лабораторную работу");
    }
    @Override
    public String[] getConditions()
    {
        String[] conditions=new String[5];
        conditions[0]="Условия получения аттестации";
        conditions[1]="Количество часов посещения: больше 2";
        conditions[2]="Оценка за тестирование: больше 2";
        conditions[3]="Количество оценок: от 3 и больше";
        conditions[4]="Выполнить лабораторную работу на оценку 3 и больше";
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
        if (getHours()>=2&&getSuccess()&&getCountOfMarks()>=3&&laboratoryWork)
        {
            setCertification(true);
        }
    }
    public boolean getLaboratoryWork()
    {
        return laboratoryWork;
    }
    public void setLaboratoryWork(boolean lr)
    {
        this.laboratoryWork = lr;
    }
    public void participateParadeOfProject()
    {
        setSuccess(true);
    }
    public int doLaboratoryWork()
    {
        int random=(int)(Math.random()*4)+2;
        if (random>2)
        {
            this.laboratoryWork=true;
            addMark(String.valueOf(random));
            certify();
        }
        return random;
    }
}
