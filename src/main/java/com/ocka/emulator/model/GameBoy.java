package com.ocka.emulator.model;
public class GameBoy {
	private Memory m = null;
	private Cpu c = null;
	private boolean on = false;
	private boolean cartridgeLoaded = false;
	private Cartridge cart = null;
	public GameBoy() throws Exception {
		m = new Memory();
		c = new Cpu(m);
	}
	public void insertCartridge(Cartridge c){
		cart = c;
	}
	public void powerOn() throws Exception {
		powerOnSequence();
		loadCartridge();
		on = true;
		if(cartridgeLoaded){
			c.start();
		}
	}
	public void powerOff(){
		on = false;
		c.stop();
	}
	public void reset() throws Exception {
		powerOff();
		powerOn();
	}
	private void loadCartridge(){
		if(cart == null){
			return;
		}
		m.load(cart);
		cartridgeLoaded = true;
	}
	private void powerOnSequence(){
		Registers r = c.getRegisters();			
		// initialise registers
		// A => 0x01 => GB
		r.setA((byte)0x01);
		r.setF((byte)0xB0);
		r.setBc(0x0013);
		r.setDe(0x00D8);
		r.setHl(0x014D);	
		r.setSp(0xFFFE);	
		r.setPc(0x100);
		// may as well clear memory
		m.clear();
		// initialise specific memory addresses
		// these are mostly I/O registers
		// comments indicate the register that's being initialised
		m.setByte(0xFF05, (byte) 0x00); // TIMA
		m.setByte(0xFF06, (byte) 0x00); // TMA
		m.setByte(0xFF07, (byte) 0x00); // TAC
		m.setByte(0xFF10, (byte) 0x80); // NR10
		m.setByte(0xFF11, (byte) 0xBF); // NR11
		m.setByte(0xFF12, (byte) 0xF3); // NR12
		m.setByte(0xFF14, (byte) 0xBF); // NR14
		m.setByte(0xFF16, (byte) 0x3F); // NR21
		m.setByte(0xFF17, (byte) 0x00); // NR22
		m.setByte(0xFF19, (byte) 0xBF); // NR24
		m.setByte(0xFF1A, (byte) 0x7F); // NR30
		m.setByte(0xFF1B, (byte) 0xFF); // NR31 
		m.setByte(0xFF1C, (byte) 0x9F); // NR32
		m.setByte(0xFF1E, (byte) 0xBF); // NR33
		m.setByte(0xFF20, (byte) 0xFF); // NR41
		m.setByte(0xFF21, (byte) 0x00); // NR42
		m.setByte(0xFF22, (byte) 0x00); // NR43
		m.setByte(0xFF23, (byte) 0xBF); // NR30
		m.setByte(0xFF24, (byte) 0x77); // NR50 
		m.setByte(0xFF25, (byte) 0xF3); // NR51
		m.setByte(0xFF26, (byte) 0xF1); // NR52
		m.setByte(0xFF40, (byte) 0x91); // LCDC
		m.setByte(0xFF42, (byte) 0x00); // SCY
		m.setByte(0xFF43, (byte) 0x00); // SCX
		m.setByte(0xFF45, (byte) 0x00); // LYC 
		m.setByte(0xFF47, (byte) 0xFC); // BGP
		m.setByte(0xFF48, (byte) 0xFF); // OBP0
		m.setByte(0xFF49, (byte) 0xFF); // OBP1
		m.setByte(0xFF4A, (byte) 0x00); // WY
		m.setByte(0xFF4B, (byte) 0x00); // WX
		m.setByte(0xFFFF, (byte) 0x00); // IE
		// game boys have an internal rom taht would start executing as this point
		// but I'm not going to bother with that
	}
}
