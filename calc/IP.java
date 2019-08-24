package calc;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class IP {

    private String ip;
    private String mask;
    private String ip_mask;

    public IP() {
        InetAddress ipAddress;
        try {
            ipAddress = InetAddress.getLocalHost();
            this.ip = ipAddress.getHostAddress();
            try {
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
                int mask = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
                this.mask = "" + mask;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        this.ip_mask = ip + "/" + mask;
    }

    public String getIp() {
        return ip;
    }

    public String getMask() {
        return mask;
    }

    public String getIp_mask() {
        return ip_mask;
    }
}
