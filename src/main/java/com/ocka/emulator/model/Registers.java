package com.ocka.emulator.model;
public class Registers {
	private byte a;
	private byte f;
	private byte b;
	private byte c;
	private byte d;
	private byte e;
	private byte h;
	private byte l;
	private int sp; // first two bytes (LSB)
	private int pc; // first two bytes (LSB)
	public int getSp(){ 
		return sp & 0xFFFF; 
	}
	public int getPc(){ 
		return pc & 0xFFFF; 
	}
	public void setSp(int sp){ 
		this.sp = sp & 0xFFFF; 
	}
	public void setPc(int pc){ 
		this.pc = pc & 0xFFFF; 
	}
	/**
	* Split the least significant 16 bits of the specified integer into two bytes.
	*/
	private byte[] split(int bytes){
		byte msb = (byte) ((bytes & 0xFF00) >> 8);
		byte lsb = (byte) (bytes & 0xFF);
		return new byte[]{ msb, lsb };
	}
	public void setBc(int bc){ 
		byte[] bytes = split(bc);
		this.b = bytes[0];
		this.c = bytes[1];	
	}
	public void setDe(int de){ 
		byte[] bytes = split(de);
		this.d = bytes[0];
		this.e = bytes[1];	
	}
	public void setHl(int hl){ 	
		byte[] bytes = split(hl);
		this.h = bytes[0];
		this.l = bytes[1];	
	}
	public void setAf(int af){
		byte[] bytes = split(af);
		this.a = bytes[0];
		this.f = bytes[1];	
	}	
	/* simple register setters and getters */
	public byte getA(){ return a; }
	public byte getB(){ return b; }
	public byte getC(){ return c; }
	public byte getD(){ return d; }
	public byte getE(){ return e; }
	public byte getF(){ return f; }
	public byte getH(){ return h; }
	public byte getL(){ return l; }
	public void setA(byte a){ this.a = a; }
	public void setB(byte b){ this.b = b; }
	public void setC(byte c){ this.c = c; }
	public void setD(byte d){ this.d = d; }
	public void setE(byte e){ this.e = e; }
	public void setF(byte f){ this.f = f; }
	public void setH(byte h){ this.h = h; }
	public void setL(byte l){ this.l = l; }
}
