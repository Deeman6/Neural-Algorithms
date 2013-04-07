package com.npk.neural.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IrisData {

	private static final String IRIS = "dataset/iris.data";
	
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
	
	public double[][] getDataArray(int index){
		ArrayList<String> alist = readFile(IRIS, index);
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
}
