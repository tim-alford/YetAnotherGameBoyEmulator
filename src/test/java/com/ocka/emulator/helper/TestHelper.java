package com.ocka.emulator.helper;
import java.io.File;
import java.util.*;
import com.ocka.emulator.model.enumerations.*;
import com.ocka.emulator.model.operations.*;
import com.ocka.emulator.model.*;
public class TestHelper {
	public static File getTestCartridge() throws Exception {
		String assets = System.getenv("EMULATOR_ASSETS");
		if(assets == null)
			throw new Exception("Error, EMULATOR_ASSETS variable is not defined.");
		return new File(String.format("%s/test.gb", assets));
	}
	public static Memory runProgram(byte[] program, Registers r) throws Exception {
		Memory m = getTestMemory(program);
		Cpu c = new Cpu(r, m);
		r.setPc(0x100);
		c.start();	
		return m;
	}
	public static Memory getTestMemory(byte[] program){
		Memory m = new Memory();
		int start = 0x100;
		for(int i = 0; i < program.length; i++)
			m.setByte(start + i, program[i]);
		m.setEnd(start + program.length);
		return m;	
	}		
}
