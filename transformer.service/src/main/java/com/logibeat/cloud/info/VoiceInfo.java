package com.logibeat.cloud.info;

public class VoiceInfo {
	
	// 语音链接
	private String voiceUrl;
	// 语音文字
	private String phoneticWriting;
	// 语音时长
	private Integer voiceDuration;
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	public String getPhoneticWriting() {
		return phoneticWriting;
	}
	public void setPhoneticWriting(String phoneticWriting) {
		this.phoneticWriting = phoneticWriting;
	}
	public Integer getVoiceDuration() {
		return voiceDuration;
	}
	public void setVoiceDuration(Integer voiceDuration) {
		this.voiceDuration = voiceDuration;
	}
	
}
