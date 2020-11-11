import java.io.IOException;
import java.util.Scanner;
/**
 * Java solution for "Spurs Rocks" URI online judge problem.
 * https://www.urionlinejudge.com.br/judge/en/problems/view/1303
 */
public class Main{
    /**
     * Clase interna que nos permitira comparar los equipo y ordenarlos.
     */
    private static class Equipo{
	int id;            // Orden en el que fueron apareciendo.
	double anotados;  // Puntos anotados en toda la liga.
	double recibidos; // Puntos recibidos en toda la liga.
	int victorias;    // Victorias obtenidas en la liga.
	int derrotas;     // Derrotas obtenidas en la liga.
	int puntos;       // Puntos obtenidos en la liga.
	
	public Equipo(int id){
	    this.id = id;
	    this.anotados = 0;
	    this.recibidos = 0;
	    this.victorias = 0;
	    this.derrotas = 0;
	    this.puntos = 0;
	}
	public void anoto(double pts){
	    this.anotados += pts;
	}
	public void recibio(double pts){
	    this.recibidos += pts;
	}
	public double getAnotados(){
	    return this.anotados;
	}
	public void gano(){
	    this.victorias += 1;
	    this.puntos += 2;
	}
	public void perdio(){
	    this.derrotas += 1;
	    this.puntos += 1;
	}
	public int getVictorias(){
	    return this.victorias;
	}
	public int getDerrotas(){
	    return this.derrotas;
	}
	public int getPuntos(){
	    return this.puntos;
	}
	public double getRecibidos(){
	    return this.recibidos;
	}
	public double getRatio(){
	    if(recibidos >0){
		return this.anotados/this.recibidos;
	    }
	    else
		return this.anotados/(this.victorias+this.derrotas);	
	}
	public int getID(){
	    return this.id;
	}
}
    public static void main(String[] args){
	int cont = 1; // Para contar las instancias,¿.
	int n; // Numero de equipos.
	Scanner scan = new Scanner(System.in);
	boolean go = true;
	while(go){
	    n = scan.nextInt();
	    if(n == 0 || n > 100 || n < 0)
		System.exit(0); 
	    if(cont >1)
		System.out.println();
	    Equipo[] equipo = new Equipo[n]; // Arreglo para comparar y ordenar.
	    
	    float loop = n*(n-1)/2; // Numero de partidos 
	    for(int i = 0; i <= loop; i++){ // Para analizar cada partido.
		int equipo1, equipo2;
		String linea = scan.nextLine().trim();
		if(!(linea.equals(""))){
		    String[] marcador = linea.split(" ");
		    equipo1 = Integer.parseInt(marcador[0]); // x
		    equipo2 = Integer.parseInt(marcador[2]); // y 
		    int pts1 = Integer.parseInt(marcador[1]); // z
		    int pts2 = Integer.parseInt(marcador[3]); // w 

		    // Inicialice el arreglo nulos entonces es necesario checar
		    // si estan vacios para crearlos, reciben como parametro el
		    // orden en el que aparecen. 
		    if(equipo[equipo1-1] == null) 
			equipo[equipo1-1] = new Equipo(equipo1);
		    if(equipo[equipo2-1] == null)
			equipo[equipo2-1] = new Equipo(equipo2);

		    //Guardamos canastas anotadas y recibidas:
		    equipo[equipo1-1].anoto(pts1);	
		    equipo[equipo1-1].recibio(pts2);	    
		    equipo[equipo2-1].anoto(pts2);
		    equipo[equipo2-1].recibio(pts1);

		    //Determinamos quien gano:
		    if(pts1 > pts2){
			equipo[equipo1-1].gano();
			equipo[equipo2-1].perdio();
		    }
		    else{
			equipo[equipo2-1].gano();
			equipo[equipo1-1].perdio();
		    }
		}
	    }
	    //Ordenamos el arreglo con el primer criterio que es puntos:
	    sort(equipo, 0, equipo.length-1);

	    //Checa los demás criterios de desempate. 
	    for(int i = 0; i<n; i++)
		for(int j = i+1; j <n; j++)
		    //Si los equipos tienen puntos iguales:
		    if(equipo[i].getPuntos() == equipo[j].getPuntos()){
			// Checamos si algun otro tiene mayor Ratio.
			if(equipo[i].getRatio() < equipo[j].getRatio())
			    change(i, j, equipo); //Los cambiamos
			// Si los equipos tiene Ratios iguales:
			if(equipo[i].getRatio() == equipo[j].getRatio()){
			    // Checamos si algun otro tiene mas puntos anotados.
			    if(equipo[i].getAnotados() <
			       equipo[j].getAnotados())
				change(i, j, equipo);
			    // Si los equipos tienen los mismos puntos:
			    if(equipo[i].getAnotados() ==
			       equipo[j].getAnotados())
				// Checamos si el ID de alguno despues en el arreglo aparecio en anterior orde.
				if(equipo[i].getID() >
				   equipo[j].getID())
				    change(i, j, equipo);
			}
		    }
	    //Salida:
	    System.out.println("Instancia "+ cont);
	    cont ++; //Incrementamos instancia.

	    //Imprimimos el arreglo ya ordenado:
	    for(int i = 0; i<n; i++)
		if(i == 0)
		    System.out.print(equipo[i].getID());
		else
		    if(i+i<n)
			System.out.print(" "+equipo[i].getID());
		    else
			System.out.print(" "+equipo[i].getID());
	    System.out.println();
	    // Imprime las estadisticas de cada equipo. Se utilizo para checar los criterios de desempate: 
	    //for(int i = 0; i < n; i++)
	    //	System.out.println("Equipo: "+ equipo[i].getID()+ " Puntos: "+equipo[i].getPuntos()+ " Gano: "+ equipo[i].getVictorias()+" Derrotas: "+equipo[i].getDerrotas()+ " Ratio: "+equipo[i].getRatio()+" Anotados: "+equipo[i].getAnotados()+" Recibidos: "+equipo[i].getRecibidos() );
	}
    }

