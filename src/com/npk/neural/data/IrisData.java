package com.npk.neural.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IrisData {

	private static final String IRIS = "dataset/iris.data";
	private static final String TEST = "dataset/test.data";
	
	private BufferedReader reader;
	private double[][] dataArray;
	
	private int rows;
	private int columns;
	
	private ArrayList<String> alist;
	/*
	 * 
	 * Read file iris.data without classification info
	 * 
	 * */
	private ArrayList<String> readFile(String file, int index){
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			alist = new ArrayList<String>();
			
				while ((line = reader.readLine()) != null){
					
					String[] aline = line.split(" ");
					for (int i=0; i<index; i++)
						alist.add(aline[i]);
				}
		
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return alist;
		
	}
	
	public double[][] getDataArray(String type, int index){
		ArrayList<String> alist;
		
		if (type.equalsIgnoreCase("full")){
			alist = readFile(IRIS, index);
		}else{
			alist = readFile(TEST, index);
		}
		rows = alist.size() / index;
		columns = index;
		dataArray = new double[rows][columns];
		
		for (int i=0;i<rows;i++){
			for (int j=0;j<columns;j++){
				dataArray[i][j] = Double.parseDouble(alist.get(i*columns + j));
			}
		}
		
		return dataArray;
	}
	
	public double[][] getDataArray(String type){
		
		ArrayList<String> alist;
		
		if (type.equalsIgnoreCase("full")){
			alist = readFile(IRIS, 5);
		}else{
			alist = readFile(TEST, 5);
		}
		
		rows = alist.size() / 5;
		columns = 5;
		dataArray = new double[rows][columns];
		
		for (int i=0;i<rows;i++){
			for (int j=0;j<columns;j++){
				dataArray[i][j] = Double.parseDouble(alist.get(i*columns + j));
			}
		}
		
		return dataArray;
	}
}
