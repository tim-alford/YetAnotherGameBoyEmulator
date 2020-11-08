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
	public static Map<Integer, Class<? extends Operation>> readOpTableConfig(String path) throws Exception {
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
		Map<Integer, Class<? extends Operation>> results = new HashMap<>();
		for(String s : readLines(f)){
			String[] tokens = s.split("\t");
			if(tokens.length != 2){
				String msg = new StringBuilder().
					append(String.format("Error, line number %d isn't composed of two tokens. ", n)).
					append("Please check op table configuration.").
					toString();
				throw new Exception(msg);
			}
			int opCode = -1;
			try{
				// parse op code, should be base 16 integer
				opCode = Integer.parseInt(tokens[0], 16);
			}catch(Exception e){
				String msg = new StringBuilder().
					append(String.format("Error, line number %d doesn't start with a hexadecimal op code. ", n)).
					append("Please check op table configuration.").
					toString();
				throw new Exception(msg);
			}
			Class<? extends Operation> opClass = null;
			try{
				opClass = (Class<? extends Operation>) Class.forName(tokens[1]);
			}catch(Exception e){
				String msg = new StringBuilder().
					append(String.format("Error, line number %d contains an invalid op class. ", n)).
					append("Please check op table configuration.").
					toString();
				throw new Exception(msg);	
			}	
			results.put(new Integer(opCode), opClass);
			n++;	
		}
		return results;
	}	
	public static void loadOpTable(Class<?> opTable[]) throws Exception {
		String path = System.getenv("OP_TABLE_CONFIG");
		if(path == null){
			String msg = new StringBuilder().
				append("Error, the \"OP_TABLE_CONFIG\" environment variable is not defined, ").
				append("please source env.sh before running.").
				toString();
			throw new Exception(msg);
		}	
		Map<Integer, Class<? extends Operation>> config = readOpTableConfig(path);
		for(Integer opCode : config.keySet()){
			opTable[opCode.intValue()] = config.get(opCode); 
		}
	}
}
