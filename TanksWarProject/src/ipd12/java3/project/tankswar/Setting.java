package ipd12.java3.project.tankswar;

public class Setting {

    public static void threadMessage(String message) {
        String threadName
                = Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    //game contr
    public static boolean THREAD_Exit = false;
    public final static boolean CLOSE_THREAD_TRUE = true;
    public final static boolean CLOSE_THREAD_FALSE = false;
    public final static int SPEED_ZERO = 0;

    //The map 
    public final static int MAP_WIDTH = 600;
    public final static int MAP_HEIGHT = 400;
    public final static int TOP_LEFT_CORNER_X = 20;
    public final static int LOWER_RIGHT_CORNER_X = MAP_WIDTH + TOP_LEFT_CORNER_X;
    public final static int TOP_LEFT_CORNER_Y = 10;
    public final static int LOWER_RIGHT_CORNER_Y = MAP_HEIGHT + TOP_LEFT_CORNER_Y;

    //Direction of tank,bullet
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int EAST = 2;
    public final static int WEST = 3;

    //The tank blood bath
    public final static int BLOODBATH_LONGITUDINAL_WIDTH = 20;
    public final static int BLOODBATH_LONGITUDINAL_HEIGHT = 30;
    public final static int BLOODBATH_HORIZONTAL_WIDTH = 30;
    public final static int BLOODBATH_HORIZONTAL_HEIGHT = 20;

    //Enemy setting
    public final static int ENEMY_MOVE_LENGTH = 30;
    public static int ENEMY_TANK_MOVESPEED = 2;

    //Thread setting
    public final static int PAUSEFOR_50_MILLISECONDS = 50;
    public final static int PAUSEFOR_100_MILLISECONDS = 100;

    //Camp
    public final static int CAMP_GREEN = 0;
    public final static int CAMP_GRAY = 1;

    //Players
    public final static int PLAYER_NUNMBER = 1;
    public static int PLAYER_TANK_MOVESPEED = 3;
    public final static int PLAYER_TANK_X = 180;
    public final static int PLAYER_TANK_Y = 220;
    public final static int PLAYER_TANK_MAXSHOOT_BULLETS = 5;

    //tank's collide & alive
    public final static boolean ISALIVE_TRUE = true;
    public final static boolean ISALIVE_FALSE = false;
    public final static boolean ISCOLLIDE_TRUE = true;
    public final static boolean ISCOLLIDE_FALSE = false;

    //bullet
    public final static int BULLET_WIDTH = 2;
    public final static int BULLET_HEIGHT = 2;
    public static int BULLET_SPEED = 5;

    //decoration
    public final static int DECORATION_LIFE = 9;

}

/**
 * reference: collision using two rectangle
 * https://www.youtube.com/watch?v=yge4GBkQsvw
 */
/*
TANK NAME
Juggernaut
Dreadnaught
Nightmare 
terrorknight 
Blood bath
Ebony&Ivory 
Nero, name of one of the leader's of ancient rome, knowned to enjoy killing people
Dante, name of the devil in spanish
Dark Twilight
Black Dawn
Vengeance
Van Helsing
and Lust&wrath, name of the 2 deadly sins
 */
