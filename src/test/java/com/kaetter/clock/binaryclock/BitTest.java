package com.kaetter.clock.binaryclock;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kaetter.clock.binaryclock.base.LinkedUnit;
import com.kaetter.clock.binaryclock.units.BitUnit;

@Test
public class BitTest {
	private BitUnit byteUnit;
	private BitUnit byteUnit2;

	@BeforeTest
	public void init() {
		byteUnit = new BitUnit();
	
	}
 
	@Test
	public void whenByteUnitIsCreatedStateIsOff() throws Exception {
		Assert.assertFalse(byteUnit.isOn());
	}

	@Test
	public void whenByteUnitIsIncreasedStateIsOn() throws Exception {
		byteUnit.step();
		Assert.assertTrue(byteUnit.isOn());
	}

 
	@Test
	public void whenByteIsIncreasedOverLimitStateIsOff() throws Exception {
		byteUnit.step();
		byteUnit.step();
		Assert.assertFalse(byteUnit.isOn());
	}

	@Test
	public void aByteKnowsIfItsNotLast() throws Exception {
		byteUnit = new BitUnit();
		LinkedUnit byteUnit2 = new BitUnit( byteUnit);
		Assert.assertFalse(byteUnit.isLast());
	}

	@Test
	public void lastByteKnowsItsTheLastByte() throws Exception {
		byteUnit = new BitUnit();
		Assert.assertTrue(byteUnit.isLast());
	}

	@Test
	public void whenByteIsIncreasedOverLimitNextByteIsIncreasedAsWell() throws Exception {
		byteUnit2 = new BitUnit(byteUnit);
		byteUnit.step();
		byteUnit.step();
		
		Assert.assertTrue(byteUnit2.isOn());

	} 
	@Test
	public void whenLastByteIsIncreasedOverLimitAllBytesAreOff() throws Exception {
		byteUnit = new BitUnit();
		byteUnit2 = new BitUnit(byteUnit);
		
		byteUnit.step();
		byteUnit.step();
		byteUnit.step();
		byteUnit.step();
		Assert.assertFalse(byteUnit2.isOn());
		Assert.assertFalse(byteUnit.isOn());
	}

}
