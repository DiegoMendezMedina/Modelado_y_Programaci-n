import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{  
  
  
    public static void main(String[] args) throws IOException {
	
        BufferedReader br = new BufferedReader(new
					       InputStreamReader(System.in));
        String input; 
        int [][] matriz;
        int[][] BIT;
	int cont = 0;
	
        while(!(input = br.readLine()).equals("0 0 0")){
	    if( cont > 0)
		System.out.println();
	    cont++;
            String[] st = input.trim().split(" ");
            int X = Integer.parseInt(st[0]);
            int Y = Integer.parseInt(st[1]);
            int P = Integer.parseInt(st[2]);
	    
            if(X > 1000 || Y > 1000 || P > 10)
                break;
	    
            matriz = new int[Y][X];    
            BIT = new int[Y+1][X+1];
            int Q = Integer.parseInt(br.readLine().trim());
	    
	    if(Q > 10000)
		break;
	    
            for(int i = 1; i <= Q; i++){
                st = br.readLine().trim().split(" ");
                String answer = st[0];
                if(answer.equals("A")){
                    int N = Integer.parseInt(st[1]);
                    int x = Integer.parseInt(st[2]);
                    int y = Integer.parseInt(st[3]);
		    if(N > 10 || (x > X && x < 0) || (y > Y && y < 0))
			break;
                    matriz[y][x] += N;
                }else{
                    int x = Integer.parseInt(st[1]);
                    int y = Integer.parseInt(st[2]);
                    int x2 = Integer.parseInt(st[3]);
                    int y2 = Integer.parseInt(st[4]);
		    construct2DBIT(matriz, BIT, X, Y);
		    int ans = summ(x,y,x2,y2, BIT);
		    System.out.println(ans *P);
                }
            }
        }
    }
    static int summ(int x, int y, int z, int w, int BIT[][]){
	int ans= 0;
	if(x <= z && y > w){
	    ans = sum(BIT, z+1, y+1) - sum(BIT, z+1, w) -  sum(BIT, x, y+1) + sum(BIT, x, w);  
	    return ans;
        }
	ans = sum(BIT, z+1, w+1) - sum(BIT, z+1, y) -  sum(BIT, x, w+1) + sum(BIT, x, y);  
	return ans;
    
    }
  
    static int sum(int BIT[][], int x, int y) {  
        int sum = 0;  
        for(; x > 0; x -= x & -x) {  
            for(; y > 0; y -= y & -y){
		if(x < BIT.length && y < BIT[0].length)
		    sum += BIT[x][y];  
            }  
        }  
        return sum;  
    }  

    static void construct2DBIT(int mat[][], int BIT[][], int X, int Y){  
	int val = 0;
	
        for (int j = 1; j <= X; j++){  
            for (int i = 1; i <= Y; i++) {
		if(i < X+1 && j < Y+1){
		    if(i <= Y && j <= X)
			val += BIT[i][j];
		    if(i <= Y && j-1 <= X)
			val -= BIT[i][j-1];
		    if(i-1 <= Y && j <= X)
			val += BIT[i-1][j-1];
		    if(i-1 <= Y && j <= X)
			val -= BIT[i-1][j];
		    BIT[i][j] += mat[BIT.length -1 -j][i-1] - val;
		}
            }  
        }  
    }  
    
}
