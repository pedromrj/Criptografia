public class CriptografiaAlgebra {
	private static final int NUMERO_DE_LETRAS = 26;

	private String palavra;
	private int[][] matriz = { { 2, 5 }, { 1, 3 } };
	private int[][] matrizInversa = { { 3, -5 }, { -1, 2 } };
	private char[] letras = { 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y' };
	private int[] criptografia;

	public CriptografiaAlgebra(String palavra) {
		this.palavra = palavra.toUpperCase();
	}

	private void setNumerosDaTabela() {
		if (palavra.length() % 2 != 0) {
			String ultima = palavra.substring(palavra.length() - 1);
			palavra += ultima;
			this.palavra = palavra.toUpperCase();
		} else {
			this.palavra = palavra.toUpperCase();
		}
		this.criptografia = new int[palavra.length()];
		for (int i = 0; i < palavra.length(); i++) {
			for (int j = 0; j < letras.length; j++) {
				if (palavra.charAt(i) == letras[j])
					criptografia[i] = j;
			}
		}
	}

	private void setNumeroCriptografados() {
		setNumerosDaTabela();
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
			if (criptografia[i] > NUMERO_DE_LETRAS) {
				criptografia[i] = criptografia[i] % NUMERO_DE_LETRAS;
			}
		}
	}

	public String getCriptografia() {
		setNumeroCriptografados();
		StringBuilder palavra = new StringBuilder();
		for (int i : criptografia) {
			palavra.append(letras[i]);
		}
		return palavra.toString();
	}
	
	public String getDescriptografia() {
		this.matriz = this.matrizInversa;
		return getCriptografia();
	}
}