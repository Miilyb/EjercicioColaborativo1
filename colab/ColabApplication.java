package com.colaborativo.colab;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
public class ColabApplication {

	//esta es una funcion de tipo que retorna 
// en esta funcion vamos a guardar el promedio de las notas en un array
public static Double promedioNotas(ArrayList<Double> notas){
	Double suma = 0.0;
	//vamos de i cero , por toda la cantidad denotas .size y aumentando de a 1 
	for (int i = 0; i < notas.size(); i++) {
		//suma = suma + notas.get(i) -> es lo mismo :D
		suma += notas.get(i);
	}
	return suma/notas.size();
}
//esta es otra funcion que llama a otra funcion
public static Boolean aprobado(ArrayList<Double> notas, Double notaAprobatoria){
	Double promedio = promedioNotas(notas);
	if(promedio >= notaAprobatoria){
		return true;
	}else{
		return false; 
	}
}
//esta es otra funcion que llama a otra funcion
public static void sobrePromedio(Double promedioGeneral, ArrayList<Double> notas, String nombreAlum){
	Double promedioAlumno = promedioNotas(notas);
	if(promedioAlumno>promedioGeneral){
		System.out.println("El alumno "+nombreAlum+" está sobre el promedio");
	}else if(promedioAlumno == promedioGeneral){
		System.out.println("El alumno "+nombreAlum+" es igual al promedio general");
	}else{
		System.out.println("El alumno "+nombreAlum+" está bajo el promedio");
	}
}

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		String nomAlum = "";
		Double notaAprobatoria = 4.0;
		//creamos hash map para poder guardar el nombre y la notas de los alumnos
		HashMap <String, ArrayList<Double>> libroClase = new HashMap<String, ArrayList<Double>>();
		  
		int cantAlum;
		int cantNotas;
		//le fijamos desde antes cuantos alumnos y cuantas notas metera 
		do{
			System.out.print("Indique la cantidad de alumnos que va a ingresar: ");
			cantAlum = teclado.nextInt();
			if(cantAlum <= 0){
				System.out.print("La cantidad de alumnos debe ser mayor a cero, porfavor intente denuevo");
			}

		}while(cantAlum <= 0);

		do{
			System.out.print("Indique la cantidad de notas por alumnos: ");
			cantNotas = teclado.nextInt();
			if(cantNotas <= 0){
				System.out.print("La cantidad de notas debe ser mayor a cero, por favor entente mas tarde");
			}
		}while(cantNotas <= 0);
		
		
		for(int i = 1; i <= cantAlum; i++){
			teclado.nextLine();
			//creamos un array cpara ir gurdando las notas por alumno
			ArrayList <Double> notasAlumnos = new ArrayList<Double>();
			System.out.print("Ingresa nombre del alumno: ");
			nomAlum = teclado.nextLine();
			//con esto guardamos la nota con el nombre de fulanito
			for(int x = 1; x <= cantNotas; x++){
				System.out.print("Ingresa nota " +x+ " del alumno "+ nomAlum +": ");
				Double nota = teclado.nextDouble();
				notasAlumnos.add(nota);
			}
			//todo lo que pedimos adelante se lo guardamos al hashmap que contiene nombre alumno mas la nota 
			libroClase.put(nomAlum, notasAlumnos);
			
		}
		
		int opcion = 1; 

		while(opcion != 0){

			do{
				System.out.println("**************Comienzo de Menú***************");
				System.out.println("Bienvenido/a");
				System.out.println("Seleccione 1 si quiere obtener el promedio de las notas por alumno.");
				System.out.println("Seleccione 2 si quiere ver si el alumno aprueba o reprueba");
				System.out.println("Seleccione 3 para ver si la nota está sobre o por debajo del promedio general");
				System.out.println("Seleccione 0 para salir del menú");
				System.out.print("Seleccione su opción: ");
				opcion = teclado.nextInt();
				
			}while(opcion < 0 || opcion > 3);

			if(opcion == 1){
				for(String i : libroClase.keySet()){
					//cada vez que ocupemos un for opara hashmap
					//el valor de la llave (en este caso el array)
					//está contenido dentro de la sintaxis nomHasmap.get(i)
					Double promAlum = promedioNotas(libroClase.get(i));
					System.out.println("El promedio del alumno: "+ i +" es de: " + promAlum);
				}
			}else if (opcion == 2){
				for(String i : libroClase.keySet()){
					Boolean aprobar = aprobado(libroClase.get(i), notaAprobatoria);
					//es lo mismo que poner if (aprobar == true)
					if(aprobar){
						System.out.println("El alumno/a "+i+" está aprobado");
					}else{
						System.out.println("El alumno/a "+i+" está reprobado");
					}
					
				}
				//key set por cada iteracion me trae el siguiente dato y el .get 
			}else if(opcion == 3){
				Double sumaPromedio = 0.0;
				for(String i : libroClase.keySet()){
					sumaPromedio = sumaPromedio + promedioNotas(libroClase.get(i));
					
				}
				Double promedioGeneral = sumaPromedio / cantAlum;
				 // puedo hacer este for altiro porque esta fuera del otro 
				 //la i en estos casos toma el valor de la llave/key el nombre por asi decirlo
				for(String i : libroClase.keySet()){
					// es i final porque ahi esta guardada la llave el nombre en este caso
					sobrePromedio(promedioGeneral, libroClase.get(i), i);
				}
			}else{
				System.out.println("Gracias por cerrar el menú, hasta pronto :D");
			}

		}

		

		
	}

}


		
	