import java.util.ArrayList;
import java.util.List;

/**
 * Created by stratos on 5/28/15.
 */
public class StatsCombiner {

    int totalRuns = 1;
    List<String> mergedStats;

    public StatsCombiner(int totalRuns, List<String>... allStats) {

        mergedStats = new ArrayList<String>();
        StringBuilder runMergedStats;
        this.totalRuns = totalRuns;

        //System.out.println("totalRuns: " + totalRuns);

        for (int i = 0; i <= totalRuns-1; i++)
        {
            runMergedStats = new StringBuilder();
            for (List<String> stats : allStats) {

                runMergedStats.append(" " + stats.get(i));

            }
            mergedStats.add(i, runMergedStats.toString());
        }

    }

    public void printStats()
    {
        for (int i=0; i<= totalRuns-1; i++)
        {
            //System.out.println("Run: " + i);
            System.out.println(mergedStats.get(i));
        }
    }
}
