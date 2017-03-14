package Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private Clip c;
	
	public Sound(String son){
		try {
			AudioInputStream ai = AudioSystem.getAudioInputStream(getClass().getResource(son));
			c = AudioSystem.getClip();
			c.open(ai);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void play(){
		c.start();
	}
	public void stop(){
		c.stop();
	}
	public Clip getClip(){
		return c;
	}
	public static void playTempSound(String son){
		Sound s = new Sound(son);
		s.play();
	}
}