package hamadmarri.soundArt;

import hamadmarri.ANN.NeuralNetwork;
import hamadmarri.ANN.Trainers.SoundArtTrainer;
import hamadmarri.ANN.Trainers.Trainer;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfugue.Player;

public class Main extends JFrame {

	private static final long serialVersionUID = -662545984403045155L;

	private static NeuralNetwork nw;
	private static InputStructure is = new InputStructure();
	private final Player player = new Player();



	public Main() throws HeadlessException {
		JPanel panel = new JPanel();
		final JTextArea text = new JTextArea(30, 50);
		final JTextArea setting = new JTextArea(40, 20);
		JButton configure = new JButton("Configure");
		JButton play = new JButton("Play");
		JScrollPane settingTable = new JScrollPane(setting);

		text.setText("She's playing the news on her piano\nSomewhat drunken fingers moving a little slow\n"
				+ "Don't call her on it, she'll just cry a bit\nSaid she liked it with your hand on her shoulder\n"
				+ "Said it made her friends seem a little colder\nShe likes to think that you're all she's got\n"
				+ "All she's got\n\nShe's got church hymns on her lips\n"
				+ "Small hands play me Fur Elise\nApologizes when she slips\nShe's just damned cute when she drinks\n\n"
				+ "Swears I'm her best friend in the world\nWants to know if I like this kind of girl\n"
				+ "Wants me to at least stay the night\n\nYou know I've got things to attend to\n"
				+ "Places to see and people to use\nBut I guess I ought to stay if I'm all she's got\nAll she's got\n\n"
				+ "She's got church hymns on her lips\nSmall hands play me Fur Elise\nApologizes when she slips"
				+ "She's just damned cute when she drinks\n\nShe told me that she liked my songs\n"
				+ "And that she wanted the world to sing along\nShe prayed maybe I wouldn't leave\n"
				+ "Wish I could change my mind, wish this all could be\n\n"
				+ "She's got church hymns on her lips\nSmall hands play me Fur Elise\nApologizes when she slips\n"
				+ "She's just damned cute when she drinks\n\n"
				+ "She's got church hymns on her lips\nSmall hands play me Fur Elise\nApologizes when she slips\n"
				+ "She's just damned cute when she drinks");

		setting.setText("She A5s\nyour B5s\ngot C5q\nme D5q\nhymns E5s\nher F5s\nlips G#5q");

		configure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = setting.getText();
				String outputPath = ".";
				int[] config = new int[] { 10, 15, 4 };
				nw = new NeuralNetwork(config, 0.79, 0.15);
				Trainer trainer = new SoundArtTrainer(input, outputPath, nw,
						100000, false, false);

				trainer.train();
			}
		});

		/*
		 * 
		 * Hamad C Salim D4w Almarri A#7s perfect F#5q
		 */
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String inputText = text.getText();
				String input;
				Scanner scn = new Scanner(inputText);
				StringBuilder sb = new StringBuilder("");
				double r[];

				while (scn.hasNext()) {
					double inputArray[] = new double[10];
					input = scn.next();

					// intput text
					for (int i = 0, j = 0; i < input.length(); i++, j++) {
						if (!Character.isLetter(input.charAt(i))) {
							j--;
							continue;
						}

						if (i >= inputArray.length)
							break;

						byte b = input.substring(i, i + 1).getBytes()[0];
						inputArray[j] = Double.parseDouble(Byte.toString(b)) / 255d;
					}

					nw.setInputValues(inputArray);
					nw.feedForward();
					r = nw.getResults();

					sb.append(is.denormalizeKey(r[0]))
							.append(is.denormalizeChord(r[1]))
							.append(is.denormalizeOctave(r[2]))
							.append(is.denormalizeDuration(r[3])).append(" ");

				} // while

				System.out.println(sb.toString());
				player.play(sb.toString());
			}
		});

		panel.add(text);
		panel.add(settingTable);
		panel.add(configure);
		panel.add(play);

		this.add(panel);

		this.setTitle("Text to Sound");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}



	public static void main(String[] args) {

		Main m = new Main();

		/*
		 * // "Frere Jacques" Pattern pattern1 = new Pattern("C5q D5q E5q C5q");
		 * 
		 * // "Dormez-vous?" Pattern pattern2 = new Pattern("E5q F5q G5h");
		 * 
		 * // "Sonnez les matines" Pattern pattern3 = new
		 * Pattern("G5i A5i G5i F5i E5q C5q");
		 * 
		 * // "Ding ding dong" Pattern pattern4 = new Pattern("C5q G4q C5h");
		 * 
		 * // Put all of the patters together to form the song Pattern song =
		 * new Pattern(); song.add(pattern1, 1); // Adds 'pattern1' to 'song'
		 * twice song.add(pattern2, 1); // Adds 'pattern2' to 'song' twice
		 * song.add(pattern3, 1); // Adds 'pattern3' to 'song' twice
		 * song.add(pattern4, 2); // Adds 'pattern4' to 'song' twice
		 * 
		 * // Play the song! Player player = new Player(); // player.play(song);
		 * 
		 * player.play("Cmaj7w D E F G A B");
		 */
	}
}
