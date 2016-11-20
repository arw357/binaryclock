package com.kaetter.clock.binaryclock;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kaetter.clock.binaryclock.units.BitSeries;
import com.kaetter.clock.binaryclock.units.NibbleUnit;
import com.kaetter.clock.exceptions.LimitPassedIsOverSeriesMaxValueException;

@Test
public class NibbleTest {
	
	private NibbleUnit nibbleUnit;
	
	@BeforeMethod	
	public void init(){
		nibbleUnit = new NibbleUnit(15);
	}

	@Test
	public void initialNibbleHasValueZero() throws Exception {
		Assert.assertTrue(nibbleUnit.getValue().equals(0));
	}

	@Test
	public void afterOneStepNibbleHasValueOne() throws Exception {
		nibbleUnit.step();
		Assert.assertTrue(nibbleUnit.getValue().equals(1));
	}

	public void afterTenStepsNibbleHasValueTen() throws Exception {
		for (int i = 0; i < 10; i++) {
			nibbleUnit.step();
		}
		Assert.assertTrue(nibbleUnit.getValue().equals(10));
	}

	public void after10RandomStepsNibbleHasValueRandom() throws Exception {
		for (int j = 0; j < 10; j++) {
			 BitSeries  nibbleUnit = new BitSeries(4,15);
			Integer random = randomStepOfNibble(nibbleUnit);
			Assert.assertEquals( nibbleUnit.getValue(),random );
		}
	}

	private Integer randomStepOfNibble( BitSeries  nibbleUnit) {
		
		Integer random = getRandomOfNibble();
		nibbleStepNumberOfTimes(nibbleUnit, random);
		
		return random;
	}

	private void nibbleStepNumberOfTimes(  BitSeries  nibbleUnit, Integer steps) {
		for (int i = 0; i < steps; i++) {
			nibbleUnit.step();
		}
		System.out.println( System.lineSeparator());
	}

	private Integer getRandomOfNibble() {
		Integer random = new Random().nextInt(16);
		return random;
	}
	
	@Test
	public void printNibble() throws Exception {
		  Integer random = randomStepOfNibble(nibbleUnit);
		  System.out.println( String.format("After %d of steps value is ",random));
	}
	@Test
	public void whenNibbleResetValueisZero() throws Exception {
		nibbleUnit.reset();
		Assert.assertTrue(nibbleUnit.getValue().equals(0));
	}
	
	@Test
	public void whenNibbleLimitReachedValueIsZero() throws Exception {
		Integer random = getRandomOfNibble();
		nibbleUnit = new NibbleUnit(random);
		for(int i = 0; i<random+1; i++){
			nibbleUnit.step();	
		}
		System.out.println(random);
		Assert.assertEquals(nibbleUnit.getValue().intValue(), 0 );
	}
	
	@Test
	public void whenCreating2SeriesTheFirstOneKnowsAboutTheSecond() throws Exception {
		BitSeries nibbleUnit2 = new BitSeries(4, 15,nibbleUnit);
		Assert.assertTrue(nibbleUnit.getNext().equals(nibbleUnit2));
		Assert.assertNull(nibbleUnit2.getNext());
		
	}
	
	@Test
	public void whenFirstBitSeriesPassesLimitTheNextOneIncreases() throws Exception {
		NibbleUnit nibbleUnit = new NibbleUnit(12);
		NibbleUnit nibble2 = new NibbleUnit( 2,nibbleUnit);
		nibbleStepNumberOfTimes(nibbleUnit,13);
 		nibbleUnit.displayValue(); nibble2.displayValue() ;
		System.out.println( System.lineSeparator());
		Assert.assertEquals(nibble2.getValue().intValue(),1);
	}
	
	@Test(expectedExceptions=LimitPassedIsOverSeriesMaxValueException.class)
	public void whenLimitPassedIsGreaterThanPossibleValueException() throws Exception {
		BitSeries nibbleUnit = new BitSeries(3,9);
	}
	@Test
	public void whenSettingValueGetValueIsTheSame() throws Exception {
		NibbleUnit nibbleUnit = new NibbleUnit(10);
		nibbleUnit.setValue(5);
		Assert.assertEquals(nibbleUnit.getValue().intValue(), 5);
	}

}
