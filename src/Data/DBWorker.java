package Data;

import java.sql.*;

public class DBWorker {
    private static final String URL = "jdbc:postgresql://localhost:5432/zachetka?user=postgres&password=1";
    private static Connection connection;

    public static void initDB()
    {
        try
        {
            connection = DriverManager.getConnection(URL);
            if (connection!=null)
            {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getDriverName());
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void closeDB()
    {
        try {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteData()
    {
        deleteZachetka();
        deleteMathematics();
        deletePhisics();
        deleteProgramming();
        deleteChemistry();

    }
    public static void deleteZachetka()
    {
        try
        {
            if (getZachetkaRowCount() != 0)
            {
                PreparedStatement zachetkaStatement = connection.prepareStatement("DELETE FROM zachetka;");
                zachetkaStatement.execute();
                zachetkaStatement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteMathematics()
    {
        try
        {
            if (getDisciplineRowCount("mathematics") != 0)
            {
                PreparedStatement mathematicsStatement = connection.prepareStatement("DELETE FROM mathematics WHERE id=1;");
                mathematicsStatement.execute();
                mathematicsStatement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void deletePhisics()
    {
        try
        {
            if (getDisciplineRowCount("phisics") != 0)
            {
                PreparedStatement phisicsStatement = connection.prepareStatement("DELETE FROM phisics WHERE id=2;");
                phisicsStatement.execute();
                phisicsStatement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteProgramming()
    {
        try
        {
            if (getDisciplineRowCount("programming") != 0)
            {
                PreparedStatement programmingStatement = connection.prepareStatement("DELETE FROM programming WHERE id=3;");
                programmingStatement.execute();
                programmingStatement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteChemistry()
    {
        try
        {
            if (getDisciplineRowCount("chemistry") != 0)
            {
                PreparedStatement chemistryStatement = connection.prepareStatement("DELETE FROM chemistry WHERE id=4;");
                chemistryStatement.execute();
                chemistryStatement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static int getDisciplineRowCount(String subject)
    {
        int count = 0;
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + subject + ";");

            while(resultSet.next())
            {
                ++count;
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

    public static void addSubjects(Zachetka zachetka)
    {
        for (int i=0; i<zachetka.getCountOfDisciplines(); ++i)
        {
            switch (zachetka.getDiscipline(i).getName())
            {
                case "Математика":
                    addMathematics((Matician) zachetka.getDiscipline(i));
                    break;
                case "Физика":
                    addPhisics((Phisicist) zachetka.getDiscipline(i));
                    break;
                case "Программирование":
                    addProgramming((Programmer) zachetka.getDiscipline(i));
                    break;
                case "Химия":
                    addChemistry((Chemist) zachetka.getDiscipline(i));
                    break;
            }
        }
    }

    public static void addMathematics(Matician matician)
    {
        try
        {
            //connection = DriverManager.getConnection(URL);
            PreparedStatement statement;

            if (getDisciplineRowCount("mathematics") != 0)
            {
                statement = connection.prepareStatement("UPDATE mathematics SET certification=?, hours=?, success=?, marks=?, olympiadepositions=? WHERE id=1;");
            }
            else
            {
                statement = connection.prepareStatement("INSERT INTO mathematics VALUES(1, ?, ?, ?, ?, ?);");
            }

            statement.setString(1, "" + matician.getCertification());
            statement.setString(2, "" + matician.getHours());
            statement.setString(3, "" + matician.getSuccess());
            statement.setString(4, matician.marksToString());
            statement.setString(5, matician.olympiadePositionsToString());

            PreparedStatement zachetkaStatement;

            if (getZachetkaRowCount() == 0)
            {
                zachetkaStatement = connection.prepareStatement("INSERT INTO zachetka(id, mathematics_id) VALUES(1, 1);");
            }
            else
            {
                zachetkaStatement = connection.prepareStatement("UPDATE zachetka SET mathematics_id=1 WHERE id=1;");
            }

            statement.execute();
            zachetkaStatement.execute();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addPhisics(Phisicist phisicist)
    {
        try
        {
            //connection = DriverManager.getConnection(URL);
            PreparedStatement statement;

            if (getDisciplineRowCount("phisics") != 0)
            {
                statement = connection.prepareStatement("UPDATE phisics SET certification=?, hours=?, success=?, marks=?, laboratorywork=? WHERE id=2;");
            }
            else
            {
                statement = connection.prepareStatement("INSERT INTO phisics VALUES(2, ?, ?, ?, ?, ?);");
            }

            statement.setString(1, "" + phisicist.getCertification());
            statement.setString(2, "" + phisicist.getHours());
            statement.setString(3, "" + phisicist.getSuccess());
            statement.setString(4, phisicist.marksToString());
            statement.setString(5, "" + phisicist.getLaboratoryWork());

            PreparedStatement zachetkaStatement;

            if (getZachetkaRowCount() == 0)
            {
                zachetkaStatement = connection.prepareStatement("INSERT INTO zachetka(id, phisics_id) VALUES(1, 2);");
            }
            else
            {
                zachetkaStatement = connection.prepareStatement("UPDATE zachetka SET phisics_id=2 WHERE id=1;");
            }

            statement.execute();
            zachetkaStatement.execute();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addProgramming(Programmer programmer)
    {
        try
        {
            //connection = DriverManager.getConnection(URL);
            PreparedStatement statement;

            if (getDisciplineRowCount("programming") != 0)
            {
                statement = connection.prepareStatement("UPDATE programming SET certification=?, hours=?, success=?, marks=?, sportprogramming=? WHERE id=3;");
            }
            else
            {
                statement = connection.prepareStatement("INSERT INTO programming VALUES(3, ?, ?, ?, ?, ?);");
            }

            statement.setString(1, "" + programmer.getCertification());
            statement.setString(2, "" + programmer.getHours());
            statement.setString(3, "" + programmer.getSuccess());
            statement.setString(4, programmer.marksToString());
            statement.setString(5, "" + programmer.getSportProgramming());

            PreparedStatement zachetkaStatement;

            if (getZachetkaRowCount() == 0)
            {
                zachetkaStatement = connection.prepareStatement("INSERT INTO zachetka(id, programming_id) VALUES(1, 3);");
            }
            else
            {
                zachetkaStatement = connection.prepareStatement("UPDATE zachetka SET programming_id=3 WHERE id=1;");
            }

            statement.execute();
            zachetkaStatement.execute();
            statement.close();
            zachetkaStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void addChemistry(Chemist chemist)
    {
        try
        {
            //connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO chemistry VALUES(4, ?, ?, ?, ?, ?);");

            if (getDisciplineRowCount("chemistry") != 0)
            {
                statement = connection.prepareStatement("UPDATE chemistry SET certification=?, hours=?, success=?, marks=?, laboratorywork=? WHERE id=4;");
            }
            else
            {
                statement = connection.prepareStatement("INSERT INTO chemistry VALUES(4, ?, ?, ?, ?, ?);");
            }

            statement.setString(1, "" + chemist.getCertification());
            statement.setString(2, "" + chemist.getHours());
            statement.setString(3, "" + chemist.getSuccess());
            statement.setString(4, chemist.marksToString());
            statement.setString(5, "" + chemist.getLaboratoryWork());

            PreparedStatement zachetkaStatement;

            if (getZachetkaRowCount() == 0)
            {
                zachetkaStatement = connection.prepareStatement("INSERT INTO zachetka(id, chemistry_id) VALUES(1, 4);");
            }
            else
            {
                zachetkaStatement = connection.prepareStatement("UPDATE zachetka SET chemistry_id=4 WHERE id=1;");
            }

            statement.execute();
            zachetkaStatement.execute();
            statement.close();
            zachetkaStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static int getZachetkaRowCount()
    {
        int count = 0;
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM zachetka;");

            while(resultSet.next())
            {
                ++count;
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();;
        }
        return count;
    }
    public static Zachetka getZachetka()
    {
        Zachetka zachetka = new Zachetka();
        ResultSet resultSet;

        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM zachetka;");

            if (getZachetkaRowCount() != 0)
            {
                while (resultSet.next())
                {
                    for (int i = 2; i < 6; ++i) {
                        switch (i) {
                            case 2:
                                if (resultSet.getString(i) != null) {
                                    getMathematics(zachetka);
                                }
                                break;
                            case 3:
                                if (resultSet.getString(i) != null) {
                                    getPhisics(zachetka);
                                }
                                break;
                            case 4:
                                if (resultSet.getString(i) != null) {
                                    getProgramming(zachetka);
                                }
                                break;
                            case 5:
                                if (resultSet.getString(i) != null) {
                                    getChemistry(zachetka);
                                }
                                break;
                        }
                    }
                }
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();;
        }

        return zachetka;
    }

    public static void getMathematics(Zachetka zachetka)
    {
        Matician matician = new Matician();
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM mathematics;");

            while (resultSet.next()) {

                matician.setCertification(Boolean.parseBoolean(resultSet.getString(2)));

                matician.setHours(Integer.parseInt(resultSet.getString(3)));

                matician.setSuccess(Boolean.parseBoolean(resultSet.getString(4)));

                if (resultSet.getString(5).length() != 0) {
                    String[] marks = resultSet.getString(5).split(" ");
                    int[] arr = new int[marks.length];

                    for (int i = 0; i < arr.length; ++i) {
                        arr[i] = Integer.parseInt(marks[i]);
                    }
                    matician.setMarks(arr);
                }

                if (resultSet.getString(6).length() != 0) {
                    String[] positions = resultSet.getString(6).split(" ");

                    matician.setOlympiadePositions(positions);
                }
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        zachetka.addDiscipline(matician);
    }
    public static void getPhisics(Zachetka zachetka)
    {
        Phisicist phisicist = new Phisicist();
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM phisics;");

            while (resultSet.next()) {

                phisicist.setCertification(Boolean.parseBoolean(resultSet.getString(2)));
                phisicist.setHours(Integer.parseInt(resultSet.getString(3)));
                phisicist.setSuccess(Boolean.parseBoolean(resultSet.getString(4)));

                if (resultSet.getString(5).length() != 0) {
                    String[] marks = resultSet.getString(5).split(" ");
                    int[] arr = new int[marks.length];

                    for (int i = 0; i < arr.length; ++i) {
                        arr[i] = Integer.parseInt(marks[i]);
                    }
                    phisicist.setMarks(arr);
                }

                if (resultSet.getString(6).length() != 0) {
                    phisicist.setLaboratoryWork(resultSet.getBoolean(6));
                }
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        zachetka.addDiscipline(phisicist);
    }
    public static void getProgramming(Zachetka zachetka)
    {
        Programmer programmer = new Programmer();
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM programming;");

            while (resultSet.next()) {

                programmer.setCertification(Boolean.parseBoolean(resultSet.getString(2)));
                programmer.setHours(Integer.parseInt(resultSet.getString(3)));
                programmer.setSuccess(Boolean.parseBoolean(resultSet.getString(4)));

                if (resultSet.getString(5).length() != 0) {
                    String[] marks = resultSet.getString(5).split(" ");
                    int[] arr = new int[marks.length];

                    for (int i = 0; i < arr.length; ++i) {
                        arr[i] = Integer.parseInt(marks[i]);
                    }
                    programmer.setMarks(arr);
                }

                if (resultSet.getString(6).length() != 0) {
                    programmer.setSportProgramming(resultSet.getBoolean(6));
                }
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        zachetka.addDiscipline(programmer);
    }
    public static void getChemistry(Zachetka zachetka)
    {
        Chemist chemist = new Chemist();
        try
        {
            //connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM chemistry;");

            while (resultSet.next()) {

                chemist.setCertification(Boolean.parseBoolean(resultSet.getString(2)));
                chemist.setHours(Integer.parseInt(resultSet.getString(3)));
                chemist.setSuccess(Boolean.parseBoolean(resultSet.getString(4)));

                if (resultSet.getString(5).length() != 0) {
                    String[] marks = resultSet.getString(5).split(" ");
                    int[] arr = new int[marks.length];

                    for (int i = 0; i < arr.length; ++i) {
                        arr[i] = Integer.parseInt(marks[i]);
                    }
                    chemist.setMarks(arr);
                }

                if (resultSet.getString(6).length() != 0) {
                    chemist.setLaboratoryWork(resultSet.getBoolean(6));
                }
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        zachetka.addDiscipline(chemist);
    }
}
