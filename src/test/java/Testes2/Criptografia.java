package Testes2;

public class Criptografia {
	
	public static char[] alfabeto(){
		char[] alfabeto = new char[26];
		
		for(int i=0;i<26;i++){
			alfabeto[i] = (char) (i+65);
		}
		
		
		return alfabeto;
		
	}

	public static void main(String[] args) {
		String textoAntes = "RFPERIN FRH ABZR PBZCYRGB R AN SERAGR QB ZRFZB PBYBDHR DHNAGBF PNENPGRERF RYR CBFFHV QRFPBAFVQRENAQB BF RFCNPBF";
		
		char[] alfabeto = alfabeto();
		char a = 'A';
		int num_a = (int ) a;
			
		String saida = "";
		
		for(int i=0;i<textoAntes.length();i++){
			char x = textoAntes.charAt(i);
			int num_x = (int) x;
			num_x = num_x-13;
			if(num_x<65){
				num_x = num_x + 26;
			}
			char saidaCaracter = (char)num_x;
			saida+=saidaCaracter;
			
		}
		
		System.out.println(saida);
		
		
		
	}

}