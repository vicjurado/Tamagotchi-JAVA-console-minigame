import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	private static Tamagochi perfil[] = new Tamagochi[100];
	private static int numeroPerfil = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcionMenu = 0;

		// Menú controlado para que no haya valores no válidos gracias a un do-while y
		// un switch.
		do {
			try {
				System.out.println(
						"[MENU]: 1. Crear un nuevo Tamagochi. | 2. Listado de Tamagochis. | 3. Elegir tamagochi. | 4. Finalizar.");
				opcionMenu = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("[ERROR]: Use solo valores numéricos.");
			}
			switch (opcionMenu) {
			case 1:
				nuevoTamagochi();
				break;
			case 2:
				if (controlador()) {
					listarTamagochi();
				} else {
					System.out.println("No hay tamagochis.");
				}
				break;
			case 3:
				if (controlador()) {
					usarTamagochi();
				} else {
					System.out.println("No hay tamagochis.");
				}
				break;
			case 4:
				System.out.println("Espero que tenga un buen día. Hasta la próxima.");
				break;
			default:
				System.out.println("Valor introducido inválido. Vuelva a intentarlo.");
				break;
			}

		} while (opcionMenu != 4);
	}

	// Controla que en la segunda y tercera opción (Mostrar listado y Elegir
	// tamagochi) no puedas acceder si no hay valores.
	public static boolean controlador() {
		for (int i = 0; i < perfil.length; i++) {
			if (perfil[i] != null) {
				return true;
			}
		}
		return false;
	}

	// Permite crear nuevos perfiles de Tamagochi, controla que no entre ningun
	// valor no deseado, también que los stats esten entre 0 y 100.
	public static void nuevoTamagochi() {
		if (numeroPerfil < perfil.length) {
			Scanner let = new Scanner(System.in);
			Scanner sc = new Scanner(System.in);
			String nombre;
			int hambre = 0, suenho = 0, higiene = 0, diversion = 0, indice = 0;
			boolean activo = true, comprobador = false;

			System.out.println("Introduce el nombre para el nuevo Tamagochi.");
			nombre = let.nextLine();

			System.out.println("Introduce de manera ordenada las stats. (Entre 0 y 100).");
			do {
				try {
					System.out.println("Hambre, sueño, higiene y diversión.");
					indice = numeroPerfil;
					hambre = sc.nextInt();
					suenho = sc.nextInt();
					higiene = sc.nextInt();
					diversion = sc.nextInt();
					if ((hambre >= 0 && hambre <= 100) && (suenho >= 0 && suenho <= 100)
							&& (higiene >= 0 && higiene <= 100) && (diversion >= 0 && diversion <= 100)) {
						comprobador = true;
					} else {
						System.out.println("Alguno de sus valores no está entre 0 y 100. Vuelva a intentarlo.");
					}
				} catch (InputMismatchException e) {
					sc.next();
					System.out.println("Use solo valores numéricos.");
				}
			} while (comprobador != true);
			perfil[numeroPerfil] = new Tamagochi(indice, nombre, hambre, suenho, higiene, diversion, activo);
			numeroPerfil++;
		}
	}

	// Imprime todos los tamagochis existentes en nuestro programa.
	public static void listarTamagochi() {
		for (int i = 0; i < numeroPerfil; i++) {
			System.out.println(perfil[i].toString());
		}
	}

	// Imprime los tamagochi y te da la opción a elegir a cada cual. Controla los
	// valores indeseados. No deja entrar a los perfiles ya muertos y avisa con un
	// mensaje.
	public static void usarTamagochi() {
		Scanner sc = new Scanner(System.in);
		int opcionTamagochi = 0;
		int opcionMenu = 0;
		boolean comprobar = false;

		System.out.println("¿Qué Tamagochi desea usar?");
		for (int i = 0; i < numeroPerfil; i++) {
			System.out.println(perfil[i].toString());
		}
		do {
			try {
				opcionTamagochi = sc.nextInt();
				if ((opcionTamagochi >= 0) && (opcionTamagochi < numeroPerfil)) {
					if (perfil[opcionTamagochi].getActivo()) {
						comprobar = true;
					} else {
						System.out.println("Tu Tamagochi murió en la guerra de Irak.");
						comprobar = true;
					}
				} else {
					System.out.println("Slot inexistente. Vuelva a intentarlo.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Use solo valores numéricos.");
			}
		} while (comprobar != true);

		// Otro menú en el que da a elegir entre una serie de opciones, llama
		// directamente los métodos desde 'Tamagochi'. Sale al pulsar 5, también lo hace
		// automaticamente al morir el Tamagochi. Como anteriormente, controlamos los
		// valores indeseados o no existentes.
		while (opcionMenu != 5 && perfil[opcionTamagochi].getActivo()) {
			try {
				System.out.println("[" + perfil[opcionTamagochi].getNombre() + "]:");
				System.out.println("[MENU]: 1. Comer. | 2. Dormir. | 3. Duchar. | 4. Jugar. | 5. Salir. ");
				opcionMenu = sc.nextInt();
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Use solo valores numéricos.");
			}
			switch (opcionMenu) {
			case 1: // Comer
				perfil[opcionTamagochi].comer();
				System.out.println(perfil[opcionTamagochi].toString());
				break;
			case 2: // Dormir
				perfil[opcionTamagochi].dormir();
				System.out.println(perfil[opcionTamagochi].toString());
				break;
			case 3: // Duchar
				perfil[opcionTamagochi].duchar();
				System.out.println(perfil[opcionTamagochi].toString());
				break;
			case 4: // Jugar
				perfil[opcionTamagochi].jugar();
				System.out.println(perfil[opcionTamagochi].toString());
				break;
			case 5: // Salir
				System.out.println("Tu tamagochi " + perfil[opcionTamagochi].getNombre()
						+ " se pone muy triste al ver que lo abandonas...");
				System.out.println(" ___");
				System.out.println("|O O|");
				System.out.println("| _'|");
				System.out.println("|___|");
				System.out.println();
				break;
			default:
				System.out.println("Valor introducido inválido. Vuelva a intentarlo.");
				break;
			}
		}
	}

}
