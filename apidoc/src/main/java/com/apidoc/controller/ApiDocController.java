package com.apidoc.controller;

import com.apidoc.entity.ApidocAction;
import com.apidoc.entity.ApidocInfo;
import com.apidoc.entity.ApidocModule;
import com.apidoc.entity.ApidocParam;
import com.apidoc.entity.bean.Detail;
import com.apidoc.service.ApiDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文档生成controller
 */
@RestController
@RequestMapping("/apidoc")
public class ApiDocController {

    @Autowired
    private ApiDocService apiDocService;

    //是否开启文档功能
    @Value("${apidoc}")
    private boolean openApiDoc;

    /**
     * 是否开启文档
     */
    @GetMapping("/isOpenApiDoc")
    public Boolean isOpenApiDoc() {
        return openApiDoc;
    }

    /**
     * 获取文档基本信息
     */
    @GetMapping("/info")
    public ApidocInfo info(String packageName) {
        return apiDocService.getInfo(packageName);
    }

    /**
     * 修改信息文档基本信息
     */
    @PostMapping("/updateInfo")
    public boolean updateInfo(@RequestBody ApidocInfo apidocInfo) {
        return apiDocService.updateInfo(apidocInfo);
    }

    /**
     * 获取模块信息
     */
    @GetMapping("/modules")
    public List<ApidocModule> modules(String packageName) {
        return apiDocService.getModules(packageName);
    }

    /**
     * 获取接口列表信息
     * 也就是获取public修饰的方法 信息
     * 根据模块id获取该模块下所有类的public 的方法 信息
     */
    @GetMapping("/actions/{moduleId}")
    public List<ApidocAction> actions(@PathVariable Integer moduleId) {
        return apiDocService.getActions(moduleId);
    }

    /**
     * 修改信息action信息
     * 即：文档的二级目录
     */
    @PostMapping("/updateAction")
    public boolean updateAction(@RequestBody ApidocAction apidocAction) {
        return apiDocService.updateAction(apidocAction);
    }

    /**
     * 修改模块排序
     */
    @PostMapping("/updateModulesSort")
    public boolean updateModulesSort(@RequestBody List<ApidocModule> apidocModuleList) {
        return apiDocService.updateModulesSort(apidocModuleList);
    }

    /**
     * 修改action排序
     */
    @PostMapping("/updateActionsSort")
    public boolean updateActionsSort(@RequestBody List<ApidocAction> apidocActionList) {
        return apiDocService.updateActionsSort(apidocActionList);
    }

    /**
     * 获取接口详情
     */
    @PostMapping("/detail")
    public Detail detail(@RequestBody ApidocAction action) {
        return apiDocService.getDetail(action.getId(), action.getMethodUUID());
    }

    /**
     * 修改接口描述信息
     */
    @PostMapping("/updateActionDescription")
    public boolean updateActionDescription(@RequestBody ApidocAction apidocAction) {
        return apiDocService.updateActionDescription(apidocAction);
    }

    /**
     * 修改接口的参数信息
     */
    @PostMapping("/updateParam")
    public boolean updateParam(@RequestBody ApidocParam apidocParam) {
        return apiDocService.updateParam(apidocParam);
    }

    /**
     * 添加接口的参数信息,返回自增id
     */
    @PostMapping("/addParam")
    public Integer addParam(@RequestBody ApidocParam apidocParam) {
        return apiDocService.addParam(apidocParam);
    }

    /**
     * 删除接口的参数信息
     */
    @DeleteMapping("/deleteParam/{id}")
    public boolean deleteParam(@PathVariable("id") Integer id) {
        return apiDocService.deleteParam(id);
    }


}



