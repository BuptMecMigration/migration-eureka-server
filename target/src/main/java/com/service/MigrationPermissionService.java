package com.service;

import com.response.CommonReturnType;
import com.response.RPCReturnType;
import com.service.model.MigrationTaskModel;

/***
 * @apiNote 此接口主要用来完成迁移许可的判断和下发，参数的设定需要调试和经验来决定
 */
public interface MigrationPermissionService {

    CommonReturnType getMigrationPermission4Client(MigrationTaskModel migrationTaskModel);
}
