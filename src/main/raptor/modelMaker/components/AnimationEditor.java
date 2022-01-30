package raptor.modelMaker.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataListener;

import raptor.modelMaker.model.Animation;
import raptor.modelMaker.model.Frame;
import raptor.modelMaker.model.Model;

public class AnimationEditor extends JPanel {
	private final JLabel animationName;
	private final AnimationEditorTable animationTable;
	private final JComboBox<Frame> frameSelection;

	private Animation selectedAnimation;

	public AnimationEditor(final Model model, final Animation selectedAnimation) {
		super();

		this.selectedAnimation = selectedAnimation;

		this.setLayout(new GridBagLayout());

		this.animationName = new JLabel((selectedAnimation == null) ? "" : selectedAnimation.getName());
		animationName.setFont(new Font("Arial", Font.BOLD, 18));
		final GridBagConstraints animationName_constraints = new GridBagConstraints();
		animationName_constraints.gridx = 0;
		animationName_constraints.gridy = 0;
		animationName_constraints.gridwidth = 4;
		animationName_constraints.gridheight = 2;
		animationName_constraints.weightx = 1.0;
		animationName_constraints.weighty = 0.0;
		animationName_constraints.fill = GridBagConstraints.BOTH;
		animationName_constraints.anchor = GridBagConstraints.CENTER;
		animationName_constraints.insets = new Insets(3, 3, 3, 3);
		animationName.setVisible(true);

		add(animationName, animationName_constraints);

		this.animationTable = new AnimationEditorTable(selectedAnimation);
		final JScrollPane animationTableScroller = new JScrollPane(animationTable);
		final GridBagConstraints animationTableScroller_constraints = new GridBagConstraints();
		animationTableScroller_constraints.gridx = 0;
		animationTableScroller_constraints.gridy = 2;
		animationTableScroller_constraints.gridwidth = 6;
		animationTableScroller_constraints.gridheight = 16;
		animationTableScroller_constraints.weightx = 1.0;
		animationTableScroller_constraints.weighty = 1.0;
		animationTableScroller_constraints.fill = GridBagConstraints.BOTH;
		animationTableScroller_constraints.anchor = GridBagConstraints.CENTER;
		animationTableScroller_constraints.insets = new Insets(3, 3, 3, 3);
		animationTableScroller.setVisible(true);

		add(animationTableScroller, animationTableScroller_constraints);

		this.frameSelection = new JComboBox<Frame>();
		frameSelection.setModel(new FrameSelectionComboBoxModel(model));
		final GridBagConstraints frameSelection_constraints = new GridBagConstraints();
		frameSelection_constraints.gridx = 3;
		frameSelection_constraints.gridy = 18;
		frameSelection_constraints.gridwidth = 2;
		frameSelection_constraints.gridheight = 2;
		frameSelection_constraints.weightx = 0.0;
		frameSelection_constraints.weighty = 0.0;
		frameSelection_constraints.fill = GridBagConstraints.NONE;
		frameSelection_constraints.anchor = GridBagConstraints.EAST;
		frameSelection_constraints.insets = new Insets(3, 3, 3, 3);
		frameSelection.setVisible(true);

		add(frameSelection, frameSelection_constraints);

		final JButton addFrameToAnimationButton = new JButton("Add");
		addFrameToAnimationButton.addActionListener(new AddFrameToAnimationButtonActionListener(frameSelection));
		final GridBagConstraints addFrameToAnimationButton_constraints = new GridBagConstraints();
		addFrameToAnimationButton_constraints.gridx = 5;
		addFrameToAnimationButton_constraints.gridy = 18;
		addFrameToAnimationButton_constraints.gridwidth = 1;
		addFrameToAnimationButton_constraints.gridheight = 2;
		addFrameToAnimationButton_constraints.weightx = 0.0;
		addFrameToAnimationButton_constraints.weighty = 0.0;
		addFrameToAnimationButton_constraints.fill = GridBagConstraints.NONE;
		addFrameToAnimationButton_constraints.anchor = GridBagConstraints.CENTER;
		addFrameToAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		addFrameToAnimationButton.setVisible(true);

		add(addFrameToAnimationButton, addFrameToAnimationButton_constraints);

		final JButton removeFrameFromAnimationButton = new JButton("Remove");
		removeFrameFromAnimationButton.addActionListener(new RemoveFrameFromAnimationButtonActionListener());
		final GridBagConstraints removeFrameFromAnimationButton_constraints = new GridBagConstraints();
		removeFrameFromAnimationButton_constraints.gridx = 0;
		removeFrameFromAnimationButton_constraints.gridy = 18;
		removeFrameFromAnimationButton_constraints.gridwidth = 1;
		removeFrameFromAnimationButton_constraints.gridheight = 2;
		removeFrameFromAnimationButton_constraints.weightx = 0.0;
		removeFrameFromAnimationButton_constraints.weighty = 0.0;
		removeFrameFromAnimationButton_constraints.fill = GridBagConstraints.NONE;
		removeFrameFromAnimationButton_constraints.anchor = GridBagConstraints.CENTER;
		removeFrameFromAnimationButton_constraints.insets = new Insets(3, 3, 3, 3);
		removeFrameFromAnimationButton.setVisible(true);

		add(removeFrameFromAnimationButton, removeFrameFromAnimationButton_constraints);

		final JButton shiftFrameUpButton = new JButton("Up");
		shiftFrameUpButton.addActionListener(new ShiftFrameButtonActionListener(false));
		final GridBagConstraints shiftFrameUpButton_constraints = new GridBagConstraints();
		shiftFrameUpButton_constraints.gridx = 4;
		shiftFrameUpButton_constraints.gridy = 0;
		shiftFrameUpButton_constraints.gridwidth = 1;
		shiftFrameUpButton_constraints.gridheight = 2;
		shiftFrameUpButton_constraints.weightx = 0.0;
		shiftFrameUpButton_constraints.weighty = 0.0;
		shiftFrameUpButton_constraints.fill = GridBagConstraints.NONE;
		shiftFrameUpButton_constraints.anchor = GridBagConstraints.EAST;
		shiftFrameUpButton_constraints.insets = new Insets(3, 3, 3, 3);
		shiftFrameUpButton.setVisible(true);

		add(shiftFrameUpButton, shiftFrameUpButton_constraints);

		final JButton shiftFrameDownButton = new JButton("Down");
		shiftFrameDownButton.addActionListener(new ShiftFrameButtonActionListener(true));
		final GridBagConstraints shiftFrameDownButton_constraints = new GridBagConstraints();
		shiftFrameDownButton_constraints.gridx = 5;
		shiftFrameDownButton_constraints.gridy = 0;
		shiftFrameDownButton_constraints.gridwidth = 1;
		shiftFrameDownButton_constraints.gridheight = 2;
		shiftFrameDownButton_constraints.weightx = 0.0;
		shiftFrameDownButton_constraints.weighty = 0.0;
		shiftFrameDownButton_constraints.fill = GridBagConstraints.NONE;
		shiftFrameDownButton_constraints.anchor = GridBagConstraints.EAST;
		shiftFrameDownButton_constraints.insets = new Insets(3, 3, 3, 3);
		shiftFrameDownButton.setVisible(true);

		add(shiftFrameDownButton, shiftFrameDownButton_constraints);
	}

