**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group \#:      | 37                   |
| -------------- | ---                  |
| Student Names: | Dominic Vandekerkhove|
|                | Alexander Varga      |
|                | John Cedric Acierto  |
|                | Ivan Tompong         |

# 1 Introduction

This assignment provides a solid foundation for gaining experience with automated requirement-based testing. The system under test is a charting simulator that calculates, creates and displays various styles of charts and graphs. We will design test cases for various functions based on the provided documentation. To develop and perform the unit tests, we will use the JUnit framework while utilizing stubbing and mocking to simulate various environments. 

# 2 Detailed description of unit test strategy

Our testing process consists of analysis, documentation and execution. For each function being tested, the tester rigorously analyzes the documentation provided. This allows the tester to understand how the function arguments are handled, where to establish boundaries, and how the function is expected to perform. Once the tester understands the functionality of the method under test, they divide the inputs into equivalence classes by establishing boundary values. Unit tests are developed for each of these equivalence classes. While testing methods with multiple input variables, we intend to utilize strong equivalence class testing. Although this requires more test cases and thus takes more time, we believe it provides a more thorough test suite. We also intend to test invalid inputs that are not within the range of acceptable boundaries to incorporate robustness testing. In addition, we will also be using mocking for the unit tests of functions that have external dependencies on methods from another class.

A benefit that comes with mocking is that we can isolate the functionalities of the methods being tested from their external dependencies. This restricts the scope of our unit test to within the test class and the class/interface the method we are testing is from. Restricting the scope of our test decreases the likelihood of our tests from failing by reducing the amount of factors that can cause the test to fail. One downside to mocking is that we have to spend additional time writing the tests to set up the mock object.

[Tests 1-3] Range.getLowerBound(): this function involves returning the lower bound of a Range object. The tests regarding this function ensure that the correct lower bound is returned when the method is called. Since the lower bound is stored as a double, a partition was placed at lowerBound = 0 creating two equivalence classes; lowerBound > 0 and lowerBound < 0. Thus, tests were conducted on both positive and negative numbers to ensure the correct value is returned, regardless of the sign. These tests are called positiveGetLowerBoundsTest() and negativeGetLowerBoundsTest() respectively. A third test was conducted on lowerBound=0 for the sake of testing the partition. This test is called zeroGetLowerBoundsTest()

[Tests 4-6] Range.getUpperBound(): the testing of this function followed the same strategy described for getLowerBound(). The tests conducted on positive and negative upper bounds are called positiveUpperBoundsTest() and negativeUpperBoundsTest() respectively. The test conducted when upperBound = 0 is called zeroUpperBoundsTest(). 

[Tests 7-11] Range.intersects(double lower, double upper): This function returns true if the input range intersects with the specified range (i.e. exampleRange) and false otherwise, so the function was tested using test ranges which were out of the exampleRange’s bounds, touching the bounds, and within bounds. Two test cases were developed to check for ranges outside exampleRange’s bounds (one lower and one higher). Two test cases were developed to check for ranges touching the lower and upper bounds. One test case was developed to check for ranges completely within exampleRange. 

[Tests 12-16] Range.constrain(double value): This function returns the value within the range that is closest to the specified value. The tests were developed in a similar way to intersect(), where values lower than the range, higher than the range, and within the range were used. Two tests were developed for values lower and higher than the range specified. Two tests were developed to check if the function works at the bounds, both lower and upper. One test was developed to check if the function works within the range specified. 

[Tests 17-18]  Range.contains(double value): This function returns true if the specified value is within the range; otherwise, false. The tests were developed as two Boundary tests; one where the value is contained within the range, expecting true, and another where the value is not within the range, therefore expecting false. From the documentation, we have two partitions
Values contained within the range
Values not contained within the range
Thus, two tests were developed for values contained in and out of the Range set specified in the testing setup. The Range instance setup has a range from (-1.0,1.0), where the first test, tests if the range contains(1.0) and the other test, tests if the range contains(2.0).

[Tests 19-22] DataUtilities.calculateColumnTotal(Values2D data, int column): This function is supposed to return the sum of the values in one column of the supplied Values2D data table. According to the documentation, this method is supposed to return zero if there is invalidinput. From the documentation, we have four partitions
Valid data table + valid column #
Valid data table + invalid column #
Invalid data table + valid column #
“NULL” input
We only need two test three of the partitions, which are the  valid data table + valid column #, Valid data table + invalid column #, and the NULL input partition. We do not need to perform unit tests for the Invalid data table + valid column # because this error is caught by Eclipse and will notify the programmer right away. 

[Tests 22-26] DataUtilities.calculateRowTotal(Values2D data, int row): This function is supposed to return the sum of the values in one row of the supplied Values2D data table. According to the documentation, this method is supposed to return zero if there is invalidinput. From the documentation, we have four partitions
Valid data table + valid row #
Valid data table + invalid row #
Invalid data table + valid row #
“NULL” input
We only need two test three of the partitions, which are the  valid data table + valid row #, Valid data table + invalid row #, and the NULL input partition. We do not need to perform unit tests for the Invalid data table + valid row # because this error is caught by Eclipse and will notify the programmer right away. 


