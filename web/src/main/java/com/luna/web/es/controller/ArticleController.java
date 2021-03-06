package com.luna.web.es.controller;

import com.luna.common.core.domain.AjaxResult;

import com.luna.web.es.entity.Article;
import com.luna.web.es.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Article)表控制层
 *
 * @author luna
 * @since 2020-04-10 16:07:19
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    
     /**
     * Select list r.
     *
     * @param article
     * @return the r
     */
    @ApiOperation(value = "查询列表", notes = "查询列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/get")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功")})
    public AjaxResult query(Article article) {
        return AjaxResult.success("查询列表", articleService.getAll(article));
    }
    
    /**
     * Select obj r.
     *
     * @param id
     * @return the r
     */
    @ApiOperation(value = "查询详情", notes = "查询详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/{id}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功")})
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success("查询详情", articleService.getById(id));
    }
    
    /**
     * Insert obj.
     *
     * @param article
     * @return the r
     */
    @ApiOperation(value = "添加", notes = "添加", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping(value = "/add")
    @ApiResponses({@ApiResponse(code = 200, message = "添加成功")})
    public AjaxResult add(@RequestBody Article article) {
        return AjaxResult.success("添加", articleService.insert(article));
    }
    
    /**
     * update obj.
     *
     * @param article
     * @return the r
     */
    @ApiOperation(value = "更新", notes = "更新", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping(value = "/")
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    public AjaxResult update(@RequestBody Article article) {
        return AjaxResult.success("更新", articleService.update(article));
    }
    
     /**
     * delete obj.
     *
     * @param id
     * @return the r
     */
    @ApiOperation(value = "删除", notes = "删除", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping(value = "/{id}")
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    public AjaxResult delete(@PathVariable("id") Integer id) {
        return AjaxResult.success("删除", articleService.deleteById(id));
    }
}