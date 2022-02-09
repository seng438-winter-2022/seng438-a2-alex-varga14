package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.*;
import org.jmock.*;
import org.jmock.api.ExpectationError;
import java.security.InvalidParameterException;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * authors: Alexander Varga, Dominic Vanderkerkhove, Cedric John Acierto, Ivan Tompong
 * course: SENG438 - Assignment 2
 */

public class DataUtilitiesTest 
{
	 @Test
	 public void testCalculateColumnTotalForTwoValue(){
	     // Setup Mockery and JMock context with interface
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     // Initialize Mock expectations
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });
	     //Execute 	
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     // verify
	     assertEquals(result, 10.0, .000000001d);
	     // tear-down: NONE in this test method
	 }
	 
	 @Test
	 public void testCalculateColumnTotalForTwoValueFalse(){
		// Setup Mockery and JMock context with interface
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	  // Initialize Mock expectations
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.0));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });	
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertFalse(result == 10.0);
	 }
	 
	 //Test expects NULL
	 @Test (expected = NullPointerException.class)
	 public void testCalculateColumnTotalNullInput(){
	     final Values2D values = null;
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals("Zero should be returned on NULL", 0, result);
	 }
	 
	//Test expects Invalid Input
	 @Test (expected = ExpectationError.class)
	 public void testCalculateColumnTotalInvalidInput(){
		// Setup Mockery and JMock context with interface
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     // Initialize Mock expectations
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(1, 0);
	             will(returnValue(2.5));
	         }
	     });	
	     double result = DataUtilities.calculateColumnTotal(values, -1);
	     assertEquals("Zero should be returned on Invalid Input", 0, result);
	 }
	 
	 
	 @Test
	 public void testCalculateRowTotalForOneValue(){
		// Setup Mockery and JMock context with interface
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     // Initialize Mock expectations
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	         }
	     });
	     // exercise	
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     // verify
	     assertEquals(result, 7.5, .000000001d);
	     // tear-down: NONE in this test method
	 }
	 
	 @Test
	 public void testCalculateRowTotalTwoValueFalse(){
		// Setup Mockery and JMock context with interface
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     // Initialize Mock expectations
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.0));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertFalse(result == 10.0);
	 }
	 
	 @Test
	    //test a valid double[] input 
	    public void testValidCreateNumberArray() {
	        // reference number array to compare to 
	        Number[] numArray = {1.0, 2.0, 3.0, 4.0, 5.0};
	        
	        // double array to convert to number array
	        double[] dubArray = {1.0, 2.0, 3.0, 4.0, 5.0};
	        
	        // convert double array to number array
	        Number[] test = DataUtilities.createNumberArray(dubArray);
	        
	        // assert each value in number array is not null and matches expected array
	        for (int i = 0; i < 5; i++) {
	            assertNotNull("Null Value at index " +i, test[i]);
	            assertEquals("Expected" +numArray[i].doubleValue() +", got " +test[i].doubleValue(),
	                    numArray[i].doubleValue(), test[i].doubleValue(), .000000001d);
	        }
	    }
	    
	    @Test (expected = IllegalArgumentException.class)
	    // test a NULL input, should return IllegalArgumenException
	    public void testNullCreateNumberArray() {
	        
	        // null input 
	        double[] dubArray = null;
	        
	        // attempt to convert null input
	        Number[] test = DataUtilities.createNumberArray(dubArray);
	    }
     
	    @Test
		public void testCreateNumberArray2DTestIfAssignmentWorks() {
			/*
			 * This test case tests to see if the method createNumberArray2D successfully creates an 
			 * exact (good)copy of the contents of the 2D array of doubles provided
			 * as a 2D array of Number objects instead
			 */
			
			double data[][] = {{1,2}, {3,4}}; //Input Value
			Number returnVal[][]; //Stores the return value
			
			returnVal = DataUtilities.createNumberArray2D(data); //Calls Function
			
			for(int row = 0; row <data.length ; row++) {
				/*
				 * Iterates through the input 2D array of doubles and the 2D array of Number objects
				 */
				for(int col = 0; col < data[row].length; col++) {			
					/*
					 * Asserts that the values of both arrays are the exact same 
					*/
					assertEquals("Values inside the array 'returnVal' should be the same as 'data'\n",
								data[row][col], returnVal[row][col]);				
				}
			}

			
		}
		
		@Test (expected = IllegalArgumentException.class)
		public void testCreateNumberArray2DTestIfFunctionRejectsNullInput() {
			/*
			 * This test case is supposed to test if the function createNumberArray2D 
			 * rejects a null argument. The function should throw an IllegalArgumentException
			 */
			
			double data[][] = null; //Input
			
			//returnVal is null and should remain null since createNumberArray2D should reject the input
			//and not return anything
			Number returnVal[][] = null; 
			
			returnVal = DataUtilities.createNumberArray2D(data);
		
			

		}
		
		@Test
		public void testGetCumulativePercentagesTestIfFunctionWorksProperly() {
			/*
			 * This test case tests to see if getCumulativePercentages(KeyedValues) works properly
			 * if a valid argument is passed into the function
			 */
			
			//Setup the mock for KeyedValues
			//the getCumulativePercentages relies on the KeyedValues interface
			Mockery contextMock = new Mockery();
			KeyedValues mock = contextMock.mock(KeyedValues.class); 
			
			contextMock.checking(new Expectations() {{  
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				/*
				 * First Set of getValue calls. These are responsible for providing
				 * the values for the sum of all the values, it is the denominator for calculating
				 * the percentages ( % = (numerator)/ (sum of all values)  )
				 */
				one(mock).getValue(1); //Get Value
				will(returnValue(10));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				one(mock).getValue(2); //Get Value
				will(returnValue(10));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				/*
				 * Second set of getValue calls. These are responsible for providing the 
				 * values' cumulative sum, i.e. the sum of all the values up until that index,
				 * which is used as the numerator of for calculating the percentages
				 * 
				 * Example: @ index 0: ( % = ([Value in index 0]) / (Sum of all values) )
				 * 			@ index 1: ( % = ( [Value in index 0] + [Value in index 1] ) / (Sum of all values) )
				 * 			@ index 2 : ( % = ( [Value in index 0] + [Value in index 1] + [Value in index 2] ) / (Sum of all values) )
				 */
				one(mock).getValue(0); //Get Value
				will(returnValue(10)); 
				
				one(mock).getKey(0); //Get Key
				will(returnValue(0));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				one(mock).getValue(1); //Get Value
				will(returnValue(10));
				
				one(mock).getKey(1); //Get Key
				will(returnValue(1));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
				
				one(mock).getValue(2); //Get Value
				will(returnValue(10));
				
				one(mock).getKey(2);  //Get Key
				will(returnValue(2));
				
				one(mock).getItemCount(); //Get itemCount
				will(returnValue(3));
			}});
			/*
			 * For simplicity, let the mocked KeyedValues object we passed in as an argument contain 10 as the value inside all 
			 * three of its three indexes.
			 */ 
			
			KeyedValues returnVal = DataUtilities.getCumulativePercentages(mock);
			
			/*
			 * Since all the values contained in mock we passed in contains all 10's, 
			 * we can assume the expected sum of all values to be 30. So for the expected
			 * values of the returned KeyedValues object, should be as follows
			 * 
			 * @index 0: % = (10/30) = 0.3333
			 * @index 1: % = ( (10 + 10)/30 ) = 0.6666
			 * @index 2: % = ( (10 + 10 + 10)/30 ) = 1.0
			 */
			double exp0 = (10.0/30.0), exp1 = ( (10.0 + 10.0)/30.0 ), exp2 = ( (10.0 + 10.0 + 10.0)/30.0 );
			
			Number expectedVals[] = {exp0, exp1, exp2};
			
			for(int i = 0; i < expectedVals.length;i++) {
				assertEquals("The percentages were not calculated correctly", expectedVals[i], returnVal.getValue(i));
			}	
		}
		
		@org.junit.Rule
		public ExpectedException Rule = ExpectedException.none(); 

		
		@Test 
		public void testGetCumulativePercentagesTestIfItThrowsInvalidParameterException() {
			/*
			 * This test case should throw an InvalidParameterException when a null object is passed in
			 */
			
			//Setup
			Mockery contextMock = new Mockery();
			KeyedValues mock = null;
			
			Rule.expect(InvalidParameterException.class); //Sets a rule that an InvalidParameterException must be thrown
			
			KeyedValues returnVal = DataUtilities.getCumulativePercentages(mock);
		
		}

}