[Tests 27-28] DataUtilities.createNumberArray2D(double[][] data): This function is supposed to take in a 2D-Array of doubles and convert it into a 2D-Array of Number objects. According to the documentation, this method is supposed to throw an InvalidParameterException if an invalid data object is passed in. From the documentation, we have three partitions
2D-Arrays that are type double
2D-Arrays that are not type double
“Null” input
We only need two test two of the partitions, which are the 2D-Arrays that are type double and the “Null” input partition. It is not necessary to perform unit tests for the 2D-Arrays that are not of type double inputs because this error is immediately caught by Eclipse and will notify the programmer right away. The compiler will also catch this type of error and will not allow you to run the program.

[Tests 29-30] DataUtilities.createNumberArray(double[] data): this function returns the data array converted into an array of Number objects. The documentation notes that NULL is a non-permissible value. So, tests were conducted on invalid inputs (data = NULL) and on valid inputs (data = {1.0, 2.0, 3.0, 4.0, 5.0} ). Regarding invalid inputs, the test ensures that an IllegalArgumentException occurred when attempting the operation. This test is called nullCreateNumberArrayTest(). For valid inputs, the test ensures that each value in the newly created number array is not NULL, and matches the values in the data argument. This test is called validCreateNumberArrayTest(). 

[Tests 31-32] DataUtilities.getCumulativePercentages(KeyedValues data): This function is supposed to take in a KeyedValues object, calculate the cumulative percentage values for that object, and return it as another KeyedValues object. Example:
Input:
Key  Value
0        5
1        9
2        2

Returns:
Key  Value
0     0.3125 (5 / 16)
1     0.875 ((5 + 9) / 16)
2     1.0 ((5 + 9 + 2) / 16)
According to the documentation, a null input is not permitted and an InvalidParameterException should be thrown for any invalid input. Here we only need to test two inputs, the null input and a valid KeyedValues input. We do not need to test for inputs that are non-null but not KeyedValues objects because this is caught by the IDE and compiler and causes an immediate error and stops you from running the program.

