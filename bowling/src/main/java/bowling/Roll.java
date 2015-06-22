package bowling;

public class Roll{
	private int mValue;
	Roll(int numberOfPins) {
		mValue = numberOfPins;
	}
	public void set(int value) {
		mValue = value;
	}
	public int get() {
		return mValue;
	}
}
