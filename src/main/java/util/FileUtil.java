package util;

import model.FileMapper;
import model.Line;
import model.ResultMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static FileMapper loadFileIntoMapper(String fileName) {
        FileMapper fileMapper = new FileMapper(fileName);

        InputStream is = FileUtil.class.getResourceAsStream("/" + fileName);
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            for (String line; (line = reader.readLine()) != null; ) {
                fileMapper.addLine(new Line(lineCount, line));
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileMapper;
    }

    public static void searchInMapper(ResultMap resultMap, FileMapper fileMapper, String searchText) {
        for (Line line : fileMapper.getLines()) {
            int column = line.getContent().indexOf(searchText);
            if (column > -1) {
                resultMap.locatedAt(fileMapper.getFileName(), line.getNumber(), column);
            }
        }
    }
}

