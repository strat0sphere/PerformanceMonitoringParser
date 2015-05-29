/**
 * Created by stratos on 5/25/15.
 */
public class ParseFiles {

    final InstanceType instanceType;
    String path = "/Users/stratos/Development/stratos-results/BigData/profiling/";

    //hadoop-Grep2
    //spark-coarse-Grep
    String experName = "";
    String profileTime = "";
    static String[] profileTimes = {"25", "300"};
    static String[] experNames = {"stats-profiling3-hadoop-bayes", "stats-profiling3-fine-bayes2", "stats-profiling3-coarse-bayes2"};
    //static String[] experNames = {"stats-profiling3-hadoop-wordcount", "stats-profiling3-spark-fine-WordCount", "stats-profiling3-spark-coarse-WordCount", "stats-profiling3-hadoop-Grep2", "stats-profiling3-spark-fine-Grep", "stats-profiling3-spark-coarse-Grep"};

    static String[] slaves = {"146", "153", "154", "156", "160", "164"};
    int totalRuns = 5;

    public ParseFiles(String experName, String profileTime, InstanceType instanceType)
    {
        this(experName, profileTime, "", instanceType);
    }
    public ParseFiles(String experName, String profileTime, String addedDirToPath, InstanceType instanceType)
    {
        this.instanceType = instanceType;
        this.path += addedDirToPath;
        this.experName = experName;
        this.profileTime = profileTime;
    }

    public void parseAndPrint()
    {

        //StatsParser statsParser = new StatsParser();
        IfstatParser ifstatParser = new IfstatParser(this.path, this.experName, this.profileTime);
        IostatParserForCPU iostatParserForCPU = new IostatParserForCPU(this.path, this.experName, this.profileTime, instanceType);
        IostatParserForDevice iostatParserForDevice = new IostatParserForDevice(this.path, this.experName, this.profileTime, instanceType);
        VmstatParser vmstatParser = new VmstatParser(this.path, this.experName, this.profileTime);

        ifstatParser.parse();
        iostatParserForCPU.parse();
        iostatParserForDevice.parse();
        vmstatParser.parse();

        //ifstatParser.printStats();
        //iostatParserForCPU.printStats();
        //iostatParserForDevice.printStats();
        //vmstatParser.printStats();


        System.out.println("\n\n\n" + this.path + this.experName + this.profileTime);
        StatsCombiner statsCombiner = new StatsCombiner(this.totalRuns, ifstatParser.getStats(), iostatParserForCPU.getStats(), iostatParserForDevice.getStats(), vmstatParser.getStats());
        statsCombiner.printStats();
    }

    public static void parseInstanceFiles(InstanceType instanceType)
    {
        ParseFiles parseFiles;
        if(instanceType.equals(InstanceType.MASTER))
        {
            for (String profileTime : profileTimes) {
                for (String experName : experNames) {
                    parseFiles = new ParseFiles(experName, profileTime, "master/", instanceType);
                    parseFiles.parseAndPrint();

                }
            }
        }
        else
        {
            for (String slave : slaves) {
                for (String profileTime : profileTimes) {
                    for (String experName : experNames) {
                        parseFiles = new ParseFiles(experName, profileTime, "slaves/" + slave + "/", instanceType);
                        parseFiles.parseAndPrint();

                    }
                }

            }

        }


    }
    public static void main(String[] args)
    {

        parseInstanceFiles(InstanceType.MASTER);
    }
}
