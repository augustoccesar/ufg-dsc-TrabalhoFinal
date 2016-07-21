package util;

import model.Line;
import model.ResultMap;

import java.util.List;
import java.util.Locale;

public class LineFinder implements Runnable {
    List<Line> lines;
    String searchText;
    String fileName;
    ResultMap resultMap;

    public LineFinder(List<Line> lines, String searchText, String fileName, ResultMap resultMap) {
        this.lines = lines;
        this.searchText = searchText;
        this.fileName = fileName;
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        for (Line line : lines) {
            int column = line.getContent().indexOf(searchText);
            if (column > -1) {
                resultMap.locatedAt(fileName, line.getNumber(), column);
                System.out.println(String.format(Locale.ENGLISH, "Thread %d encontrou a String %s no arquivo %s na linha %d e coluna %d",
                        Thread.currentThread().getId(), searchText, fileName, line.getNumber(), column));
            }
        }
    }
}