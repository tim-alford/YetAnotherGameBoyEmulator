package com.ocka.emulator.model;
import java.util.*;
public class Cpu {
	private Registers r = null; 
	private Memory m = null;
	private boolean executing = false;
	private final OperationData opTable[] = new OperationData[0xFFFF];
	private final double frequency = 4.194304 * 1000000; // Hz *clock* cycles, approx 4x machine frequency. 4 clock cycles = 1 machine cycle.
	private final double period = 1 / frequency; // length of one cycle (seconds)
	private long clock = 0;  // current clock value
	public Cpu(Registers r, Memory m) throws Exception {
		this.r = r;
		this.m = m;
		CpuHelper.loadOpTable(opTable);
	}
	public Cpu(Memory m) throws Exception {
		this.m = m;
		r = new Registers();
		CpuHelper.loadOpTable(opTable);
	}
	public OperationData[] getOpTable(){
		return opTable;
	}
	/**
	* Gets the current byte from the ROM being executed.
	* @return The current byte from the ROM that's being executed. Indicated by the program counter.
	*/
	public int current(){
		byte b = m.getByte(r.getPc());
		int opCode = (b & 0xFF);
		return opCode; 
	}
	/**
	* Increments the program counter and returns the next byte to be executed.
	* @return The next byte to be executed as indicated by the program counter.
	*/
	public int next(){
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
	* @return The number of cycles elapsed during execution.
	*/
	public int execute(int opCode) throws Exception {
		OperationData data = null;
		if(opCode > opTable.length){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is out of bounds. ", opCode)).
				append("Maximum value can be 0xFFFE").
				toString();
			throw new Exception(msg);
		}
		data = opTable[opCode];
		if(data == null){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is not mapped to any data. ", opCode)).
				toString();
			throw new Exception(msg);
		}
		Class<?> opClass = data.getImplementation();
		int cycles = data.getCycles(); // *clock* cycles
		if(opClass == null){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is not mapped. ", opCode)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);
		}
		// run op
		// return time to execute
		Operation op = (Operation) opClass.newInstance();
		op.execute(this, m);	
		return cycles;
	}
	public void start() throws Exception {
		executing = true;
		while(r.getPc() != m.getEnd()){
			int opCode = (int) current();
			int cycles = execute(opCode);
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
