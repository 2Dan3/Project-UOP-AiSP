package dataStructures;

import java.util.Arrays;


public class MyArrayList {
	
private static final int SIZE_FACTOR=1;
	
	private Object data[];
	
	private int index;
	
	private int size;
	
	public MyArrayList(){
		this.data=new Object[0];
		this.size=0;
	}
	
	public void add(Object obj){
//		System.out.println("index: "+this.index+" size: "+this.size+" data size: "+this.data.length);
		if(this.index == this.size){
			
			increaseSizeAndReallocate();
		}
		data[this.index] = obj;
		this.index++;
		
	}
	
	private void increaseSizeAndReallocate() {
		this.size += SIZE_FACTOR;
		Object newData[]=new Object[this.size];
		for(int i = 0; i < data.length;i++){
			newData[i]=data[i];
		}
		this.data=newData;
		}
	
	public Object get(int i) throws Exception{
		if(i > this.index-1){
			throw new Exception("ArrayIndexOutOfBounds");
		}
		if(i < 0){
			throw new Exception("Negative Value");
		}
		return this.data[i];
		
	}
	
	public void remove(int i) throws Exception{
//		System.out.println("REMOVEindex: "+this.index+" size: "+this.size+" data size: "+this.data.length);
		if(i > this.index - 1){
			throw new Exception("ArrayIndexOutOfBounds");
		}
		if(i < 0){
			throw new Exception("Negative Value");
		}
//		System.out.println("Object getting removed: "+this.data[i]);
		
		Object[] newData = new Object[this.data.length - 1];
		
		for(int j = 0, k = 0; j < this.data.length; j++) {
	        if(j == i)
	            continue;
	        newData[k++] = this.data[j];
	    }
		this.data = newData;
		index-=1;
		size-=1;
				
	}
	
	public int length() {
		return this.data.length;
	}

	public static void main(String args[]) throws Exception {
		
		MyArrayList mal = new MyArrayList();
		
		System.out.println(mal);
		
		mal.add("a");
		mal.add("b");
		mal.add("c");
		mal.add("d");
		mal.add("e");
		mal.add("f");
		mal.add("g");
		mal.add("h");
		mal.add("i");
		mal.add("j");
		
		System.out.println(mal);
		
		System.out.println(mal.get(7));
		
		mal.remove(5);
		
		System.out.println(mal);
		
		mal.add("k");

		System.out.println(mal);
		
		System.out.println(mal.get(7));
		
		System.out.println(mal.length());
		
	}

	@Override
	public String toString() {
		return Arrays.toString(data);
	}
}
