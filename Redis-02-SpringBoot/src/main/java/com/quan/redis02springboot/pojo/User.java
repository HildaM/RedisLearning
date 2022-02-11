package com.quan.redis02springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/2/11 10:44
 */

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private String name;
    private Integer age;
}
