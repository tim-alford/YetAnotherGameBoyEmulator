package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDCA implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		int opCode = c.current();
		Registers r = c.getRegisters();
		switch(opCode){
			case 0xE2:
				int base = 0xFF00;
			 	base += (r.getC() & 0xFF);
				m.setByte(base, r.getA());
				break;
			default:
 				String msg = new StringBuilder().
					append("Error, LDCA doesn't know how to handle op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}		
	}
}
