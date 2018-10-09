package ru.stqa.pft.mantis.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testPoint() {
    Point p1  = new Point (-1,3);
    Point p2  = new Point (6,2);
    Assert.assertEquals(p1.distance(p2), 7.0710678118654755);
  }
}
