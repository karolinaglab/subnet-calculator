package calc;


public class Calculator {

    public static boolean checkIP(String ip) {
        String[] ipString = ip.split("\\.");
        if (ipString.length != 4) return false;
        int[] ipTab = new int[4];

        for (int i = 0; i < 4; i++) {
            if (ipString[i].matches("[0-9]+")) {
                ipTab[i] = Integer.parseInt(ipString[i]);
                if (ipTab[i] <= 255) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static int[] calculateNetworkAddress(int[] ip, int[] mask) {

        int[] networkAddress = new int[32];
        for (int i = 0; i < 32; i++) {
            networkAddress[i] = ip[i] * mask[i];
        }
        return networkAddress;
    }

    public static char calculateNetworkClass(int[] ip) {

        if (ip[0] == 0) {
            return 'A';
        } else if (ip[1] == 0) {
            return 'B';
        } else if (ip[2] == 0) {
            return 'C';
        } else if (ip[3] == 0) {
            return 'D';
        } else return 'E';

    }

    public static boolean isPrivate(String ip) {
        String[] ipString = ip.split("\\.");
        int[] ipTab = new int[4];
        for (int i = 0; i < 4; i++) {
            ipTab[i] = Integer.parseInt(ipString[i]);
        }
        if (ipTab[0] == 10 && (ipTab[1] >= 0 && ipTab[1] <= 255) && (ipTab[2] >= 0 && ipTab[2] <= 255) && (ipTab[3] >= 0 && ipTab[3] <= 255)) {
            return true;
        } else if (ipTab[0] == 172 && (ipTab[1] >= 16 && ipTab[1] <= 31) && (ipTab[2] >= 0 && ipTab[2] <= 255) && (ipTab[3] >= 0 && ipTab[3] <= 255)) {
            return true;
        } else if (ipTab[0] == 192 && ipTab[1] == 168 && (ipTab[2] >= 0 && ipTab[2] <= 255) && (ipTab[3] >= 0 && ipTab[3] <= 255)) {
            return true;
        } else {
            return false;
        }
    }

    public static int[] calculateBroadcastAddress(int[] ip, int[] mask) {
        int[] broadcastAddress = new int[32];
        for (int i = 0; i < 32; i++) {
            if (mask[i] == 0) {
                broadcastAddress[i] = 1;
            } else {
                broadcastAddress[i] = ip[i];
            }
        }
        return broadcastAddress;
    }

    public static String calculateFirstHostAddressDecimal(String networkAddress) {

        String[] addressTab = networkAddress.split("\\.");
        int minHost = Integer.parseInt(addressTab[3]);
        minHost += 1;
        addressTab[3] = "" + minHost;
        String minHostAddress = new String();
        for (int i = 0; i < 4; i++) {
            minHostAddress += addressTab[i];
            if (i < 3) {
                minHostAddress += ".";
            }
        }
        return minHostAddress;
    }

    public static String calculateLastHostAddressDecimal(String broadcastAddress) {
        String[] addressTab = broadcastAddress.split("\\.");
        int maxHost = Integer.parseInt(addressTab[3]);
        maxHost -= 1;
        addressTab[3] = "" + maxHost;
        String maxHostAddress = new String();
        for (int i = 0; i < 4; i++) {
            maxHostAddress += addressTab[i];
            if (i < 3) {
                maxHostAddress += ".";
            }
        }
        return maxHostAddress;
    }

    public static String calculateHostAddressBinary(String decimalHostAddress) {
        String binaryHostAddress = new String();
        binaryHostAddress = Converter.binaryIP(decimalHostAddress);
        int[] hostTab = Converter.ipTab(binaryHostAddress);
        binaryHostAddress = Converter.binaryAddress(hostTab);

        return binaryHostAddress;

    }

    public static int calculateMaxHostCount(String mask) {
        int maskInteger = Integer.parseInt(mask);
        int maxHostCount = (int) Math.pow(2, (32 - maskInteger)) - 2;
        return maxHostCount;
    }

    public static boolean isHost(String ip, String networkAddress, String broadcastAddress) {
        String[] ipString = ip.split("\\.");
        String[] netString = networkAddress.split("\\.");
        String[] brString = broadcastAddress.split("\\.");

        if (ipString[3].equals(netString[3]) || ipString[3].equals(brString[3])) {
            return false;
        } else return true;
    }

}
