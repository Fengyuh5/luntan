package com.xiansi.model;

public class Notification {
    private Integer id;

    private Integer notifier;

    private Integer receiver;

    private Integer outer_id;

    private Integer type;

    private Long gmt_create;

    private Integer status;

    private String notifier_name;

    private String outer_title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotifier() {
        return notifier;
    }

    public void setNotifier(Integer notifier) {
        this.notifier = notifier;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getOuter_id() {
        return outer_id;
    }

    public void setOuter_id(Integer outer_id) {
        this.outer_id = outer_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Long gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotifier_name() {
        return notifier_name;
    }

    public void setNotifier_name(String notifier_name) {
        this.notifier_name = notifier_name == null ? null : notifier_name.trim();
    }

    public String getOuter_title() {
        return outer_title;
    }

    public void setOuter_title(String outer_title) {
        this.outer_title = outer_title == null ? null : outer_title.trim();
    }
}