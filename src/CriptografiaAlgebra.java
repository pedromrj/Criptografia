public class CriptografiaAlgebra {
	private static final int NUMERO_DE_LETRAS = 26;
	private static final int MODULO = -1;

	private String palavra;
	private int[][] matriz = { { 2, 5 }, { 1, 3 } };
	private int[][] matrizInversa = { { 3, -5 }, { -1, 2 } };
	private char[] letras = { 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y' };
	private int[] criptografia;

	public CriptografiaAlgebra(String palavra) {
		palavra = palavra.replaceAll(" ", "");
		if (palavra.length() % 2 != 0) {
			String ultima = palavra.substring(palavra.length() - 1);
			palavra += ultima;
			this.palavra = palavra.toUpperCase();
		} else {
			this.palavra = palavra.toUpperCase();
		}
		this.criptografia = new int[palavra.length()];
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}

	public int[][] getMatrizInversa() {
		return matrizInversa;
	}

	public void setMatrizInversa(int[][] matrizInversa) {
		this.matrizInversa = matrizInversa;
	}

	public String getCriptografia() {
		for (int i = 0; i < palavra.length(); i++) {
			for (int j = 0; j < letras.length; j++) {
				if (palavra.charAt(i) == letras[j]) {
					criptografia[i] = j;
					break;
				}
			}
		}
		int numero1 = 0;
		int numero2 = 1;
		for (int i = 1; i < criptografia.length; i += 2) {
			int letra1 = (matriz[0][0] * criptografia[numero1]) + (matriz[0][1] * criptografia[numero2]);
			int letra2 = (matriz[1][0] * criptografia[numero1]) + (matriz[1][1] * criptografia[numero2]);
			criptografia[i - 1] = letra1;
			criptografia[i] = letra2;
			numero1 += 2;
			numero2 += 2;
		}
		for (int i = 0; i < criptografia.length; i++) {
			if (criptografia[i] >= NUMERO_DE_LETRAS) {
				criptografia[i] = criptografia[i] % NUMERO_DE_LETRAS;
			} else if (criptografia[i] < 0 && criptografia[i] % NUMERO_DE_LETRAS != 0) {
				criptografia[i] = NUMERO_DE_LETRAS - ((criptografia[i] * MODULO) % NUMERO_DE_LETRAS);
			} else if (criptografia[i] < 0 && criptografia[i] % NUMERO_DE_LETRAS == 0) {
				criptografia[i] = 0;
			}
		}
		StringBuilder palavra = new StringBuilder();
		for (int i : criptografia) {
			palavra.append(letras[i]);
		}
		return palavra.toString();
	}

	public String getDescriptografia() {
		setMatriz(getMatrizInversa());
		String palavra = getCriptografia();
		return palavra;
	}
}