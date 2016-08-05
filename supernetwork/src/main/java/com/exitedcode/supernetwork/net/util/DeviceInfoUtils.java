package com.exitedcode.supernetwork.net.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Window;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 
 * 设备信息工具类DeviceInfoUtils (提供获取设备屏幕、状态栏、IMEI号等与设备有关的相关信息)
 * 
 */
public final class DeviceInfoUtils {
	/**
	 * DeviceInfoUtils类TAG生成标记
	 */
	private static final String TAG = "DeviceInfoUtils";

	/**
	 * 取得屏幕宽度像素
	 * 
	 * @param activity
	 *            设备上下文
	 * @return 屏幕的宽度
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = getDisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 得到屏幕大小
		return dm.widthPixels;
	}

	/**
	 * 取得屏幕高度像素
	 * 
	 * @param activity
	 *            设备上下文
	 * @return 屏幕的高度
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = getDisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm); // 得到屏幕大小
		return dm.heightPixels;
	}

	/**
	 * 取得状态栏高度
	 * 
	 * @param activity
	 *            设备上下文
	 * @return 状态栏高度
	 */
	public static int getStatusBarHeight(Activity activity) {
		Rect rectgle = new Rect();
		Window window = activity.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		return rectgle.top;
	}

	/**
	 * 获取屏幕分辨率
	 * 
	 * @param context
	 * @return 获取到的屏幕分辨率
	 */
	public static String getDisplay(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		String resolution = screenWidth + "_" + screenHeight;
		LOGGER.d(TAG, resolution);
		return resolution;
	}

	/**
	 * 获得屏幕的尺寸
	 * 
	 * @param context
	 * @return 获得屏幕的尺寸
	 */
	public static Point getDisplayMetrics(Context context) {
		Point dp = null;
		DisplayMetrics dm = new DisplayMetrics();
		dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		int screenHeight = dm.heightPixels;
		dp = new Point(screenWidth, screenHeight);
		return dp;
	}

	private static String mDeviceImei;

	/**
	 * 获取imei,如果获取不到，则生成一个15位号码
	 * 
	 * @return 获取imei,如果获取不到，则生成一个15位号码
	 */
	/*public static String getImei(final Context context) {
		if (!TextUtils.isEmpty(mDeviceImei)) {
			return mDeviceImei;
		}

		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei;

		try {
			imei = tm.getDeviceId();// imei
		} catch (Exception e) {
			LOGGER.e(TAG, "imei obtained exception", e);
			imei = null;
		}

		if (StringUtils.isEmpty(imei) || "0".equals(imei)) {
			// 如果imei号为空或0，取mac地址作为imei号传递
			try {
				WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifi.getConnectionInfo();
				imei = info.getMacAddress();
			} catch (Exception e) {
				imei = null;
			}

			// 如果mac地址为空或0，则通过uuid生成的imei号来传递
			if (StringUtils.isEmpty(imei) || "0".equals(imei)) {
				imei = SharePersistentUtils.getString(context, LibConstant.IMEI);
				LOGGER.d(TAG, "imei = " + imei);

				if (StringUtils.isEmpty(imei)) {
					imei = UUIDUtils.getUUID(15);
					if (StringUtils.isEmpty(imei)) {
						return "0";
					}
					LOGGER.d(TAG, "imei new = " + imei);
					SharePersistentUtils.saveString(context, LibConstant.IMEI, imei);
					*//*
					 * SharePersistentUtils.savePerference(context,
					 * UtilLibConstant.IMEI, imei);
					 *//*
				}
			}
		}

		// TODO　(ly) 转为小写
		if (imei != null) {
			imei = imei.toLowerCase(Locale.US);
		}

		mDeviceImei = imei;

		return imei;
	}*/

