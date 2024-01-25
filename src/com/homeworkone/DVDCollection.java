package com.homeworkone;

import java.io.*;
import java.util.Scanner;

public class DVDCollection {

	// Data fields

	/** The current number of DVDs in the array */
	private int numdvds;

	/** The array to contain the DVDs */
	private DVD[] dvdArray;

	/** The name of the data file that contains dvd data */
	private String sourceName;

	/**
	 * Boolean flag to indicate whether the DVD collection was modified since it was
	 * last saved.
	 */
	private boolean modified;

	/**
	 * Constructs an empty directory as an array with an initial capacity of 7. When
	 * we try to insert into a full array, we will double the size of the array
	 * first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}

	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String st = "numdvds ="+ numdvds +"\n"+
		"dvdArray.length ="+ dvdArray.length +"\n";
		for (int i = 0 ; i < numdvds; ++i) {
			st = st + "dvdArray["+i+"] = "+dvdArray[i].getTitle()+"/"
											+dvdArray[i].getRating()+"/"
												+dvdArray[i].getRunningTime()+"\n";
		}
		
		return st;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		DVD dv = new DVD(title, rating, Integer.parseInt(runningTime));
		int insertIndex = findIndex(title);

//		numdvds = 5
//		0   1	2	3	4	5	6	7	8	9	10
//		a	b	d	e	f	w
//		insertindex = 2; (d)
//		arr[insetindex] =d

		for (int i = numdvds; i > insertIndex; --i) {
			dvdArray[i] = dvdArray[i - 1];
		}
		dvdArray[insertIndex] = dv;
		numdvds++;

	}

	public void removeDVD(String title) {
		for (int i = 0 ; i < numdvds; ++i) {
//			if (title.equals(dvdArray[i].getTitle())) {
//			dvdArray[i] = dvdArray[i+1];	
//			}
			if (title.equals(dvdArray[i].getTitle())) {
				for (int j = i; j < numdvds-1; j++) {
					dvdArray[j] = dvdArray[j+1];
				}	
			}
		}
		numdvds--;
		dvdArray[numdvds]=new DVD("EMPTY", "XYZZY", 00);  // I am very confused in this too. How to do it
		
	}

	public String getDVDsByRating(String rating) {
		
		return null; // STUB: Remove this line.

	}

	public int getTotalRunningTime() {

		return 0; // STUB: Remove this line.

	}

	public void loadData(String filename) {
		sourceName = filename;
		File fs = new File(filename);
		try {
			Scanner sc = new Scanner(fs);
			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine());
				String data = sc.nextLine();
				System.out.println(data);
				String[] parts;
				parts = data.split(",");
				// System.out.println(parts[0]+parts[1]+parts[2]);
				addOrModifyDVD(parts[0], parts[1], parts[2]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save() {
		String data = "";
		try {
			FileWriter fs = new FileWriter(sourceName);
			for (int i = 0; i < numdvds; ++i) {
				data += dvdArray[i].toString();
			}
			fs.write(data);
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Additional private helper methods go here:

	public int findIndex(String title) {

		for (int i = 0; i < numdvds; ++i) {
			int comparision_result = dvdArray[i].getTitle().compareToIgnoreCase(title);
			if (comparision_result > 0) {
				return i;
			}
		}

		return numdvds;

	}

}
