package ipd12.java3.project.tankswar;

public class Record {

    public static int getEnemyNumber() {
        return enemyNumber;
    }

    public static void setEnemyNumber(int aEnemyNumber) {
        enemyNumber = aEnemyNumber;
    }

    public static void reduceEnemyNumber() {
        enemyNumber--;
    }

    public static void reduceHeroHP() {
        setHeroHP(getHeroHP() - 1);
    }

    public static int getHeroHP() {
        return heroHP;
    }

    public static void setHeroHP(int aHeroHP) {
        heroHP = aHeroHP;
    }

    public static int getDestroyAllEnemies() {
        return destroyAllEnemies;
    }

    public static int setDestroyAllEnemies(int aDestroyAllEnemies) {
        return destroyAllEnemies = aDestroyAllEnemies;
    }
    public static void addDestroyAllEnemies(){
        destroyAllEnemies++;
    }
    private static int enemyNumber = 10;
    private static int destroyAllEnemies =0;
    private static int heroHP = 3;
}
