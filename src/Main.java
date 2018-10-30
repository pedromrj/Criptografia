import java.util.Scanner;

public class Main {
	private static final Scanner TECLADO = new Scanner(System.in);
	public static void main(String[] args) {
		String palavra = lerString("Digite a palavra ou frase: ");
		CriptografiaAlgebra crip = new CriptografiaAlgebra();
		crip.setPalavra(palavra);
		System.out.println(crip.getCriptografia());
	}
	
	public static String lerString(String msg) {
		System.out.println(msg);
		return TECLADO.nextLine();
	}
}
