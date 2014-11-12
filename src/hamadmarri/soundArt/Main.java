package hamadmarri.soundArt;

import org.jfugue.*;
//import org.jnativehook.GlobalScreen;
//import org.jnativehook.NativeHookException;
//import org.jnativehook.keyboard.NativeKeyEvent;
//import org.jnativehook.keyboard.NativeKeyListener;

public class Main {

	Player player = new Player();



	public static void main(String[] args) {

		 // "Frere Jacques"
		 Pattern pattern1 = new Pattern("C5q D5q E5q C5q");
		
		 // "Dormez-vous?"
		 Pattern pattern2 = new Pattern("E5q F5q G5h");
		
		 // "Sonnez les matines"
		 Pattern pattern3 = new Pattern("G5i A5i G5i F5i E5q C5q");
		
		 // "Ding ding dong"
		 Pattern pattern4 = new Pattern("C5q G4q C5h");
		
		 // Put all of the patters together to form the song
		 Pattern song = new Pattern();
		 song.add(pattern1, 1); // Adds 'pattern1' to 'song' twice
		 song.add(pattern2, 1); // Adds 'pattern2' to 'song' twice
		 song.add(pattern3, 1); // Adds 'pattern3' to 'song' twice
		 song.add(pattern4, 2); // Adds 'pattern4' to 'song' twice
		
		 // Play the song!
		 Player player = new Player();
//		 player.play(song);

		 player.play("Cmin9 D E F G A B");
	}
}


/*
 * 
 * 
 * Keys:	C	C#/Db	D	D#/Eb	E	F	F#/Gb	G	G#/Ab	A	A#/Bb	B = 12  
 * Octaves:	3 - 7 = 5
 * 
 * 12 * 5 = 60
 */



//	Duration		Character
//	whole			w
//	half			h
//	quarter			q
//	eighth			i
//	sixteenth		s
//	thirty-second 	t
//	sixty-fourth	x
//	one-twenty-eig	o
//	= 8


// nothing
//	maj
//	min
//	aug
//	dim
//	dom7
//	maj7
//	min7
//	sus4
//	sus2
//	maj6
//	min6
//	dom9
//	maj9
//	min9
//	dim7
//	add9
//	min11
//	dom11
//	dom13
//	min13
//	maj13
//	dom7<5
//	dom7>5
//	maj7<5
//	maj7>5
//	minmaj7
//	dom7<5<9
//	dom7<5>9
//	dom7>5<9
//	dom7>5>9 
// = 30

