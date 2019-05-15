package org.hiphone.consul.config.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author HiPhone
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "student")
@ApiModel(value = "StudentConfig", description = "用于测试consul配置中心的配置类")
public class StudentConfig {

    @ApiModelProperty(value = "学生的姓名", name = "name")
    private String name;

    @ApiModelProperty(value = "学生的年龄", name = "age")
    private Integer age;

    @ApiModelProperty(value = "学生的性别", name = "sex")
    private String sex;
}
