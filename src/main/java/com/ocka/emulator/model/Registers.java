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
	private static final int ZERO_FLAG = 0x80;
	private static final int SUBTRACT_FLAG = 0x40;
	private static final int HALF_CARRY_FLAG = 0x20;
	private static final int CARRY_FLAG = 0x10;
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
	private int pack(byte msb, byte lsb){
		int x = 0;
		x |= (int)((msb&0xFF)<<8);
		x |= (int)(lsb&0xFF);
		return x & 0xFFFF;
	}
	public void setBc(int bc){ 
		byte[] bytes = split(bc);
		this.c = bytes[0];
		this.b = bytes[1];	
	}
	public int getBc(){
		return pack(c, b);
	}
	public void setDe(int de){ 
		byte[] bytes = split(de);
		this.e = bytes[0];
		this.d = bytes[1];	
	}
	public int getDe(){
		return pack(e, d);
	}
	public void setHl(int hl){ 	
		byte[] bytes = split(hl);
		this.l = bytes[0];
		this.h = bytes[1];	
	}
	public int getHl(){
		return pack(l, h);
	}
	public void setAf(int af){
		byte[] bytes = split(af);
		this.f = bytes[0];
		this.a = bytes[1];	
	}	
	public int getAf(){
		return pack(f, a);
	}
	/* methods for checking flag register */
	public boolean isZeroBitSet(){
		return (f & ZERO_FLAG) == ZERO_FLAG;
	}
	public boolean isSubtractBitSet(){
		return (f & SUBTRACT_FLAG) == SUBTRACT_FLAG; 
	}
	public boolean isHalfCarryBitSet(){
		return (f & HALF_CARRY_FLAG) == HALF_CARRY_FLAG;
	}
	public boolean isCarryBitSet(){
		return (f & CARRY_FLAG) == CARRY_FLAG;
	}
	public void setZeroBit(){
		f |= ZERO_FLAG;	
	}
	public void setSubtractBit(){
		f |= SUBTRACT_FLAG;
	}
	public void setHalfCarryBit(){
		f |= HALF_CARRY_FLAG;
	}
	public void setCarryBit(){
		f |= CARRY_FLAG;		
	}
	public void clearFlagBits(){
		f &= 0x00;
	}
	public void clearZeroBit(){
		f &= (ZERO_FLAG ^ 0xFF);	
	}
	public void clearSubtractBit(){
		f &= (SUBTRACT_FLAG ^ 0xFF);	
	}
	public void clearCarryBit(){
		f &= (CARRY_FLAG ^ 0xFF);	
	}
	public void clearHalfCarryBit(){
		f &= (HALF_CARRY_FLAG ^ 0xFF);	
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
