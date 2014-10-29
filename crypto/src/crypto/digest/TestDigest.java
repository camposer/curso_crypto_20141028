package crypto.digest;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class TestDigest {
	private static final String ALGORITMO = "SHA-1";
	private static final String FICHERO = "file/quijote.txt";
	
	public static void main(String[] args) throws Exception {
		byte[] bytes = Files.readAllBytes(new File(FICHERO).toPath());
		
		MessageDigest md = MessageDigest.getInstance(ALGORITMO);
		byte[] digest = md.digest(bytes);
		
		System.out.println(ALGORITMO + " del fichero: " + FICHERO);
		System.out.println(DatatypeConverter.printHexBinary(digest));
		
	}
}
