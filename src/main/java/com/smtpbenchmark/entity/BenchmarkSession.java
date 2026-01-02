package com.smtpbenchmark.entity;


public class BenchmarkSession {
    private String benchmarkName;
    private Integer messagesNumber;
    private String length;
    private long minTime;
    private long maxTime;
    private double avgTime;
    public BenchmarkSession(String benchmarkName, Integer messagesNumber, String length, long minTime, long maxTime,
            double avgTime, String attachment) {
        this.benchmarkName = benchmarkName;
        this.messagesNumber = messagesNumber;
        this.length = length;
        this.minTime = minTime;
        this.maxTime = maxTime;
        this.avgTime = avgTime;
        this.attachment = attachment;
    }
    private String attachment;

    
    public String getBenchmarkName() {
        return benchmarkName;
    }
    public void setBenchmarkName(String benchmarkName) {
        this.benchmarkName = benchmarkName;
    }
    public Integer getMessagesNumber() {
        return messagesNumber;
    }
    public void setMessagesNumber(Integer messagesNumber) {
        this.messagesNumber = messagesNumber;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public long getMinTime() {
        return minTime;
    }
    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }
    public long getMaxTime() {
        return maxTime;
    }
    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }
    public double getAvgTime() {
        return avgTime;
    }
    public void setAvgTime(double avgTime) {
        this.avgTime = avgTime;
    }
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
