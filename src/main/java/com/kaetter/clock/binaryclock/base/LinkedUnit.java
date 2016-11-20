package com.kaetter.clock.binaryclock.base;

public interface LinkedUnit<T> {

	T getNext();

	void setNext(T next);

	boolean isLast();

	void step();

}