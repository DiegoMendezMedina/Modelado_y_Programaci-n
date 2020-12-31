#include <stdio.h>
#include <stdlib.h>
#include "lista_ligada.h"

/**
 * Programa que verifica los metodos implementados en lista_ligada.h
 */

int lista_inserta_primero_test();
int lista_elimina_primero_test();
int lista_imprime_test();
int lista_tamanio_test(Lista lista);
int lista_es_vacia_test();

int main(){
  int test_vacia, test_inserta, test_elimina, test_imp_tam;


  printf("\n\n* * * * * * * * * * * * * * * * *\n*"
	 " C o r r i e n d o   t e s t s *"
	 "\n* * * * * * * * * * * * * * * * *\n\n");
  test_vacia = lista_es_vacia_test();
  (test_vacia)? printf("Funciona\n\n") :
    printf("No funciona\n\n");
  
  test_inserta = lista_inserta_primero_test();
  (test_inserta)? printf("Funciona\n\n") :
    printf("No funciona\n\n");
  
  test_elimina = lista_elimina_primero_test();
  (test_elimina)? printf("Funciona\n\n"):
    printf("No funciona\n\n");

  test_imp_tam = lista_imprime_test();
  (test_imp_tam)? printf("Funciona\n"):
    printf("NO funciona\n");
  
  printf("\n-------------------------\n");
  (test_vacia && test_inserta && test_elimina && test_imp_tam)?
    printf("Los metodos funcionan\n") : printf("Algun metodo no funciona\n");
  printf("-------------------------\n");

  return 0;
}


/**
 * Verifica que el metodo lista_es_vacia() funcione.
 * @return 1 si lista_es_vacia() funciona,
 *         0 si no.
 */
int lista_es_vacia_test(){
  printf("lista_es_vacia --- ");
  
  Lista vacia = {NULL};   /* Creamos una lista vacia                      */
  Lista lista;            /* Inicializamos una lista                      */
  Nodo * nodo;            /* Nodo que sera la nueva cabeza de lista       */
  nodo= (Nodo *) malloc(sizeof(Nodo));   /* Creamos el espacio de memoria */
  nodo -> elemento = 1;   /* Le asignamos valor                           */ 
  lista.cabeza = nodo;    /* Lo hacemos la cabeza de lista                */

  /* Regresa 1 si vacia es una lista vacia y lista no es vacia, 0 en otro caso*/
  return lista_es_vacia(vacia) && !lista_es_vacia(lista);
}


/**
 * Verifica que el metodo lista_inerta_primero() funcione.
 * @return 1 si lista_inserta_primero() funciona, 
 *        0 si no.
 */
int lista_inserta_primero_test(){
  printf("lista_inserta_primero --- ");
  
  Lista lista = {NULL};             /* Creamos una lista vacia */
  lista_inserta_primero(&lista, 1);
  lista_inserta_primero(&lista, 2);

  /* Regresa 1 si no es vacia y la cabeza es 2, 0 caso contrario */
  return !lista_es_vacia(lista) && lista.cabeza -> elemento == 2;
}


/**
 * Verifica que lista_elimina_primero() funcione.
 * @return 1 si lista_elimina_primero() funciona,
 *         0 en caso contrario.
 */
int lista_elimina_primero_test(){ 
  printf("lista_elimina_primero --- ");
  
  Lista lista = {NULL};              /* Creamos una lista vacia */
  lista_inserta_primero(&lista, 1);  /* Ya verificamos que este metodo sirve */
  lista_inserta_primero(&lista, 2);  
  lista_elimina_primero(&lista);
  
  /* El metodo sirve sii el codigo anterior implica que el elemento 
     en la cabeza es igual a 1 */ 
  return lista.cabeza -> elemento == 1;
}

/**
 * Verifica que lista_imprime() y lista_tamanio_test() funcionen.
 * @return 1 si ambos metodos funcionan.
 *         0 en otro caso.
 */
int lista_imprime_test(){
  Lista lista = {NULL};
  lista_inserta_primero(&lista, 1);
  lista_inserta_primero(&lista, 2);
  lista_inserta_primero(&lista, 3);
  
  /* Tenemos la lista 3 -> 2 -> 1, se verifica en la consola que la imprima: */ 
  printf("Imprimiendo lista 3,2,1 : \n");
  lista_imprime(lista);
  printf("\n");

  /* Checamos que lista_tamanio() funcione*/
  return lista_tamanio_test(lista);
}


/**
 * Verifica que lista_tamanio() funcione.
 * @param lista, Lista. Lista que sabemos tiene 3 nodos.
 * @return 1 si lista_tamanio() funciona,
 *         0 en caso contrario.
 */ 
int lista_tamanio_test(Lista lista){
  printf("lista_tamanio --- ");
  
  /* Sabemos que la lista recibida tiene longitud 3 */
  Lista vacia = {NULL};  /* Creamos una lista vacia*/
  
  /* Verificamos que el tamaño de lista sea tres y de vacia cero */
  return lista_tamanio(lista) == 3 && lista_tamanio(vacia) == 0;
}

