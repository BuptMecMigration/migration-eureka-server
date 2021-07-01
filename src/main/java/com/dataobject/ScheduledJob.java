package com.dataobject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledJob {

    public String jobID;

    public String taskFlag;

    public String serviceID;

    public Integer migrationMode;

    public HashMap<String, Node> routeMap;

    public long startTime;

    public long endTime;

    public long wholeTime;

    public Integer migrationOffset;

    public Integer retryTime;

    public String watchState;
}
