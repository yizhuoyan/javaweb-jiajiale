package com.vip.dto;

import lombok.Data;

@Data
public class AvatarDto {
    String accountId;
    String saveLocation;
    String contentType;
    long totalBytes;
    int width;
    int height;
    //是否存放在本地存放
    boolean isLocalSave;



}