# 3 Test cases developed
| Test   No. | Class         | Function   Under Test                             | Input   Variables                                         | Expected   Outcome                                                                                   | Actual   Outcome                                                                                            | Result |
|------------|---------------|---------------------------------------------------|-----------------------------------------------------------|------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|--------|
| 1          | Range         | getLowerBound()                                   | lowerBound   = -10                                        | Returns   -10                                                                                        | Returns   -10                                                                                               | PASS   |
| 2          | Range         | getLowerBound()                                   | lowerBound   = 0                                          | Returns   0                                                                                          | Returns   0                                                                                                 | PASS   |
| 3          | Range         | getLowerBound()                                   | lowerBound   = 10                                         | Returns   10                                                                                         | Returns   10                                                                                                | PASS   |
| 4          | Range         | getUpperBound()                                   | upperBound   = -10                                        | Returns   -10                                                                                        | Returns   lower bound                                                                                       | FAIL   |
| 5          | Range         | getUpperBound()                                   | upperBound   = 0                                          | Returns   0                                                                                          | Returns   lower bound                                                                                       | FAIL   |
| 6          | Range         | getUpperBound()                                   | upperBound   = 10                                         | Returns   10                                                                                         | Returns   lower bound                                                                                       | FAIL   |
| 7          | Range         | intersects(double   lower, double upper)          | lower   = 8.0      upper = 9.0                            | Returns   false                                                                                      | Returns   true                                                                                              | FAIL   |
| 8          | Range         | intersects(double   lower, double upper)          | lower   = 9.0      upper = 10.0                           | Returns   true                                                                                       | Returns   true                                                                                              | PASS   |
| 9          | Range         | intersects(double   lower, double upper)          | lower   = 15.0      upper = 16.0                          | Returns   true                                                                                       | Returns   true                                                                                              | PASS   |
| 10         | Range         | intersects(double   lower, double upper)          | lower   = 20.0      upper = 21.0                          | Returns   true                                                                                       | Returns   false                                                                                             | FAIL   |
| 11         | Range         | intersects(double   lower, double upper)          | lower   = 21.0      upper = 22.0                          | Returns   false                                                                                      | Returns   false                                                                                             | PASS   |
| 12         | Range         | constrain(double   value)                         | value   = 8.0                                             | Returns   10.0                                                                                       | Returns   15.0                                                                                              | FAIL   |
| 13         | Range         | constrain(double   value)                         | value   = 10.0                                            | Returns   10.0                                                                                       | Returns   10.0                                                                                              | PASS   |
| 14         | Range         | constrain(double   value)                         | value   = 15.0                                            | Returns   15.0                                                                                       | Returns   15.0                                                                                              | PASS   |
| 15         | Range         | constrain(double   value)                         | value   = 20.0                                            | Returns   20.0                                                                                       | Returns   20.0                                                                                              | PASS   |
| 16         | Range         | constrain(double   value)                         | value   = 22.0                                            | Returns   20.0                                                                                       | Returns   20.0                                                                                              | PASS   |
| 17         | Range         | contains(double   value)                          | Value   = 1.0                                             | Returns   true                                                                                       | Returns   true                                                                                              | PASS   |
| 18         | Range         | contains(double   value)                          | Value   = 2.0                                             | Returns   false                                                                                      | Returns   false                                                                                             | PASS   |
| 19         | DataUtilities | calculateColumnTotal(Values2D   data, int column) | data   = { {7.0, 0}, {2.5,0} }                            | Returns   9.5                                                                                        | Returns   9.5                                                                                               | PASS   |
| 20         | DataUtilities | calculateColumnTotal(Values2D   data, int column) | data   = NULL                                             | Returns   0                                                                                          | Returns   0                                                                                                 | PASS   |
| 21         | DataUtilities | calculateColumnTotal(Values2D   data, int column) | Column   = -1                                             | Returns   0                                                                                          | Returns   0                                                                                                 | PASS   |
| 22         | DataUtilities | calculateColumnTotal(Values2D   data, int column) | data   = { {7.5, 0}, {2.5,0} }                            | Returns   10.0                                                                                       | Returns   10.0                                                                                              | PASS   |
| 23         | DataUtilities | calculateRowTotal(Values2D   data, int row)       | data   = {0, 7.5 }                                        | Returns   7.5                                                                                        | Returns   7.5                                                                                               | PASS   |
| 24         | DataUtilities | calculateRowTotal(Values2D   data, int row)       | data   = { {0, 7.0}, {0, 2.5} }                           | Returns   9.5                                                                                        | Returns   9.5                                                                                               | PASS   |
| 25         | DataUtilities | calculateRowTotal(Values2D   data, int row)       | data   = NULL                                             | Returns   0                                                                                          | Returns   0                                                                                                 | PASS   |
| 26         | DataUtilities | calculateRowTotal(Values2D   data, int row)       | row   = -1                                                | Returns   0                                                                                          | Returns   0                                                                                                 | PASS   |
| 27         | DataUtilities | createNumberArray2D   (double[][] data)           | data   = { {1,2},      {3,4} }                            | Recreates   the 2-Dimensional Array of doubles into a 2-Dimensional Array of Number   objects        | The   2-Dimensional Array returned contains the following;      { {1, null},       {3, null} }              | FAIL   |
| 28         | DataUtilities | createNumberArray2D   (double[][] data)           | data   = null                                             | Throw   IllegalArgument Exception                                                                    | IllegalArgument   Exception thrown                                                                          | PASS   |
| 29         | DataUtilities | createNumberArray      (double[] data)            | Data   = NULL                                             | Illegal      Argument      Exception                                                                 | Illegal      Argument      Exception                                                                        | PASS   |
| 30         | DataUtilities | createNumberArray      (double[] data)            | Data         = {1.0, 2.0, 3.0, 4.0, 5.0}                  | Constructs   an array of number objects (1.0, 2.0, 3.0, 4.0, 5.0)                                    | Constructs   an array of number objects      (1.0, 2.0, 3.0, 4.0, NULL)       Fails to convert last element | FAIL   |
| 31         | DataUtilities | getCumulativePercentages(KeyedValues   data)      | Data   =       Key: Values:      0 10      1 10      2 10 | Return   Value =       Key: Values:      0 0.33333 (10/30)      1 0.66666 (20/30)      2 1.0 (30/30) | ReturnValue   =      Key: Values:      0 0.5      1 1.0      2 1.5                                          | FAIL   |
| 32         | DataUtilities | getCumulativePercentages(KeyedValues   data)      | Data   = null                                             | InvalidParameterException   thrown                                                                   | IllegalArgumentException   is thrown                                                                        | FAIL   |

# 4 How the team work/effort was divided and managed
Each team member chose 2-3 functions to be tested using JUnit, while making sure that 5 of these selected functions are from DataUtilities class and 5 more are from Range class. Each team member was responsible for creating the test cases for the selected function. The expected outputs were based on the function description provided within the documentation page, and each team member was required to read through these. 

# 5 Difficulties encountered, challenges overcome, and lessons learned
One difficulty we encountered occurred during the setup phase for this assignment. Some members were unable to start testing right away because of an inappropriate JDK being set up on their systems. We were however able to overcome this obstacle by consulting with a TA to determine the source of error. 

# 6 Comments/feedback on the lab itself
Overall, this lab provided a great opportunity to gain experience with unit testing and mocking. The lab report was clear and easy to follow. 
