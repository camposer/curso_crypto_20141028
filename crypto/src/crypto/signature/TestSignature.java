package crypto.signature;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class TestSignature {
	private static final String FICHERO = "file/quijote.txt";
	private static final String FICHERO_FIRMA = "file/quijote.txt.sig";
	private static final String ALGORITMO_ASIMETRICO = "RSA";
	private static final String ALGORITMO_FIRMA = "SHA1withRSA";
	
	public static void main(String[] args) throws Exception {
		// 1. Generando par de claves
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITMO_ASIMETRICO);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		
		firmar(keyPair.getPrivate());
		verificar(keyPair.getPublic());
	}

	private static void firmar(PrivateKey key) throws Exception {
		byte[] bytes = Files.readAllBytes(new File(FICHERO).toPath());
		
		Signature signature = Signature.getInstance(ALGORITMO_FIRMA);
		signature.initSign(key);
		signature.update(bytes);
		byte[] firma = signature.sign();
		
		Files.write(new File(FICHERO_FIRMA).toPath(), 
				firma, 
				StandardOpenOption.CREATE);
		
	}
	
	private static void verificar(PublicKey key) throws Exception { 
		byte[] bytes = Files.readAllBytes(new File(FICHERO).toPath());
		byte[] bytesFirma = Files.readAllBytes(new File(FICHERO_FIRMA).toPath());
		
		Signature signature = Signature.getInstance(ALGORITMO_FIRMA);
		signature.initVerify(key);
		signature.update(bytes);
		boolean verificado = signature.verify(bytesFirma);

		System.out.println("Verificado: " + verificado);
	}
}
