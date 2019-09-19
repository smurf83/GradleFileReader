package Application;

public class Event
{
    private String id;

    private String state;

    private String type;

    private String duration;
    
    private String host;
    
    private boolean alert;
    
    public Event() {
    	super();
    }
    
    public Event(String id, String state, String type, String duration, String host) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.duration = duration;
		this.host = host;
	}
    
	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
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

    public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
    public String toString()
    {
        return "ClassPojo [host = "+host+", id = "+id+", state = "+state+", type = "+type+", timestamp = "+duration+"]";
    }
}
