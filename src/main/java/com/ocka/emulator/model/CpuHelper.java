// vim: ts=2
package com.ocka.emulator.model;
import java.io.*;
import java.util.*;
public class CpuHelper {
	public static List<String> readLines(File f) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = null;
		List<String> lines = new LinkedList<String>();
		while((line = reader.readLine()) != null){
			lines.add(line);
		}
		return lines;
	}
	private static Class<? extends Operation> getOpClass(String data, int line) throws Exception {
		Class<? extends Operation> opClass = null;
		try{
			opClass = (Class<? extends Operation>) Class.forName(data);
		}catch(Exception e){
			String msg = new StringBuilder().
				append(String.format("Error, line number %d contains an invalid op class. ", line)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);	
		}	
		return opClass;
	}
	private static int getOpCode(String data, int line) throws Exception {
		int opCode = -1;
		try{
			// parse op code, should be base 16 integer
			opCode = Integer.parseInt(data, 16);
		}catch(Exception e){
			String msg = new StringBuilder().
				append(String.format("Error, line number %d doesn't start with a hexadecimal op code. ", line)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);
		}
		return opCode;
	}	
	private static int getCycles(String data, int line) throws Exception {
		int cycles = -1;
		try{
			cycles = Integer.parseInt(data);
		}catch(Exception e){
			String msg = new StringBuilder().
				append(String.format("Error, line number %d doesn't contain a valid cycle count. ", line)).
				append("Please check op table configuration.").
				toString();
			throw new Exception(msg);
		}
		return cycles;
	}
	public static Map<Integer, OperationData> readOpTableConfig(String path) throws Exception {
		File f = new File(path);
		if(!f.exists()){
			String msg = new StringBuilder().
				append("Error, the file \"").
				append(f.getAbsolutePath()).
				append("\" does not exist. Check \"OP_TABLE_CONFIG\" environment variable.").
				toString();
			throw new Exception(msg);
		}
		int n = 0;
		final int REQUIRED_TOKENS = 3; // op code, class, cycles
		Map<Integer, OperationData> results = new HashMap<>();
		for(String s : readLines(f)){
			String[] tokens = s.split("\t");
			if(tokens.length != REQUIRED_TOKENS){
				String msg = new StringBuilder().
					append(String.format("Error, line number %d isn't composed of two tokens. ", n)).
					append("Please check op table configuration.").
					toString();
				throw new Exception(msg);
			}
			int opCode = getOpCode(tokens[0], n);
			Class<? extends Operation> opClass = getOpClass(tokens[1], n);
			int cycles = getCycles(tokens[2], n);
			OperationData data = new OperationData();
			data.setCycles(cycles);
			data.setImplementation(opClass);
			results.put(new Integer(opCode), data);
			n++;	
		}
		return results;
	}	
	public static void loadOpTable(OperationData opTable[]) throws Exception {
		String path = System.getenv("OP_TABLE_CONFIG");
		if(path == null){
			String msg = new StringBuilder().
				append("Error, the \"OP_TABLE_CONFIG\" environment variable is not defined, ").
				append("please source env.sh before running.").
				toString();
			throw new Exception(msg);
		}	
		Map<Integer, OperationData> config = readOpTableConfig(path);
		for(Integer opCode : config.keySet()){
			opTable[opCode.intValue()] = config.get(opCode); 
		}
	}
}
