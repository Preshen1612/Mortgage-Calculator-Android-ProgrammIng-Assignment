Description:
I have implemented a single-activity Android application using a handful of basic controls. I have used the following concepts of Android Application Development in this project: the use of Android Studio, basic UI elements, and the Android documentation.

View Layout:
The view has the following elements:

Amount Borrowed:
This is an EditText into which the user will enter the amount to be borrowed as a floating-point value (e.g., “1000.00”).

Interest Rate:
This is a SeekBar ranging from 0.0 to 10.0, indicating the annual percentage rate of the interest. The initial value is set to 5.0. As the SeekBar is moved, the displayed interest rate also changes.
The interest rate is represented as decimals, with granularity to two decimal places. 

Loan Term:
This is a RadioGroup with the choices 7, 15, and 30 representing the number of years of the loan.

Taxes and Insurance:
This is a CheckBox that allows the user to select whether taxes and insurance are to be included in the monthly payment. This is a single checkbox.

Calculate:
This is a Button that, when pressed, will calculate the user’s monthly payments based on the values entered.

Monthly Payment:
This is a TextView that displays the monthly payment.

Finally, the Mortgage in dollars is displayed at the bottom of the screen.
