package com.mainacad.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandomizerTest {

  @Test
  void testGetRandomNumber() {
    Integer testNumber = Randomizer.getRandomNumber(50, 100);
    assertNotNull(testNumber);
    assertTrue(testNumber >= 50 & testNumber <= 100);
  }

  @Test
  void testGetRandomString() {
    String testString = Randomizer.getRandomString(20);
    assertNotNull(testString);
    assertEquals(20, testString.length());
  }
}