package dataStructures;

import dataStructures.MyHashMap.Employee;

class Entry {
		Employee key;
	    String value;
	    Entry next;
	 
	    Entry(Employee k, String v) {
	        key = k;
	        value = v;
	    }
	 
	    
	    public String getValue() {
	        return value;
	    }
	 
	    public void setValue(String value) {
	        this.value = value;
	    }
	 
	    public Employee getKey() {
	        return key;
	    }
	}
