package ru.stqa.pft.mantis.pft.sandbox;

import java.util.ArrayList;

public class MyFirstProgram {

	public static void main (String[] args){

	Point p1  = new Point (-1,3);
	Point p2  = new Point (6,2);
	int app[] = {3, 5, 7, 8};
		ArrayList<Integer> appList = new ArrayList<Integer>();
		appList.add(3);
		appList.add(5);
		appList.add(7);
		appList.add(8);
	int max = app[0];
	for (int i = 0 ; i < app.length; i ++){
		if (max > app[i]){
			max = app [i];
		}
	}

    System.out.println(max);

    //System.out.println(p1.distance(p2));
}
	public static double distance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));

	}
}