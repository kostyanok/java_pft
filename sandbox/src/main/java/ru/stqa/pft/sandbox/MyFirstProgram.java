package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main (String[] args){
	Point p1  = new Point (-1,3);
	Point p2  = new Point (6,2);
	Point d = new Point (-1,3,6,2);

    System.out.println(distance(p1,p2));
    System.out.println(d.distance());
}
	public static double distance(Point p1, Point p2) {
		return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));

	}
}