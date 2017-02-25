package application;

/**
 * Class for displaying an item in the ComboBox.
 */
public class ComboBoxItem<T> {
	private String label;
	private T value;
	
	public ComboBoxItem(String label, T value) {
		super();
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return label;
	}
}
