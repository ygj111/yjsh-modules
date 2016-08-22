package com.hhh.fund.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class EncryptDES {
	
	//SecretKey 负责保存对称密钥
	private SecretKey secretKey;
	//Cipher负责完成加密或解密工作
	private Cipher c;
	//该字节数组负责保存加密的结果
	private byte[] cipherByte;
	
	public EncryptDES(String deskey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException{
		//生成密钥
		DESKeySpec desKeySpec = new DESKeySpec(deskey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		this.secretKey=secretKey;
		//生成Cipher对象,指定其支持的DES算法
		c = Cipher.getInstance("DES");
	}
	
	/**
	 * 对字符串加密
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encrytor(String str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		SecureRandom random = new SecureRandom();
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		c.init(Cipher.ENCRYPT_MODE, secretKey,random);
		byte[] src = str.getBytes();
		// 加密，结果保存进cipherByte
		cipherByte = c.doFinal(src);
		return cipherByte;
	}

	/**
	 * 对字符串解密
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] decryptor(byte[] buff) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		SecureRandom random = new SecureRandom();
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式
		c.init(Cipher.DECRYPT_MODE, secretKey,random);
		cipherByte = c.doFinal(buff);
		return cipherByte;
	}
	
	/**
	 * 加密文件
	 * @param sourceFileName 需要加密的文件
	 * @param targetFileName 加密后的文件
	 * @throws IOException 
	 * @throws InvalidKeyException 
	 */
	public void encrytFile(String sourceFileName, String targetFileName) throws InvalidKeyException, IOException {
		c.init(Cipher.ENCRYPT_MODE, secretKey);
		InputStream is = new FileInputStream(sourceFileName);
		OutputStream out = new FileOutputStream(targetFileName);
		CipherInputStream cis = new CipherInputStream(is, c);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
	}
	
	/**
	 * 解密文件
	 * @param sourceFileName 需要解密的文件
	 * @param targetFileName 解密后的文件
	 * @throws IOException 
	 * @throws InvalidKeyException 
	 */
	public void decryptFile(String sourceFileName, String targetFileName) throws IOException, InvalidKeyException {
		c.init(Cipher.DECRYPT_MODE, secretKey);
		InputStream is = new FileInputStream(sourceFileName);
		OutputStream out = new FileOutputStream(targetFileName);
		CipherOutputStream cos = new CipherOutputStream(out, c);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			cos.write(buffer, 0, r);
		}
		cos.close();
		out.close();
		is.close();
	}
	
	/**
	 * @param args
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws Exception {
		//密钥必须大于等于8位
		String desKey="12345678";
		EncryptDES de1 = new EncryptDES(desKey);
		EncryptDES de2 = new EncryptDES(desKey);
		String msg ="你好,你们好";
		byte[] encontent = de1.encrytor(msg);
		byte[] decontent = de2.decryptor(encontent);
		System.out.println("明文是:" + msg);
		System.out.println("加密后:" + new String(encontent));
		System.out.println("解密后:" + new String(decontent));
		EncryptDES de3 = new EncryptDES(desKey);
		EncryptDES de4 = new EncryptDES(desKey);
		//改成自己的文件路径
		de3.encrytFile("C:\\Users\\3hygj\\Desktop\\信息(1).txt","C:\\Users\\3hygj\\Desktop\\信息(2).txt");
		de4.decryptFile("C:\\Users\\3hygj\\Desktop\\信息(2).txt","C:\\Users\\3hygj\\Desktop\\信息(3).txt");
	}

}