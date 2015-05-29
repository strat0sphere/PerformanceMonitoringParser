/**
 * Created by stratos on 5/25/15.
 */
public class ParserFactory {

    String path;
    String experName = "";
    String profileTime = "";

    public ParserFactory(String path, String experName, String profileTime)
    {
        this.path = path;
        this.experName = experName;
        this.profileTime = profileTime;

    }

    public Parser getParser(String parserType) {

        if( parserType.equalsIgnoreCase("ifstat") )
            return new IfstatParser(path, experName, profileTime);
        else if ( parserType.equalsIgnoreCase("iostatCPU"))
            return new IostatParserForCPU(path, experName, profileTime);
        else if ( parserType.equalsIgnoreCase("iostatDevice"))
            return new IostatParserForDevice(path, experName, profileTime);
        else if ( parserType.equalsIgnoreCase("vmstat"))
            return new VmstatParser(path, experName, profileTime);

        return null;
    }
}
