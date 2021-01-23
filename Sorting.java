// The "Sorting" class.
/*----------------------------------------
Author: Eric Zhou
Date: Nov 7, 2018
Purpose: To complete Sorting exercise.
Input: Keyboard
Output: Console
------------------------------------------*/
import java.awt.*;
import hsa.Console;

public class Sorting
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (25, 100);
	boolean loop = true;
	while (loop == true)
	{
	    SortMethods a = new SortMethods (100);
	    a.reset ();
	    c.println ("Original: sorted=" + a.sorted () + ", " + a);

	    long bubbleTime = a.bubble ();
	    c.println ("Bubble: sorted=" + a.sorted () + ", (After: " + bubbleTime + "ms) " + a);
	    a.reset ();

	    long selectionTime = a.selection ();
	    c.println ("Selection: sorted=" + a.sorted () + ", (After: " + selectionTime + "ms) " + a);
	    a.reset ();

	    long insertionTime = a.insertion ();
	    c.println ("Insertion: sorted=" + a.sorted () + ", (After: " + insertionTime + "ms) " + a);
	    a.reset ();

	    long quickTime = a.quick (0, a.size - 1);
	    c.println ("Quick: sorted=" + a.sorted () + ", (After: " + quickTime + "ms) " + a);
	    a.reset ();
	    
	    c.println ();
	    String userContinue = "Y";
	    do
	    {
		c.print ("Would user like to continue Y/N: ");
		userContinue = c.readLine ();
		if (userContinue.toUpperCase ().equals ("N"))
		{
		    c.println ("Enter 'y' when you want to continue!");
		}

	    }
	    while (!(userContinue.toUpperCase ().equals ("Y")));
	    {
		if (userContinue.toUpperCase ().equals ("Y"))
		{
		    c.clear ();
		}
	    }
	    int size;
	    boolean enterSize = true;

	    do
	    {
		c.print ("Enter an array size to sort a random array (1 - 4000): ");
		size = c.readInt ();
		if (size > 4000 || size < 0)
		{
		    c.println ("Invalid size! Must be greater than 0 and less than 4000!");
		}
	    }
	    while (size > 4000 || size < 0);
	    SortMethods[] database = new SortMethods [10];
	    int doubleSize = size;
	    for (int i = 0 ; i < 10 ; i++)
	    {
		database [i] = new SortMethods (doubleSize);
		database [i].reset ();
		doubleSize *= 2;
	    }

	    c.clear ();

	    String title = "    Size  Quick  Shell  Merge  Insertion  Selection   Bubble    Comb (ms)";
	    c.println (title);
	    boolean bubs = true;
	    boolean select = true;
	    boolean insert = true;
	    boolean combed = true;
	    long timeBubble = 0;
	    long selectTime = 0;
	    long insertTime = 0;
	    long combTime = 0;
	    for (int i = 0 ; i < 10 ; i++)
	    {
		if (bubs == true)
		{
		    timeBubble = database [i].bubble ();
		    database [i].reset ();
		}
		if (select == true)
		{
		    selectTime = database [i].selection ();
		    database [i].reset ();
		}
		if (insert == true)
		{
		    insertTime = database [i].insertion ();
		    database [i].reset ();
		}
		if (combed == true)
		{
		    combTime = database[i].comb();
		    database[i].reset();
		}
		database [i].reset ();
		database [i].graph (bubs, timeBubble, select, selectTime, insert, insertTime,combed,combTime, c);

		if (timeBubble > 10000)
		{
		    bubs = false;
		}
		if (selectTime > 10000)
		{
		    select = false;
		}
		if (insertTime > 10000)
		{
		    insert = false;
		}
		if(combTime > 10000)
		{
		    combed = false;
		}
	    }
	    c.println ();

	    //rerun
	    String rerun = "Y";
	    do
	    {
		c.print ("Would you like to rerun the program Y/N: ");
		rerun = c.readLine ();
	    }
	    while (!(rerun.toUpperCase ().equals ("Y") || rerun.toUpperCase ().equals ("N")));
	    {
		if (rerun.toUpperCase ().equals ("N"))
		{
		    loop = false;
		}
		if (rerun.toUpperCase ().equals ("Y"))
		{
		    c.clear ();
		}
	    }
	}


	// Place your program here.  'c' is the output console
    } // main method
} // Sorting class
/* ----------------------------------------------------------------------
Class: SortMethods
Author: Eric Zhou
Date: 11/7/2018
Purpose: To hold Sorting and Searching Methods.
Data Elements
    original: the original array of random integers.
    aClone: a clone of the original array used by the sorting methods.
    size: size of original.
Methods
    reset: clone original and put that into aClone.
    toString: converts array aClone into a string.
    sorted: returns boolean statement depending on if aClone is sorted.
    bubble: a bubble sort method.
    selection: a selection sort method.
    insertion: a insertion sort method.
    quick: a quick sort method.
    merge: merges the left and right side together.
    shell: a shell sort method.
    comb: a comb sort method.
    graph: graphs all method's time for a certain size
-------------------------------------------------------------------------*/
class SortMethods
{
    protected int[] original;
    protected int[] aClone;
    protected int size;

