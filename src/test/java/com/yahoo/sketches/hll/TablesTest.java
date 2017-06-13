/*
 * Copyright 2017, Yahoo! Inc. Licensed under the terms of the
 * Apache License 2.0. See LICENSE file at the project root for terms.
 */

package com.yahoo.sketches.hll;

import static com.yahoo.sketches.hll.Tables.couponMappingXarr;
import static com.yahoo.sketches.hll.Tables.couponMappingYarr;
import static com.yahoo.sketches.hll.Tables.cubicInterpolateUsingTable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import com.yahoo.sketches.SketchesArgumentException;
/**
 * @author Lee Rhodes
 *
 */
public class TablesTest {

  @Test
  public void checkInterpolationExceptions() {
    try {
      cubicInterpolateUsingTable(couponMappingXarr, couponMappingYarr, -1);
      fail();
    } catch (SketchesArgumentException e) {
      //expected
    }
    try {
      cubicInterpolateUsingTable(couponMappingXarr, couponMappingYarr, 11000000.0);
      fail();
    } catch (SketchesArgumentException e) {
      //expected
    }
  }

  @Test
  public void checkCornerCases() {
    int len = couponMappingXarr.length;
    double x = couponMappingXarr[len - 1];
    double y = cubicInterpolateUsingTable(couponMappingXarr, couponMappingYarr, x);
    double yExp = couponMappingYarr[len - 1];
    assertEquals(y, yExp, 0.0);
  }

  @Test
  public void printlnTest() {
    println("PRINTING: "+this.getClass().getName());
  }

  /**
   * @param s value to print
   */
  static void println(String s) {
    //System.out.println(s); //disable here
  }

}
