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
	/**
	* Load a single byte into a register.
	*/
	private void doLoadByte(Cpu c, Memory m, byte opCode, byte data) throws Exception {
		m.clear();
		m.setByte(0x100, (byte)opCode);
		m.setByte(0x101, (byte)data);
		m.setEnd(0x102);
		c.getRegisters().setPc(0x100);
		c.start();
	}
	/* 1. LD A,n tests */
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
	/* 2. LD R1,R2 */
	/* 3. LD A,n */
	@Test
	public void testLoadAA() throws Exception {
		byte[] program = new byte[]{(byte)0x7F};
		Registers r = new Registers(); 
		r.setA((byte)0x10);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x10)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAB() throws Exception {
		byte[] program = new byte[]{(byte)0x78};
		Registers r = new Registers(); 
		r.setB((byte)0x11);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x11)));
		assertThat(r.getB(),is(equalTo((byte)0x11)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAC() throws Exception {
		byte[] program = new byte[]{(byte)0x79};
		Registers r = new Registers(); 
		r.setC((byte)0x12);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x12)));
		assertThat(r.getB(), is(equalTo((byte)0x00)));
		assertThat(r.getC(),is(equalTo((byte)0x12)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}	
	@Test
	public void testLoadAD() throws Exception {
		byte[] program = new byte[]{(byte)0x7A};
		Registers r = new Registers(); 
		r.setD((byte)0x13);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x13)));
		assertThat(r.getB(), is(equalTo((byte)0x00)));
		assertThat(r.getC(),is(equalTo((byte)0x00)));
		assertThat(r.getD(),is(equalTo((byte)0x13)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAE() throws Exception {
		byte[] program = new byte[]{(byte)0x7B};
		Registers r = new Registers(); 
		r.setE((byte)0x14);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x14)));
		assertThat(r.getB(), is(equalTo((byte)0x00)));
		assertThat(r.getC(), is(equalTo((byte)0x00)));
		assertThat(r.getD(), is(equalTo((byte)0x00)));
		assertThat(r.getE(), is(equalTo((byte)0x14)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAH() throws Exception {
		byte[] program = new byte[]{(byte)0x7C};
		Registers r = new Registers(); 
		r.setH((byte)0x15);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x15)));
		assertThat(r.getB(), is(equalTo((byte)0x00)));
		assertThat(r.getC(), is(equalTo((byte)0x00)));
		assertThat(r.getD(), is(equalTo((byte)0x00)));
		assertThat(r.getE(), is(equalTo((byte)0x00)));
		assertThat(r.getH(), is(equalTo((byte)0x15)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAL() throws Exception {
		byte[] program = new byte[]{(byte)0x7D};
		Registers r = new Registers(); 
		r.setL((byte)0x16);
		TestHelper.runProgram(program, r);
		assertThat(r.getA(), is(equalTo((byte)0x16)));
		assertThat(r.getB(), is(equalTo((byte)0x00)));
		assertThat(r.getC(), is(equalTo((byte)0x00)));
		assertThat(r.getD(), is(equalTo((byte)0x00)));
		assertThat(r.getE(), is(equalTo((byte)0x00)));
		assertThat(r.getH(), is(equalTo((byte)0x00)));
		assertThat(r.getL(), is(equalTo((byte)0x16)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}	
	@Test
	public void testLoadAAddressBC() throws Exception {	
		Registers r = new Registers();
		r.setBc(0xCCDD);
		Memory m = new Memory();
		m.setByte(0xCCDD, (byte) 0x11);
		m.setByte(0x100, (byte) 0x0A);
		m.setEnd(0x101);
		r.setPc(0x100);	
		Cpu c = new Cpu(r, m);
		c.start();
		assertThat(r.getA(), is(equalTo((byte)0x11)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}	
	@Test
	public void testLoadAddressDE() throws Exception {
		Registers r = new Registers();
		r.setDe(0xEEAA);	
		r.setPc(0x100);
		Memory m = new Memory();
		m.setByte(0xEEAA, (byte) 0x12);
		m.setByte(0x100, (byte) 0x1A);
		m.setEnd(0x101);
		Cpu c = new Cpu(r, m);
		c.start();
		assertThat(r.getA(), is(equalTo((byte)0x12)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAddressHL() throws Exception {
		Registers r = new Registers();
		r.setHl(0x1122);	
		r.setPc(0x100);
		Memory m = new Memory();
		m.setByte(0x1122, (byte) 0x13);
		m.setByte(0x100, (byte) 0x7E);
		m.setEnd(0x101);
		Cpu c = new Cpu(r, m);
		c.start();
		assertThat(r.getA(), is(equalTo((byte)0x13)));
		assertThat(r.getPc(), is(equalTo(0x101)));
	}
	@Test
	public void testLoadAddressNN() throws Exception {
		Registers r = new Registers();
		r.setPc(0x100);
		Memory m = new Memory();
		m.setByte(0x100, (byte) 0xFA);
		m.setByte(0x101, (byte) 0x11);
		m.setByte(0x102, (byte) 0x13);
		m.setByte(0x1311, (byte) 0x19);
		m.setEnd(0x103);
		Cpu c = new Cpu(r, m);
		c.start();
		assertThat(r.getA(), is(equalTo((byte)0x19)));
		assertThat(r.getPc(), is(equalTo(0x103)));
	}
	@Test
	public void testLoadAddressNumber() throws Exception {
		Registers r = new Registers();
		r.setPc(0x100);
		Memory m = new Memory();
		m.setByte(0x100, (byte) 0x3E);
		m.setByte(0x101, (byte) 0xEF);
		m.setEnd(0x102);
		Cpu c = new Cpu(r, m);
		c.start();
		assertThat(r.getA(), is(equalTo((byte)0xEF)));
		assertThat(r.getPc(), is(equalTo(0x102)));
	}
	/* 4. LD n,A */
	/* 5. LD A,(C) */
	/* 6. LD (C),A */
	/* 7, 8, 9. LDD A,(HL) */
	/* 10, 11, 12. LDD (HL), A */
	/* 13, 14, 15. LDI A, (HL) */
	/* 16, 17, 18. LDI (HL), A  */
	/* 19. LDH (n), A */
}
