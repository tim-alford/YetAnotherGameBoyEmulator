package com.ocka.emulator.model;
import com.ocka.emulator.helper.TestHelper;
import org.junit.*;
import java.io.*;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.ocka.emulator.model.enumerations.*;
import com.ocka.emulator.model.operations.*;
public class TestCpu {
	private void doLoadByte(Cpu c, Memory m, byte opCode, byte data) throws Exception {
		m.clear();
		m.setByte(0x100, (byte)opCode);
		m.setByte(0x101, (byte)data);
		m.setEnd(0x102);
		c.getRegisters().setPc(0x100);
		c.start();
	}
	@Test
	public void testLoadRegisterB() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x06, (byte)0x11);
		assertThat(c.getRegisters().getB(), is(equalTo((byte)0x11)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
	@Test
	public void testLoadRegisterC() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x0E, (byte)0x12);
		assertThat(c.getRegisters().getC(), is(equalTo((byte)0x12)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
	@Test
	public void testLoadRegisterD() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x16, (byte)0x13);
		assertThat(c.getRegisters().getD(), is(equalTo((byte)0x13)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
	@Test
	public void testLoadRegisterE() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x1E, (byte)0x14);
		assertThat(c.getRegisters().getE(), is(equalTo((byte)0x14)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
	@Test
	public void testLoadRegisterH() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x26, (byte)0x15);
		assertThat(c.getRegisters().getH(), is(equalTo((byte)0x15)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
	@Test
	public void testLoadRegisterL() throws Exception {
		Memory m = new Memory();
		Cpu c = new Cpu(m);
		doLoadByte(c, m, (byte)0x2E, (byte)0x16);
		assertThat(c.getRegisters().getL(), is(equalTo((byte)0x16)));
		assertThat(c.getRegisters().getPc(), is(equalTo(0x102)));
	}
}
