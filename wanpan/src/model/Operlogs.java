package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Operlogs {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String id;
    private String username;
	private Date operTime;
    private String operTimeStr;
    private String content;
    private String ip;
	
    public static SimpleDateFormat getSdf() {
		return sdf;
	}
	public static void setSdf(SimpleDateFormat sdf) {
		Operlogs.sdf = sdf;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getOperTimeStr() {
		if (operTime != null) {
            return sdf.format(operTime);
        }
        return "";
	}
	public void setOperTimeStr(String operTimeStr) {
		this.operTimeStr = operTimeStr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
