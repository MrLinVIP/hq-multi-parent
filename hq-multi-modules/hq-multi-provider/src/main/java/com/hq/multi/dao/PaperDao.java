package com.hq.multi.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hq.multi.entity.PaperEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-11-28 17:43:21
 */
@Mapper
public interface PaperDao extends BaseMapper<PaperEntity> {
	IPage<PaperDao> listMyPapers(IPage<T> var1, @Param("paperName") String paperName);
}
