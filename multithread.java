package mp2;
import java.util.Random;
import java.util.Scanner;

public class multithread {

	public static void main(String[] args) {
		
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		
		
		int[][] normProduct = new int[20][20];
		int[][] multiProduct = new int[20][20];
		//scan array1 size bounds
		int m = scan.nextInt();
        int n = scan.nextInt();
		int[][] array1 = new int[m][n];
		//generate array1 random values

		
		//scan array2 size bounds
		int r = scan.nextInt();
		int c = scan.nextInt();
		scan.close();
		int[][] array2 = new int[r][c];
		//scan array2 random values
		
		//set and print array 1
		System.out.println("\n"+"Array1");
		for (int i = 0; i < m; i++) 
		{
			for(int j = 0; j < n; j++)
			{
				array1[i][j] = rand.nextInt(Integer.MAX_VALUE);
				System.out.print(array1[i][j] + " ");
			}
			System.out.println("");
	    }

		//set and print array 2
		System.out.println("\n"+"Array2");
		for (int i = 0; i < r; i++) 
		{
			for(int j = 0; j < c; j++)
			{
				array2[i][j] = rand.nextInt(Integer.MAX_VALUE);
				System.out.print(array2[i][j] + " ");
			}
			System.out.println("");
	    }

		
		if(n == r)
		{
			System.out.println("\n"+"Matrix can be multiplied");
		}
		else
		{
			System.out.println("Try Again, Matrix Row and Column Size is not appropriate");
			System.exit(0);
		}
			
		int numThreads = 5;
		multithreadmatrix[] thd = new multithreadmatrix[numThreads];

		for(int i = 0; i < numThreads; i++)
		{
			thd[i] = new multithreadmatrix(i,array1,array2,multiProduct);
			thd[i].start();
		}
		
		
		for (int i = 0; i < numThreads; i++) {
            try {
                thd[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		
		System.out.println("\n" + "Multithread Multiplication:" + "\n");
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < 20; j++)
			{
				System.out.print(multiProduct[i][j]+ " ");
			}
			System.out.println();
		}
		
		
		//Multiply and Print result
		System.out.println("\n"+"Normal Method Product: Array1*Array2");
		for (int i = 0; i < m; i++) 
		{
			for(int j = 0; j < c; j++)
			{
				
				for(int k = 0; k < r; k++)
				{
					normProduct[i][j] += array1[i][k] * array2[k][j];
				}
				System.out.print(normProduct[i][j] + " ");
			}
			System.out.println("");
	    }
			
	
	}
}

class multithreadmatrix extends Thread{
	
	private int thdnum;
	private int[][] array1;
	private int[][] array2;
	private int[][] multiProduct;

	
	public multithreadmatrix(int thdnum, int[][] array1, int[][] array2, int[][] multiProduct)
	{
		this.thdnum = thdnum;
		this.array1 = array1;
        this.array2 = array2;
        this.multiProduct = multiProduct;
	}
	
	@Override
	public void run()
	{
		System.out.println("Thread Created: " + thdnum);
		
		for(int k = thdnum*4; k < (thdnum+1)*4; k++)
		{
			for(int i = 0; i < 20; i++)
			{
				for(int j = 0; j < 20; j++)
				{
					 multiProduct[i][j] += array1[i][k] * array2[k][j];
					 
				}
			}
			System.out.println("Thread Active: " + thdnum);
		}
		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException e)
		{	
		}
		System.out.println("Thread Finished: " + thdnum);
	}
	
}