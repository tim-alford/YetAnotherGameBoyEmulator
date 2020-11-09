package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class NoOp implements Operation {
	private static final int CLOCK_CYCLES = 4;
	public int getCycles(){
		return CLOCK_CYCLES;
	}
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
	}
}
