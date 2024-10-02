

class MultiThread implements Runnable {

    private int [][] A;
    private int [][] B;
    private int [][] product;
    private int start;
    private int end;


    public MultiThread(int start, int end, int [][] A, int [][] B, int [][] product){
        this.start = start;
        this.end = end;
        this.A = A;
        this.B = B;
        this.product = product;
    }

    public void run(){
        for(int i = start; i < end; i++){
            for(int j = 0; j < 20; j++){
                int sum = 0;
                for(int k = 0; k < 20; k++){
                    sum += A[i][k] * B[k][j];
                }
                product[i][j] = sum;
            }
        }
    }


}
