package com.ocka.emulator.model;
import com.ocka.emulator.helper.TestHelper;
import org.junit.*;
import java.io.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.ocka.emulator.model.enumerations.*;
public class TestCartridge {
	private static Cartridge r = null;
	@BeforeClass
	public static void readCartridge(){
		try{
			File f = TestHelper.getTestCartridge();	
			TestCartridge.r = Cartridge.open(f);
		}catch(Exception x){
			throw new RuntimeException("Failed to read Cartridge", x);
		}
	}
	@Test
	public void testRomType() throws Exception {
		assertThat(r.getType(), is(not(equalTo(Cartridge.UNKNOWN))));
		assertThat(r.getType(), is(equalTo((byte)(0x13)))); // Cartridge+MBC3+RAM+BATT
		assertThat(CartridgeType.values[r.getType()].getName(), is(equalTo("ROM+MBC3+RAM+BATT")));
	}
	@Test
	public void testGetByte(){
		assertThat(r.getByte(0x0134), is(equalTo((byte)0x50)));
		char c = (char) r.getByte(0x0134);
		assertThat(c, is(equalTo('P')));
	}
	@Test
	public void testRomTitle() throws Exception {
		assertThat(r.getTitle(), is(not(nullValue())));
		assertThat(r.getTitle().charAt(0), is(equalTo('P')));
		assertThat(r.getTitle().contains("POKEMON BLUE"), is(true));
	}
	@Test
	public void testIsColour(){
		assertThat(r.isColour(), is(false));
	}
	@Test
	public void testRomSize(){
		assertThat(r.getRomSize(), is(not(Cartridge.UNKNOWN)));
		assertThat(r.getRomSize(), is(equalTo((byte)5))); // 1MB Cartridge
		assertThat(RomSize.values[r.getRomSize()].getName(), is(equalTo("1MB")));
	}
	@Test
	public void testRamSize(){
		assertThat(r.getRamSize(), is(not(Cartridge.UNKNOWN)));
		assertThat(r.getRamSize(), is(equalTo((byte)3))); // 32KB RAM
		assertThat(RamSize.values[r.getRamSize()].getName(), is(equalTo("32KB")));
	}
	@Test
	public void testChecksum(){
		assertThat(r.getChecksum(), is(not(nullValue())));
		assertThat(r.getChecksum()[0], is(equalTo((byte)0x9D)));
		assertThat(r.getChecksum()[1], is(equalTo((byte)0x0A)));
		assertThat(r.isChecksumValid(), is(false)); // GB ignores this ...
		assertThat(r.isPowerUpChecksumValid(), is(true));
	}
	
}
