/*************************************
 * Practica Modelado y programación. *
 *                                   *
 * Lista ligada en c.                *
 *                                   *
 *************************************/


/**
 * Nodo, definicion recursiva.
 * Un nodo es un nodo vacio o
 * Un elemento y un nodo siguiente
 */ 
typedef struct Nodo {
  int elemento;
  struct Nodo * siguiente;
} Nodo;


/**
 * Lista ligada, definicion recursiva
 * Un nodo, que por su definicion es: 
 * Un nodo vacio (lista vacia) o 
 * Un elemento seguido de un nodo.
 */ 
typedef struct Lista{
  struct Nodo * cabeza;
} Lista;


/**
 * lista_inserta_primero:
 * Inserta un elemento al inicio de la lista.
 * @param lista, Lista *. Puntero a la lista.
 *        valor, valor del elemento de la nueva cabeza.
 * Recibe el puntero de la lista para poder acceder al 
 * puntero de la cabeza.
 */
void lista_inserta_primero(Lista * lista, int valor) {
  Nodo * nodo;                         /* Nodo que sera la nueva cabeza   */
  
  nodo= (Nodo *) malloc(sizeof(Nodo)); /* Asignamos el espacio de memoria */
  nodo -> elemento = valor;            /* y el valor                      */
  
  /* a = b sii b = a. Actualizamos la nueva cabeza */ 
  nodo -> siguiente = lista->cabeza; 
  lista -> cabeza = nodo; 
}

/**
 * lista_elimina_primero: 
 * Elimina el primer elemento de la lista recibida.
 * @param lista, Lista *. Puntero a la lista.
 */
void lista_elimina_primero(Lista * lista) {
  Nodo * n = NULL;                   /* Nueva cabeza                      */

  if (lista -> cabeza == NULL)       /* No se elimina nada en lista vacia */ 
    return;

  n = lista -> cabeza -> siguiente;  /* Nodo que sera la sig cabeza       */
  free(lista -> cabeza);             /* Nos desasemos de la anterior      */
  lista -> cabeza = n;               /* Actualizamos la nueva cabeza.     */
}


/**
 * lista_imprime: 
 * Imprime elemento por elemento (salto de linea) 
 * de la lista recibida.
 * @param lista, Lista. Lista por imprimir.
 */
void lista_imprime(Lista lista) {
  Nodo* n = lista.cabeza;           /* Nodo que iterara la lista     */
  
  while (n != NULL) {               /* Mientras el nodo no sea vacio */
    printf("%d\n", n -> elemento);  /* Imprimimos                    */
    n = n -> siguiente;             /* n Continua iterando la lista  */
  }
}


/**
 * lista_es_vacia:
 * Nos dice si la lista recibida es vacia o no.
 * @param lista, Lista. Lista por verificar.
 * @return 1 si es vacia.
 *         0 si no es vacia.
 */
int lista_es_vacia(Lista lista){
  return (lista.cabeza == NULL); /* Una lista es vacia sii la cabeza es vacia */
}


/**
 * lista_tamanio:
 * Da el numero de elementos en la lista recibida.
 * @param lista, Lista.
 * @return int. Número de elementos en la lista.
 */
int lista_tamanio(Lista lista){
  Nodo* n = lista.cabeza; /* Nodo que iterara la lista */
  int cont = 0;           /* tamaño de la lista */

  /* Iteramos la lista hasta encontrar un nodo vacio e incrementamos el 
     contador por cada nodo no vacio en la lista */ 
  while (n != NULL) {     
    cont ++;
    n = n->siguiente;
  }
  
  return cont;
}
