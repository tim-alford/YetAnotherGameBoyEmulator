// vim: ts=2
package com.ocka.emulator.model;
import com.ocka.emulator.model.enumerations.*;
import java.io.*;
import java.nio.charset.Charset;
public class Cartridge {
	public static final byte UNKNOWN = (byte) 0xFF;
	private static final int ENTRY_POINT = 0x0100;
	private static final int TITLE_START = 0x0134;
	private static final int TITLE_END = 0x0142;
	private static final int COLOUR_FLAG = 0x0143;
	private static final int RAM_SIZE = 0x0149;
	private static final int CARTRIDGE_TYPE = 0x0147;
	private static final int CARTRIDGE_SIZE = 0x0148;
	private static final int COMPLEMENT_CHECK = 0x014D;
	private static final int CHECKSUM_START = 0x014E;
	private static final int CHECKSUM_END = 0x014F;
	private static final int POWER_UP_CHECKSUM_START = 0x0134;
	private static final int POWER_UP_CHECKSUM_END = 0x014D;
	private byte type;
	private String title;
	private byte[] data;
	private boolean colour;
	private byte ramSize;
	private byte romSize;
	private byte[] checksum; // two bytes
	private Cartridge(){ 
		data = null;
		type = UNKNOWN;
		title = null;
		ramSize = UNKNOWN;
		romSize = UNKNOWN;
		checksum = null;
	}
	private byte[] read(File f) throws Exception {
		InputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int read = -1;
		final int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		while((read = in.read(buffer)) != -1){	
			out.write(buffer, 0, read);	
		}	
		return out.toByteArray();
	}		
	private byte getType(byte[] data){
		return data[CARTRIDGE_TYPE];	
	}
	public byte getType(){
		return type;
	}
	public byte getByte(int index){
		return data[index];
	}
	private String getTitle(byte[] data){
		return new String(data, TITLE_START, TITLE_END - TITLE_START, Charset.forName("US-ASCII"));
	}
	public String getTitle(){
		return title;
	}
	public boolean isColour(){
		return colour;
	}
	private boolean isColour(byte[] data){
		return data[COLOUR_FLAG] == 0x80;
	}
	private byte getRamSize(byte[] data){
		return data[RAM_SIZE];
	}
	public byte getRamSize(){
		return ramSize;
	}
	private byte getRomSize(byte[] data){
		return data[CARTRIDGE_SIZE];
	}
	public byte getRomSize(){
		return romSize;
	}
	private byte[] getChecksum(byte[] data){
		byte[] checksum = new byte[2];
		checksum[0] = data[CHECKSUM_START];
		checksum[1] = data[CHECKSUM_END];
		return checksum;
	}
	public boolean isChecksumValid(){
		int sum = 0;
		for(int i = 0; i < data.length; i++){
			// ignore checksum bytes when calculating checksum
			if(i == CHECKSUM_START || i == CHECKSUM_END)
				continue;
			sum += data[i];
		}
		return ((sum & 0xFF00) >> 8) == this.checksum[0] &&
			(sum & 0xFF) == this.checksum[1];
	}
	public byte[] getChecksum(){
		return checksum;
	}
	public boolean isPowerUpChecksumValid(){
		int nonce = 25, sum = 0;
		for(int i = POWER_UP_CHECKSUM_START; i <= POWER_UP_CHECKSUM_END; i++)
			sum += data[i];	
		sum += nonce;
		return ( sum & 0xFF ) == 0;
	}
	public byte[] getData(){
		return data;
	}
	public static Cartridge open(File f) throws Exception {
		if(!f.exists()){
			String msg = new StringBuilder().
				append("Error, the file \"").
				append(f.getAbsolutePath()).
				append("\" does not exist.").
				toString();
			throw new Exception(msg);
		}	
		Cartridge r = new Cartridge();
		byte[] data = r.read(f);
		r.type = r.getType(data);
		r.title = r.getTitle(data);
		r.colour = r.isColour(data);
		r.romSize = r.getRomSize(data);
		r.ramSize = r.getRamSize(data);
		r.checksum = r.getChecksum(data);
		r.data = data;
		return r;
	}
}
