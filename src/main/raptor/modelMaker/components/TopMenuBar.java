package raptor.modelMaker.components;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopMenuBar extends JMenuBar {
	private static final String EXIT_COMMAND = "exit";

	public TopMenuBar() {
		final JMenu fileMenu = new JMenu("File");

		final JMenuItem newButton = new JMenuItem("New");
		final JMenuItem saveButton = new JMenuItem("Save");
		final JMenuItem loadButton = new JMenuItem("Load");

		final JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.setActionCommand(EXIT_COMMAND);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (EXIT_COMMAND.equals(e.getActionCommand())) {
					for (final Frame f : Frame.getFrames()) {
						((JFrame) f).dispose();
					}
				}
			}
		});

		fileMenu.add(newButton);
		fileMenu.add(saveButton);
		fileMenu.add(loadButton);
		fileMenu.add(exitButton);
		this.add(fileMenu);
		this.setVisible(true);
	}
}
