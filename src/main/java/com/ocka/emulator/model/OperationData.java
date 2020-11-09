package com.ocka.emulator.model;
public class OperationData {
	private Class<?> implementation;
	private int cycles;
	public void setImplementation(Class<?> implementation){
		this.implementation = implementation;
	}
	public Class<?> getImplementation(){
		return implementation;
	}
	public int getCycles(){
		return cycles;
	}
	public void setCycles(int cycles){
		this.cycles = cycles;
	}
}
