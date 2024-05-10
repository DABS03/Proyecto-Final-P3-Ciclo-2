package Clases;

import java.util.Date;

public class DateRange 
{
    private Date startDate;
    private Date endDate;

    public DateRange(Date startDate, Date endDate) 
    {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean seSolapanCon(DateRange otro) 
    {
        return !(this.endDate.before(otro.startDate) || this.startDate.after(otro.endDate));
    }
}
