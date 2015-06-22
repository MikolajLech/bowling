package bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BowlingGameCalculatorTestMikolajImpl {
	@Before 
	public void newCalc() {
		calc = new BowlingGameResultCalculatorMikolajImpl();
	}
	@Test
	public void shouldReturn1For1() {
		// given
		int pins = 1;
		// when
		calc = new BowlingGameResultCalculatorMikolajImpl();
		calc.roll(pins);
		int score = calc.score();
		// then
		assertEquals(1, score);
	}
	@Test
	public void shouldReturn5For5() {
		calc.roll(5);
		assertEquals(5, calc.score());
	}
	@Test
	public void shouldReturn6For51() {
		initCalc(new ArrayList<Integer>(Arrays.asList(5, 1)));
		assertEquals(6, calc.score());
	}
	@Test
	public void shouldReturn12For821() { // one spare
		initCalc(new ArrayList<Integer>(Arrays.asList(8, 2, 1)));
		assertEquals(12, calc.score());
	}
	@Test
	public void shouldReturn16For1825() { // no spare, no strike
		initCalc(new ArrayList<Integer>(Arrays.asList(1, 8, 2, 5)));
		assertEquals(16, calc.score());
	}
	@Test
	public void shouldReturn24For1052() { // one strike
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 5, 2)));
		assertEquals(24, calc.score());
	}
	@Test
	public void shouldReturn32For10528() { // one strike
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 5, 2, 8)));
		assertEquals(32, calc.score());
	}
	@Test
	public void shouldReturn23For5545() { // one spare
		initCalc(new ArrayList<Integer>(Arrays.asList(5, 5, 4, 5)));
		assertEquals(23, calc.score());
	}
	@Test
	public void shouldReturn53For5410642() { // one strike, one spares
		initCalc(new ArrayList<Integer>(Arrays.asList(5, 4, 10, 6, 4, 2)));
		calc.prtGame();
		assertEquals(43, calc.score());
	}
	@Test
	public void shouldReturn43For7310612() { // one strike, one spare
		initCalc(new ArrayList<Integer>(Arrays.asList(7, 3, 10, 6, 1, 2)));
		assertEquals(46, calc.score());
	}
	@Test
	public void shouldReturn62For10461051() { // two strikes, one spare
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 4, 6, 10, 5, 1)));
		assertEquals(62, calc.score());
	}
	@Test
	public void shouldReturn73For104610101() { // three strikes one spare
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 4, 6, 10, 10, 1)));
		assertEquals(73, calc.score());
	}
	@Test
	public void shouldReturn90For10101010() { // all strikes
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10)));
		assertEquals(90, calc.score());
	}
	@Test
	public void shouldReturn90For1001045() { // strikes and one 0 and 10 in one round
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 0, 10, 4, 5)));
		assertEquals(43, calc.score());
	}
	@Test
	public void shouldReturn58For82735564() { // all spares
		initCalc(new ArrayList<Integer>(Arrays.asList(8, 2, 7, 3, 5, 5, 6, 4)));
		assertEquals(58, calc.score());
	}
	@Test
	public void shouldReturnTrueFor101010101010101010101010() { // last round strike = 2 bonus balls at the end
		for (int i = 0; i < 12; i++)
			calc.roll(10);
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturn300For101010101010101010101010() { // max points in game
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10)));
		assertEquals(300, calc.score());
	}
	@Test
	public void shouldReturnTrueFor1010101010101010108210() { // last round spare = 1 bonus ball at the end
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 8, 2, 10)));
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturnTrueFor101010101010101010824() { // last round spare = 1 bonus ball at the end
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 8, 2, 4)));
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturnTrueFor1010101010101010251034() { // last round strike = 2 bonus balls at the end
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 2, 5, 10, 3, 4)));
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturnTrueFor22222222222222222222() { // no bonus balls at the end
		for (int i = 0; i < 20; i++)
			calc.roll(2);
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturnTrueFor10101010101010102222() { // no bonus balls at the end
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 2, 2, 2, 2)));
		assertTrue(calc.isFinished());
	}
	@Test
	public void shouldReturnTrueFor10101010101010101010101022() { // all strikes, more rolls than should be in whole game
		initCalc(new ArrayList<Integer>(Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2, 2)));
		assertTrue(calc.isFinished());
	}
	
	private BowlingGameResultCalculator calc = new BowlingGameResultCalculatorMikolajImpl();
	private void initCalc(List<Integer> rolls) {
		for (int Int : rolls) {
			calc.roll(Int);
		}
	}
}
