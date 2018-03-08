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
        gradeCount++;
    }

    static int enemyNumber = 5;
    static int gradeCount = 0;
    static int secondPlayerHP = 3;
    static int firstPlayerHP = 3;

    static int firstPlayerShootCounter = 0;
    static int secondPlayerShootCounter = 0;

}
