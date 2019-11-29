package com.hq.multi.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-11-28 17:43:21
 */
@Data
@TableName("paper")
public class PaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String code;
	/**
	 * 
	 */
	private String uuid;
	/**
	 * 
	 */
	private String paperName;
	/**
	 * 考试时长(单位分钟)
	 */
	private Integer maxTime;
	/**
	 * 试卷分数
	 */
	private BigDecimal amount;
	/**
	 * 
	 */
	private String diffcult;
	/**
	 * 
	 */
	private String courseinfoCode;
	/**
	 * 
	 */
	private String charpterCode;
	/**
	 * 
	 */
	private String knowledgePointCode;
	/**
	 * 
	 */
	private String knowledgePointNames;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 
	 */
	private String areaName;
	/**
	 * 试卷类型
	 */
	private Integer usedType;
	/**
	 * 
	 */
	private Date startTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Boolean isdelete;
	/**
	 * 0下架，1上架
	 */
	private Boolean isuse;
	/**
	 * 
	 */
	private String subjectIdList;
	/**
	 * 
	 */
	private Boolean isfixed;
	/**
	 * 
	 */
	private Integer proId;
	/**
	 * 是否多模态使用(0否,1是)
	 */
	private Boolean isMmUsed;
	/**
	 * 多模态的 专业id
	 */
	private String majorMid;
	/**
	 * 多模态的 专业名称
	 */
	private String majorMname;
	/**
	 * 多模态的 课程id
	 */
	private String courseMid;
	/**
	 * 多模态的 课程名称
	 */
	private String courseMname;
	/**
	 * 多模态的 课次Id
	 */
	private String lessonId;
	/**
	 * 多模态的 课次名称
	 */
	private String lessonName;
	/**
	 * 多模态的 排课计划Id
	 */
	private String classplanId;
	/**
	 * 是否强制批改(0否,1是)
	 */
	private Boolean isForcePg;
	/**
	 * 最大考试次数
	 */
	private Integer examTimes;
	/**
	 * 创建人id
	 */
	private Integer createUserId;
	/**
	 * 最后修改时间
	 */
	private Date updateTime;
	/**
	 * 答案解析是否可查看
	 */
	private Boolean isLook;

}
