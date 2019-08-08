package com.mlkt.development.permissionsutils.model;

import java.util.List;

public class PermissionRequest {

    public List<String> permission;
    public int requestCode;

    public PermissionRequest(List<String> permission,
                        int requestCode) {
        this.permission = permission;
        this.requestCode = requestCode;
    }

}