	public void setAnimation(final Animation selectedAnimation) {
		this.selectedAnimation = selectedAnimation;
		refresh();
	}

	public void setModel(final Model model, final Animation selectedAnimation) {
		this.selectedAnimation = selectedAnimation;
		frameSelection.setModel(new FrameSelectionComboBoxModel(model));
		refresh();
	}

	public void refresh() {
		animationName.setText((selectedAnimation == null) ? "" : selectedAnimation.getName());
		animationTable.setAnimation(selectedAnimation);
	}

	public void setSelectedFrameTableIndex(final int index) {
		animationTable.setSelectedIndex(index);
	}

	private class AddFrameToAnimationButtonActionListener implements ActionListener {
		private final JComboBox<Frame> frameSelection;

		public AddFrameToAnimationButtonActionListener(final JComboBox<Frame> frameSelection) {
			this.frameSelection = frameSelection;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (selectedAnimation == null)
				return;

			final Frame selectedFrame = (Frame)frameSelection.getSelectedItem();

			if (selectedFrame == null)
				return;

			selectedAnimation.addFrame(selectedFrame.getName(), 1);

			animationTable.animationChanged();
			animationTable.setRowSelectionInterval(selectedAnimation.size()-1, selectedAnimation.size()-1);
		}
	}

	private class RemoveFrameFromAnimationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent event) {
			if (selectedAnimation == null)
				return;

			final int selectedIndex = animationTable.getSelectedRow();

			if (selectedIndex < 0)
				return;

			selectedAnimation.removeFrame(selectedIndex);

			animationTable.animationChanged();
		}
	}

	private class ShiftFrameButtonActionListener implements ActionListener {
		private final boolean shiftDown;

		public ShiftFrameButtonActionListener(final boolean shiftDown) {
			this.shiftDown = shiftDown;
		}

		@Override
		public void actionPerformed(final ActionEvent event) {
			if (selectedAnimation == null)
				return;

			final int selectedIndex = animationTable.getSelectedRow();

			if (selectedIndex < 0)
				return;

			final int newIndex = (shiftDown) ? selectedAnimation.shiftFrameDown(selectedIndex) : selectedAnimation.shiftFrameUp(selectedIndex);

			animationTable.animationChanged();
			animationTable.setRowSelectionInterval(newIndex, newIndex);
		}
	}

	private static class FrameSelectionComboBoxModel implements ComboBoxModel<Frame> {
		private Model model;
		private Frame selected;

		public FrameSelectionComboBoxModel(final Model model) {
			this.model = model;
			this.selected = model.getFrames().get(0);
		}

		@Override
		public Frame getElementAt(final int index) {
			return model.getFrames().get(index);
		}

		@Override
		public int getSize() {
			return model.getFrames().size();
		}

		@Override
		public Object getSelectedItem() {
			return selected;
		}

		@Override
		public void setSelectedItem(final Object frame) {
			if (!(frame instanceof Frame))
				return;
			final Frame casted = (Frame)frame;
			this.selected = casted;
		}

		@Override
		public void addListDataListener(final ListDataListener listDataListener) {
			return;
		}

		@Override
		public void removeListDataListener(final ListDataListener listDataListener) {
			return;
		}
	}
}
