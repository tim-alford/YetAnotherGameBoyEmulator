package com.ocka.emulator.model;
import java.util.*;
public class Cpu {
	private final Registers r = new Registers();
	private Memory m = null;
	private boolean executing = false;
	private final Class<?> opTable[] = new Class[0xFFFF];
	private final double frequency = 4.194304 * 1000000; // Hz *clock* cycles
	private final double period = 1/frequency; // length of one cycle (seconds)
	private long clock = 0;  // current clock value
	public Cpu(Memory m) throws Exception {
		this.m = m;
		CpuHelper.loadOpTable(opTable);
	}
	public Class<?>[] getOpTable(){
		return opTable;
	}
	/**
	* Gets the current byte from the ROM being executed.
	* @return The current byte from the ROM that's being executed. Indicated by the program counter.
	*/
	public byte current(){
		return m.getByte(r.getPc());
	}
	/**
	* Increments the program counter and returns the next byte to be executed.
	* @return The next byte to be executed as indicated by the program counter.
	*/
	public byte next(){
		r.setPc(r.getPc()+1);
		return current();
	}
	public double getPeriod(){
		return period;
	}
	public long getClock(){
		return clock;
	}
	/**
	* Execute the specified op code.
	* @param opCode The code of the operation to be performed.
	*/
	public void execute(byte opCode) throws Exception {
		Class<?> opClass = null;
		if(opCode > opTable.length){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is out of bounds. ", opCode)).
				append("Maximum value can be 0xFFFE").
				toString();
			throw new Exception(msg);
		}
		opClass = opTable[opCode];
		if(opClass == null){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is not mapped. ", opCode)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);
		}
		Operation op = (Operation) opClass.newInstance();
		op.execute(this, m);	
	}
	public void start() throws Exception {
		executing = true;
		while(r.getPc() != m.getEnd()){
			byte opCode = current();
			execute(opCode);
			next();
		}
	}
	public void stop(){
		executing = false;
	}
	public Registers getRegisters(){
		return r;
	}
}
