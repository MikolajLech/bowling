package bowling;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Round implements Iterable<Roll> {
	List<Roll> mRolls = new ArrayList<Roll>();
	
	public void setRoll(int index, Roll value) {
		mRolls.set(index, value);
	}
	public Roll getRoll(int index) {
		return mRolls.get(index);
	}
	public void add(Roll roll) {
		mRolls.add(roll);
	}
    public Iterator<Roll> iterator() {
    	Iterator<Roll> iterRoll = mRolls.iterator();
        return iterRoll; 
    }
    public int getRollVal(int index) {
    	return mRolls.get(index).get();
    }
    public int getSize() {
    	return mRolls.size();
    }
	public boolean ifStrike() {
		return ifOneRollInRound() && mRolls.get(0).get() == 10; // if there is no roll or more than one roll in round it can't be a strike
	}
	public int indexOf(Roll roll) {
		return mRolls.indexOf(roll);		
	}
	public boolean ifSpare() {
		return (ifTwoRollsInRound() && getRollVal(0) + getRollVal(1) == 10);  // if there are no two rolls in round there can't be a spare, go this check to avoid out of bounds in next condition
	}
	public boolean isOver() {
		return ifStrike() || ifTwoRollsInRound();
	}
	private boolean ifOneRollInRound() {
		return mRolls.size() == 1;
	}
	private boolean ifTwoRollsInRound() {
		return mRolls.size() == 2;
	}
}
