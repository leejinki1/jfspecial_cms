package com.jfspecial.modules.admin.infostat;

import com.jfinal.log.Log;

import java.util.ArrayList;
import java.util.List;

public class InfoStatCache {

	private final static Log log = Log.getLog(InfoStatCache.class);
	private final static List<String> cacheList = new ArrayList<String>();

	private InfoStatCache() {
	}

	/**
	 * 初始化
	 * 
	 * 2018年11月22日 上午10:46:52 ljk
	 */
	public static void init() {
		initNowDay();

		log.info("####InfoStatCache初始化......");
		// 去当天数据
		//List<TbInfoStat> pageViewList = TbInfoStat.dao.findByWhere(" where create_day = ?", DateUtils.getNow());
		//for (TbInfoStat pageView : pageViewList) {
		//	cacheList.add(pageView.getStr("ip"));
		//}

	}

	/**
	 * 添加PV，需进行代码同步
	 *
	 * @param ip
	 */
	public static synchronized void add(String ip) {

	}

	/**
	 * 今日访问量
	 */
	public static Integer size() {
		return cacheList.size();
	}

	/**
	 * 当前日期处理
	 */
	private static void initNowDay() {

	}

}