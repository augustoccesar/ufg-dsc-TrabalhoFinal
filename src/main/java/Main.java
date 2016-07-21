import model.FileMapper;
import model.Line;
import model.ResultMap;
import util.FileUtil;
import util.LineFinder;
import util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static final int MAX_THREADS = 5;

    public static void main(String[] args){
        String searchText = "como";
        ArrayList<String> filesToSearch = new ArrayList<String>() {{
            add("file1.txt");
            add("file2.txt");
            add("file3.txt");
            add("file4.txt");
            add("file5.txt");
        }};

        ResultMap resultMap = new ResultMap(searchText);

        for(String file : filesToSearch){
            FileMapper fileMapper = FileUtil.loadFileIntoMapper(file);
            List<Line> lines = fileMapper.getLines();
            int maxLinesPerThread = lines.size() / getThreadsMax();

            ExecutorService executorService = Executors.newFixedThreadPool(getThreadsMax());

            System.out.println("==================================================================");
            System.out.println(String.format("Procurando no arquivo %s", fileMapper.getFileName()));
            System.out.println(String.format("Total lines = %d", lines.size()));
            System.out.println(String.format("Total threads executando = %d", getThreadsMax()));
            System.out.println(String.format("Total lines por thread = %d", maxLinesPerThread));

            List<List<Line>> linesPerThread = ListUtil.chopped(lines, maxLinesPerThread);

            for (List<Line> aLinesPerThread : linesPerThread) {
                executorService.execute(new LineFinder(aLinesPerThread, searchText, fileMapper.getFileName(), resultMap));
            }

            executorService.shutdown();
        }

    }

    private static int getThreadsMax(){
        int cpus = Runtime.getRuntime().availableProcessors();
        int scaleFactor = 1;
        int maxThreads = cpus * scaleFactor;
        maxThreads = (maxThreads > 0 ? maxThreads : 1);
        return maxThreads;
    }
}
