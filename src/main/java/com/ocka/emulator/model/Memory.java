package com.ocka.emulator.model;
public class Memory {
	private byte[] m = new byte[0x10000]; // 16 m bit ram, max address 0xFFFF	
	private int end;
	public Memory(){
		end = 0xFFFF;
	}
	/**
	* Clear all of the memory, initialising it to zero.
	*/
	public void clear(){
		for(int i = 0; i < m.length; i++){
			m[i] = 0;
		}
	}
	/**
	* Load the bottom 32KB from the specified cartridge into memory.
	*/
	public void load(Cartridge c){
		byte[] data = c.getData();
		for(int i = 0; i < 0x8000; i++){
			m[i] = data[i];
		}
	}
	public void setByte(int index, byte value){
		m[index] = value;	
	}
	public byte getByte(int index){
		return m[index];
	}
	public int getEnd(){
		return end;
	}
	public void setEnd(int end){
		this.end = end;
	}
}
