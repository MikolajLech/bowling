package bowling;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Iterables;

public class BowlingGameResultCalculatorMikolajImpl implements BowlingGameResultCalculator {
	List<Round> mRounds = new ArrayList<Round>();

	@Override
	public void roll(int numberOfPins) { // filling game with roll data
		addRollToGame(new Roll(numberOfPins)); /* create new round or add roll to old round
											or new one in function getRound() */
	}
	@Override
	public int score() { // evaluating game score
		int score = 0;
		for(Round round : mRounds) {
			for(Roll roll : round) { // implemented iterable<round>
				score += roll.get() + spareBonus(round, roll) + strikeBonus(round, roll);
			}
		}
		return score;
	}
	@Override
	public void prtGame() {
		for(Round round : mRounds) {
			for(Roll roll : round) {
				System.out.print(roll.get());
			}
			System.out.println();
		}
	}
	@Override
	public boolean isFinished() {
		return isLastRoundOfGame();
	}
	private int spareBonus(Round round, Roll roll) {
		if (ifSecondRoundOrHigher(round) && ifEarlierRoundHadSpare(round)
				&& ifFirstRollInRound(round, roll)) // add spare bonus only for
									// the first roll in the
									// next round, and start
									// from second round
			return roll.get();
		return 0;
	}
	private boolean ifFirstRollInRound(Round round, Roll roll) {
		return round.indexOf(roll) == 0;
	}
	private int strikeBonus(Round round, Roll roll) {
		if(ifEalierRoundHadStrike(round)) { 
			if(ifEalierEalierRoundHadStrike(round)) 
				return roll.get() * 2; // [10; 10; 2, 4] -> bonus for roll 2 = 2 + 2
			return roll.get(); // [10; 2, 4] -> bonus for roll 2 = 2
		}
		return 0;		
	}	
	private void addRollToGame(Roll roll) {
		if(isLastRoundOfGame())
			addBonusesToLastRoundOfGame(roll);
		else {
			Round round = getRound();
			round.add(roll);
		}  
	}
    private Round getRound() {
    	Round lastRound = Iterables.getLast(mRounds, null);
    	if(lastRound == null || lastRound.isOver()) { // if last round is over or it is the first 
    													//round create and return new round
    		Round newRound = new Round();
    		mRounds.add(newRound);
    		return newRound;
    	}
    	return lastRound; // if the round is not finished return it further rolls
    }
	private void addBonusesToLastRoundOfGame(Roll roll) {
		Round lastRound = Iterables.getLast(mRounds);
		if((lastRound.ifStrike() && alreadyCountedBonuses(lastRound)) 
				|| (lastRound.ifSpare() && alreadyCountedBonuses(lastRound)))
			lastRound.add(roll); // if last round has strike or spare and player still have bonus throws to use
	}
	private boolean alreadyCountedBonuses(Round lastRound) { /* if the size of last round is 3 it means
														we've already counted bonuses*/
		return lastRound.getSize() < 4;
	}
	private boolean ifSecondRoundOrHigher(Round round) {
		return mRounds.indexOf(round) >= 1;
	}	
	private boolean ifThirdRoundOrHigher(Round round) {
		return mRounds.indexOf(round) >= 2;
	}
	private boolean isLastRoundOfGame() {
		return mRounds.size() == 10; // game has 10 rounds, don't add further rolls
	}
	private boolean ifEarlierRoundHadSpare(Round round) { 
		return mRounds.get(mRounds.indexOf(round)-1).ifSpare();
	}
	private boolean ifEalierRoundHadStrike(Round round) {
		return ifSecondRoundOrHigher(round) && mRounds.get(mRounds.indexOf(round)-1).ifStrike();
	}
	private boolean ifEalierEalierRoundHadStrike(Round round) {
		return ifThirdRoundOrHigher(round) && mRounds.get(mRounds.indexOf(round)-2).ifStrike();		
	}
}
