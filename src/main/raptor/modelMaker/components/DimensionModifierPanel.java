package raptor.modelMaker.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import raptor.modelMaker.model.Model;

public class DimensionModifierPanel extends JPanel {
	private final DimensionValueListener widthListener;
	private final DimensionValueListener heightListener;
	private final DimensionValueListener centerXListener;
	private final DimensionValueListener centerYListener;

	public DimensionModifierPanel(final Model model, final JComponent redraw) {
		final JTextField widthField = new JTextField();
		widthField.setText(String.valueOf(model.getWidth()));
		this.widthListener = new DimensionValueListener(widthField, new WidthValueChanger(model), redraw);
		widthField.setColumns(6);
		widthField.addKeyListener(widthListener);
		add(widthField);

		final JTextField heightField = new JTextField();
		heightField.setText(String.valueOf(model.getHeight()));
		this.heightListener = new DimensionValueListener(heightField, new HeightValueChanger(model), redraw);
		heightField.setColumns(6);
		heightField.addKeyListener(heightListener);
		add(heightField);

		final JTextField centerOffsetXField = new JTextField();
		centerOffsetXField.setText(String.valueOf(model.getCenterOffsetX()));
		this.centerXListener = new DimensionValueListener(centerOffsetXField, new CenterOffsetXValueChanger(model), redraw);
		centerOffsetXField.setColumns(6);
		centerOffsetXField.addKeyListener(centerXListener);
		add(centerOffsetXField);

		final JTextField centerOffsetYField = new JTextField();
		centerOffsetYField.setText(String.valueOf(model.getCenterOffsetY()));
		this.centerYListener = new DimensionValueListener(centerOffsetYField, new CenterOffsetYValueChanger(model), redraw);
		centerOffsetYField.setColumns(6);
		centerOffsetYField.addKeyListener(centerYListener);
		add(centerOffsetYField);
	}

	public void setModel(final Model model) {
		widthListener.setModel(model);
		heightListener.setModel(model);
	}

	private static abstract class ValueChanger {
		protected Model model;

		public ValueChanger(final Model model) {
			this.model = model;
		}

		public abstract void setValue(final int value);
		public abstract int getValue();

		public void setModel(final Model model) {
			this.model = model;
		}
	}

	private static class DimensionValueListener implements KeyListener {
		private final JTextField field;
		private final ValueChanger valueChanger;
		private final JComponent redraw;

		public DimensionValueListener(final JTextField field, final ValueChanger valueChanger, final JComponent redraw) {
			this.field = field;
			this.valueChanger = valueChanger;
			this.redraw = redraw;
		}

		@Override
		public void keyReleased(final KeyEvent event) {
			Integer value = null;
			try {
				value = Integer.parseInt(field.getText());
			} catch (final Exception e) { /* swallow */ }
			if (value == null)
				return;
			valueChanger.setValue(value);
			redraw.repaint();
		}

		@Override
		public void keyPressed(final KeyEvent event) { /* no-op */ }

		@Override
		public void keyTyped(final KeyEvent event) { /* no-op */ }

		public void setModel(final Model model) {
			valueChanger.setModel(model);
			field.setText(String.valueOf(valueChanger.getValue()));
		}
	}

	private static class WidthValueChanger extends ValueChanger {
		public WidthValueChanger(final Model model) {
			super(model);
		}

		@Override
		public void setValue(final int value) {
			this.model.setWidth(value);
		}

		@Override
		public int getValue() {
			return this.model.getWidth();
		}
	}

	private static class HeightValueChanger extends ValueChanger {
		public HeightValueChanger(final Model model) {
			super(model);
		}

		@Override
		public void setValue(final int value) {
			this.model.setHeight(value);
		}

		@Override
		public int getValue() {
			return this.model.getHeight();
		}
	}

	private static class CenterOffsetXValueChanger extends ValueChanger {
		public CenterOffsetXValueChanger(final Model model) {
			super(model);
		}

		@Override
		public void setValue(final int value) {
			this.model.setCenterOffsetX(value);
		}

		@Override
		public int getValue() {
			return this.model.getCenterOffsetX();
		}
	}

	private static class CenterOffsetYValueChanger extends ValueChanger {
		public CenterOffsetYValueChanger(final Model model) {
			super(model);
		}

		@Override
		public void setValue(final int value) {
			this.model.setCenterOffsetY(value);
		}

		@Override
		public int getValue() {
			return this.model.getCenterOffsetY();
		}
	}
}
