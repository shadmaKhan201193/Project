package com.jbpm.Components;

import java.math.BigInteger;

public class Test {

	    public static void main(String[] args) {
	    	String assignedUser = null;
		BigInteger assignedCount = null;
	    	BigInteger minCount = BigInteger.valueOf(Long.MAX_VALUE);
	    	 Object[][]  nodeInstanceLog =  {
	    		    {"aditi", BigInteger.valueOf(4)},
	    		    {"shalini", BigInteger.valueOf(2)},
	    		    {"owner", BigInteger.valueOf(1)}
	    		};
	    	
	    	for (Object[] row : nodeInstanceLog ) {
				String owner = (String) row[0];
				BigInteger count = (BigInteger) row[1];

				if ( count.compareTo(minCount) < 0) {
					minCount = count;
					assignedUser = owner;
					assignedCount = count;
				}
			}
	    	
	    	
	    	System.out.println("hi shaliu");
	}

}

