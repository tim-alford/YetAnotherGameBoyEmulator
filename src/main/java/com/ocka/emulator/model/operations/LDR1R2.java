package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class LDR1R2 implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
		Registers r = c.getRegisters();
		int opCode = (int) c.current();
		switch(opCode){
			// load A register
			case 0x7F: r.setA(r.getA()); break;
			case 0x78: r.setA(r.getB()); break;
			case 0x79: r.setA(r.getC()); break;
			case 0x7A: r.setA(r.getD()); break;
			case 0x7B: r.setA(r.getE()); break;
			case 0x7C: r.setA(r.getH()); break;
			case 0x7D: r.setA(r.getL()); break;
			case 0x7E: r.setA(m.getByte(r.getHl())); break;
			// load B register
			case 0x40: r.setB(r.getB()); break;
			case 0x41: r.setB(r.getC()); break;
			case 0x42: r.setB(r.getD()); break;
			case 0x43: r.setB(r.getE()); break;
			case 0x44: r.setB(r.getH()); break;
			case 0x45: r.setB(r.getL()); break;
			case 0x46: r.setB(m.getByte(r.getHl())); break;
			// load C register
			case 0x48: r.setC(r.getB()); break;
			case 0x49: r.setC(r.getC()); break;
			case 0x4A: r.setC(r.getD()); break;
			case 0x4B: r.setC(r.getE()); break;
			case 0x4C: r.setC(r.getH()); break;
			case 0x4D: r.setC(r.getL()); break;
			case 0x4E: r.setC(m.getByte(r.getHl())); break;
			// load D register
			case 0x50: r.setD(r.getB()); break;
			case 0x51: r.setD(r.getC()); break;
			case 0x52: r.setD(r.getD()); break;
			case 0x53: r.setD(r.getE()); break;
			case 0x54: r.setD(r.getH()); break;
			case 0x55: r.setD(r.getL()); break;
			case 0x56: r.setD(m.getByte(r.getHl())); break;
			// load E register
			case 0x58: r.setE(r.getB()); break;
			case 0x59: r.setE(r.getC()); break;
			case 0x5A: r.setE(r.getD()); break;
			case 0x5B: r.setE(r.getE()); break;
			case 0x5C: r.setE(r.getH()); break;
			case 0x5D: r.setE(r.getL()); break;
			case 0x5E: r.setE(m.getByte(r.getHl())); break;
			// load H register
			case 0x60: r.setH(r.getB()); break;
			case 0x61: r.setH(r.getC()); break;
			case 0x62: r.setH(r.getD()); break;
			case 0x63: r.setH(r.getE()); break;
			case 0x64: r.setH(r.getH()); break;
			case 0x65: r.setH(r.getL()); break;
			case 0x66: r.setH(m.getByte(r.getHl())); break;
			// load L register
			case 0x68: r.setL(r.getB()); break;
			case 0x69: r.setL(r.getC()); break;
			case 0x6A: r.setL(r.getD()); break;
			case 0x6B: r.setL(r.getE()); break;
			case 0x6C: r.setL(r.getH()); break;
			case 0x6D: r.setL(r.getL()); break;
			case 0x6E: r.setL(m.getByte(r.getHl())); break;
			// load memory at location pointer to by HL
			case 0x70: m.setByte(r.getHl(), r.getB()); break;
			case 0x71: m.setByte(r.getHl(), r.getC()); break;
			case 0x72: m.setByte(r.getHl(), r.getD()); break;
			case 0x73: m.setByte(r.getHl(), r.getE()); break;
			case 0x74: m.setByte(r.getHl(), r.getH()); break;
			case 0x75: m.setByte(r.getHl(), r.getL()); break;
			case 0x36: 
			 	byte param = (byte) c.next();
				m.setByte(r.getHl(), param); 
				break;
			default:
				String msg = new StringBuilder().
					append("Error, LoadRegister doesn't support op code ").
					append(String.format("%x", opCode)).
					toString();
				throw new Exception(msg);
		}
	}
}