    public SortMethods (int getSize)
    {
	this.size = getSize;
	this.original = new int [this.size];
	for (int i = 0 ; i < this.size ; i++)
	{
	    this.original [i] = (int) ((Math.random () * this.size - 1) + 1);
	}
    }

    public SortMethods ()
    {
	this.size = 0;
	this.original = new int [0];
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: To clone the original array.
    Input: None
    Output: None
    ---------------------------------*/
    public void reset ()
    {
	this.aClone = new int [this.size];
	for (int i = 0 ; i < this.size ; i++)
	{
	    this.aClone [i] = this.original [i];
	}
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: Converts aClone array into string
    Input: None
    Output: String
    ---------------------------------*/
    public String toString ()
    {
	String array = "";
	for (int i = 0 ; i < this.size ; i++)
	{
	    array += this.aClone [i] + " ";
	}
	return array;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: Sees if aClone is sorted.
    Input: None
    Output: Boolean statement
    ---------------------------------*/
    public boolean sorted ()
    {
	boolean isSorted = true;
	for (int i = 0 ; i < (this.size - 1) ; i++)
	{
	    if (this.aClone [i] > this.aClone [i + 1])
	    {
		isSorted = false;
	    }
	}
	return isSorted;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018y

    Purpose: Bubble sort method.
    Input: None
    Output: Time it takes to sort
    ---------------------------------*/
    public long bubble ()
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	int old, topSorted = 1; //each time, the largest is moved to the end of the array
	for (int count = 0 ; count < this.size; count++) //pass thru array n-1
	{
	    for (int i = 0 ; i < this.size - topSorted; i++)
	    {
		if (this.aClone [i] > this.aClone [i + 1]) //examine 1 and 2, 2 and 3...
		{
		    old = this.aClone [i];
		    this.aClone [i] = this.aClone [i + 1]; //swap
		    this.aClone [i + 1] = old;
		}
	    }
	    topSorted++;
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: Selection sort method.
    Input: None
    Output: Time it takes to sort
    ---------------------------------*/
    public long selection ()
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	//finding the largest
	int largest, old;
	for (int count = this.size - 1 ; count > 0 ; count--)
	{
	    largest = count;
	    for (int i = 0 ; i < count ; i++)
	    {
		if (this.aClone [i] > this.aClone [largest])
		{
		    largest = i; //saving largest index
		}
	    }
	    if (largest != count) //if current count index is not the largest
	    {
		old = this.aClone [count];
		this.aClone [count] = this.aClone [largest];
		this.aClone [largest] = old;
	    }
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: Insertion sort method.
    Input: None
    Output: Time it takes to sort
    ---------------------------------*/
    public long insertion ()
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	for (int count = 1 ; count < this.size ; count++)
	{
	    int temp = this.original [count];
	    int findIndex = count;
	    while (findIndex > 0 && temp < this.aClone [findIndex - 1]) //since stops at 0, smallest element will be inserted at 0
	    {
		this.aClone [findIndex] = this.aClone [findIndex - 1]; //shifting bigger element 1 position higher until temp is >
		findIndex--;
	    }
	    this.aClone [findIndex] = temp; //inserting
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/07/2018
    Purpose: Quick sort method.
    Input: int x and y values
    Output: Time it takes to sort
    ---------------------------------*/
    public long quick (int x, int y)
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	int left = x - 1;
	int right = y + 1;
	int t;
	int pivot = this.aClone [(x + y) / 2];
	do
	{
	    left++; //reaching near pivot
	    right--;
	    while (left < y && this.aClone [left] < pivot)
	    {
		left++;
	    }
	    while (right > x && this.aClone [right] > pivot)
	    {
		right--;
	    }
	    if (left < right)
	    {
		t = this.aClone [left];
		this.aClone [left] = this.aClone [right];
		this.aClone [right] = t;
	    }
	}
	while (left <= right);
	{
	    if (x < right)
	    {
		quick (x, right);
	    }
	    if (left < y)
	    {
		quick (left, y);
	    }
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/08/2018
    Purpose: Recursively splits the aClone array and merges it back together.
    Input: int array (aClone local copy)
    Output: Time it takes to sort
    ---------------------------------*/
    public long merge (int[] a)
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();

	if (a.length > 1)
	{
	    int[] left = new int [a.length / 2]; //divide and conqueror
	    int[] right = new int [a.length - a.length / 2];
	    //assigning left & right array values
	    for (int i = 0 ; i < a.length / 2 ; i++)
	    {
		left [i] = a [i];
	    }
	    for (int j = 0, k = a.length / 2 ; k < a.length ; j++, k++)
	    {
		right [j] = a [k];
	    }

	    this.merge (left);
	    this.merge (right);

	    //merging back together
	    int leftIndex = 0, rightIndex = 0, arrIndex = 0;
	    while (leftIndex < a.length / 2 && rightIndex < (a.length - (a.length / 2)))
	    {
		if (left [leftIndex] <= right [rightIndex])
		{
		    a [arrIndex] = left [leftIndex];
		    leftIndex++;
		}
		else
		{
		    a [arrIndex] = right [rightIndex];
		    rightIndex++;
		}
		arrIndex++;
	    }
	    //any extra values
	    while (leftIndex < a.length / 2)
	    {
		a [arrIndex] = left [leftIndex];
		leftIndex++;
		arrIndex++;
	    }
	    while (rightIndex < (a.length - (a.length / 2)))
	    {
		a [arrIndex] = right [rightIndex];
		rightIndex++;
		arrIndex++;
	    }
	    this.aClone = a;
	}

	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }


    /*--------------------------------
    Author: Eric Zhou
    Date: 11/08/2018
    Purpose: Shell sorting method
    Input: None
    Output: Time it takes to sort
    ---------------------------------*/
    public long shell ()
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	int gap = 1;
	int n = this.size;
	while (gap <= n)
	{
	    gap = (gap * 3) + 1;
	}
	while (gap > 1)
	{
	    gap = gap / 3;
	    for (int i = gap ; i <= n - 1 ; i++)
	    {
		int hold = this.aClone [i];
		int j = i - gap;
		while (j >= 0 && this.aClone [j] > hold)
		{
		    this.aClone [j + gap] = this.aClone [j];
		    j = j - gap;
		}
		this.aClone [j + gap] = hold;
	    }
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }
    /*--------------------------------
    Author: Eric Zhou
    Date: 11/09/2018
    Purpose: A comb sorting method.
    Input: None
    Output: Time it takes to sort
    ---------------------------------*/
    public long comb()
    {
	long start, stop, timeTaken;
	start = System.currentTimeMillis ();
	int gap = this.size;
	boolean inorder = this.sorted();
	while (gap > 1 || inorder == false)
	{
	    gap = (int) Math.round(0.66*gap); 
	    inorder = true;
	    for (int i = 0; i < this.size-gap; i++)
	    {
		int j = i + gap;
		if (this.aClone[i] > this.aClone[j])
		{
		    int old = this.aClone[i];
		    this.aClone[i] = this.aClone[j];
		    this.aClone[j] = old;
		    inorder = false;
		}
	    }
	}
	stop = System.currentTimeMillis ();
	timeTaken = stop - start;
	return timeTaken;
    }
    /*--------------------------------
    Author: Eric Zhou
    Date: 11/08/2018
    Purpose: Graphs all sort methods time!
    Input: boolean bubble, select, insert, combed, long var: bubbleTime, selectionTime, insertionTime, combTime, Console c
    Output: None
    ---------------------------------*/
    public void graph (boolean bubs, long bubbleTime, boolean select, long selectionTime, boolean insert, long insertionTime, boolean combed, long combTime, Console c)
    {
	StringBuffer printTime = new StringBuffer ();
	//seeing if prev time is under 10 seconds
	long quickTime = this.quick (0, this.size - 1);
	this.reset ();
	long mergeTime = this.merge (this.aClone);
	this.reset ();
	long shellTime = this.shell ();
	this.reset ();

	//print
	printTime.append (this.size);
	while (printTime.length () < 8) //num = word length + 1
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());

	printTime.append (quickTime);
	while (printTime.length () < 7)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());

	printTime.append (shellTime);
	while (printTime.length () < 7)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());

	printTime.append (mergeTime);
	while (printTime.length () < 7)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());

	if (insert == true)
	{
	    printTime.append (insertionTime);
	}
	else
	{
	    printTime.append ("10000+");
	}
	while (printTime.length () < 11)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());

	if (select == true)
	{
	    printTime.append (selectionTime);

	}
	else
	{
	    printTime.append ("10000+");
	}
	while (printTime.length () < 11)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());
	if (bubs == true)
	{
	    printTime.append (bubbleTime);
	}
	else
	{
	    printTime.append ("10000+");
	}
	while (printTime.length () < 9)
	{
	    printTime.insert (0, " ");
	}
	c.print (printTime.toString ());
	printTime.delete (0, printTime.toString ().length ());
	
	if (combed == true)
	{
	    printTime.append(combTime);
	}
	else
	{
	    printTime.append("10000+");
	}
	while (printTime.length() < 8)
	{
	    printTime.insert (0, " ");
	}
	c.print(printTime.toString());
	printTime.delete (0, printTime.toString ().length ());
	c.println ();

    }
} //SortMethods class
