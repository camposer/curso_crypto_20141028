package crypto.cipher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;


public class TestSKStream {
	private static final String ALGORITMO = "DESede";
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


	private static void descifrar(Key key) throws Exception {
		// 1. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		// 2. Construyendo flujos de entrada (texto cifrado)
		FileInputStream fis = new FileInputStream(FICHERO_CIFRADO);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		BufferedReader br = new BufferedReader(new InputStreamReader(cis));
		
		// 3. Construyendo fichero de salida (texto-plano)
		PrintWriter pw = new PrintWriter(new FileOutputStream(FICHERO_DESCIFRADO));
		
		String linea = br.readLine(); 
		while (linea != null) {
			pw.println(linea);
			linea = br.readLine();
		}
		
		// 4. Cerrando flujos
		pw.flush();
		pw.close();
		br.close();

	}


	private static void cifrar(Key key) throws Exception {
		// 1. Inicializando el motor de cifrado
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// 2. Construyendo flujos de salida cifrada!!
		FileOutputStream fos = new FileOutputStream(FICHERO_CIFRADO);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher);
		PrintWriter pw = new PrintWriter(cos);
		
		// 3. Construyendo flujo de lectura (texto plano)
		FileInputStream fis = new FileInputStream(FICHERO_ORIGINAL);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String linea = br.readLine(); 
		while (linea != null) {
			pw.println(linea);
			linea = br.readLine();
		}
		
		// 4. Cerrando flujos
		pw.flush();
		pw.close();
		br.close();
	}
}
