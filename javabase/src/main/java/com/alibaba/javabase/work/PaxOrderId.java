package com.alibaba.javabase.work;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author quanhangbo
 * @date 2025-01-20 20:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaxOrderId implements Identifier {
    private String id;

}
