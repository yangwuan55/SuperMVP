package com.exitedcode.supernetwork.net.util;

import com.exitedcode.supernetwork.Env;

/**
 * 引用新的Logger日志输出，Logger.s()是发送到服务器端
 * 
 * @author ly
 * 
 */
public class LOGGER {

	/**
	 * 是否把Log信息发送到crashlytics上
	 */
	public static final boolean IS_UPLOAD_TO_CRASHLYTICS = false;

	/**
	 * 是否把Log信息输出到Android日志控制台
	 */
	public static boolean IS_OUTPUT_ANDROIDLOG = Env.isDebug();

	/**
	 * Send a DEBUG log message.
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.d(tag, msg, tr);
		}
	}

	public static void i(String tag, String msg) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.i(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.w(tag, msg, tr);
		}
	}

	public static void w(String tag, Throwable tr) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.w(tag, tr);
		}
	}

	public static void e(String tag, String msg) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (IS_OUTPUT_ANDROIDLOG) {
			android.util.Log.e(tag, msg, tr);
		}
	}

}