	/**
	 * 取得版本信息
	 * 
	 * @param context
	 * @return 版本Name
	 */
	public static String getVersionString(Context context) {
		String sPackageName = context.getPackageName();
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(sPackageName, 0);
			return String.valueOf(pi.versionName);
		} catch (NameNotFoundException e) {
			LOGGER.d(TAG, "Could not retrieve package info", e);
			throw new RuntimeException(e);
		}
	}

	public static int getVersionCode(Context context) {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}

	/**
	 * 获取屏幕的DisplayMetrics对象
	 * 
	 * @return DisplayMetrics对象
	 */
	public static DisplayMetrics getDisplayMetrics() {
		DisplayMetrics mDm;

		mDm = new DisplayMetrics();

		return mDm;
	}

	/**
	 * 获取wifi网络下的的MAC地址
	 * 
	 * @return MAC地址
	 */
	public static String getMacAddress(Context context) {
		String macAddress = null;
		try {
			WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifiManager.getConnectionInfo();
			macAddress = info.getMacAddress();
		} catch (Exception e) {
			LOGGER.d(TAG, "getMacAddress error", e);
		}
		if (TextUtils.isEmpty(macAddress)) {
			return "";
		} else {
			return macAddress;
		}
	}

	/**
	 * 获取手机的IMEI号
	 * 
	 * @return IMEI号
	 */
	/*
	 * public static String getIMEI(Context context) { String imei = null; try {
	 * TelephonyManager telephonyManager = (TelephonyManager) context
	 * .getSystemService(Context.TELEPHONY_SERVICE); imei =
	 * telephonyManager.getDeviceId(); } catch (Exception e) {
	 * 
	 * } if (TextUtils.isEmpty(imei)) { return ""; } else { return imei; } }
	 */

	/**
	 * 从dip转化为px
	 * 
	 * @param context
	 * @param dipNum
	 *            要转换的dip值
	 * @return 转化后的像素值
	 */
	public static int fromDipToPx(Context context, int dipNum) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dipNum * dm.densityDpi / 160;
	}

	/**
	 * 从dip转化为px
	 * 
	 * @param context
	 * @param dipNum
	 *            要转换的dip值
	 * @return 转化后的像素值
	 */
	public static float fromDipToPx(Context context, float dipNum) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dipNum * dm.densityDpi / 160;
	}

	public static int fromPxTodip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 取得density比例
	 * 
	 * @param context
	 * @return 当前设备Density的比例
	 */
	public static float getDensityScale(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.densityDpi * 1.0f / 160;
	}

	/**
	 * 获取设备的类型
	 * 
	 * @param context
	 * @return 设备的类型phoneType，如"GSM"
	 */
	public static final String getPhoneType(Context context) {
		String phoneType = null;

		try {
			android.telephony.TelephonyManager tel = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

			int type = tel.getPhoneType();
			switch (type) {
			case android.telephony.TelephonyManager.PHONE_TYPE_GSM: {
				phoneType = "GSM";
			}
				break;
			case android.telephony.TelephonyManager.PHONE_TYPE_CDMA: {
				phoneType = "CDMA";
			}
				break;
			case android.telephony.TelephonyManager.PHONE_TYPE_NONE: {
				phoneType = "NONE";
			}
				break;
			default: {
				phoneType = String.valueOf(tel.getPhoneType());
			}
				break;
			}

		} catch (Exception e) {
			phoneType = "error";
		}

		return phoneType;
	}

	/**
	 * 获取sim卡运营商的类型
	 *
	 * @param context
	 * @return 获取sim卡运营商的类型"ct"
	 */
	public static final String getSimOperatorType(Context context) {
		String simOperatorType = null;

		try {
			android.telephony.TelephonyManager tel = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

			simOperatorType = tel.getSimOperator();
			if ("46000".equals(simOperatorType)) {
				simOperatorType = "cm";
			} else if ("46001".equals(simOperatorType)) {
				simOperatorType = "cuni";
			} else if ("46002".equals(simOperatorType)) {
				simOperatorType = "cm2";
			} else if ("46003".equals(simOperatorType)) {
				simOperatorType = "ct";
			}

		} catch (Exception e) {
			simOperatorType = "error";
		}

		return simOperatorType;
	}

	public static String getCpuName() {
		String cpu = "";
		BufferedReader buffr = null;
		FileReader fr = null;
		try {
			fr = new FileReader("/proc/cpuinfo");
			buffr = new BufferedReader(fr);
			String cpuInfo = buffr.readLine();
			String[] array = cpuInfo.split(":\\s+", 2);
			if (array.length >= 2) {
				cpu = array[1];
			}
		} catch (Exception e) {
			cpu = "";
		} finally {
			try {
				if (buffr != null) {
					buffr.close();
					fr.close();
				}
			} catch (Exception e) {
				LOGGER.e("TAG", "", e);
			}
		}
		return cpu;
	}

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean hasInternet(Context context) {
        boolean flag;

        if (((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

}
