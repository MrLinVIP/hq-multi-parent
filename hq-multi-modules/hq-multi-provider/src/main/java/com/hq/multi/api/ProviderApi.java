package com.hq.multi.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hq.multi.entity.PaperEntity;
import com.hq.multi.result.R;
import com.hq.multi.service.PaperService;
import com.hq.multi.utils.RedisUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author linliangkun
 * @Title: ProviderController
 * @ProjectName hq-multi-parent
 * @Description: TODO
 * @date 2019/11/19 001916:22
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderApi {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PaperService paperService;

    @GetMapping("/test")
    public String test() {
        String t = redisUtils.get("test0");
        return t;
    }

    @GetMapping("/list")
    public R list() {
        Page page = new Page(1, 5);
        QueryWrapper<PaperEntity> query = new QueryWrapper<>();
        IPage<PaperEntity> iPage = paperService.pageMaps(page, query);
        return R.ok(iPage);
    }

}
