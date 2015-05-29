/**
 * Created by stratos on 5/25/15.
 */
public class IfstatParser extends StatsParser implements Parser {

    private int offset = 3; //The line containing the stats is x number of lines after the token

    public IfstatParser  (String path, String experName, String profileTime)
    {
        super(path, experName, profileTime);
    }


    /*public List<String> getFiles(String type)
    {
        File directory = new File(super.path + "/" + type);
        File[] listOfFiles = directory.listFiles();

        for (int i=0; i<listOfFiles.length; i++)
        {
            if(listOfFiles[i].isDirectory()) {
                File subDir = new File(super.path + listOfFiles[i].getName());
                File[] inputFiles = subDir.listFiles();
                for(int j=0; j<inputFiles.length; j++) {
                    if (!inputFiles[j].getName().contains("out.csv"))
                        continue;
                    //createReader(super.path + listOfFiles[i].getName() + "/", inputFiles[j].getName());
                    //parseFile

                }

            }
            else
                continue;
        }
    }
    */
    @Override
    public void parse() {

        super.parseFile("ifstat", offset);

    }
}
