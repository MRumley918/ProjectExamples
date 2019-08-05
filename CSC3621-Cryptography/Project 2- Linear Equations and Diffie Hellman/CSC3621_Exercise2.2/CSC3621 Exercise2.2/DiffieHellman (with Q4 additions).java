import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {
	static DiffieHellman dh = new DiffieHellman();
	public static BigInteger SecretA = new BigInteger("45");
	public static BigInteger SecretB = new BigInteger("78");

	class msg1 {
		BigInteger modulus;
		BigInteger base;
		BigInteger valueA;
	}

	class msg2 {
		BigInteger valueB;
	}

	private static msg1 message = dh.new msg1();
	private static msg2 message2 = dh.new msg2();
	
	private static msg1 ComputeMessageAtoB() {

		int modLength = 1024;
		SecureRandom rnd = new SecureRandom();
		message.modulus = BigInteger.probablePrime(modLength, rnd);
		BigInteger q = ((message.modulus).subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		do {
			message.base = new BigInteger(modLength, rnd);
			BigInteger b = (message.base).modPow(((message.modulus).subtract(BigInteger.ONE)).divide(q),
					message.modulus);
			if (!(b.equals(BigInteger.ONE))) {
				continue;
			}
		} while (message.base.compareTo(message.modulus) >= 0
				&& (!((message.base).modPow(q, (message.modulus))).equals(BigInteger.ONE)));
		message.valueA = message.base.modPow(SecretA, message.modulus);
		return message;
	}
	private static msg1 InterceptMessageA(msg1 message){
		int modLength =1024;
		SecureRandom rnd = new SecureRandom();
		message.valueA= new BigInteger(modLength,rnd);
		return message;
	}

	private static msg2 ComputeMessageBtoA(msg1 message) {

		message2.valueB = message.base.modPow(SecretB, message.modulus);
		return message2;
	}

	private static BigInteger ComputeKeyA(msg2 message2) {
		return message2.valueB.modPow(SecretA, message.modulus);
	}

	private static BigInteger ComputeKeyB(msg1 message) {
		return message.valueA.modPow(SecretB, message.modulus);
	}

	public static void main(String[] args) {

		msg1 msg1 = ComputeMessageAtoB();
		msg2 msg2 = ComputeMessageBtoA(msg1);
		BigInteger KeyA = ComputeKeyA(msg2);
		BigInteger KeyB = ComputeKeyB(msg1);
		System.out.println("SecretA: " + SecretA);
		System.out.println("SecretB: " + SecretB);
		System.out.println("msg1.modulus: " + msg1.modulus);
		System.out.println("msg1.base: " + msg1.base);
		System.out.println("msg1.valueA: " + msg1.valueA);
		System.out.println("msg2.valueB: " + msg2.valueB);
		System.out.println("Key A: " + KeyA);
		System.out.println("Key B: " + KeyB);

		if (KeyA.equals(KeyB)) {
			System.out.println("DIFFIE HELLMAN SUCCESS");
		} else {
			System.out.println("FAIL");
		}
		msg1 msg1AtoEve = ComputeMessageAtoB();
		System.out.println("SecretA: " + SecretA);
		System.out.println("SecretB: " + SecretB);
		System.out.println("msg1AtoEve.modulus: " +msg1AtoEve.modulus);
		System.out.println("msg1AtoEve.base: "+msg1AtoEve.base);
		System.out.println("msg1AtoEve.valueA: "+msg1AtoEve.valueA);
		msg1 msg1EvetoB = InterceptMessageA(msg1AtoEve);
		System.out.println("EVE INTERCEPTS MESSAGE AND CHANGES VALUE");
		System.out.println("msg1EvetoB.valueA: "+msg1EvetoB.valueA);
		msg2 msg2fromBtoEve = ComputeMessageBtoA(msg1EvetoB);
		System.out.println("msg2fromBtoEve.valueB: "+msg2fromBtoEve.valueB);
		msg2 msg2fromEvetoA = ComputeMessageBtoA(msg1AtoEve);
		System.out.println("msg2fromEvetoA.valueB: "+msg2fromEvetoA.valueB);
		BigInteger AliceKey= ComputeKeyA(msg2fromEvetoA);
		System.out.println("Alice Key: "+AliceKey);
		BigInteger BobKey= ComputeKeyB(msg1EvetoB);
		System.out.println("Bob Key: "+BobKey);
		BigInteger EveKeyA = ComputeKeyA(msg2fromBtoEve);
		System.out.println("Eve Key A: "+EveKeyA);
		BigInteger EveKeyB = ComputeKeyB(msg1AtoEve);
		System.out.println("Eve Key B: "+EveKeyB);
		if(AliceKey.equals(EveKeyA)){
			System.out.println("ALICE/EVE SHARE KEY");
		}
		if(BobKey.equals(EveKeyB)){
			System.out.println("BOB/EVE SHARE KEY");
		}
		
	}

}
