package com.ocka.emulator.model;
public interface Operation {
	public void execute(Cpu c, Memory m) throws Exception;
}
