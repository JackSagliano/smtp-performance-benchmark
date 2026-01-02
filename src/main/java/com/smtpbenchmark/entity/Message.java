package com.smtpbenchmark.entity;

public class Message {
    private String object;
    private String message;
    private long sendingTime;
    private long receptionTime;
    private long durationTime;
    private String attachment; //Lo imposto a "Sì" o "No" a seconda se contiene allegati o no
    private String size; //spazio in Byte del messaggio

    public Message() {}
    public void setObject(String object) {
        this.object = object;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getObject() {
        return object;
    }
    public String getMessage() {
        return message;
    }

    public long getSendingTime() {
        return sendingTime;
    }
    public void setSendingTime(long sendingTime) {
        this.sendingTime = sendingTime;
    }
    public long getReceptionTime() {
        return receptionTime;
    }
    public void setReceptionTime(long receptionTime) {
        this.receptionTime = receptionTime;
    }

    public long getDurationTime() {
        return durationTime;
    }
    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
}
