package ipd12.java3.project.tankswar;

public class Hero extends Tank {

    public Hero(int x, int y, int camp, int direction, int speed, boolean isAlive, boolean isCollision) {
        super(x, y, camp, direction, speed, isAlive, isCollision);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String playerName;
    private String email;
}
