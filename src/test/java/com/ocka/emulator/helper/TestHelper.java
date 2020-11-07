package com.ocka.emulator.helper;
import java.io.File;
public class TestHelper {
	public static File getTestCartridge() throws Exception {
		String assets = System.getenv("EMULATOR_ASSETS");
		if(assets == null)
			throw new Exception("Error, EMULATOR_ASSETS variable is not defined.");
		return new File(String.format("%s/test.gb", assets));
	}
}
