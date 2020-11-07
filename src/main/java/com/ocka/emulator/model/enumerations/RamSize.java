package com.ocka.emulator.model.enumerations;
public class RamSize {
	public static RamSize[] values = new RamSize[5]; 
	static {
		values[0] = new RamSize("NONE");
		values[1] = new RamSize("2KB");
		values[2] = new RamSize("8KB");
		values[3] = new RamSize("32KB");
		values[4] = new RamSize("128KB");
	}
	private RamSize(String name){
		this.name = name;
	}	
	public String getName(){
		return name;
	}
	private String name;
}
