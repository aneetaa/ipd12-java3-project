package ipd12.java3.project.tankswar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File {

    public File(String path, String fileName) {
        setPath(path);
        setFileName(fileName);
    }

    public void fileWriter(int playerMoveSpeed, int playerBulletSpeed, int enemyNumber, String playerOneName, String playerTwoName) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.getPath() + "\\" + this.getFileName(), false /* APPEND */)))) {
            pw.printf("%s,%s,%s,%s,%s%n", "PlayerMoveSpeed", "PlayerBulletSpeed", "EnemyNumber", "PlayerOneName", "PlayerTwoName");
            pw.printf("%d,%d,%d,%s,%s%n", playerMoveSpeed, playerBulletSpeed, enemyNumber, playerOneName, playerTwoName);
        }
    }

    public void fileReader() throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getPath() + "\\" + this.getFileName()))) {
            br.readLine(); // this will read the first
            String column[] = br.readLine().split(",");
            Record.playerMoveSpeed = Integer.parseInt(column[0]);
            Record.playerBulletSpeed = Integer.parseInt(column[1]);
            Record.enemyNumber = Integer.parseInt(column[2]);
            Record.playerOneName = column[3];
            Record.playerTwoName = column[4];
        }
    }

    public void fileWriterHighestRecord() throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.getPath() + "\\" + this.getFileName(), false /* APPEND */)))) {
            pw.printf("%s%n", "DestroyAllEnemies");
            pw.printf("%d%n", Record.heightRecord);
        }
    }

    public void fileReaderHighestRecord() throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getPath() + "\\" + this.getFileName()))) {
            br.readLine(); // this will read the first line
            Record.heightRecord = Integer.parseInt(br.readLine());
        }
    }

    public String getPath() {
        return path;
    }

    public final void setPath(String path) {
        this.path = path;

    }

    public String getFileName() {
        return fileName;
    }

    public final void setFileName(String fileName) {
        if (!fileName.matches(".+\\.[C|c][S|s][V|v]$")) {
            this.fileName = fileName + ".csv";
        } else {
            this.fileName = fileName;
        }
    }

    private String path;
    private String fileName;

}
