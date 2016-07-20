package com.njnu.like.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MacAddressUtil
{
    
    /**
     * @param args
     */
    private static String hexByte(byte b)
    {
        String s = "000000" + Integer.toHexString(b);
        return s.substring(s.length() - 2);
    }
    
    public static List<String> getHostMAC()
        throws SocketException, UnknownHostException
    {
        //		       Enumeration<NetworkInterface> networkInterface_enumeration;   
        //		       String mac_address = "";   
        //		        networkInterface_enumeration = NetworkInterface.getNetworkInterfaces();   
        //		       while (networkInterface_enumeration.hasMoreElements()) {   
        //		            byte[] mac = networkInterface_enumeration.nextElement().getHardwareAddress();   
        //		            if (mac == null)   
        //		               continue;   
        //		            mac_address = hexByte(mac[0]) + split + hexByte(mac[1]) + split   
        //		                   + hexByte(mac[2]) + split + hexByte(mac[3]) + split   
        //		                    + hexByte(mac[4]) + split + hexByte(mac[5]);   
        //	        }   
        //		        return mac_address;   
        
        NetworkInterface network = null;
        
        //获取所有
        Enumeration<NetworkInterface> enumNetworkInterface = NetworkInterface.getNetworkInterfaces();
        
        byte[] mac = null;
        List<String> maxAddresses = new ArrayList<String>();
        
        while (enumNetworkInterface.hasMoreElements())
        {
            
            network = enumNetworkInterface.nextElement();
            mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            //可能获取的是lo网卡，是没有mac地址的
            if (mac != null)
            {
                for (int i = 0; i < mac.length; i++)
                {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                
                maxAddresses.add(sb.toString());
            }
        }
        
        return maxAddresses;
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stubEnumeration<NetworkInterface> networkInterface_enumeration;   
        
        try
        {
            System.out.println(getHostMAC());
        }
        catch (SocketException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
