package calc;

public class Converter {


    public static int[] maskTab(String mask) {
        int[] maskTab = new int[32];
        int integerMask = Integer.parseInt(mask);
        for (int i = 0; i < integerMask; i++) {
            maskTab[i] = 1;
        }
        return maskTab;
    }

    public static int[] ipTab(String ip) {
        int[] ipTab = new int[32];
        for (int i = 0; i < 32; i++) {
            if (ip.charAt(i) == '1') {
                ipTab[i] = 1;
            } else if (ip.charAt(i) == '0') {
                ipTab[i] = 0;
            }
        }
        return ipTab;
    }

    public static String binaryIP(String ip) {
        String[] ipString = ip.split("\\.");

        String stringIP = new String();
        String[] binaryIP = new String[4];
        int[] oct = new int[4];
        for (int i = 0; i < 4; i++) {
            oct[i] = Integer.parseInt(ipString[i]);
            binaryIP[i] = Integer.toBinaryString(oct[i]);
            while (binaryIP[i].length() < 8) {
                binaryIP[i] = '0' + binaryIP[i];
            }
            stringIP += binaryIP[i];
        }
        return stringIP;
    }

    public static String binaryAddress(int[] addressTab) {
        String address = new String();
        for (int i = 0; i < addressTab.length; i++) {
            if (addressTab[i] == 1) {
                address += "1";
            } else if (addressTab[i] == 0) {
                address += "0";
            }
            if ((i + 1) % 8 == 0 && (i + 1) != addressTab.length) {
                address += ".";
            }
        }
        return address;
    }

    public static String decimalAddress(String binAddress) {
        String decAddress = new String();
        int count = 0;
        String[] addressTab = binAddress.split("\\.");
        for (int i = 0; i < addressTab.length; i++) {
            int decimal = Integer.parseInt(addressTab[i], 2);
            String part = "" + decimal;
            decAddress += part;
            if (count < 3) {
                decAddress += ".";
            }
            count++;
        }
        return decAddress;
    }
}
