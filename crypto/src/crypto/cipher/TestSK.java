package crypto.cipher;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;


public class TestSK {
	private static final String ALGORITMO = "AES";
	private static final String FICHERO_ORIGINAL = "file/quijote.txt";
	private static final String FICHERO_CIFRADO = "file/quijote-cifrado.txt";
	private static final String FICHERO_DESCIFRADO = "file/quijote-descifrado.txt";
	
	public static void main(String[] args) throws Exception {
		// 0. Obteniendo clave simétrica
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
		Key key = keyGen.generateKey();

		cifrar(key);
		System.out.println("Fichero cifrado...");
		descifrar(key);
		System.out.println("Fichero descifrado...");
	}

	private static void cifrar(Key key) throws Exception {
		// 1. Obteniendo bytes del fichero
		byte[] mensaje = Files.readAllBytes(new File(FICHERO_ORIGINAL).toPath());
		
		// 2. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		// 3. Cifrando mensaje original
		byte[] mensajeCifrado = cipher.doFinal(mensaje);
		
		// 4. Escribiendo fichero cifrado
		Files.write(new File(FICHERO_CIFRADO).toPath(), 
				mensajeCifrado, 
				StandardOpenOption.CREATE);
	}
	
	private static void descifrar(Key key) throws Exception {
		// 1. Obteniendo bytes del fichero
		byte[] mensajeCifrado = Files.readAllBytes(new File(FICHERO_CIFRADO).toPath());
		
		// 2. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		// 3. Cifrando mensaje original
		byte[] mensaje = cipher.doFinal(mensajeCifrado);
		
		// 4. Escribiendo fichero cifrado
		Files.write(new File(FICHERO_DESCIFRADO).toPath(), 
				mensaje, 
				StandardOpenOption.CREATE);
	}
	
}