    /**
     * Metodo que cambia dos elementos del arreglo, dado su indice.
     */
    private static void change(int a, int b, Equipo[] equipos){
	Equipo aux = equipos[a];
	equipos[a] = equipos[b];
	equipos[b] = aux;
    }
    /**
     * Metodo sort; no hay cambios particulares adecuado a la nueva clase.
     */
    private static void sort(Equipo arr[], int l, int r){
	if (l < r) { 
	    int m = (l + r) / 2; 
 
	    sort(arr, l, m); 
	    sort(arr, m + 1, r); 
  
	    merge(arr, l, m, r); 
	} 
    }
    
    /**
     * Núcleo del Merge_sorth algorithm. 
     * En este caso fue necesario añadir
     * un arreglo exclusivo a Puntaje, que es el criterio principal para 
     * determinar los lugares.
     * Los cambios se realizan en el arreglo de la clase interna.
     */
    private static void merge(Equipo equipos[], int l, int m, int r){ 
	int n1 = m - l + 1; 
	int n2 = r - m;

	//Arreglo que tendra los puntajes de cada equipo.
	int arr[] = new int[equipos.length];

	// Lo llenamos:
	for(int i = 0; i < arr.length; i++)
	    arr[i] = equipos[i].getPuntos();

	//Arreglos del lado izquierdo del arreglo recibido:
	int L[] = new int[n1];
	Equipo LL[] = new Equipo[n1];

	//Arreglos del lado derecho del arreglo recibido:
	
	int R[] = new int[n2]; 
	Equipo RR[] = new Equipo[n2]; 

	//LLenamos
	for (int i = 0; i < n1; ++i) {
	    L[i] = arr[l + i];
	    LL[i] = equipos[l + i]; //Es necesario hacer el cambio en el de equipos tambíen.
	}
	//Llenamos
	for (int j = 0; j < n2; ++j) {
	    R[j] = arr[m + 1 + j];
	    RR[j] = equipos[m + 1 + j];  //^^
	}

	//Merge:
	int i = 0, j = 0; 
  
	int k = l; 
	while (i < n1 && j < n2) { 
	    if (L[i] >= R[j]) { 
		equipos[k] = LL[i]; // ^^
		i++; 
	    } 
	    else { 
		equipos[k] = RR[j]; //^^
		j++; 
	    } 
	    k++; 
	} 
	while (i < n1) { 
	    equipos[k] = LL[i]; 
	    i++; 
	    k++; 
	} 
	while (j < n2) { 
	    equipos[k] = RR[j]; 
	    j++; 
	    k++; 
	} 
    }
}
