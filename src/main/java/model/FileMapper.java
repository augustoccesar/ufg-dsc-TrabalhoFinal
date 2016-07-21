package model;

import java.util.ArrayList;
import java.util.List;

public class FileMapper {
    private String fileName;
    private List<Line> lines = new ArrayList<>();

    public FileMapper(String fileName) {
        this.fileName = fileName;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    public String getFileName() {
        return fileName;
    }
}
