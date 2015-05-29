/**
 * Created by stratos on 5/25/15.
 */
public class ParserFactory {

    String path;
    String experName = "";
    String profileTime = "";
    InstanceType instanceType;

    public ParserFactory(String path, String experName, String profileTime, InstanceType instanceType)
    {
        this.path = path;
        this.experName = experName;
        this.profileTime = profileTime;
        this.instanceType = instanceType;

    }

    public Parser getParser(String parserType) {

        if( parserType.equalsIgnoreCase("ifstat") )
            return new IfstatParser(path, experName, profileTime);
        else if ( parserType.equalsIgnoreCase("iostatCPU"))
            return new IostatParserForCPU(path, experName, profileTime, instanceType);
        else if ( parserType.equalsIgnoreCase("iostatDevice"))
            return new IostatParserForDevice(path, experName, profileTime, instanceType);
        else if ( parserType.equalsIgnoreCase("vmstat"))
            return new VmstatParser(path, experName, profileTime);

        return null;
    }
}
