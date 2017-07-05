package model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Tasks {
private String infohash;

private String id;
 
private String user ;
 
private String name ;

private String url ;
 
private String percentDone ;

private int pollTime;

public String getInfohash() {
	return infohash;
}

public void setInfohash(String infohash) {
	this.infohash = infohash;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getPercentDone() {
	return percentDone;
}

public void setPercentDone(String percentDone) {
	this.percentDone = percentDone;
}

public int getPollTime() {
	return pollTime;
}

public void setPollTime(int pollTime) {
	this.pollTime = pollTime;
}

 
 
 


}
