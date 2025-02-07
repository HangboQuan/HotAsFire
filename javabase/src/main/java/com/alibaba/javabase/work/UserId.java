package com.alibaba.javabase.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserId implements Identifier {

    private Long id;
    private String guid;
}
