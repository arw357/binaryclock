package com.kaetter.clock.binaryclock.units;

public class NibbleUnit extends BitSeries {
	public static final int NIBBLE_LENGTH=4;
	public NibbleUnit(int limit, BitSeries previousSeries) {
		super(4,limit, previousSeries);
	}

	public NibbleUnit(Integer limit) {
		super(4, limit);
	}
	
	 

}
