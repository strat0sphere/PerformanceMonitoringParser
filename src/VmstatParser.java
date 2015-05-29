/**
 * Created by stratos on 5/25/15.
 */
public class VmstatParser extends StatsParser implements Parser {

    private int offset = 4; //The line containing the stats is x number of lines after the token

    public VmstatParser (String path, String experName, String profileTime)
    {
        super(path, experName, profileTime);
    }

    @Override
    public void parse() {

        super.parseFile("vmstat", offset);
    }
}
