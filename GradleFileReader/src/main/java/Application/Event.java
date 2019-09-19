package Application;

public class Event
{
    private String id;

    private String state;

    private String type;

    private String timestamp;
    
    private String host;
    
    public Event() {
    	super();
    }
    
    public Event(String id, String state, String type, String timestamp, String host) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.timestamp = timestamp;
		this.host = host;
	}

	public String getHost ()
    {
        return host;
    }

    public void setHost (String host)
    {
        this.host = host;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [host = "+host+", id = "+id+", state = "+state+", type = "+type+", timestamp = "+timestamp+"]";
    }
}
