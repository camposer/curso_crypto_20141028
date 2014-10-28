package crypto.cipher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;


public class TestSK {
	private static final String ALGORITMO = "DES";
	
	
	public static void main(String[] args) throws Exception {
		// 1. Obteniendo clave simétrica
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
		Key key = keyGen.generateKey();

		cifrar(key);
		descifrar(key);
	}


	private static void cifrar(Key key) throws Exception {
		// 1. Obteniendo bytes del fichero
		byte[] mensaje = Files.readAllBytes(new File("file/quijote.txt").toPath());
		
		// 2. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		// 3. Cifrando mensaje original
		byte[] mensajeCifrado = cipher.doFinal(mensaje);
		
		// 4. Escribiendo fichero cifrado
		Files.write(new File("file/quijote-cifrado.txt").toPath(), 
				mensajeCifrado, 
				StandardOpenOption.CREATE_NEW);
	}
}
