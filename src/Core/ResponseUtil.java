package Core;

import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

import freemarker.core.ReturnInstruction.Return;
import model.Tasks;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResponseUtil {// 从115的完整响应中获取所需信息的工具类

	public static String getInfohashByAdd(JSONObject content) throws Exception {
		if (content.getString("info_hash").equals(""))
			return "任务已存在";
		else
			return content.getString("info_hash");
	}

	public static String getNameByAdd(JSONObject content) throws Exception {
		return content.getString("name");
	}

	public static JSONObject getItemByid(JSONObject content, String id)
			throws Exception {
		JSONObject json = null;

		JSONArray data = content.getJSONArray("data");

		Iterator<Object> it = data.iterator();
		while (it.hasNext()) {
			JSONObject ob = (JSONObject) it.next();// 文件和文件夹的ID位置不同（离线任务都是fid）
			if (ob.has("ico")) {
				if (ob.getString("fid").equals(id)) {
					json = ob;
					break;
				}
			} else {
				if (ob.getString("cid").equals(id)) {
					json = ob;
					break;
				}
			}
		}
		return json;// 找不到返回null;
	}

	public static JSONArray getSubItemsByid(JSONObject content)
			throws Exception {
		JSONArray items = null;
		JSONObject json1 = JSONObject.fromObject(content);
		items = json1.getJSONArray("data");
		return items;// 找不到返回null;
	}

	public static String getIdByInfohash(JSONObject content, Tasks t)
			throws Exception {
		JSONArray data = content.getJSONArray("tasks");
		Iterator<Object> it = data.iterator();
		while (it.hasNext()) {
			JSONObject ob = (JSONObject) it.next();
			if (ob.getString("info_hash").equals(t.getInfohash())) {
				if (ob.getString("file_id").equals(""))
					return "notDone";// 找到了但未完成
				else {
					// 已完成 获得name, 并顺便设下t.name,PercentDone
					t.setName(ob.getString("name"));
					t.setPercentDone("100%");
					// 通过name在文件列表获取id,找不到自然抛出not found Exception
					GetIDbyName(
							GetResponse.openfile("437789382553092060", "0"), t);
					return t.getId();
				}
			}
		}
		return "notDone";
	}

	public static boolean GetIDbyName(JSONObject content, Tasks t)
			throws Exception {
		String id = "notfound";

		JSONArray rootfiles = content.getJSONArray("data");
		Iterator<Object> it2 = rootfiles.iterator();
		while (it2.hasNext()) {
			JSONObject temp = (JSONObject) it2.next();
			if (temp.getString("n").equals(t.getName())) {
				if (temp.has("ico"))
					id = temp.getString("fid");
				else
					id = temp.getString("cid");
			}
		}

		if (id.equals("notfound"))// 找不到抛出Exception。
			throw new Exception("not found");
		t.setId(id);
		return true;
	}

}
