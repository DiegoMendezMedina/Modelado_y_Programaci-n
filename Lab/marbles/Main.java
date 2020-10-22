import java.io.IOException;
import java.util.Scanner;
/*
 * Java solution for "Where's the marble?" URI online judge problem.
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1025
 */
public class Main {
 
    public static void main(String[] args) throws IOException {
    int n, q;
    int cont = 1;
    int j = 0;
    boolean go = true;
    Scanner scan = new Scanner(System.in);
    while(go){
        n = scan.nextInt();
        q = scan.nextInt();
	if(n > 10000 || q > 10000 || (n <= 0 && q <=0))
	    System.exit(0);
	int[] marbles = new int[n];
	int[] queries = new int[q];
	for(int i = 1; i <= n; i++){
	    marbles[i-1] = scan.nextInt();
	}   
	sort(marbles, 0, marbles.length-1);
	System.out.println("CASE# "+cont+":");
	cont++;
	for(int i = 1; i <= q; i++){
	    int querie = scan.nextInt();
	    int index = search(marbles, querie);
	    if (index >= 0) 
		System.out.println(querie+" found at "+(index+1));
	    else  
		System.out.println(querie+" not found");
	}
    }   
    }
    public static int search (int [] marbles, int querie) {
	for (int i=0;i < marbles.length; i++)
	    if (marbles[i] == querie)
		return i;
	return -1;
    }
    private static void merge(int arr[], int l, int m, int r) 
    { 
        int n1 = m - l + 1; 
        int n2 = r - m; 
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
  
        for (int i = 0; i < n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j = 0; j < n2; ++j) 
            R[j] = arr[m + 1 + j]; 
  
        int i = 0, j = 0; 
  
        int k = l; 
        while (i < n1 && j < n2) { 
            if (L[i] <= R[j]) { 
                arr[k] = L[i]; 
                i++; 
            } 
            else { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while (i < n1) { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    private static void sort(int arr[], int l, int r) 
    { 
        if (l < r) { 
            int m = (l + r) / 2; 
  
            sort(arr, l, m); 
            sort(arr, m + 1, r); 
  
            merge(arr, l, m, r); 
        } 
    } 
 
}
