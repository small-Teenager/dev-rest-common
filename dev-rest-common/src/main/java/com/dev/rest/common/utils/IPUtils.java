package com.dev.rest.common.utils;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 保存IP地址不要用char(),应该使用long unsigned，
 * 使用inet_aton和inet_ntoa函数实现整数和IP地址的转换
 *
 */
public class IPUtils {

    /**
     * Converts an IPv4 address from dotted-quad string format to a long integer.
     *
     * @param ipAddress the IP address in dotted-quad string format (e.g., "192.168.1.1")
     * @return the IP address as a long integer
     * @throws IllegalArgumentException if the IP address is invalid
     */
    public static long inetAton(String ipAddress) {
        try {
            byte[] ipBytes = InetAddress.getByName(ipAddress).getAddress();
            return ((long) (ipBytes[0] & 0xFF) << 24)
                    | ((long) (ipBytes[1] & 0xFF) << 16)
                    | ((long) (ipBytes[2] & 0xFF) << 8)
                    | ((long) (ipBytes[3] & 0xFF));
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Invalid IP address: " + ipAddress, e);
        }
    }

    /**
     * Converts a long integer to an IPv4 address in dotted-quad string format.
     *
     * @param ipLong the IP address as a long integer
     * @return the IP address in dotted-quad string format
     */
    public static String inetNtoa(long ipLong) {
        byte[] ipBytes = new byte[4];
        ipBytes[0] = (byte) ((ipLong >> 24) & 0xFF);
        ipBytes[1] = (byte) ((ipLong >> 16) & 0xFF);
        ipBytes[2] = (byte) ((ipLong >> 8) & 0xFF);
        ipBytes[3] = (byte) (ipLong & 0xFF);

        try {
            return InetAddress.getByAddress(ipBytes).getHostAddress();
        } catch (UnknownHostException e) {
            // This should never happen because we constructed the InetAddress from a valid byte array
            throw new RuntimeException("Unable to convert long to IP address", e);
        }
    }


    public static void main(String[] args) {
        String ipAddress = "192.168.1.1";
        long ipLong = inetAton(ipAddress);

        System.out.println("IP Address: " + ipAddress + " -> Long: " + ipLong);

        String convertedIp = inetNtoa(ipLong);
        System.out.println("Long: " + ipLong + " -> IP Address: " + convertedIp);
    }
}

