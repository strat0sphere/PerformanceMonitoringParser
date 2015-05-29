/**
 * Created by stratos on 5/25/15.
 */
public class IostatParserForDevice extends StatsParser implements Parser {

    private int offset = 13; //Get vda stats
    ///TODO: Value should be 13 for slaves and 16 for masters

    public IostatParserForDevice (String path, String experName, String profileTime, InstanceType instanceType)
    {
        super(path, experName, profileTime);
        if (instanceType.equals(InstanceType.MASTER))
            offset = 16;
    }

    @Override
    public void parse() {
        super.parseFile("iostat", offset);
    }
}
