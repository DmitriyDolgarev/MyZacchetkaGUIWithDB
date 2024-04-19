package Data;

import java.util.ArrayList;

public abstract class Discipline {
    private String name;
    private boolean certification;
    private int hours;
    private boolean success;
    private String[] difTests=new String[3];
    private ArrayList<String> marks = new ArrayList<String>();
    public Discipline()
    {
        this.certification=false;
        this.hours=0;
        this.success=false;
    }
    public abstract String[] getConditions();
    public abstract int doLesson();
    public abstract int startTest();
    public abstract void certify();
    public String getName(){
        return name;
    }
    public int getHours()
    {
        return hours;
    }
    public boolean getSuccess()
    {
        return success;
    }
    public boolean getCertification()
    {
        return this.certification;
    }
    public String getDifTest(int index)
    {
        return difTests[index];
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setHours(int hours)
    {
        this.hours=hours;
    }
    public void setSuccess(boolean success)
    {
        this.success=success;
    }
    public void setCertification(boolean certification) {
        this.certification = certification;
    }
    public void setDifTests(String test, String action, String info)
    {
        difTests[0]=test;
        difTests[1]=action;
        difTests[2]=info;
    }
    public void addMark(String mark)
    {
        this.marks.add(mark);
    }
    public int[] getMarks()
    {
        int[] marks=new int[this.marks.size()];
        for (int i=0; i< marks.length; ++i)
        {
            marks[i]=Integer.parseInt(this.marks.get(i));
        }
        return marks;
    }
    public String marksToString()
    {
        String result = "";

        for (int i=0; i<marks.size(); ++i)
        {
            result += marks.get(i);
            result += " ";
        }
        return result;
    }
    public void setMarks(int[] marks)
    {
        for (int i=0; i<marks.length; ++i)
        {
            this.marks.add(String.valueOf(marks[i]));
        }
    }
    public int getCountOfMarks()
    {
        return marks.size();
    }
}
