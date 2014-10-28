package crypto.cipher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;


public class TestPK {
	private static final String ALGORITMO = "RSA";
	private static final String FICHERO_ORIGINAL = "file/quijote.txt";
	private static final String FICHERO_CIFRADO = "file/quijote-cifrado.txt";
	private static final String FICHERO_DESCIFRADO = "file/quijote-descifrado.txt";
	private static final int MAX_BLOQUE_CIFRADO = 117;
	private static final int MAX_BLOQUE_DESCIFRADO = 128;
	
	public static void main(String[] args) throws Exception {
		// 0. Obteniendo clave simétrica
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITMO);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		System.out.println("Par de claves generado...");
		
		cifrar(keyPair.getPublic());
		System.out.println("Fichero cifrado (con clave pública)...");
		descifrar(keyPair.getPrivate());
		System.out.println("Fichero descifrado (con clave privada)...");
	}


	private static void descifrar(Key key) throws Exception {
		// 1. Obteniendo bytes del fichero
		byte[] mensajeCifrado = Files.readAllBytes(new File(FICHERO_CIFRADO).toPath());
		
		// 2. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		// 3. Calculando número de bloques
		int bloques = (int)Math.ceil((double)mensajeCifrado.length / MAX_BLOQUE_DESCIFRADO);
		
		// 4. Cifrando por bloque
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int maxBloqueDescifradoLen = MAX_BLOQUE_DESCIFRADO;
		for (int i = 0; i < bloques; i++) {
			if (i + 1 == bloques)
				maxBloqueDescifradoLen = mensajeCifrado.length - (i * MAX_BLOQUE_DESCIFRADO);
			
			byte[] bloque = cipher.doFinal(mensajeCifrado, i * MAX_BLOQUE_DESCIFRADO, maxBloqueDescifradoLen);
			
			baos.write(bloque);
		}
		
		// 5. Escribiendo fichero cifrado
		Files.write(new File(FICHERO_DESCIFRADO).toPath(), 
				baos.toByteArray(), 
				StandardOpenOption.CREATE);
	}


	private static void cifrar(Key key) throws Exception {
		// 1. Obteniendo bytes del fichero
		byte[] mensaje = Files.readAllBytes(new File(FICHERO_ORIGINAL).toPath());
		
		// 2. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		// 3. Calculando número de bloques
		int bloques = (int)Math.ceil((double)mensaje.length / MAX_BLOQUE_CIFRADO);
		
		// 4. Cifrando por bloque
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int maxBloqueCifradoLen = MAX_BLOQUE_CIFRADO;
		
		for (int i = 0; i < bloques; i++) {
			if (i + 1 == bloques)
				maxBloqueCifradoLen = mensaje.length - (i * MAX_BLOQUE_CIFRADO);
				
			byte[] bloqueCifrado = cipher.doFinal(mensaje, i * MAX_BLOQUE_CIFRADO, maxBloqueCifradoLen);
			
			baos.write(bloqueCifrado);
		}
		
		// 5. Escribiendo fichero cifrado
		Files.write(new File(FICHERO_CIFRADO).toPath(), 
				baos.toByteArray(), 
				StandardOpenOption.CREATE);
	}
}
