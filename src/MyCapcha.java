import java.util.LinkedList;
import java.util.Random;
public class MyCapcha
{
	static char[] geek_Password(int len)
    {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = Capital_chars+ Small_chars +numbers;
        Random rndm_method = new Random();
        char[] password = new char[len];
        for (int i = 0; i < len; i++)
        {
            password[i] =values.charAt(rndm_method.nextInt(values.length()));
        }
        return password;
    }
	static String geek_Password(int len,String subject)
    {
        String numbers = "0123456789";
        String values = numbers; 
        Random rndm_method = new Random();
        char[] password = new char[len];
        for (int i = 0; i < len; i++)
        {
            password[i] =values.charAt(rndm_method.nextInt(values.length()));
        }
        String s1=new String(password);
        int val=101+Integer.parseInt(s1);
        return subject+val;
    }
	public static LinkedList<String> getQuestion(String subject)
	{
		int count=0;
		LinkedList<String> l;
		l=new LinkedList<String>();
		for(int i=1;count!=10;i++)
		{
			String s=MyCapcha.geek_Password(1,subject);
			if(l==null)
			{
				l.add(s);
				count++;
			}
			else
			{
				if(l.contains(s))
				{
					continue;
				}
				else
				{
					l.add(s);
					count++;
				}
			}
		}
		return l;
	}
}