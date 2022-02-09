package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

/**
 * authors: Alexander Varga, Dominic Vanderkerkhove, Cedric John Acierto, Ivan Tompong
 * course: SENG438 - Assignment 2
 */

public class RangeTest {
    private Range exampleRangeAlex;
    private Range exampleRangeCedric;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	exampleRangeAlex = new Range(-1, 1);
    	exampleRangeCedric = new Range(10.0, 20.0);
    }


    @Test
    public void testCentralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRangeCedric.getCentralValue(), .000000001d);
    }
    
    @Test
    public void testIntersectsLessThanRange() {
        //This test should return false because it does not intersect with
        //exampleRange.
        assertFalse("8.0-9.0 does not intersect", 
        		exampleRangeCedric.intersects(8.0, 9.0));
    }
    
    @Test
    public void testIntersectsTouchesLowerBound() {
        //This test should return true because intersects with exampleRange.
        assertTrue("9.0-10.0 intersects range", 
        		exampleRangeCedric.intersects(9.0, 10.0));
    }
    
    @Test
    public void testIntersectsWithinRange() {
        //This test should return true because intersects with exampleRange.
        assertTrue("15.0-16.0 intersects range", 
        		exampleRangeCedric.intersects(15.0, 16.0));
    }
    
    @Test
    public void testIntersectsTouchesUpperBound() {
        //This test should return true because intersects with exampleRange.
        assertTrue("20.0-21.0 intersects range",
        		exampleRangeCedric.intersects(20.0, 21.0));
    }
    
    @Test
    public void testIntersectsMoreThanRange() {
        //This test should return false because it does not intersect with
        //exampleRange.
        assertFalse("21.0-22.0 does not intersect range",
        		exampleRangeCedric.intersects(21.0, 22.0));
    }
    
    @Test
    public void testConstrainLessThanLowerBound() {
        //Tests for nearest value to 8.0 in the range.
        assertEquals("Nearest value should be lower bound.",
                10.0, exampleRangeCedric.constrain(8.0), 0.000000001d);
    }
    
    @Test
    public void testConstrainAtLowerBound() {
        //Tests for nearest value to 10.0 in the range.
        assertEquals("Nearest value should be lower bound.",
                10.0, exampleRangeCedric.constrain(10.0), 0.000000001d);
    }
    
    @Test
    public void testConstrainWithinRange() {
        //Tests for nearest value to 15.0 in the range.
        assertEquals("Nearest value should be the value itself",
                15.0, exampleRangeCedric.constrain(15.0), 0.000000001d);
    }
    
    @Test
    public void testConstrainAtUpperBound() {
        //Tests for nearest value to 20.0 in the range.
        assertEquals("Nearest value should be upper bound.",
                20.0, exampleRangeCedric.constrain(20.0), 0.000000001d);
    }
    
    @Test
    public void testConstrainMoreThanUpperBound() {
        //Tests for nearest value to 22.0 in the range.
        assertEquals("Nearest value should be upper bound.", 
                20.0, exampleRangeCedric.constrain(22.0), 0.000000001d);
    }
    
    @Test
    public void testRangeContainsTrue()
    {
    	//check for valid input in Range, expect true
    	assertTrue(exampleRangeAlex.contains(1.0));
    }
    
    @Test
    public void testRangeContainsFalse()
    {
    	//check for invalid input in Range, expect false
    	assertFalse(exampleRangeAlex.contains(2.0));
    }
    
    @Test
    // test getting a negative lower bound
    public void testNegativeGetLowerBound() {    
        
        // establish range with negative lower bound
        Range negLower = new Range(-10, 0);
        
        // use getLowerBound() to retrieve lower bound
        double lowerBound = negLower.getLowerBound();
        
        // assert the lower bound matches the expected value
        assertEquals("Expecting -10, got " +lowerBound, -10, lowerBound, .000000001d);
    }
    
    @Test
    // test getting a positive lower bound
    public void testPositiveGetLowerBound() {

        // establish range with positive lower bound
        Range posLower = new Range(10, 20);
        
        // use getLowerBound() to retrieve lower bound
        double lowerBound = posLower.getLowerBound();
        
        // assert the lower bound matches the expected value
        assertEquals("Expecting 10, got " +lowerBound, 10, lowerBound, .000000001d);
    }
    
    @Test
    // test getting a lower bound of 0
    public void testZeroGetLowerBound() {
        
        // establish range with a lower bound of 0
        Range zeroLower = new Range(0, 10);
        
        // use getLowerBound() to retrieve lower bound        
        double lowerBound = zeroLower.getLowerBound();
        
        // assert lower bound is 0
        assertEquals("Expecting 0, got " +lowerBound, 0, lowerBound, .000000001d);
    }
    
    @Test
    // test getting a negative upper bound
    public void testNegativeGetUpperBound() {
        
        //establish range with a negative upper bound
        Range negUpper = new Range(-20, -10);
        
        // use getUpperBound() to retrieve upper bound
        double upperBound = negUpper.getUpperBound();
        
        // assert the upper bound matches the expected value
        assertEquals("Expecting -10, got " +upperBound, -10, upperBound, .000000001d);
    }
    
    @Test
    // test getting a positive upper bound
    public void testPositiveGetUpperBound() {    
        
        //establish range with a positive upper bound
        Range posUpper = new Range(0, 10);
        
        // use getUpperBound() to retrieve upper bound
        double upperBound = posUpper.getUpperBound();
        
        // assert the upper bound matches the expected value
        assertEquals("Expecting 10, got " +upperBound, 10, upperBound, .000000001d);
    }
    
    @Test
    // test getting an upper bound of 0
    public void testZeroGetUpperBound() {    
        
        //establish range with an upper bound of 0
        Range zeroUpper = new Range(-10, 0);
        
        // use getUpperBound() to retrieve upper bound
        double upperBound = zeroUpper.getUpperBound();
        
        // assert upper bound is 0
        assertEquals("Expecting 0, got " +upperBound, 0, upperBound, .000000001d);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
