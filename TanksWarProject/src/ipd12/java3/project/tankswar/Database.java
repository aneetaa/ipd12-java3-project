package ipd12.java3.project.tankswar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    public static Connection dbConn;

    private final static String URL = "jdbc:sqlite:tankwar.sqlite";

    public Database() throws SQLException {
        dbConn = DriverManager.getConnection(URL);
        createTanks();
        createBullets();
    }

    public static void createTanks() throws SQLException {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tanks (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "     x INTEGER NOT NULL,\n"
                + "     y INTEGER NOT NULL,\n"
                + "     direction INTEGER NOT NULL,\n"
                + "     speed INTEGER NOT NULL,\n"
                + "	camp INTEGER NOT NULL,\n"
                + "	isAlive TEXT NOT NULL,\n"
                + "	isCollision TEXT NOT NULL\n"
                + ");";

        try (Statement stmt = dbConn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("The DB tanks table established");

        }
    }

    public static void createBullets() throws SQLException {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS bullets (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "     x INTEGER NOT NULL,\n"
                + "     y INTEGER NOT NULL,\n"
                + "     direction INTEGER NOT NULL,\n"
                + "     speed INTEGER NOT NULL,\n"
                + "	camp INTEGER NOT NULL,\n"
                + "	isAlive TEXT NOT NULL\n"
                + ");";

        try (Statement stmt = dbConn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("The DB bullet table established");

        }
    }

    public ArrayList<Tank> getAllTanks() throws SQLException {
        String sql = "SELECT * FROM tanks";
        ArrayList<Tank> list = new ArrayList<>();
        try (Statement statement = dbConn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            //Iterate through the java resultSet
            while (rs.next()) {
                Tank tank = new Tank();
                tank.setId(rs.getInt("id"));
                tank.setX(rs.getInt("x"));
                tank.setY(rs.getInt("y"));
                tank.setDirection(rs.getInt("direction"));
                tank.setSpeed(rs.getInt("speed"));
                tank.setCamp(rs.getInt("camp"));
                tank.setIsAlive(rs.getBoolean("isAlive"));
                tank.setCollision(rs.getBoolean("isCollision"));
                list.add(tank);
            }
        }
        return list;
    }

    public ArrayList<Bullet> getAllBullets() throws SQLException {
        String sql = "SELECT * FROM bullets";
        ArrayList<Bullet> list = new ArrayList<>();
        try (Statement statement = dbConn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            //Iterate through the java resultSet
            while (rs.next()) {
                Bullet bullet = new Bullet();
                bullet.setId(rs.getInt("id"));
                bullet.setX(rs.getInt("x"));
                bullet.setY(rs.getInt("y"));
                bullet.setDirection(rs.getInt("direction"));
                bullet.setSpeed(rs.getInt("speed"));
                bullet.setCamp(rs.getInt("camp"));
                bullet.setIsAlive(rs.getBoolean("isAlive"));
                list.add(bullet);
            }
        }
        return list;
    }

    public void addTank(Tank tank) throws SQLException {
        String sql = "INSERT INTO tanks (x,y,direction,speed,camp,isAlive,isCollision)VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setInt(1, tank.getX());
            preparedStatement.setInt(2, tank.getY());
            preparedStatement.setInt(3, tank.getDirection());
            preparedStatement.setInt(4, tank.getSpeed());
            preparedStatement.setInt(5, tank.getCamp());
            preparedStatement.setBoolean(6, tank.isIsAlive());
            preparedStatement.setBoolean(7, tank.isCollision());
            preparedStatement.executeUpdate();
            System.out.printf("The %s tank add to DB.\n", tank.getClass().getName());
        }
    }

    public void addBullet(Bullet bullet) throws SQLException {
        String sql = "INSERT INTO bullets (x,y,direction,speed,camp,isAlive)VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.setInt(1, bullet.getX());
            preparedStatement.setInt(2, bullet.getY());
            preparedStatement.setInt(3, bullet.getDirection());
            preparedStatement.setInt(4, bullet.getSpeed());
            preparedStatement.setInt(5, bullet.getCamp());
            preparedStatement.setBoolean(6, bullet.isIsAlive());
            preparedStatement.executeUpdate();
            System.out.printf("The %s bullet add to DB.\n", bullet.getClass().getName());
        }
    }

    public void deleteTanks() throws SQLException {
        String sql = "TRUNCATE TABLE tanks;";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    public void deleteBullets() throws SQLException {
        String sql = "TRUNCATE TABLE tanks;";
        try (PreparedStatement preparedStatement = dbConn.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }

    public Connection getDbConn() {
        return dbConn;
    }
}
