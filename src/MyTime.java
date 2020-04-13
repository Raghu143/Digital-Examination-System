class MyTime extends Thread
{
	int time;
	int i;
	public static boolean flag=true;
	MyTime(int t)
	{
		time=t;
	}
	public void run()
	{
		String m,s;
		i=time;
		while(true)
		{
			i--;
			if(flag==false)
				break;
			for(int j=59;j>=0;j--)
			{
				if(flag==false)
					break;
					if(i<10)
						m="0"+i;
					else
						m=""+i;
					if(j<10)
						s="0"+j;
					else
						s=""+j;
					String ss="00"+" : "+m+" : "+s;
					if(ss.equals("00 : 00 : 00"))
					{
						Exam.lblTime.setText("Time Up");
						new Logout().setVisible(true);
						
					}	
					else
					{
						Exam.lblTime.setText(ss);
					}
						
					try
					{
						Thread.currentThread().sleep(1000);
					}
					catch (Exception e)
					{
					}
					
				}
			}
	}
}