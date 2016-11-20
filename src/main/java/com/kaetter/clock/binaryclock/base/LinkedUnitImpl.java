package com.kaetter.clock.binaryclock.base;

public  abstract  class LinkedUnitImpl<T extends LinkedUnitImpl>  implements LinkedUnit<T> {

	private T next;
	private int limit ; 
	protected int value;
	
	public LinkedUnitImpl( T previous, Integer limit) {
		this(limit);
		previous.setNext(this);
	}

	public LinkedUnitImpl( Integer limit) {
		this.setLimit(limit);
	}
	
	protected abstract boolean isOverLimit();

	protected abstract void reset();

	public T  getNext() {
		return next;
	}

	public void setNext(T  next) {
		this.next = next;
	}

	public boolean isLast() {
		return getNext() == null;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Integer getValue() {
		return value;
	}

}