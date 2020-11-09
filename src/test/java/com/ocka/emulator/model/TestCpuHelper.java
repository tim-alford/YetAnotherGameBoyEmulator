package com.ocka.emulator.model;
import com.ocka.emulator.helper.TestHelper;
import org.junit.*;
import java.io.*;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.ocka.emulator.model.enumerations.*;
import com.ocka.emulator.model.operations.*;
public class TestCpuHelper {
	private static String CONFIG_PATH = null;
	@BeforeClass	
	public static void setup(){
		CONFIG_PATH = System.getenv("OP_TABLE_CONFIG");
	}
	@Test
	public void testReadLines() throws Exception {
		List<String> lines = CpuHelper.readLines(new File(CONFIG_PATH));
		assertThat(lines.isEmpty(), is(false));
		assertThat(lines.contains("06\tcom.ocka.emulator.model.operations.LoadByte\t8"), is(true));
		assertThat(lines.contains("2E\tcom.ocka.emulator.model.operations.LoadByte\t8"), is(true));
		assertThat(lines.contains("00\tcom.ocka.emulator.model.operations.NoOp\t4"), is(true));
	}
	@Test
	public void testReadOpTableConfig() throws Exception {
		Map<Integer, OperationData> mappings = CpuHelper.readOpTableConfig(CONFIG_PATH);
		assertThat(mappings.size(), is(greaterThan(0)));
		assertThat(mappings.keySet().contains(0x06), is(true));
		assertThat(mappings.keySet().contains(0x0E), is(true));
		assertThat(mappings.keySet().contains(0x16), is(true));
		assertThat(mappings.keySet().contains(0x1E), is(true));
		assertThat(mappings.keySet().contains(0x26), is(true));
		assertThat(mappings.keySet().contains(0x2E), is(true));
		assertThat(mappings.get(0x06).getImplementation(), is(equalTo(LoadByte.class)));
		assertThat(mappings.get(0x00).getImplementation(), is(equalTo(NoOp.class)));
	}
	@Test
	public void testLoadOpTable() throws Exception {
		OperationData opClasses[] = new OperationData[0xFFFF];
		CpuHelper.loadOpTable(opClasses);
		assertThat(opClasses[0x06].getImplementation(), is(equalTo(LoadByte.class)));
	}
}
