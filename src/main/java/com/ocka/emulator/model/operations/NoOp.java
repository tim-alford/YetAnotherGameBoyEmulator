package com.ocka.emulator.model.operations;
import com.ocka.emulator.model.*;
public class NoOp implements Operation {
	@Override
	public void execute(Cpu c, Memory m) throws Exception {
	}
}
