import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.JRadioButton;
public class UserSelection {
	public static String getUserSelection(JRadioButton opt1,JRadioButton opt2,JRadioButton opt3,JRadioButton opt4)
	{
		String userval=null;
		if(opt1.isSelected())
			userval=opt1.getText();
		else if(opt2.isSelected())
			userval=opt2.getText();
		else if(opt3.isSelected())
			userval=opt3.getText();
		else if(opt4.isSelected())
			userval=opt4.getText();
		return userval;
	}
	public static void setUserSelection(String val,JRadioButton opt1,JRadioButton opt2,JRadioButton opt3,JRadioButton opt4)
	{
		if(val.equals(opt1.getText()))
			opt1.setSelected(true);
		else if(val.equals(opt2.getText()))
			opt2.setSelected(true);
		else if(val.equals(opt3.getText()))
			opt3.setSelected(true);
		else if(val.equals(opt3.getText()))
			opt3.setSelected(true);
	}
	public static void welcomeMessage()
	{
		try
        {
            // set property as Kevin Dictionary
            System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory"); 
                 
            // Register Engine
            Central.registerEngineCentral
                ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
 
            // Create a Synthesizer
            Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));     
     
            // Allocate synthesizer
            synthesizer.allocate();        
             
            // Resume Synthesizer
            synthesizer.resume();    
             
            // speaks the given text until queue is empty.
            synthesizer.speakPlainText("Welcome to digital examination system", null);         
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
             
            // Deallocate the Synthesizer.
            synthesizer.deallocate();                                 
        } 
 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

	}
}
