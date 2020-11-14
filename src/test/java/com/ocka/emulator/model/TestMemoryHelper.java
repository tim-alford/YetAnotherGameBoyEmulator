package com.ocka.emulator.model;
import com.ocka.emulator.helper.TestHelper;
import org.junit.*;
import java.io.*;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.ocka.emulator.model.enumerations.*;
import com.ocka.emulator.model.operations.*;
public class TestMemoryHelper {
	public static Memory getTestMemory(byte... program){
		Memory m = new Memory();
		int start = 0x100;
		for(int i = 0; i < program.length; i++)
			m.setByte(start + i, program[i]);
		m.setEnd(start + program.length);
		return m;	
	}		
}
