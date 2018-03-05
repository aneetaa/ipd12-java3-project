package ipd12.java3.project.tankswar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class File {

    public File(String path,
            String fileName) {
        setPath(path);
        setFileName(fileName);
    }

    File(String gamelogcsv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void fileWriter() throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.getPath() + "\\" + this.getFileName(), false /* APPEND */)))) {
            pw.printf("%s%n", "DestroyAllEnemies");
            pw.printf("%d%n", Record.getDestroyAllEnemies());
        }
    }

    public void fileReader() throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.getPath() + "\\" + this.getFileName()))) {
            br.readLine(); // this will read the first line
            Record.setDestroyAllEnemies(Integer.parseInt(br.readLine()));
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
