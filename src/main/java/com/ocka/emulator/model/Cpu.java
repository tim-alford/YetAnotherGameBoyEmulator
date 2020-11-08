package com.ocka.emulator.model;
import java.util.*;
public class Cpu {
	private final Registers r = new Registers();
	private Memory m = null;
	private boolean executing = false;
	private final List<Class<? extends Operation>> opTable = new ArrayList<>(0xFFFF);
	public Cpu(Memory m) throws Exception {
		this.m = m;
		CpuHelper.loadOpTable(opTable);
	}
	public List<Class<? extends Operation>> getOpTable(){
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
	public void execute(byte opCode) throws Exception {
		Class<? extends Operation> opClass = opTable.get(opCode);
		if(opClass == null){
			String msg = new StringBuilder().
				append(String.format("Error, op code %x is not mapped. ", opCode)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);
		}
		Operation op = opClass.newInstance();
		op.execute(this, m);	
	}
	public void start() throws Exception {
		executing = true;
		while(true){
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
