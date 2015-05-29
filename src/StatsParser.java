import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stratos on 5/25/15.
 */
public class StatsParser {

    final String token = "newrun"; //Find this token that indicates a new run
    BufferedReader reader = null;
    PrintWriter writer = null;
    String experName = "";
    String profileTime = "20";
    String path = "";
    boolean parseLine = false;
    //Map<String, ArrayList[]> stats; //For example master-hadoop-bayes-300, 0 --> 10.06\t87.56\t5\t189...
    List<String> runStats;
    int offset = 3; //The line containing the stats is x number of lines after the token

    public StatsParser(String path, String experName, String profileTime)
    {
        this.path = path;
        this.experName = experName;
        this.profileTime = profileTime;
        //stats = new HashMap<String, ArrayList[]>();
        runStats = new ArrayList<String>();
    }

    public void parseFile(String statsType, int offset)
    {
        String inputFileName = experName + "-" + statsType + "-" + profileTime + ".txt";

        try {
            reader = new BufferedReader(new FileReader(path + inputFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String line = "";
        int run = 0;
        int lineNo = 0;

        try {
            while ((line = reader.readLine()) != null)
            {
                lineNo++;
                //System.out.println(line);
                if (lineNo == offset)
                {
                    runStats.add(run, line);
                    run++;

                }

                if (getFirstWordFromLine(line).equals(token)) {
                    lineNo = 0;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<String> getStats() {
        return runStats;
    }

    public void printStats()
    {
            for (String run : runStats)
            {
                System.out.println(run);
            }
    }


    public static String getFirstWordFromLine(String line)
    {
        return line.split(" ")[0];
    }

}
