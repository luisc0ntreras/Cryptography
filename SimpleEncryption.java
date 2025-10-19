import java.util.*;

public class SimpleEncryption{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter your message:\n");
        String msg = sc.nextLine();

        System.out.println("Enter your key:\n");
        String key = sc.nextLine();

        StringBuilder sb = new StringBuilder(msg.length());
        while (sb.length() < msg.length()) {
            if (key.isEmpty()){ // if our key is empty it appends one zero to size of msg.length
                sb.append('\0');
            } else {
                sb.append(key); // append whole key string
            }
        }
        sb.setLength(msg.length()); // set sb length equal to msg.length
        String keypad = sb.toString(); // convert key into a string

        StringBuilder cipher = new StringBuilder(msg.length()); //encryption
        for (int i = 0; i < msg.length(); i++) {
            int m = msg.charAt(i) % 255;
            int k = keypad.charAt(i) % 255;
            int c = (m+k) % 255;  // grabs both values and encrypts them into c
            cipher.append((char) c);
        }
        String ascii = "";
        for (int i = 0; i <cipher.length(); i++){
            if (i>0) ascii += "-";
            ascii += (cipher.charAt(i) % 255); //append enxrypted char to cipher string
        }
        StringBuilder decrypt = new StringBuilder(msg.length()); // decryption
        for (int i = 0; i < cipher.length(); i++){
            int c = cipher.charAt(i) % 255;
            int k = keypad.charAt(i) % 255;
            int m = c-k;  //subtracts key from cipher
            if (m < 0) m += 255; //wrap around if result is negative
            decrypt.append((char) (m%255)); //append decrypted char to string
        }

        System.out.println("Encypted message: " + ascii);
        System.out.println("Encrypted string: " + cipher.toString());
        System.out.println("Decrypted text: " + decrypt.toString());

    }

}