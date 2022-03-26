package raptor.modelMaker.components;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import raptor.modelMaker.main.ModelMaker;
import raptor.modelMaker.model.Model;
import raptor.modelMaker.model.io.ModelReader;
import raptor.modelMaker.model.io.ModelWriter;

public class TopMenuBar extends JMenuBar {
	private static final String EXIT_COMMAND = "exit";

	public TopMenuBar(final ModelMaker modelMaker) {
		final JMenu fileMenu = new JMenu("File");

		final JMenuItem newButton = new JMenuItem("New");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final String name = JOptionPane.showInputDialog(ModelMaker.getParentFrame(), "Model name:");

				if (name == null || name.isEmpty())
					return;

				modelMaker.setup(new Model(name));
			}
		});

		final JMenuItem saveButton = new JMenuItem("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final Model model = modelMaker.getModel();

				if (model == null)
					return;

				final FileNameExtensionFilter fileExtensionFilter = new FileNameExtensionFilter("Model Maker File", ModelWriter.MODEL_FILE_EXTENSION);
				final JFileChooser fileChooser = new JFileChooser(new File(ModelMaker.getConfiguration().getFileChooserHomePath()));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(fileExtensionFilter);

				int fileChooserReturn = JFileChooser.ERROR_OPTION;

				while (fileChooserReturn != JFileChooser.APPROVE_OPTION) {
					fileChooserReturn = fileChooser.showDialog(ModelMaker.getParentFrame(), "Save");

					if (fileChooserReturn == JFileChooser.CANCEL_OPTION)
						return;
				}

				final File directory = fileChooser.getSelectedFile();
				final File file = new File(directory.getPath() + File.separator + model.getName() + "." + ModelWriter.MODEL_FILE_EXTENSION);

				OutputStream ostream = null;
				try {
					ostream = new FileOutputStream(file);

					ModelWriter.write(model, ostream);
				} catch (final Throwable t) {
					JOptionPane.showMessageDialog(ModelMaker.getParentFrame(), "Error saving file: " + t.getMessage());
					t.printStackTrace();
				} finally {
					try {
						if (ostream != null)
							ostream.close();
					} catch (final Throwable t) {
						// swallow
					}
				}
			}
		});

		final JMenuItem loadButton = new JMenuItem("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final FileNameExtensionFilter fileExtensionFilter = new FileNameExtensionFilter("Model Maker File", ModelWriter.MODEL_FILE_EXTENSION);
				final JFileChooser fileChooser = new JFileChooser(new File(ModelMaker.getConfiguration().getFileChooserHomePath()));
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(fileExtensionFilter);

				int fileChooserReturn = JFileChooser.ERROR_OPTION;

				while (fileChooserReturn != JFileChooser.APPROVE_OPTION) {
					fileChooserReturn = fileChooser.showDialog(ModelMaker.getParentFrame(), "Load");

					if (fileChooserReturn == JFileChooser.CANCEL_OPTION)
						return;
				}

				final File file = fileChooser.getSelectedFile();

				Model model = null;

				InputStream istream = null;
				try {
					istream = new FileInputStream(file);

					model = ModelReader.read(istream);
				} catch (final Throwable t) {
					JOptionPane.showMessageDialog(ModelMaker.getParentFrame(), "Error loading file: " + t.getMessage());
					t.printStackTrace();
				} finally {
					try {
						if (istream != null)
							istream.close();
					} catch (final Throwable t) {
						// swallow
					}
				}

				if (model != null)
					modelMaker.setup(model);
			}
		});

		final JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.setActionCommand(EXIT_COMMAND);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (EXIT_COMMAND.equals(e.getActionCommand())) {
					for (final Frame f : Frame.getFrames()) {
						try {
							((JFrame) f).dispose();
						} catch (final Throwable t) {
							// swallow
						}
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
