/**
 * Created by stratos on 5/25/15.
 */
public class IostatParserForCPU extends StatsParser implements Parser {

    private int offset = 10; //Get avg-cpu stats
    //TODO: value should be 10 for slaves and 13 for masters

    public IostatParserForCPU  (String path, String experName, String profileTime, InstanceType instanceType)
    {
        super(path, experName, profileTime);
        if (instanceType.equals(InstanceType.MASTER))
            offset = 13;
    }

    @Override
    public void parse() {
        super.parseFile("iostat", offset);
    }
}
