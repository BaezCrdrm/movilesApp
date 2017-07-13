package mx.unam.primera.com.model;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Samuel on 12/05/2017.
 */

public class Event
{
    private String _id;
    private String _name;
    private Date _sch;
    private Date _ending;
    private Interval _duration;
    private String _des;
    private EventType _type;
    private List<Channel> _channelList;

    public Event()
    {
        _type = new EventType();
        _channelList = new ArrayList<>();
    }

    //region Propierties
    public String getId()
    {
        return this._id;
    }

    public void setId(String value)
    {
        this._id = value;
    }

    public String getName()
    {
        return this._name;
    }

    public void setName(String value)
    {
        this._name = value;
    }

    public Date getDate()
    {
        return this._sch;
    }

    public void setDate(Date value)
    {
        this._sch = value;
    }

    public void setEnding(Date value)
    {
        this._ending = value;
        Calendar c1 = new GregorianCalendar();
        c1.setTime(this._sch);
        DateTime d1 = getDt(c1);

        c1 = new GregorianCalendar();
        c1.setTime(this._ending);
        DateTime d2 = getDt(c1);
        this._duration = new Interval(d1, d2);
    }

    public Period getDuration()
    {
        return this._duration.toPeriod();
    }

    public String getDescription()
    {
        return this._des;
    }

    public void setDescription(String des)
    {
        this._des = des;
    }

    public EventType getType() { return this._type; }

    public void setType(EventType value)
    {
        this._type = value;
    }

    public List<Channel> getChannelList()
    {
        return this._channelList;
    }

    public void setChannelList(List<Channel> channelList)
    {
        this._channelList = channelList;
    }
    //endregion

    private DateTime getDt(Calendar calendar)
    {
        DateTime dt = new DateTime(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE));

        return dt;
    }
}
