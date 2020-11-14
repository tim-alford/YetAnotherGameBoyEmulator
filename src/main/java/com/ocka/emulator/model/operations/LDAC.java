package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDAC implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		int opCode = c.current();
		Registers r = c.getRegisters();
		switch(opCode){
			case 0xF2:
				int base = 0xFF00;
			 	base += (r.getC() & 0xFF);
				r.setA(m.getByte(base));
				break;
			default:
 				String msg = new StringBuilder().
					append("Error, LDAC doesn't know how to handle op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}		
	}
}
