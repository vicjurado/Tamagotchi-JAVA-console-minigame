class Tamagochi {
	private String nombre;
	private int hambre;
	private int suenho;
	private int higiene;
	private int diversion;
	private int indice; // Parámetro que controla en todo momento el índice de cada Tamagochi.
	private boolean activo = true; // Parámetro que controla que el Tamagochi este muerto o no.

	public Tamagochi(int indice, String nombre, int hambre, int suenho, int higiene, int diversion, boolean activo) {
		this.indice = indice;
		this.nombre = nombre;
		this.hambre = hambre;
		this.suenho = suenho;
		this.higiene = higiene;
		this.diversion = diversion;
		this.activo = true;
	}

	public boolean getActivo() {
		return activo;
	}

	public String getNombre() {
		return nombre;
	}

	// Las cuatro funciones que tenemos son iguales, con un condicional en el que si
	// alcanza el valor 100, llama a la función pletórico que devuelve un mensaje.
	// En todas las circunstancias llama al método comprobarEstado para hacer una
	// serie de comprobaciones que revisaremos más tarde.
	public void comer() {
		hambre += 20;
		if (hambre >= 100) {
			hambre = 100;
			pletorico();
		}
		suenho -= 10;
		higiene -= 10;
		diversion -= 10;
		comprobarEstado();
	}

	public void dormir() {
		suenho += 20;
		if (suenho >= 100) {
			suenho = 100;
			pletorico();
		}
		hambre -= 10;
		higiene -= 10;
		diversion -= 10;
		comprobarEstado();
	}

	public void duchar() {
		higiene += 20;
		if (higiene >= 100) {
			higiene = 100;
			pletorico();
		}
		hambre -= 10;
		suenho -= 10;
		diversion -= 10;
		comprobarEstado();
	}

	public void jugar() {
		diversion += 20;
		if (diversion >= 100) {
			diversion = 100;
			pletorico();
		}
		hambre -= 10;
		suenho -= 10;
		higiene -= 10;
		comprobarEstado();
	}

	// Este método decide si el Tamagochi muere (alguno de sus valores alcanza 0 y
	// llama al método morir) o salta una alarma avisando que alguno de nuestros
	// valores se encuentra en 20 o por debajo.
	public void comprobarEstado() {
		if (hambre <= 0 || suenho <= 0 || higiene <= 0 || diversion <= 0) {
			morir();
		} else if (hambre <= 20 || suenho <= 20 || higiene <= 20 || diversion <= 20) {
			System.out.println("¡Revisa las stats de " + nombre + ", está falto de cariño!");
		}
	}

	// Método morir, coloca el boolean 'activo' en false, por lo que quedará como
	// inactivo, valga la redundancia. También cambia todos los valores a 0.
	public void morir() {
		activo = false;
		hambre = 0;
		suenho = 0;
		higiene = 0;
		diversion = 0;

		System.out.println("(X_X)");
		System.out.println("...");
		System.out.println("Has dejado morir a " + nombre + "...");
		System.out.println("     ___   ");
		System.out.println(" ---|   |---");
		System.out.println("|    DEP    |");
		System.out.println(" ---|   |---");
		System.out.println("    |   |   ");
		System.out.println("    |   |   ");
		System.out.println("    |___|   ");
		System.out.println();
		System.out.println("Espero que tengas la conciencia tranquila...");
		System.out.println("...");
		System.out.println("Cuida al resto mejor o tendré que llamar a asuntos sociales...");
	}

	// Método pletórico, avisa de que alguno de sus valores está en 100.
	public void pletorico() {
		System.out.println(nombre + " está que se sube por las paredes... ¡ESTÁ PLETÓRICO!");
		System.out.println("(@~@)");
		System.out.println("Será mejor que lo tranquilices o acabará con la humanidad...");
	}

	@Override
	public String toString() {
		return "Numero " + indice + ": " + nombre + " - STATS: Hambre: " + hambre + " , sueño: " + suenho
				+ ", higiene: " + higiene + ", diversion: " + diversion + ".";
	}

}