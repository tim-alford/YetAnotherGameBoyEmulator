package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDIAHL implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		int opCode = c.current();
		Registers r = c.getRegisters();
		switch(opCode){
			case 0x2A:
				int address = r.getHl();
				r.setA(m.getByte(address));
				r.setHl(address + 1);
				break;
			default:
 				String msg = new StringBuilder().
					append("Error, LDDAHL doesn't know how to handle op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}		
	}
}
