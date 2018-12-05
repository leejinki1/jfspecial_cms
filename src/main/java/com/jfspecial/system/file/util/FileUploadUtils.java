package com.jfspecial.system.file.util;

import com.jfinal.kit.PathKit;
import com.jfspecial.modules.admin.site.TbSite;
import com.jfspecial.util.PathUtils;
import com.jfspecial.util.StrUtils;

import java.io.File;

public class FileUploadUtils {

	public static final int UPLOAD_MAX = 10 * 1024 * 1024;

	/**
	 * 基础目录
	 */
	private static final String BASE_PATH = PathKit.getWebRootPath() + File.separator;

	/**
	 * 基础JFLYFOX目录
	 */
	private static final String JFSPECIAL_PATH = "jfspecial" + File.separator;

	/**
	 * 上传临时目录
	 */
	private static final String UPLOAD_TMP_PATH = "tmp";

	/**
	 * 临时路径
	 * 
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @param site
	 * @param type
	 * @return
	 */
	public static String getUploadTmpPath(TbSite site) {
		String sitePath = getSitePath(site);
		String path = JFSPECIAL_PATH + sitePath + UPLOAD_TMP_PATH;

		return path;
	}

	/**
	 * 上传路径
	 *
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @param site
	 * @param type
	 * @return
	 */
	public static String getUploadPath(TbSite site, String type) {
		String sitePath = getSitePath(site);
		String path = JFSPECIAL_PATH + sitePath + type;
		File file = new File(BASE_PATH + path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path;
	}

	/**
	 * 获取站点路径
	 *
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @param site
	 * @return
	 */
	public static String getSitePath(TbSite site) {
		String sitePath = site == null || StrUtils.isEmpty(site.getTemplate()) ? ""
				: (site.getTemplate() + File.separator);
		return rebuild(sitePath);
	}

	/**
	 * 获取根路径
	 *
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @return
	 */
	public static String getBasePath() {
		return rebuild(File.separator + JFSPECIAL_PATH);
	}

	/**
	 * 获取路径根路径
	 *
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @return
	 */
	public static String getRootPath() {
		return rebuild(PathKit.getWebRootPath());
	}

	/**
	 * 重构路径
	 *
	 * 2018年11月27日 下午4:07:23 ljk
	 * 
	 * @param path
	 * @return
	 */
	public static String rebuild(String path) {
		return PathUtils.rebuildNoDecode(path);
	}

}
