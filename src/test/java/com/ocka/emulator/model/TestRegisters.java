package com.ocka.emulator.model;
import com.ocka.emulator.helper.TestHelper;
import org.junit.*;
import java.io.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.ocka.emulator.model.enumerations.*;
public class TestRegisters {
	@Test
	public void testGetSetA(){
		Registers r = new Registers();
		assertThat(r.getA(), is(equalTo((byte)0)));
		r.setA((byte)0xA);
		assertThat(r.getA(), is(equalTo((byte)10)));
		r.setA((byte)0x1D);
		assertThat(r.getA(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetB(){
		Registers r = new Registers();
		assertThat(r.getB(), is(equalTo((byte)0)));
		r.setB((byte)0xA);
		assertThat(r.getB(), is(equalTo((byte)10)));
		r.setB((byte)0x1D);
		assertThat(r.getB(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetC(){
		Registers r = new Registers();
		assertThat(r.getC(), is(equalTo((byte)0)));
		r.setC((byte)0xA);
		assertThat(r.getC(), is(equalTo((byte)10)));
		r.setC((byte)0x1D);
		assertThat(r.getC(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetD(){
		Registers r = new Registers();
		assertThat(r.getD(), is(equalTo((byte)0)));
		r.setD((byte)0xA);
		assertThat(r.getD(), is(equalTo((byte)10)));
		r.setD((byte)0x1D);
		assertThat(r.getD(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetE(){
		Registers r = new Registers();
		assertThat(r.getE(), is(equalTo((byte)0)));
		r.setE((byte)0xA);
		assertThat(r.getE(), is(equalTo((byte)10)));
		r.setE((byte)0x1D);
		assertThat(r.getE(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetF(){
		Registers r = new Registers();
		assertThat(r.getF(), is(equalTo((byte)0)));
		r.setF((byte)0xA);
		assertThat(r.getF(), is(equalTo((byte)10)));
		r.setF((byte)0x1D);
		assertThat(r.getF(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetH(){
		Registers r = new Registers();
		assertThat(r.getH(), is(equalTo((byte)0)));
		r.setH((byte)0xA);
		assertThat(r.getH(), is(equalTo((byte)10)));
		r.setH((byte)0x1D);
		assertThat(r.getH(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetL(){
		Registers r = new Registers();
		assertThat(r.getL(), is(equalTo((byte)0)));
		r.setL((byte)0xA);
		assertThat(r.getL(), is(equalTo((byte)10)));
		r.setL((byte)0x1D);
		assertThat(r.getL(), is(equalTo((byte)0x1D)));
	}
	@Test
	public void testGetSetSp(){
		Registers r = new Registers();
		assertThat(r.getSp(), is(equalTo(0)));
		r.setSp(0xFFFE);
		assertThat(r.getSp(), is(equalTo(0xFFFE)));
		r.setSp(0x10000);
		assertThat(r.getSp(), is(equalTo(0)));
	}
	@Test
	public void testGetSetPc(){
		Registers r = new Registers();
		assertThat(r.getPc(), is(equalTo(0)));
		r.setPc(0xFFFE);
		assertThat(r.getPc(), is(equalTo(0xFFFE)));
		r.setPc(0x10000);
		assertThat(r.getPc(), is(equalTo(0)));
	}
	@Test
	public void testSetBc(){
		Registers r = new Registers();
		assertThat(r.getB(), is(equalTo((byte)0)));
		assertThat(r.getC(), is(equalTo((byte)0)));
		r.setBc(0xAABB);
		assertThat(r.getB(), is(equalTo((byte)0xBB)));
		assertThat(r.getC(), is(equalTo((byte)0xAA)));
	}
	@Test
	public void testSetAF(){
		Registers r = new Registers();
		assertThat(r.getA(), is(equalTo((byte)0)));
		assertThat(r.getF(), is(equalTo((byte)0)));
		r.setAf(0xFFEE);
		assertThat(r.getA(), is(equalTo((byte)0xEE)));
		assertThat(r.getF(), is(equalTo((byte)0xFF)));
	}
	@Test
	public void testSetDe(){
		Registers r = new Registers();
		assertThat(r.getD(), is(equalTo((byte)0)));
		assertThat(r.getE(), is(equalTo((byte)0)));
		r.setDe(0xCC11);
		assertThat(r.getD(), is(equalTo((byte)0x11)));
		assertThat(r.getE(), is(equalTo((byte)0xCC)));
	}
	@Test
	public void testSetHl(){
		Registers r = new Registers();
		assertThat(r.getH(), is(equalTo((byte)0)));
		assertThat(r.getL(), is(equalTo((byte)0)));
		r.setHl(0x8822);
		assertThat(r.getH(), is(equalTo((byte)0x22)));
		assertThat(r.getL(), is(equalTo((byte)0x88)));
	}
	@Test
	public void testZeroBit(){
		Registers r = new Registers();
		r.setZeroBit();
		assertThat(r.isZeroBitSet(), is(true));
		r.clearZeroBit();
		assertThat(r.isZeroBitSet(), is(false));
	}
	@Test
	public void testSubtractBit(){
		Registers r = new Registers();
		r.setSubtractBit();
		assertThat(r.isSubtractBitSet(), is(true));
		r.clearSubtractBit();
		assertThat(r.isSubtractBitSet(), is(false));
	}
	@Test
	public void testCarryBit(){
		Registers r = new Registers();
		r.setCarryBit();
		assertThat(r.isCarryBitSet(), is(true));
		r.clearCarryBit();
		assertThat(r.isCarryBitSet(), is(false));
	}
	@Test
	public void testHalfCarryBit(){
		Registers r = new Registers();
		r.setHalfCarryBit();
		assertThat(r.isHalfCarryBitSet(), is(true));
		r.clearHalfCarryBit();
		assertThat(r.isHalfCarryBitSet(), is(false));
	}
	@Test
	public void testSetFlagBits(){
		Registers r = new Registers();
		r.setZeroBit();
		r.setHalfCarryBit();
		r.setCarryBit();
		r.setSubtractBit();
		assertThat(r.getF(), is(equalTo((byte)0xF0)));
		r.clearFlagBits();
		assertThat(r.getF(), is(equalTo((byte)0x00)));
	}
}
