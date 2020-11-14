package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDNA implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		int opCode = c.current();
		Registers r = c.getRegisters();
		int address = 0;
		switch(opCode){
			case 0x7F: r.setA(r.getA()); break;
			case 0x47: r.setB(r.getA()); break;
			case 0x4F: r.setC(r.getA()); break;
			case 0x57: r.setD(r.getA()); break;
			case 0x5F: r.setE(r.getA()); break;
			case 0x67: r.setH(r.getA()); break;
			case 0x6F: r.setL(r.getA()); break;
			case 0x02: 
				address = r.getBc();
				m.setByte(address, r.getA());
				break;
			case 0x12: 
				address = r.getDe();
				m.setByte(address, r.getA());
				break;
			case 0x77: 
				address = r.getHl();
				m.setByte(address, r.getA());
				break;
			case 0xEA: 
				int lsb = c.next();
				int msb = c.next();
				address = (lsb & 0xFF);
				address |= ((msb & 0xFF) << 8);
				m.setByte(address, r.getA());
				break;
			default:
 				String msg = new StringBuilder().
					append("Error, LDNA doesn't know how to handle op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}		
	}
}
