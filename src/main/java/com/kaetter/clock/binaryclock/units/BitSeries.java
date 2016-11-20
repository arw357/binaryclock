package com.kaetter.clock.binaryclock.units;

import com.kaetter.clock.binaryclock.base.LinkedUnitImpl;
import com.kaetter.clock.exceptions.LimitPassedIsOverSeriesMaxValueException;

public class BitSeries extends LinkedUnitImpl<BitSeries>   {

	private int seriesLength ;
	private BitUnit firstBit = new BitUnit();
	
	
	public BitSeries(Integer seriesLength,Integer limit ) {
		super(limit);
		this.setSeriesLength(seriesLength);
		checkLimit();
		initSeries();
	}

	private void checkLimit() throws LimitPassedIsOverSeriesMaxValueException{
		int n=0; 
		int sum=0;
		while (n <  getSeriesLength()) {
		    sum = (int) (sum + Math.pow(2, n)); 
		    n++;
		}
		if(getLimit()>sum){
			throw new LimitPassedIsOverSeriesMaxValueException();
		}
		
	}

	public BitSeries(int seriesLength, int limit, BitSeries previousSeries) {
		super(previousSeries,limit);
		this.setSeriesLength(seriesLength);
		checkLimit();
		initSeries();
	}
	
	private void initSeries() {
		BitUnit bitUnit = firstBit;

		for (int i = 0 ;i<seriesLength ;i++){
			bitUnit = new BitUnit(bitUnit);
		}
	}

	public void step() {
		 
		firstBit.step();
		calculateValue();
		
	}

	public void calculateValue() {

		value = 0;
		BitUnit bitUnit = firstBit;
		
		for (int i = 0 ; i<getSeriesLength();i++){
			value = bitUnit.isOn() ? value + (int) Math.pow(2, i) : value;
			bitUnit=bitUnit.getNext();
		}
		
		if(value>getLimit()){
			stepNextSeries();
			reset();
			value=0;
		}
	}



	private void stepNextSeries() {
		if(!isLast()){
			getNext().step();
		}
	}
	
	public void displayValue(){
		
		BitUnit bitUnit = firstBit;
		for (int i = 0 ; i<getSeriesLength();i++){
			System.out.print(bitUnit.isOn()?" 1 ":" 0 ");
			bitUnit=bitUnit.getNext();
		}
		System.out.print("("+ getValue()+")");
	}
	
	public void  reset(){
		
		BitUnit bitUnit = firstBit;
		for (int i = 0 ; i<getSeriesLength();i++){
			bitUnit.setOff();
			bitUnit=bitUnit.getNext();
		}
	}

	@Override
	protected boolean isOverLimit() {
		 return getValue()>getLimit();
	}

	public void setValue(int value){
		BitUnit bitUnit = firstBit;
		for (int i = 0 ; i<getSeriesLength();i++){
			if(getBit(value,i)==0) {
				bitUnit.setOff();
			} else {
				bitUnit.setOn();
			}
			bitUnit=bitUnit.getNext();
		}
		
		calculateValue();
	}
	
	int getBit(int n, int k) {
	    return (n >> k) & 1;
	}

	public int getSeriesLength() {
		return seriesLength;
	}



	public void setSeriesLength(int seriesLength) {
		this.seriesLength = seriesLength;
	}

	 
}
