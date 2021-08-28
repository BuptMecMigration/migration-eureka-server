package com.dataobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrationToken {

    private String status;

    private String tokenCode;

    private Integer Kval;

    private Integer userId;

    private Integer taskId;

    public static MigrationToken fail() {
        MigrationToken token = new MigrationToken();
        token.setStatus("fail");
        return token;
    }

}
