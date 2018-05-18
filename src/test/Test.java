package test;

import java.io.IOException;

import org.apache.log4j.Logger;

public class Test {
	private static Logger logger = Logger.getLogger(Test.class);
//		System.out.println("hello java!");
//		logger.debug("This is debug message!");
//		
//		logger.info("This is info message!");
//		
//		logger.error("This is error message!");
	
	
	public static void main(String[] args) {
		String[] strs1 = new String[2];
		strs1[0] = "axs";
		strs1[1] = "zsd";
		String[] strs2 = new String[2];
		strs2[0] = "axs";
		strs2[1] = "zsd";
		System.out.println(strs1.equals(strs2));
		
		boolean bool = true;
		for (int i = 0; i < strs2.length; i++) {
			if (strs1[i] != strs2[i]) {
				bool = false;
			}
		}
		System.out.println(bool);
	}
}
