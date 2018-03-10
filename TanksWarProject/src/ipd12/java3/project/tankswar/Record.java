package ipd12.java3.project.tankswar;

public class Record {

    public static void reduceEnemyNumber() {
        enemyNumber--;
    }

    public static void reduceSecondPlayerHP() {
        secondPlayerHP--;
    }

    public static void reduceFirstPlayerHP() {
        firstPlayerHP--;
    }

    public static void addGradeCount() {
        currentRecord++;
    }

    static int enemyNumber;
    static int currentRecord = 0;
    static int heightRecord = 0;
    static int secondPlayerHP = 3;
    static int firstPlayerHP = 3;

    static int firstPlayerShootCounter = 0;
    static int secondPlayerShootCounter = 0;

    //settings
    static int playerMode;
    static int playerMoveSpeed;
    static int playerBulletSpeed;
    static String playerOneName;
    static String playerTwoName;
}
