package com.njnu.like.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class License implements ServletContextListener
{
    //public void	ifHavePermission(){
    //
    //	String expresslyMd5 = "";
    //	String nativeExpresslyMd5 = "";
    //	String schoolName = "";
    //	String beginTime = "";
    //	String endTime = "";
    //	String macAddress = "";
    //	String ciphertext = "";
    //	List<String> nativeMacAddresses = null;
    //	BufferedReader bufferedReader = null;
    //	Logger logger = Logger.getLogger(AESUtil.class);
    //	try {
    //		nativeMacAddresses = MacAddressUtil.getHostMAC();
    //
    //		File file = new File("./license.txt");
    //		if (!file.exists()) {
    //			logger.error("系统没有授权，无法运行！");
    //			System.exit(0);
    //		}
    //
    //		InputStreamReader isr = new InputStreamReader(new FileInputStream(
    //				file), "UTF-8");
    //
    //		bufferedReader = new BufferedReader(isr);
    //
    //		schoolName = bufferedReader.readLine().split("：")[1];
    //		beginTime = bufferedReader.readLine().split("：")[1];
    //		endTime = bufferedReader.readLine().split("：")[1];
    //		macAddress = bufferedReader.readLine().split("：")[1];
    //		ciphertext = bufferedReader.readLine().split("：")[1];
    //
    //		String expressly = schoolName + beginTime + endTime + macAddress;
    //		System.out.println(expressly);
    //		expresslyMd5 = MD5Util.createMD5(expressly);
    //		System.out.println(expresslyMd5);
    //		nativeExpresslyMd5 = AESUtil.decrypt(ciphertext);
    //
    //		if (!expresslyMd5.equals(nativeExpresslyMd5)) {
    //			logger.error("系统没有授权，无法运行！");
    //			System.exit(0);
    //		}
    //		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //		Calendar currentCalendar = Calendar.getInstance();
    //		Calendar beginCalendar = Calendar.getInstance();
    //		Calendar endCalendar = Calendar.getInstance();
    //		beginCalendar.clear();
    //		endCalendar.clear();
    //		beginCalendar.setTime(formatter.parse(beginTime));
    //		endCalendar.setTime(formatter.parse(endTime));
    //		if (currentCalendar.compareTo(beginCalendar) == -1
    //				|| currentCalendar.compareTo(endCalendar) == 1) {
    //
    //			logger.error("系统没有授权，无法运行！");
    //			System.exit(0);
    //
    //		}
    //		for (int i = 0; i < nativeMacAddresses.size(); i++) {
    //			String nativeMacAddress = nativeMacAddresses.get(i);
    //			if (nativeMacAddress.equals(macAddress)) {
    //				break;
    //			}
    //			if (++i == nativeMacAddresses.size()) {
    //				logger.error("系统没有授权，无法运行！");
    //				System.exit(0);
    //
    //			}
    //		}
    //	} catch (Exception e) {
    //		logger.error("检查系统授权出错，无法运行！");
    //		System.exit(0);
    //	} finally {
    //		if (bufferedReader != null) {
    //			try {
    //
    //				bufferedReader.close();
    //			} catch (IOException e) {
    //				// TODO Auto-generated catch block
    //				e.printStackTrace();
    //			}
    //		}
    //	}
    //	
    //}
    
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        /* NioTcpServer server = new NioTcpServer("192.168.100.128", 8050);
         server.start();*/
        
        String expresslyMd5 = "";
        String nativeExpresslyMd5 = "";
        String schoolName = "";
        String beginTime = "";
        String endTime = "";
        String macAddress = "";
        String ciphertext = "";
        List<String> nativeMacAddresses = null;
        BufferedReader bufferedReader = null;
        Logger logger = Logger.getLogger(AESUtil.class);
        try
        {
            nativeMacAddresses = MacAddressUtil.getHostMAC();
            
            File file = new File("./license.txt");
            if (!file.exists())
            {
                logger.error("系统没有授权，无法运行！");
                System.exit(0);
            }
            
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            
            bufferedReader = new BufferedReader(isr);
            
            schoolName = bufferedReader.readLine().split("：")[1];
            beginTime = bufferedReader.readLine().split("：")[1];
            endTime = bufferedReader.readLine().split("：")[1];
            macAddress = bufferedReader.readLine().split("：")[1];
            ciphertext = bufferedReader.readLine().split("：")[1];
            
            String expressly = schoolName + beginTime + endTime + macAddress;
            
            expresslyMd5 = MD5Util.createMD5(expressly);
            
            nativeExpresslyMd5 = AESUtil.decrypt(ciphertext);
            
            if (!expresslyMd5.equals(nativeExpresslyMd5))
            {
                logger.error("系统没有授权，无法运行！");
                System.exit(0);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Calendar currentCalendar = Calendar.getInstance();
            Calendar beginCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            beginCalendar.clear();
            endCalendar.clear();
            beginCalendar.setTime(formatter.parse(beginTime));
            endCalendar.setTime(formatter.parse(endTime));
            if (currentCalendar.compareTo(beginCalendar) == -1 || currentCalendar.compareTo(endCalendar) == 1)
            {
                
                logger.error("系统没有授权，无法运行！");
                System.exit(0);
                
            }
            for (int i = 0; i < nativeMacAddresses.size(); i++)
            {
                String nativeMacAddress = nativeMacAddresses.get(i);
                if (nativeMacAddress.equals(macAddress))
                {
                    break;
                }
                if (i + 1 == nativeMacAddresses.size())
                {
                    logger.error("系统没有授权，无法运行！");
                    System.exit(0);
                    
                }
            }
        }
        catch (Exception e)
        {
            logger.error("检查系统授权出错，无法运行！");
            System.exit(0);
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        arg0.getServletContext().setAttribute("schoolName", schoolName);
    }
    
}
