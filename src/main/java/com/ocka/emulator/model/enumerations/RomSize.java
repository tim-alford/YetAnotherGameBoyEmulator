package com.ocka.emulator.model.enumerations;
public class RomSize {
	public static RomSize[] values = new RomSize[0x55]; 
	static {
		values[0] = new RomSize("32KB");
		values[1] = new RomSize("64KB");
		values[2] = new RomSize("128KB");
		values[3] = new RomSize("256KB");
		values[4] = new RomSize("512KB");
		values[5] = new RomSize("1MB");
		values[6] = new RomSize("2MB");
		values[0x52] = new RomSize("1.1MB");
		values[0x53] = new RomSize("1.2MB");
		values[0x54] = new RomSize("1.5MB");
	}
	private RomSize(String name){
		this.name = name;
	}	
	public String getName(){
		return name;
	}
	private String name;
}
