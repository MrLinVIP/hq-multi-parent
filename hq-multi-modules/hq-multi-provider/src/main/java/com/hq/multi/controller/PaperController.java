package com.hq.multi.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hq.multi.dao.PaperDao;
import com.hq.multi.result.R;
import com.hq.multi.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.multi.entity.PaperEntity;
import com.hq.multi.service.PaperService;





@RestController
@RequestMapping("provider/paper")
@Api(tags = "试卷接口", description = "PaperController")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperDao paperDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @ApiOperation(value = "获取章节列表", notes = "根据课程编号获取章节列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    public R list(@ApiParam(value = "当前页", required = true) @RequestParam Integer currentPage,
                  @ApiParam(value = "每页行数", required = true) @RequestParam Integer pageSize,
                  @ApiParam(value = "试卷名称") @RequestParam(required = false) String paperName ){
        Page page = new Page(currentPage, pageSize);
        QueryWrapper<PaperEntity> query = new QueryWrapper<>();
        query.likeRight("paper_name",paperName);
        //IPage<PaperEntity> iPage = paperService.pageMaps(page, query);
        IPage<PaperEntity> iPage = paperDao.listMyPapers(page,paperName);
        return R.ok(iPage);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("multi:paper:info")
    public R info(@PathVariable("id") Integer id){
		PaperEntity paper = paperService.getById(id);

        return R.ok().put("paper", paper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("multi:paper:save")
    public R save(@RequestBody PaperEntity paper){
		paperService.save(paper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("multi:paper:update")
    public R update(@RequestBody PaperEntity paper){
		paperService.updateById(paper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("multi:paper:delete")
    public R delete(@RequestBody Integer[] ids){
		paperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
