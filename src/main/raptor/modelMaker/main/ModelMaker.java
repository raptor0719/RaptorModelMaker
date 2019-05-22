package raptor.modelMaker.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import raptor.modelMaker.model.WorkingAnimation;
import raptor.modelMaker.model.WorkingFrame;
import raptor.modelMaker.model.WorkingLogicalFrame;
import raptor.modelMaker.model.WorkingModel;
import raptor.modelMaker.model.WorkingWireModel;

public class ModelMaker {
	private WorkingModel model = new WorkingModel(0, "defaultModel");
	private WorkingWireModel wireModel = new WorkingWireModel(0, "defaultWireModel", 8, 8);

	public ModelMaker() {
		model.setWireModel(wireModel);

		// Setup
		final JFrame frame = new JFrame();
		final JPanel panel = new JPanel();

		frame.setSize(1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setLayout(new GridBagLayout());

		// Top Menu Bar
		final JPanel topMenuBarPanel = new JPanel();
		final GridBagConstraints topMenuBarPanel_constraints = new GridBagConstraints();
		topMenuBarPanel_constraints.gridx = 0;
		topMenuBarPanel_constraints.gridy = 0;
		topMenuBarPanel_constraints.gridwidth = 3;
		topMenuBarPanel_constraints.gridheight = 1;
		topMenuBarPanel_constraints.weightx = 0.1;
		topMenuBarPanel_constraints.weighty = 0.0;
		topMenuBarPanel_constraints.fill = GridBagConstraints.BOTH;
		topMenuBarPanel_constraints.anchor = GridBagConstraints.FIRST_LINE_START;

		topMenuBarPanel.setVisible(true);

		panel.add(new TopMenuBar(), topMenuBarPanel_constraints);

		/***********************************
		* Animation Selection Panel
		***********************************/
		final JPanel animationSelectionPanel = new JPanel();
		animationSelectionPanel.setLayout(new BoxLayout(animationSelectionPanel, BoxLayout.Y_AXIS));
		final GridBagConstraints animationSelectionPanel_constraints = new GridBagConstraints();
		animationSelectionPanel_constraints.gridx = 2;
		animationSelectionPanel_constraints.gridy = 1;
		animationSelectionPanel_constraints.gridwidth = 1;
		animationSelectionPanel_constraints.gridheight = 1;
		animationSelectionPanel_constraints.weightx = 0.1;
		animationSelectionPanel_constraints.weighty = 0.0;
		animationSelectionPanel_constraints.fill = GridBagConstraints.BOTH;
		animationSelectionPanel_constraints.anchor = GridBagConstraints.PAGE_START;

		final JComboBox<WorkingAnimation> animationSelection = new JComboBox<>();
		final JTextField newAnimation_idInput = new JTextField();
		final JTextField newAnimation_titleInput = new JTextField();
		final JButton newAnimationInput = new JButton("New Animation");
		final JButton deleteAnimationInput = new JButton("Delete Animation");
		final JButton setAnimationPropsInput = new JButton("Set Properties");

		newAnimationInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				wireModel.addAnimation(Integer.parseInt(newAnimation_idInput.getText()), newAnimation_titleInput.getText());
				animationSelection.setModel(new DefaultComboBoxModel<WorkingAnimation>(wireModel.getAnimations().toArray(new WorkingAnimation[wireModel.getAnimations().size()])));
			}
		});
		deleteAnimationInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wireModel.getAnimations().remove(animationSelection.getSelectedIndex());
				animationSelection.setModel(new DefaultComboBoxModel<WorkingAnimation>(wireModel.getAnimations().toArray(new WorkingAnimation[wireModel.getAnimations().size()])));
			}
		});
		setAnimationPropsInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final WorkingAnimation anim = wireModel.getAnimations().get(animationSelection.getSelectedIndex());
				anim.setId(Integer.parseInt(newAnimation_idInput.getText()));
				anim.setName(newAnimation_titleInput.getText());
			}
		});

		animationSelectionPanel.add(animationSelection);
		animationSelectionPanel.add(newAnimation_idInput);
		animationSelectionPanel.add(newAnimation_titleInput);
		animationSelectionPanel.add(newAnimationInput);
		animationSelectionPanel.add(deleteAnimationInput);
		animationSelectionPanel.add(setAnimationPropsInput);

		animationSelectionPanel.setVisible(true);

		panel.add(animationSelectionPanel, animationSelectionPanel_constraints);

		/********************************/

		/***********************************
		* Frame Selection Panel
		***********************************/
		final JPanel frameSelectionPanel = new JPanel();
		frameSelectionPanel.setLayout(new GridBagLayout());
		final GridBagConstraints frameSelectionPanel_constraints = new GridBagConstraints();
		frameSelectionPanel_constraints.gridx = 2;
		frameSelectionPanel_constraints.gridy = 2;
		frameSelectionPanel_constraints.gridwidth = 1;
		frameSelectionPanel_constraints.gridheight = 1;
		frameSelectionPanel_constraints.weightx = 0.1;
		frameSelectionPanel_constraints.weighty = 0.0;
		frameSelectionPanel_constraints.fill = GridBagConstraints.BOTH;
		frameSelectionPanel_constraints.anchor = GridBagConstraints.PAGE_START;

		final JTable frameSelectionTable = new JTable();
		final JScrollPane frameSelectionTable_scroller = new JScrollPane(frameSelectionTable);
		final JButton frameSelection_setPortionButton = new JButton("Set Portion");
		final JButton frameSelection_setIndexButton = new JButton("Set Index");
		final JTextField frameSelection_numberInput = new JTextField();
		final JButton newFrameInput = new JButton("New Frame");

		frameSelectionTable_scroller.setPreferredSize(new Dimension(100, 100));
		frameSelectionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		frameSelectionTable.setCellSelectionEnabled(false);
		frameSelectionTable.setRowSelectionAllowed(true);

		frameSelectionTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println(frameSelectionTable.getSelectedRow());
			}

		});

		frameSelection_setPortionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final int portion = Integer.parseInt(frameSelection_numberInput.getText());
				if (portion < 0)
					return;
				final WorkingAnimation selected = (WorkingAnimation)animationSelection.getSelectedItem();
				selected.getFrames().get(frameSelectionTable.getSelectedRow()).setPortion(portion);
				frameSelectionTable.setModel(createFrameTableModel(selected));
			}
		});
		frameSelection_setIndexButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final int position = Integer.parseInt(frameSelection_numberInput.getText());
				if (position < 0)
					return;
				final WorkingAnimation selected = (WorkingAnimation)animationSelection.getSelectedItem();
				final WorkingLogicalFrame selectedFrame = selected.getFrames().remove(frameSelectionTable.getSelectedRow());
				if (position < selected.getFrames().size())
					selected.getFrames().add(position, selectedFrame);
				else
					selected.getFrames().add(selectedFrame);
				frameSelectionTable.setModel(createFrameTableModel(selected));
			}
		});
		newFrameInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final WorkingLogicalFrame newFrame = new WorkingLogicalFrame(wireModel.getPointCount(), wireModel.getDirectionCount(), Integer.parseInt(frameSelection_numberInput.getText()));
				final WorkingAnimation selected = (WorkingAnimation)animationSelection.getSelectedItem();
				selected.getFrames().add(newFrame);
				frameSelectionTable.setModel(createFrameTableModel(selected));
				System.out.println(frameSelectionTable.getSelectedRow());
				if (frameSelectionTable.getSelectedRow() < 0)
					frameSelectionTable.setRowSelectionInterval(0, 0);
			}
		});

		frameSelectionPanel.add(frameSelectionTable_scroller, getGridBagConstraints(0, 0, 2, 1, 1, 0.8, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
		frameSelectionPanel.add(frameSelection_numberInput, getGridBagConstraints(0, 1, 2, 1, 0.5, 0.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
		frameSelectionPanel.add(frameSelection_setPortionButton, getGridBagConstraints(0, 2, 2, 1, 0.5, 0.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
		frameSelectionPanel.add(frameSelection_setIndexButton, getGridBagConstraints(0, 3, 2, 1, 0.5, 0.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER));
		frameSelectionPanel.add(newFrameInput, getGridBagConstraints(0, 4, 2, 1, 1, 0.0, GridBagConstraints.BOTH, GridBagConstraints.CENTER));

		frameSelectionPanel.setVisible(true);

		panel.add(frameSelectionPanel, frameSelectionPanel_constraints);

		/********************************/

		/***********************************
		* Point Selection Panel
		***********************************/
		final JPanel pointSelectionPanel = new JPanel();
		pointSelectionPanel.setLayout(new BoxLayout(pointSelectionPanel, BoxLayout.Y_AXIS));
		final GridBagConstraints pointSelectionPanel_constraints = new GridBagConstraints();
		pointSelectionPanel_constraints.gridx = 1;
		pointSelectionPanel_constraints.gridy = 1;
		pointSelectionPanel_constraints.gridwidth = 1;
		pointSelectionPanel_constraints.gridheight = 2;
		pointSelectionPanel_constraints.weightx = 0.1;
		pointSelectionPanel_constraints.weighty = 0.7;
		pointSelectionPanel_constraints.fill = GridBagConstraints.BOTH;
		pointSelectionPanel_constraints.anchor = GridBagConstraints.PAGE_START;

		final JLabel pointSelectionHeader = new JLabel("Points");
		final JTable pointSelectionTable = new JTable();
		final JScrollPane pointSelectionTable_scroller = new JScrollPane(pointSelectionTable);

		pointSelectionTable_scroller.setPreferredSize(new Dimension(100, 100));
		pointSelectionTable_scroller.setBorder(BorderFactory.createEmptyBorder());



		pointSelectionPanel.add(pointSelectionHeader);
		pointSelectionPanel.add(pointSelectionTable_scroller);

		pointSelectionPanel.setVisible(true);

		panel.add(pointSelectionPanel, pointSelectionPanel_constraints);

		/********************************/

		// Frame Modifier
		final JPanel frameModifierPanel = new JPanel();
		final GridBagConstraints frameModifierPanel_constraints = new GridBagConstraints();
		frameModifierPanel_constraints.gridx = 0;
		frameModifierPanel_constraints.gridy = 1;
		frameModifierPanel_constraints.gridwidth = 1;
		frameModifierPanel_constraints.gridheight = 2;
		frameModifierPanel_constraints.weightx = 1.0;
		frameModifierPanel_constraints.weighty = 1.0;
		frameModifierPanel_constraints.fill = GridBagConstraints.BOTH;
		frameModifierPanel_constraints.anchor = GridBagConstraints.CENTER;

		frameModifierPanel.setBackground(Color.white);
		frameModifierPanel.setVisible(true);

		panel.add(frameModifierPanel, frameModifierPanel_constraints);

		// Controls
		final JPanel controlsPanel = new JPanel();
		final GridBagConstraints controlsPanel_constraints = new GridBagConstraints();
		controlsPanel_constraints.gridx = 0;
		controlsPanel_constraints.gridy = 3;
		controlsPanel_constraints.gridwidth = 1;
		controlsPanel_constraints.gridheight = 2;
		controlsPanel_constraints.weightx = 0.0;
		controlsPanel_constraints.weighty = 0.0;
		controlsPanel_constraints.fill = GridBagConstraints.BOTH;
		controlsPanel_constraints.anchor = GridBagConstraints.CENTER;

		final JSlider framesSlider = new JSlider();
		final JSlider directionSlider = new JSlider();
		final JTextField frameCountInput = new JTextField();
		final JButton playButton = new JButton("Play");

		controlsPanel.add(framesSlider);
		controlsPanel.add(directionSlider);
		controlsPanel.add(frameCountInput);
		controlsPanel.add(playButton);

		controlsPanel.setVisible(true);

		panel.add(controlsPanel, controlsPanel_constraints);

		// Final
		frame.add(panel);
		panel.setVisible(true);
		frame.setVisible(true);
	}

	private GridBagConstraints getGridBagConstraints(
			final int gridx, final int gridy, final int gridwidth, final int gridheight,
			final double weightx, final double weighty, final int fill, final int anchor) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.fill = fill;
		constraints.anchor = anchor;
		return constraints;
	}

	private TableModel createFrameTableModel(final WorkingAnimation animation) {
		final String[] colNames = new String[] {"Frame", "Portion"};
		return new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return colNames.length;
			}

			@Override
			public int getRowCount() {
				return animation.getFrames().size();
			}

			@Override
			public Object getValueAt(final int row, final int col) {
				if (row < getRowCount() && row >= 0 && col <  getColumnCount() && col >=0) {
					return (col == 0) ? new Integer(row) : animation.getFrames().get(row).getPortion();
				} else {
					return null;
				}
			}

			@Override
			public boolean isCellEditable(final int row, final int col) {
				return false;
			}

			@Override
			public String getColumnName(final int col) {
				return colNames[col];
			}
		};
	}

	private TableModel createPointTableModel(final WorkingFrame frame) {
		final String[] colNames = new String[] {"index", "x", "y", "z", "rot"};
		return new AbstractTableModel() {
			@Override
			public int getColumnCount() {
				return colNames.length;
			}

			@Override
			public int getRowCount() {
				return frame.getPoints().length;
			}

			@Override
			public Object getValueAt(final int row, int col) {
				if (row < getRowCount() && row >= 0 && col <  getColumnCount() && col >=0) {
					switch (col) {
						case 0:
							return row;
						case 1:
							return frame.getPoints()[row].getX();
						case 2:
							return frame.getPoints()[row].getY();
						case 3:
							return frame.getPoints()[row].getZ();
						case 4:
							return frame.getPoints()[row].getRot();
						default:
							return null;
					}
				} else {
					return null;
				}
			}

			@Override
			public boolean isCellEditable(final int row, final int col) {
				return false;
			}

			@Override
			public String getColumnName(final int col) {
				return colNames[col];
			}
		};
	}
}
