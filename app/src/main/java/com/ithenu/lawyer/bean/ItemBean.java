package com.ithenu.lawyer.bean;

/**
 * Created by Mars on 2016/3/3.
 */

/**
 * Bean封装数据
 */
public class ItemBean {
    public int ImageResid;
    public String ItemTitle;
    public String ItemContent;

    public ItemBean(int imageResid, String itemTitle, String itemContent) {
        ImageResid = imageResid;
        ItemTitle = itemTitle;
        ItemContent = itemContent;
    }
}
