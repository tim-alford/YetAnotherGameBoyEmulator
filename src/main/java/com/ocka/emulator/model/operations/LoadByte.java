package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LoadByte implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		int op = (int) c.current(); // integer for convenience
		byte param = c.next(); // need a parameter
		Registers r = c.getRegisters();
		switch(op){
			case 0x06:
				r.setB(param);
				break;
			case 0x0E:
				r.setC(param);
				break;
			case 0x16:
				r.setD(param);
				break;
			case 0x1E:
				r.setE(param);
				break;
			case 0x26:
				r.setH(param);
				break;
			case 0x2E:
				r.setL(param);
				break;
			default:
				String msg = new StringBuilder().
					append("Error, LoadByte doesn't know how to handle ").
					append(String.format("op code %d. Check op code mapping table.", op)).
					toString();
				throw new Exception(msg);
		}
	}
}
