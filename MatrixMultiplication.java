
import java.util.Random;

public class MatrixMultiplication {
    public static void main(String[] args){

        Random random = new Random();

        // initialize 2 20 x 20 matrices with random values

        // matrix 1 
        int [][] A = new int [20][20];
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                A[i][j] = random.nextInt(Integer.MAX_VALUE);
            }
        }

        System.out.print("Matrix A:");
        System.out.println();
         for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(A[i][j] + ", ");
            }
            // print new line to differentiate rows
            System.out.println();
        }



        // matrix 2
        int [][] B = new int [20][20];
        for(int i = 0; i < 20;i++){
            for(int j = 0; j < 20; j++){
                B[i][j] = random.nextInt(Integer.MAX_VALUE);
            }
        }

        System.out.print("Matrix B:");
        System.out.println();
         for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(B[i][j] + ", ");
            }
            // print new line to differentiate rows
            System.out.println();
        }


        // create 5 threads 
        Thread[] threads = new Thread[5];

        // create product matrix 
        int [][] product = new int [20][20];


        // loop to dynamically create threads 
        for(int i = 0; i < 5; i++){
            int start = i * 4;
            int end = start + 4;
            MultiThread worker = new MultiThread(start, end, A, B, product);
            threads[i] = new Thread(worker);
            threads[i].start();
        }

        // wait for threads to complete
        for(int i = 0; i < 5; i++){
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // print out matrix 
        System.out.print("Total product using multi-thread matrix multiplication is:");
        System.out.println();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(product[i][j] + ", ");
            }
            // print new line to differentiate rows
            System.out.println();
        }

        // calculate product using non multi thread to check answers
        int [][] non_mulithread_product = new int [20][20];
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                int sum = 0;
                for(int k = 0; k < 20; k++){
                    sum += A[i][k] * B[i][k];
                }
                non_mulithread_product[i][j] = sum;
            }
        }

        System.out.print("Total product using normal matrix multiplication is:");
        System.out.println();
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(non_mulithread_product[i][j] + ", ");
            }
            // print new line to differentiate rows
            System.out.println();
        }



        
}
}