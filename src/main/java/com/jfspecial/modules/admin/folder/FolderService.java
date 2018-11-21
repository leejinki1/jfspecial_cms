package com.jfspecial.modules.admin.folder;

import com.jfinal.log.Log;
import com.jfspecial.component.util.JFSpecialUtils;
import com.jfspecial.jfinal.base.BaseService;
import com.jfspecial.system.log.SysLog;
import com.jfspecial.util.StrUtils;
import com.jfspecial.util.cache.Cache;
import com.jfspecial.util.cache.CacheManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 目录管理
 * 
 * @author ljk 2018-11-14
 */
public class FolderService extends BaseService {

	private final static Log log = Log.getLog(SysLog.class);

	/**
	 * 目录缓存
	 */
	private final static String cacheName = "FolderService";

	/**
	 * 更新缓存
	 *
	 */
	public void updateCache() {
		CacheManager.get(cacheName).clear();

		// 初始化urlKey
		initMenuKey();
	}

	private final static String urlkeyCacheName = "JFSpecialUtils";
	private static Cache urlkeyCache ;

	public static void initMenuKey() {
		log.info("####目录Key初始化......");
		if (urlkeyCache == null) {
			urlkeyCache = CacheManager.get(urlkeyCacheName);
		}
		urlkeyCache.clear();
		List<TbFolder> folders = TbFolder.dao.findByWhere(" where status = 1 order by sort");
		for (TbFolder tbFolder : folders) {
			if (StrUtils.isNotEmpty(tbFolder.getKey())) {
				// 分站点存储
				urlkeyCache.add(tbFolder.getKey() + "_" + tbFolder.getSiteId(), tbFolder.getId() + "");
				urlkeyCache.add(tbFolder.getId() + "", tbFolder.getKey());
			}
		}
	}

	/**
	 * 通过ID获取URLKey
	 *
	 * @param key
	 * @return
	 */
	public static String getMenu(String key) {
		return (urlkeyCache.get(key) == null) ? key : urlkeyCache.get(key).toString();
	}
	
	/**
	 * 通过URLKey获取ID
	 *
	 * @param key
	 * @param siteId
	 * @return
	 */
	public static String getMenu(String key, int siteId) {
		String urlKey = key + "_" + siteId;
		return (urlkeyCache.get(urlKey) == null) ? key : urlkeyCache.get(urlKey).toString();
	}

	/**
	 * 获取栏目信息
	 *
	 * 
	 * @return
	 */
	public TbFolder getFolder(int folderId) {
		TbFolder folder = TbFolder.dao.findFirstCache(cacheName, "getFolder_" + folderId //
		, "select * from tb_folder where id = ? ", folderId);
		return folder;
	}

	/**
	 * 不通过索引获取所有目录
	 * @return
	 */
	public List<TbFolder> getFolders(int siteId) {
		return TbFolder.dao.findCache(cacheName, "getFolders_" + siteId, //
				"select * from tb_folder where site_id = ? order by sort,id ", siteId);
	}

	/**
	 * 获取栏目菜单
	 * 
	 * * 2018年11月18日 上午3:43:39 ljk
	 * 
	 * @return
	 */
	public Map<String, List<TbFolder>> getFolderMenus(int siteId) {
		Map<String, List<TbFolder>> map = new HashMap<String, List<TbFolder>>();
		List<TbFolder> folders = getFolders(siteId);
		for (TbFolder folder : folders) {
			if (folder.getStatus() == JFSpecialUtils.STATUS_HIDE)
				continue;

			String parentId = String.valueOf(folder.getParentId());
			List<TbFolder> list = map.get(parentId);
			if (list == null) {
				list = new ArrayList<TbFolder>();
				map.put(parentId, list);
			}
			list.add(folder);
		}
		return map;
	}

	/**
	 * 目录复选框
	 * 
	 * @return
	 */
	public String selectFolder(Integer selected, int siteId) {
		return selectFolder(selected, null, siteId);
	}

	/**
	 * 目录复选框
	 * 
	 * @return
	 */
	public String selectFolder(Integer selected, Integer selfId, int siteId) {
		String where = " where 1 = 1 ";
		if (selfId != null && selfId > 0) {
			where += "and id !=" + selfId;
		}
		List<TbFolder> list = TbFolder.dao.find(" select id,name from tb_folder " //
				+ where + " and site_id = ? order by sort,create_time desc ", siteId);
		StringBuffer sb = new StringBuffer("");
		if (list != null && list.size() > 0) {
			for (TbFolder folder : list) {
				sb.append("<option value=\"");
				sb.append(folder.getInt("id"));
				sb.append("\" ");
				sb.append((selected != null && folder.getInt("id").intValue() == selected) ? "selected" : "");
				sb.append(">");
				sb.append(folder.getStr("name"));
				sb.append("</option>");
			}
		}
		return sb.toString();
	}
}
