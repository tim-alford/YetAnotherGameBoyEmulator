package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDAN implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {	
		int opCode = (int) c.current();
		Registers r = c.getRegisters();
		switch(opCode){
			case 0x7F: r.setA(r.getA()); break;
			case 0x78: r.setA(r.getB()); break;
			case 0x79: r.setA(r.getC()); break;
			case 0x7A: r.setA(r.getD()); break;
			case 0x7B: r.setA(r.getE()); break;
			case 0x7C: r.setA(r.getH()); break;
			case 0x7D: r.setA(r.getL()); break;
			case 0x0A: r.setA(m.getByte(r.getBc())); break;
			case 0x1A: r.setA(m.getByte(r.getDe())); break;
			case 0x7E: r.setA(m.getByte(r.getHl())); break;
			case 0xFA: 
				byte lsb = (byte) c.next(); 
				byte msb = (byte) c.next(); 
				int address = ((int)(msb & 0xFF)) << 8;
				address |= ((int)(lsb & 0xFF));
				address &= 0xFFFF; 
				r.setA(m.getByte(address)); 
				break;
			case 0x3E: 
				byte param = (byte) c.next(); 
				r.setA(param); 
				break;
			default:
				String msg = new StringBuilder().
					append("Error, LDAN doesn't understand the op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}
	}
}
