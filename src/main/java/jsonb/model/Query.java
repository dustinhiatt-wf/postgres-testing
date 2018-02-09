package jsonb.model;

import java.util.Map;

public class Query {
    public String name;
    public long revision = 0;
    public Map<String, Object> parameters;
}
