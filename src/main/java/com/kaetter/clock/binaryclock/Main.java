package com.kaetter.clock.binaryclock;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.kaetter.clock.binaryclock.units.BitSeries;
import com.kaetter.clock.binaryclock.units.NibbleUnit;

 
public class Main {

    public static void main(String[] args) throws InterruptedException { 
    	
    	Calendar cal = Calendar.getInstance();
    	Integer second =cal.get(Calendar.SECOND);
    	Integer hour =cal.get(Calendar.HOUR);
    	Integer minute =cal.get(Calendar.MINUTE);
    	BitSeries seconds = new BitSeries(6,59);
    	seconds.setValue(second);
    	BitSeries minutes = new BitSeries(6,59,seconds);
    	minutes.setValue(minute);
    	BitSeries hours = new NibbleUnit(12,minutes);
    	hours.setValue(hour);
    	
        while(true) {
        	seconds.step();
        	hours.displayValue();minutes.displayValue();seconds.displayValue();
        	System.out.println(System.lineSeparator());
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

}
