package hamadmarri.soundArt;

public final class InputStructure {
	// input example C maj 7 w

	public static final int NUMBER_OF_KEYS = 12;
	public static final int NUMBER_OF_CHORDS = 31;
	public static final int NUMBER_OF_OCTAVES = 5;
	public static final int NUMBER_OF_DURATIONS = 8;

	public static final String KEYS[] = { "C", "C#", "D", "D#", "E", "F", "F#",
			"G", "G#", "A", "A#", "B" };

	// Chords
	public static final String CHORDS[] = { "n", "maj", "min", "aug", "dim",
			"dom7", "maj7", "min7", "sus4", "sus2", "maj6", "min6", "dom9",
			"maj9", "min9", "dim7", "add9", "min11", "dom11", "dom13", "min13",
			"maj13", "dom7<5", "dom7>5", "maj7<5", "maj7>5", "minmaj7",
			"dom7<5<9", "dom7<5>9", "dom7>5<9", "dom7>5>9" };

	public static final String OCTAVES[] = { "3", "4", "5", "6", "7" };

	// Duration Character
	// whole w
	// half h
	// quarter q
	// eighth i
	// sixteenth s
	// thirty-second t
	// sixty-fourth x
	// one-twenty-eig o
	// = 8
	public static final String DURATIONS[] = { "w", "h", "q", "i", "s", "t",
			"x", "o" };

	private String key = "C"; // default key is C
	private String chord = "n"; // default value is no chord
	private String octave = "3"; // default octave is 3
	private String duration = "q"; // default duration is quarter



	public InputStructure() {
		super();
	}



	public InputStructure(String key, String chord, String octave,
			String duration) {
		super();
		this.key = key;
		this.chord = chord;
		this.octave = octave;
		this.duration = duration;
	}



	public InputStructure(String input) {
		setAll(input);
	}



	public void setAll(String input) {
		// input example C maj 7 w

		int startPivot = 0;
		int endPivot = input.length();

		// check for key
		if (input.substring(1, 2).equals("#")) {
			this.key = input.substring(0, 2);
			startPivot = 2;
		} else {
			this.key = input.substring(0, 1);
			startPivot = 1;
		}

		// check for duration
		String d = input.substring(endPivot - 1, endPivot);
		for (String c : InputStructure.DURATIONS) {
			if (c.equals(d)) {
				this.duration = String.valueOf(d);
				endPivot--;
			}
		}

		// check for octave
		String o = input.substring(endPivot - 1, endPivot);
		for (String c : InputStructure.OCTAVES) {
			if (c.equals(o)) {
				this.octave = String.valueOf(o);
				endPivot--;
			}
		}

		// check for chrods
		if (!input.substring(startPivot, endPivot).isEmpty())
			this.chord = input.substring(startPivot, endPivot);

	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getChord() {
		return chord;
	}



	public void setChord(String chord) {
		this.chord = chord;
	}



	public String getOctave() {
		return octave;
	}



	public void setOctave(String octave) {
		this.octave = octave;
	}



	public String getDuration() {
		return duration;
	}



	public void setDuration(String duration) {
		this.duration = duration;
	}



	public double normalizeKey(String key) {
		return genericNormalize(key, KEYS, NUMBER_OF_KEYS);
	}



	public double normalizeChord(String chord) {
		return genericNormalize(chord, CHORDS, NUMBER_OF_CHORDS);
	}



	public double normalizeOctave(String octave) {
		return genericNormalize(octave, OCTAVES, NUMBER_OF_OCTAVES);
	}



	public double normalizeDuration(String duration) {
		return genericNormalize(duration, DURATIONS, NUMBER_OF_DURATIONS);
	}



	private double genericNormalize(String s, String array[], int maxNumber) {
		double d = -1;

		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(s))
				d = (double) i;
		}

		return (double) d / (double) (maxNumber - 1);
	}

}
