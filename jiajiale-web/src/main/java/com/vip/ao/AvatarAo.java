package com.vip.ao;

import lombok.Data;

@Data
public class AvatarAo {
    String saveLocation;
    String contentType;
    long totalBytes;
    int width;
    int height;
    boolean isLocalSave;

}
