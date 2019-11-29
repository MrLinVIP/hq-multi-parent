package com.hq.multi.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hq.multi.result.R;
import com.hq.multi.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hq.multi.entity.PaperEntity;
import com.hq.multi.service.PaperService;





@RestController
@RequestMapping("provider/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(){
        Page page = new Page(1,5);
        IPage<PaperEntity> iPage = paperService.pageMaps(page,null);

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
