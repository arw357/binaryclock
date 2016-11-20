package com.kaetter.clock.binaryclock.units;

import com.kaetter.clock.binaryclock.base.LinkedUnitImpl;

public class BitUnit extends LinkedUnitImpl<BitUnit>   {

	private static final int BIT_LIMIT = 1;
	private boolean on;

	public BitUnit(BitUnit  previousByte) {
		super(previousByte, BIT_LIMIT);
	}

	public BitUnit() {
		super(BIT_LIMIT);
	}

	public boolean isOn() {
		return on;
	}

	public void setState() {
		on = value > 0;
	}

	public void step() {
		++value;
		if (isOverLimit()) {
			reset();
			if (!isLast()) {
				getNext().step();
			}
		}
		setState();
	}

	protected void reset() {
		value = 0;
	}

	public boolean isOverLimit() {
		return getValue() > BIT_LIMIT;
	}


	public void setOff() {
		reset();
		setState();
	}
	
	public void setOn(){
		value = 1;
		setState();
	}
}
