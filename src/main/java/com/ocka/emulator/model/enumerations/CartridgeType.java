package com.ocka.emulator.model.enumerations;
public class CartridgeType {
	public static final CartridgeType[] values = new CartridgeType[0x100];
	static {
		for(int i = 0; i < values.length; i++){
			values[i] = null;
		}
		values[0] = new CartridgeType("ROM ONLY");
		values[1] = new CartridgeType("ROM+MBC1");
		values[2] = new CartridgeType("ROM+MBC1+RAM");
		values[3] = new CartridgeType("ROM+MBC1+RAM+BATT");
		values[5] = new CartridgeType("ROM+MBC2");
		values[6] = new CartridgeType("ROM+MBC2+BATTERY");
		values[8] = new CartridgeType("ROM+RAM");
		values[9] = new CartridgeType("ROM+RAM+BATTERY");
		values[11] = new CartridgeType("ROM+MMM01");
		values[12] = new CartridgeType("ROM+MMM01+SRAM");
		values[13] = new CartridgeType("ROM+MMM01+SRAM+BATT");
		values[15] = new CartridgeType("ROM+MBC3+TIMER+BATT");
		values[16] = new CartridgeType("ROM+MBC3+TIMER+RAM+BATT");
		values[17] = new CartridgeType("ROM+MBC3");
		values[18] = new CartridgeType("ROM+MBC3+RAM");
		values[19] = new CartridgeType("ROM+MBC3+RAM+BATT");
		values[25] = new CartridgeType("ROM+MBC5");
		values[26] = new CartridgeType("ROM+MBC5+RAM");
		values[27] = new CartridgeType("ROM+MBC5+RAM+BATT");
		values[28] = new CartridgeType("ROM+MBC5+RUMBLE");
		values[29] = new CartridgeType("ROM+MBC5+RUMBLE+SRAM");
		values[30] = new CartridgeType("ROM+MBC5+RUMBLE+SRAM+BATT");
		values[31] = new CartridgeType("Pocket Camera");
		values[253] = new CartridgeType("Bandai TAMA5");
		values[254] = new CartridgeType("Hudson HuC-3");
		values[255] = new CartridgeType("Hudson HuC-1");
	}
	public String[] getFeatures(){
		return this.name.split("+");
	}
	private String name;
	private CartridgeType(String name){
		this.name = name;
	}		
	public String getName(){
		return name;
	}
}
