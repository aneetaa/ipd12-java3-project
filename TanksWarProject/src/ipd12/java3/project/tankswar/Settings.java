package ipd12.java3.project.tankswar;

public interface Settings {

    //game
    boolean CLOSE_THREAD_TRUE = true;
    boolean CLOSE_THREAD_FALSE = false;
    int PLAYER_ONE_CODE = 0;
    int PLAYER_TWO_CODE = 1;

    //The map
    int MAP_WIDTH = 600;
    int MAP_HEIGHT = 400;
    int MAP_TOP_LEFT_CORNER_X = 20;
    int MAP_LOWER_RIGHT_CORNER_X = MAP_WIDTH + MAP_TOP_LEFT_CORNER_X;
    int MAP_TOP_LEFT_CORNER_Y = 10;
    int MAP_LOWER_RIGHT_CORNER_Y = MAP_HEIGHT + MAP_TOP_LEFT_CORNER_Y;

    //Direction of tank,bullet
    int NORTH = 0;
    int SOUTH = 1;
    int EAST = 2;
    int WEST = 3;

    //The tank blood bath
    int BLOODBATH_LONGITUDINAL_WIDTH = 20;
    int BLOODBATH_LONGITUDINAL_HEIGHT = 30;
    int BLOODBATH_HORIZONTAL_WIDTH = 30;
    int BLOODBATH_HORIZONTAL_HEIGHT = 20;

    //Enemy setting
    int ENEMY_MOVE_LENGTH = 30;
    int ENEMY_TANK_MOVESPEED = 2;

    //Thread setting
    long PAUSEFOR_50_MILLISECONDS = 50;
    long PAUSEFOR_100_MILLISECONDS = 100;

    //Camp
    int CAMP_GRAY = 0;
    int CAMP_GREEN = 1;
    int CAMP_YELLOW = 2;

    //Players
    int PLAYER_SINGLE = 1;
    int PLAYER_DOUBLE = 2;
    int PLAYER_TANK_MOVESPEED = Record.playerMoveSpeed;
    int PLAYER_TANK_X = 300;
    int PLAYER_TANK_Y = 220;
    int PLAYER_TANK_MAXSHOOT_BULLETS = 100;
    int PLAYER_SECOND_TANK_X = 200;
    int PLAYER_SECOND_TANK_Y = 220;

    //tank's collide & alive
    boolean ISALIVE_TRUE = true;
    boolean ISALIVE_FALSE = false;
    boolean ISCOLLIDE_TRUE = true;
    boolean ISCOLLIDE_FALSE = false;

    //bullet
    int BULLET_WIDTH = 2;
    int BULLET_HEIGHT = 2;
    int BULLET_SPEED = Record.playerBulletSpeed;

    //decoration
    int DECORATION_LIFE = 9;

}

/**
 * reference: collision using two rectangle
 * https://www.youtube.com/watch?v=yge4GBkQsvw
 *
 * https://www.youtube.com/watch?v=OKpyFH2k5nk&list=PLApzEUNqmYJVH2wjy3mp9iw6wXgAyK5Zx&index=12
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
