package com.boonya.hibernate.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "sequence")
@Data
public class Sequence {

    /**
     * 自定义ID生成规则
     * public enum GenerationType{
     *
     *     TABLE,
     *
     *     SEQUENCE,
     *
     *     IDENTITY,
     *
     *     AUTO
     *
     * }
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "DBIdentifyGenerator")
    @GenericGenerator(name = "DBIdentifyGenerator", strategy = "com.boonya.hibernate.util.DBIdentifyGenerator")
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "timestamp")
    private Long timestamp;
}
