package objects;

/**
 * @author Administrator
 *
 *         count the times a property has been mentioned from the data & the
 *         value assigned to that property by the user.
 */
public class CountNValue {

	private int propertyCount = 0;
	private double valueSum = 0;

	public CountNValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addValCount(double userVal) {
		propertyCount++;
		valueSum += userVal;

	}

	public int getPropertyCount() {
		return propertyCount;
	}

	// public void setPropertyCount(int propertyCount) {
	// this.propertyCount = propertyCount;
	// }

	public double getValueSum() {
		return valueSum;
	}

	// public void setValueSum(double valueSum) {
	// this.valueSum = valueSum;
	// }

	public double properyAvgPoint() {
		return valueSum / propertyCount;
	}

}
