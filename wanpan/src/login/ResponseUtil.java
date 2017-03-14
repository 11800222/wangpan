package login;

import java.util.Iterator;
import java.util.Scanner;

import freemarker.core.ReturnInstruction.Return;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResponseUtil {// 从115的完整响应中获取所需信息的工具类
	public static void main(String ss[]) {
		try {

			// String id = ResponseUtil.getInfohashByAdd(content);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getInfohashByAdd(String content) throws Exception {
		JSONObject json1 = JSONObject.fromObject(content);
		if (json1.getString("state").equals("false")) {
			if (json1.getString("errcode").equals("99"))
				throw new Exception("needvalidate");
			else
				throw new Exception(json1.getString("error_msg"));
		}
		return json1.getString("info_hash");
	}

	public static String getNameByAdd(String content) throws Exception {
		JSONObject json1 = JSONObject.fromObject(content);
		if (json1.getString("state").equals("false"))
			throw new Exception("needvalidate");
		return json1.getString("name");
	}

	public static JSONObject getItemByid(String content, String id)
			throws Exception {
		JSONObject json = null;
		JSONObject json1 = JSONObject.fromObject(content);
		if (json1.getString("state").equals("false"))
			throw new Exception("needvalidate");

		JSONArray data = json1.getJSONArray("data");

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

	public static JSONArray getSubItemsByid(String content) throws Exception {
		JSONArray items = null;
		JSONObject json1 = JSONObject.fromObject(content);
		items = json1.getJSONArray("data");
		return items;// 找不到返回null;
	}

	public static String getIdByInfohash(String content, String infohash)
			throws Exception {
		String id = "notfound";
		String name = "";
		JSONObject json1 = JSONObject.fromObject(content);
		if (json1.getString("state").equals("false"))
			throw new Exception("needvalidate");
		JSONArray data = json1.getJSONArray("tasks");

		Iterator<Object> it = data.iterator();
		while (it.hasNext()) {
			JSONObject ob = (JSONObject) it.next();
			if (ob.getString("info_hash").equals(infohash)) {
				if (ob.getString("file_id").equals(""))
					return "notDone";// 找到了但未完成
				else {
					// 已完成 获得name
					name = ob.getString("name");
					id = GetIDbyName(
							GetResponse.openfile("437789382553092060", "0"),
							name);

				}
			}
		}
		return id;// 找不到返回"notfound"
	}

	public static String GetIDbyName(String content, String name)
			throws Exception {
		String id = "notfound";
		JSONObject root = JSONObject.fromObject(content);
		if (root.getString("state").equals("false"))
			throw new Exception("needvalidate");
		JSONArray rootfiles = root.getJSONArray("data");
		Iterator<Object> it2 = rootfiles.iterator();
		while (it2.hasNext()) {
			JSONObject temp = (JSONObject) it2.next();
			if (temp.getString("n").equals(name)) {
				if (temp.has("ico"))
					id = temp.getString("fid");
				else
					id = temp.getString("cid");
			}
		}
		return id;
	}

}
