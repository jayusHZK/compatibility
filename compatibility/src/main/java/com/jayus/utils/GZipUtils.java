package com.jayus.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Slf4j
public class GZipUtils {
	public static final int BUFFER = 1024;
	public static final String EXT = ".gz";

	/**
	 * 数据压缩
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] compress(byte[] data) {
		byte[] output = null;
		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;
		try {
			bais = new ByteArrayInputStream(data);
			baos = new ByteArrayOutputStream();

			// 压缩
			compress(bais, baos);

			output = baos.toByteArray();

			baos.flush();

		} catch (Exception e) {

//			log.error("", e);
		} finally {
			try {
//				baos.close();
//
//				bais.close();
			} catch (Exception e) {
//				log.error("", e);
			}
		}

		return output;
	}

	/**
	 * 文件压缩
	 *
	 * @param file
	 * @throws Exception
	 */
	public static void compress(File file) throws Exception {
		compress(file, true);
	}

	/**
	 * 文件压缩
	 *
	 * @param file
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void compress(File file, boolean delete) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath() + EXT);

		compress(fis, fos);

//		fis.close();
		fos.flush();
//		fos.close();

		if (delete) {
			file.delete();
		}
	}

	/**
	 * 数据压缩
	 *
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static void compress(InputStream is, OutputStream os) {
		GZIPOutputStream gos = null;
		try {
			gos = new GZIPOutputStream(os);

			int count;
			byte data[] = new byte[BUFFER];
			while ((count = is.read(data, 0, BUFFER)) != -1) {
				gos.write(data, 0, count);
			}

			gos.finish();

			gos.flush();

		} catch (Exception e) {
//			log.error("", e);
		} finally {

			try {
//				gos.close();
			} catch (Exception e) {
//				log.error("", e);
			}

		}
	}

	/**
	 * 文件压缩
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void compress(String path) throws Exception {
		compress(path, true);
	}

	/**
	 * 文件压缩
	 *
	 * @param path
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void compress(String path, boolean delete) throws Exception {
		File file = new File(path);
		compress(file, delete);
	}

	/**
	 * 数据解压缩
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] decompress(byte[] data) {

		ByteArrayInputStream bais = null;
		ByteArrayOutputStream baos = null;

		try {
			bais = new ByteArrayInputStream(data);
			baos = new ByteArrayOutputStream();

			// 解压缩

		boolean unzipResult=decompress(bais, baos);
	 
			data = baos.toByteArray();

			baos.flush();

		} catch (Exception e) {
			log.warn("解压异常，{}",data.toString());
		} finally {
			try {
				if(null!=baos) {
//				baos.close();

				}
				if(null!=bais) {
//				bais.close();
				}
			} catch (Exception e) {
 			log.error("", e);
			}
		}
		return data;
	}

	public static String decompressStr(byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		byte[] ret = null;
		ret = decompress(data);
		return new String(ret);
	}

	/**
	 * 文件解压缩
	 *
	 * @param file
	 * @throws Exception
	 */
	public static void decompress(File file) throws Exception {
		decompress(file, true);
	}

	/**
	 * 文件解压缩
	 *
	 * @param file
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void decompress(File file, boolean delete) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(file.getPath().replace(EXT, ""));
		decompress(fis, fos);
//		fis.close();
		fos.flush();
//		fos.close();

		if (delete) {
			file.delete();
		}
	}

	/**
	 * 数据解压缩
	 *
	 * @param is
	 * @param os
	 * @throws Exception
	 */
	public static boolean  decompress(InputStream is, OutputStream os) {
		GZIPInputStream gis = null;
		boolean unzipResult = true;
		try {
			gis = new GZIPInputStream(is);

			int count;
			byte data[] = new byte[BUFFER];
			while ((count = gis.read(data, 0, BUFFER)) != -1) {
				os.write(data, 0, count);
			}

			//gis.close();
		} catch (Exception e) {
			unzipResult=false;
			log.warn("解压异常===>{}",e.getMessage());
		} finally {

			if (null != gis) {
				try {
//					gis.close();
				} catch (Exception e) {
 					log.error("关闭流异常", e);
				}
			}
		}
		return unzipResult;
	}

	/**
	 * 文件解压缩
	 *
	 * @param path
	 * @throws Exception
	 */
	public static void decompress(String path) throws Exception {
		decompress(path, true);
	}

	/**
	 * 文件解压缩
	 *
	 * @param path
	 * @param delete
	 *            是否删除原始文件
	 * @throws Exception
	 */
	public static void decompress(String path, boolean delete) throws Exception {
		File file = new File(path);
		decompress(file, delete);
	}
}
