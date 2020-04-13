import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeDate extends Thread {
	static void getTime()
	{
		Thread t=new Thread()
		{
			public void run()
			{
				try {
					for(;;)
					{
						Calendar cal=new GregorianCalendar();
						int day=cal.get(Calendar.DAY_OF_MONTH);
						int month=cal.get(Calendar.MONTH);
						int year=cal.get(Calendar.YEAR);
						int sec=cal.get(Calendar.SECOND);
						int min=cal.get(Calendar.MINUTE);
						int hour=cal.get(Calendar.HOUR);
						month++;
						String mm="",ss="",m="",h="",d="";
						if(month<10)
							mm="0"+month;
						else
							mm=""+month;
						if(sec<10)
							ss="0"+sec;
						else
							ss=""+sec;
						if(min<10)
							m="0"+min;
						else
							m=""+min;
						if(hour<10)
							h="0"+hour;
						else
							h=""+hour;
						if(day<10)
							d="0"+day;
						else
							d=""+day;
						String s="Time - "+h+": "+m+": "+ss+", Date - "+d+"/ "+mm+"/ "+year;
						Admin.lblTimeDate.setText(s);
						sleep(1000);
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};	
		t.start();
	}	
}