package com.test.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;

public class MemcachedUtils {
	private static final Logger logger = Logger.getLogger(MemcachedUtils.class);  
    private static MemCachedClient cachedClient;  
    static {  
        if (cachedClient == null)  
            cachedClient = new MemCachedClient("memcachedPool");  
    }  
  
    private MemcachedUtils() {}  
  
    /** 
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean set(String key, Object value) {  
        return setExp(key, value, null);  
    }  
  
    /** 
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean set(String key, Object value, Date expire) {  
        return setExp(key, value, expire);  
    }  
  
    /** 
     * �򻺴�����µļ�ֵ�ԡ�������Ѿ����ڣ���֮ǰ��ֵ�����滻�� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    private static boolean setExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.set(key, value, expire);  
        } catch (Exception e) {  
            // ��¼Memcached��־  
            MemcachedLog.writeLog("Memcached set��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean add(String key, Object value) {  
        return addExp(key, value, null);  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean add(String key, Object value, Date expire) {  
        return addExp(key, value, expire);  
    }  
  
    /** 
     * ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    private static boolean addExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.add(key, value, expire);  
        } catch (Exception e) {  
            // ��¼Memcached��־  
            MemcachedLog.writeLog("Memcached add��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @return 
     */  
    public static boolean replace(String key, Object value) {  
        return replaceExp(key, value, null);  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean replace(String key, Object value, Date expire) {  
        return replaceExp(key, value, expire);  
    }  
  
    /** 
     * �������Ѿ�����ʱ��replace ����Ż��滻�����еļ��� 
     *  
     * @param key 
     *            �� 
     * @param value 
     *            ֵ 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    private static boolean replaceExp(String key, Object value, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.replace(key, value, expire);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached replace��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * get �������ڼ�����֮ǰ��ӵļ�ֵ����ص�ֵ�� 
     *  
     * @param key 
     *            �� 
     * @return 
     */  
    public static Object get(String key) {  
        Object obj = null;  
        try {  
            obj = cachedClient.get(key);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached get��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return obj;  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @return 
     */  
    public static boolean delete(String key) {  
        return deleteExp(key, null);  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    public static boolean delete(String key, Date expire) {  
        return deleteExp(key, expire);  
    }  
  
    /** 
     * ɾ�� memcached �е��κ�����ֵ�� 
     *  
     * @param key 
     *            �� 
     * @param expire 
     *            ����ʱ�� New Date(1000*10)��ʮ������ 
     * @return 
     */  
    private static boolean deleteExp(String key, Date expire) {  
        boolean flag = false;  
        try {  
            flag = cachedClient.delete(key, expire);  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached delete��������keyֵ��" + key + "\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �������е����м�/ֵ�� 
     *  
     * @return 
     */  
    public static boolean flashAll() {  
        boolean flag = false;  
        try {  
            flag = cachedClient.flushAll();  
        } catch (Exception e) {  
            MemcachedLog.writeLog("Memcached flashAll��������\r\n" + exceptionWrite(e));  
        }  
        return flag;  
    }  
  
    /** 
     * �����쳣ջ��Ϣ��String���� 
     *  
     * @param e 
     * @return 
     */  
    private static String exceptionWrite(Exception e) {  
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        e.printStackTrace(pw);  
        pw.flush();  
        return sw.toString();  
    }  
  
    /** 
     *  
     * @ClassName: MemcachedLog 
     * @Description: Memcached��־��¼ 
     * @author yinjw 
     * @date 2014-6-18 ����5:01:37 
     *  
     */  
    private static class MemcachedLog {  
        private final static String MEMCACHED_LOG = "D:\\memcached.log";  
        private final static String LINUX_MEMCACHED_LOG = "/usr/local/logs/memcached.log";  
        private static FileWriter fileWriter;  
        private static BufferedWriter logWrite;  
        // ��ȡPID�������ҵ���Ӧ��JVM����  
        private final static RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();  
        private final static String PID = runtime.getName();  
  
        /** 
         * ��ʼ��д���� 
         */  
        static {  
            try {  
                String osName = System.getProperty("os.name");  
                if (osName.indexOf("Windows") == -1) {  
                    fileWriter = new FileWriter(MEMCACHED_LOG, true);  
                } else {  
                    fileWriter = new FileWriter(LINUX_MEMCACHED_LOG, true);  
                }  
                logWrite = new BufferedWriter(fileWriter);  
            } catch (IOException e) {  
                logger.error("memcached ��־��ʼ��ʧ��", e);  
                closeLogStream();  
            }  
        }  
  
        /** 
         * д����־��Ϣ 
         *  
         * @param content 
         *            ��־���� 
         */  
        public static void writeLog(String content) {  
            try {  
                logWrite.write("[" + PID + "] " + "- [" + new SimpleDateFormat("yyyy��-MM��-dd�� hhʱ:mm��:ss��").format(new Date().getTime()) + "]\r\n"  
                        + content);  
                logWrite.newLine();  
                logWrite.flush();  
            } catch (IOException e) {  
                logger.error("memcached д����־��Ϣʧ��", e);  
            }  
        }  
  
        /** 
         * �ر��� 
         */  
        private static void closeLogStream() {  
            try {  
                fileWriter.close();  
                logWrite.close();  
            } catch (IOException e) {  
                logger.error("memcached ��־����ر�ʧ��", e);  
            }  
        }  
    }  
}
