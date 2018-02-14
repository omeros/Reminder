package mmn14targil2;                   //   omer golan

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Objects;

/************* Constructor ************************************/
public class Date implements Serializable
/***************************************************************/
{
	int _day;
	String _month;
	int _year;
	
    /************** Constructor ************************************/	
	public Date(int day, String month, int year)
	/**********************************************************/
	{
		_day=day;
		_month=month;
		_year=year;
		 
		
	}
	
	/******* override equals *********************************/
	public boolean equals(Object o)
	/****************************************************/
	{
		
		// If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
        if (o == null)
            return false;
        
        /* Check if o is an instance of Date or not*/
      if (!(o instanceof Date))
      {
          return false;
      }
       
      // typecast o to Date so that we can compare data members 
        Date d = (Date) o;
		
		int x=d.get_day();
		String y=d.get_month();
		int z=d.get_year();
		if((x==_day)&&(Objects.equals(y,_month) )&&(z==_year) )
		{			
		    return true;
		}
		else
		{
			return false;
		}
	}
	
	/******* override hashCode *********************************/
	public int hashCode()
	/**********************************************************/
	{
//		  int result = 17;
//        result = 31 * result + _day;                       <=====
//        result = 31 * result + _month.hashCode();          <=====
//        result = 31 * result + _year;                      <===== ?למה זה לא עבד
		  return Objects.hash(_day, _month, _year);
//        return result;
        
		
	}

	public int get_day() {
		return _day;
	}

	public void set_day(int _day) {
		this._day = _day;
	}

	public String get_month() {
		return _month;
	}

	public void set_month(String _month) {
		this._month = _month;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}

}
