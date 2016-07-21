package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultMap {
    private String searchText;
    private HashMap<String, ArrayList<Position>> result;

    public ResultMap(String searchText) {
        this.searchText = searchText;
        result = new HashMap<>();
    }

    public void locatedAt(String fileName, Integer line, Integer column) {
        ArrayList<Position> lineArray = this.result.get(fileName);
        if (lineArray == null)
            lineArray = new ArrayList<>();
        lineArray.add(new Position(line, column));
        this.result.put(fileName, lineArray);
    }

    public String getSearchText() {
        return searchText;
    }

    public HashMap<String, ArrayList<Position>> getResult() {
        return result;
    }

    public class Position {
        private Integer line;
        private Integer column;

        public Position(Integer line, Integer column) {
            this.line = line;
            this.column = column;
        }

        public Integer getLine() {
            return line;
        }

        public Integer getColumn() {
            return column;
        }
    }
}
