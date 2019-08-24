package calc;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RunCalc {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IP host = new IP();
        String ip = new String();
        String mask = new String();
        boolean check = true;


        if (args.length == 0) {
            ip = host.getIp();
            mask = host.getMask();
        } else {
            String myString = args[0];
            if (myString.contains("/")) {
                String[] ipAndMask = myString.split("/");
                ip = ipAndMask[0];
                mask = ipAndMask[1];
                if (ipAndMask.length != 2) {
                    check = false;
                }
            } else check = false;
        }

        if (Calculator.checkIP(ip) && check) {
            String fileContent = new String();

            String stringIP = Converter.binaryIP(ip);

            int[] ipTab = Converter.ipTab(stringIP);
            int[] maskTab = Converter.maskTab(mask);
            int[] networkAddress = Calculator.calculateNetworkAddress(ipTab, maskTab);
            int[] broadcastAddress = Calculator.calculateBroadcastAddress(ipTab, maskTab);
            char networkClass = Calculator.calculateNetworkClass(ipTab);

            fileContent += "IP adress with mask: " + ip + "/" + mask + "\n" + "\n" + "Network class: " + networkClass + "\n";

            if (Calculator.isPrivate(ip)) {
                fileContent += "Address is private" + "\n";
            } else {
                fileContent += "Address is public" + "\n";
            }

            String binaryIP = Converter.binaryAddress(ipTab);
            fileContent += "Binary IP: " + binaryIP + "\n" + "Decimal IP: " + ip + "\n";

            String binaryMask = Converter.binaryAddress(maskTab);
            String decimalMask = Converter.decimalAddress(binaryMask);
            fileContent += "Binary mask: " + binaryMask + "\n" + "Decimal mask: " + decimalMask + "\n";

            String binaryNetworkAddress = Converter.binaryAddress(networkAddress);
            String decimalNetworkAddress = Converter.decimalAddress(binaryNetworkAddress);
            fileContent += "Binary network address: " + binaryNetworkAddress + "\n" + "Decimal network address: " + decimalNetworkAddress + "\n";

            String binaryBroadcastAddress = Converter.binaryAddress(broadcastAddress);
            String decimalBroadcastAddress = Converter.decimalAddress(binaryBroadcastAddress);
            fileContent += "Binary broadcast address: " + binaryBroadcastAddress + "\n" + "Decimal broadcast address: " + decimalBroadcastAddress + "\n";

            String decimalFirstHostAddress = Calculator.calculateFirstHostAddressDecimal(decimalNetworkAddress);
            String binaryFirstHostAddress = Calculator.calculateHostAddressBinary(decimalFirstHostAddress);
            fileContent += "Binary first host address: " + binaryFirstHostAddress + "\n" + "Decimal first host address: " + decimalFirstHostAddress + "\n";

            String decimalLastHostAddress = Calculator.calculateLastHostAddressDecimal(decimalBroadcastAddress);
            String binaryLastHostAddress = Calculator.calculateHostAddressBinary(decimalLastHostAddress);
            fileContent += "Binary last host address: " + binaryLastHostAddress + "\n" + "Decimal last host address: " + decimalLastHostAddress + "\n";

            int maxHostCount = Calculator.calculateMaxHostCount(mask);
            fileContent += "Max host count: " + maxHostCount + "\n";
            System.out.println(fileContent);


            if (Calculator.isHost(ip, decimalNetworkAddress, decimalBroadcastAddress)) {
                System.out.println("Address is host, do you want to send ping? y/n");
                String r = scanner.nextLine();
                if (r.equals("y")) {
                    PingIP.runSystemCommand("ping " + ip);
                }
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("d:/Projects/samplefile1.txt"));
                writer.write(fileContent);
                writer.close();
            } catch (IOException ex) {

            }
        } else {
            System.err.println("IP isn't correct");
        }
    }

}
