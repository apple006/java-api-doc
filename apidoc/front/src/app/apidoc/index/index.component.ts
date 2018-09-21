import {Component, OnInit} from '@angular/core';
import {ROOT_URL, URL_PARAM} from '../../config';
import {HttpService} from '../../http-service.service';
import {NzMessageService} from 'ng-zorro-antd';
import {apis} from "../../api";
import {DragulaService} from "ng2-dragula";
import {Subscription} from "rxjs";

// @ts-ignore
/**
 * 首先声明：该前端代码用了最新的angular版本，语法是最新的
 * 但是： 细节处理和算法上非常粗糙，哈哈哈，时间有限，公司急着用，先实现再说吧，后期优化
 * 如果你看到某个算法，觉得傻逼，没事，本来应该写一天的功能，我20分钟就实现了，理解下下。偷笑  :)
 *
 */
@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  isopenapidoc = false;        // 是否开启文档
  isopenapidocShow;        // 是否开启文档
  info;        // 文档基本信息
  modules;     // 功能模块信息列表
  action;      //二级目录信息
  detail;      // 接口详细信息
  paramType;  //请求参数类型
  moduleName;  //模块名称
  isGetActions = false;

  //演示demo
  mockUrl: string;//请求url
  mockReqParams;//请求参数
  mockReqParamsStr;//请求参数 页面展示
  mockReqParamsFrom;//请求参数 页面展示
  mockShowFrom = false;//请求参数 页面展示
  mockMethod: any; // 请求方法
  mockShowBlob = false;//是否显示数据流类型的（比如验证码）
  mockBlobUrl;//数据流url
  mockResponse: any;//请求后得到的数据
  mockShowResponse = false;//是否显示响应数据

  //示例功能demo
  demoUrl: string; //请求地址
  demoReqParams;//请求参数
  demoResponse;//响应数据

  //编辑还是显示
  edit = {
    info: false,
    module: false,
    actionDescription: false,
  };

  /**
   * 清空缓存
   */
  private clearCache() {
    // this.detail = null;      // 接口详细信息
    this.paramType = null;//请求参数类型
    // this.moduleName = null;//模块名称
    //演示功能
    this.mockUrl = null;//请求url
    this.mockReqParams = null;//请求参数
    this.mockReqParamsStr = null;//请求参数 页面展示
    this.mockReqParamsFrom = null;//请求参数 页面展示
    this.mockShowFrom = false;//请求参数 页面展示
    this.mockMethod = null; // 请求方法
    this.mockShowBlob = false;//是否显示数据流类型的（比如验证码）
    this.mockBlobUrl = null;//数据流url
    this.mockResponse = null;//请求后得到的数据
    this.mockShowResponse = false;//是否显示响应数据

    //示例功能demo
    this.demoUrl = null; //请求地址
    this.demoReqParams = null;//请求参数
    this.demoResponse = null;//响应数据
  }

  //左侧菜单的拖拽排序功能
  subs = new Subscription();

  constructor(private http: HttpService,
              private  messageService: NzMessageService,
              private dragulaService: DragulaService) {
    //设置移动规则
    this.dragulaService.createGroup("MODULES", {
      direction: 'horizontal',
      moves: (el, source, handle) => handle.className.indexOf("modules-handel") != -1
    });
    //模块排序
    this.subs.add(this.dragulaService.dropModel("MODULES")
      .subscribe(({sourceModel, targetModel, item}) => {
        // console.log(JSON.stringify(sourceModel))
        let i = 1;
        for (let m of sourceModel) {
          m.order = i;
          i++;
        }
        this.http.post(apis.updateModulesSort, sourceModel).subscribe(
          data => {
            if (data) {
              console.log(data);
            }
          },
          error => {
            this.error(error)
          }
        );

      })
    );

    //接口排序
    this.subs.add(this.dragulaService.dropModel("ACTIONS")
      .subscribe(({sourceModel, targetModel, item}) => {
        console.log(JSON.stringify(sourceModel));
        let i = 1;
        for (let m of sourceModel) {
          m.order = i;
          i++;
        }
        this.http.post(apis.updateActionsSort, sourceModel).subscribe(
          data => {
            if (data) {
              console.log(data);
            }
          },
          error => {
            this.error(error)
          }
        );

      })
    );

  }

  ngOnDestroy() {
    this.subs.unsubscribe();
  }

  ngOnInit() {
    scroll(0, 0);
    this.isOpenApiDoc();
  }

  isOpenApiDoc() {
    this.http.get(apis.isOpenApiDoc).subscribe(
      data => {
        console.log(data);
        if (data) {
          this.isopenapidoc = true;
          this.init();
        } else {
          this.isopenapidocShow = true;
        }
      }
    );
  }

  //初始化数据
  init() {
    //获取文档基本信息
    this.http.get(apis.info + URL_PARAM).subscribe(
      data => {
        console.log("文档基本信息： ", JSON.stringify(data));
        this.info = data;
      },
      error => {
        this.error(error)
      }
    );
    //获取一级目录 模块列表
    this.http.get(apis.modules + URL_PARAM).subscribe(
      data => {
        this.modules = data;
        console.log("一级目录： ", JSON.stringify(data));
      },
      error => {
        this.error(error);
      }
    );
  }

  /**
   * 获取左侧目录二级菜单
   * 接口概要信息列表
   * @param module 模块
   */
  getActions(module) {
    this.isGetActions = !this.isGetActions;
    //只有在菜单展开时才加载数据
    if (this.isGetActions) {
      module.isLoadingActions = true;
      this.moduleName = module.name;
      this.http.get(apis.actions + "/" + module.id).subscribe(
        data => {
          delete  module.isLoadingActions;
          console.log("二级目录 ", JSON.stringify(data))
          if (data) {
            module.list = data;
          }
        },
        error => {
          delete  module.isLoadingActions;
          this.error(error);
        }
      );
    }
  }

  //保存接口信息
  saveAction(action: any) {
    delete action.edit;
    console.log(JSON.stringify(action))
    this.http.post(apis.updateAction, action).subscribe(
      data => {
        console.log(data);
      },
      error => {
        this.error(error);
      }
    );
  }

  TimeFn = null;

  //获取接口详情
  getDetail(action) {
    // 取消上次延时未执行的方法
    clearTimeout(this.TimeFn);
    //执行延时
    this.TimeFn = setTimeout(() => {
      //单击事件执行
      this.getDetailClick(action);
    }, 500);
  }

  actionEdit(action) {
    // 取消上次延时未执行的方法
    clearTimeout(this.TimeFn);
    //执行双击事件
    action.edit = !action.edit;
  }

  private getDetailClick(action) {
    action.isLoadingDetail = true;
    //清空缓存
    this.clearCache();
    this.action = action;
    this.http.post(apis.detail, this.action).subscribe(
      data => {
        delete action.isLoadingDetail;
        console.log("功能详情： ", JSON.stringify(data));
        if (data) {
          this.detail = data;
          //构建请求参数
          this.buildRequestParams(data);
        }
      },
      error => {
        delete action.isLoadingDetail;
        this.error(error);
      }
    );
  }

  /**
   * 构建请求参数  供演示使用
   * @param action
   */
  buildRequestParams(action) {
    if (action) {
      this.mockUrl = ROOT_URL + action.mapping;//演示的url默认为后台返回的mapping
      this.mockMethod = action.requestMethod;//演示的请求方式
      this.demoUrl = ROOT_URL + action.mapping;//示例的url
      const requestType: string = action.requestParam.type;//请求参数类型
      const responseType: string = action.responseParam.type;//响应数据类型
      const request = action.requestParam.params;//请求参数
      //判断请求类型
      //后台数据 @see com.apidoc.common.Const 类
      //-----请求或响应方式（类型）--------
      // public static final String URL = "URL拼接参数 (示例: ?a=XX&&b=XX)";
      // public static final String URI = "URI占位符 (示例: /XXX/{id}/{name})";
      // public static final String JSON = "JSON类型数据";
      // public static final String FROM = "FROM表单数据";
      // public static final String BLOB = "BLOB二进制流";
      //url方式
      if (this.contain(requestType, "URL")) {
        if (action.requestParam && action.requestParam.params && action.requestParam.params.length > 0) {//存在参数
          //拼接参数 以?隔开
          let i = 0;
          for (const param of  action.requestParam.params) {
            i++;
            if (i === 1) {
              this.mockUrl = ROOT_URL + action.mapping + "?" + param.name + "=" + this.getDefaultValue(param);
              this.demoUrl = ROOT_URL + action.mapping + "?" + param.name + "=" + (param.description ? param.description : "参数");
            } else {
              this.mockUrl = this.mockUrl + "&" + param.name + "=" + this.getDefaultValue(param);
              this.demoUrl = this.demoUrl + "&" + param.name + "=" + (param.description ? param.description : "参数");
            }
          }
        } else {
          this.mockUrl = ROOT_URL + action.mapping;
          this.demoUrl = ROOT_URL + action.mapping;
        }
      }
      //json方式
      if (this.contain(requestType, "JSON")) {
        if (action.requestParam && action.requestParam.params && action.requestParam.params.length > 0) {
          this.mockReqParams = this.getParams(action.requestParam.params, {});
          this.mockReqParamsStr = this.fromtJSON(this.mockReqParams);
          this.demoReqParams = this.fromtJSON(this.getParams(action.requestParam.params, {}));
        }
      }
      //from 方式
      if (this.contain(requestType, "FROM")) {
        this.mockShowFrom = true;
        if (action.requestParam && action.requestParam.params && action.requestParam.params.length > 0) {
          this.mockReqParamsFrom = action.requestParam.params;
        }
      }
      //blob 数据流格式
      if (this.contain(responseType, "BLOB")) {
        this.mockShowBlob = true;
      }

      //构建响应参数
      if (action.responseParam && action.responseParam.params && action.responseParam.params.length > 0) {
        this.demoResponse = this.fromtJSON(this.getParams(action.responseParam.params, {}))
      }

    }
  }

  private getParams(params, result) {
    console.log("构建前的的参数", JSON.stringify(params));
    let ret = this.buildParams(params, result);
    console.log("初步构建完的参数", JSON.stringify(ret));
    //如果是数组 返回对象的第一个key
    if (params.length == 1 && this.contain(params[0].dataType, "array")) {
      let objArray = Object.keys(result);
      console.log("数组的时候 取对象的第一个key  ", JSON.stringify(objArray))
      let key = objArray[0];
      ret = result[key];
    }
    console.log("完全构建完的参数", JSON.stringify(ret))
    return ret;
  }

  //构建参数
  private buildParams(params, result): any {
    if (params && params.length > 0) {
      for (const value of params) {

        if (value.list && value.list.length > 0) {
          //把list递归
          if (this.contain(value.dataType, "object")) {//对象
            if (Array.isArray(result)) {
              result[0] = {};
              this.buildParams(value.list, result[0]);
            } else {
              result[value.name] = {};
              this.buildParams(value.list, result[value.name]);
            }
          } else if (this.contain(value.dataType, "array")) {//数组
            if (Array.isArray(result)) {
              result[0] = [];
              this.buildParams(value.list, result[0]);
            } else {
              result[value.name] = [];
              this.buildParams(value.list, result[value.name]);
            }
          }
        } else {
          result[value.name] = this.getDefaultValue(value);
        }

      }//for end
    }
    return result;
  }

  /**
   * 根据数据类型设置默认值
   * @param value 默认值
   */
  private getDefaultValue(param) {
    if (!param || !param.dataType) {
      return null;
    }
    //存在默认值时 返回默认值
    if (param.defaultValue) {
      return param.defaultValue;
    }
    //不存在默认值时 返回类型的默认值
    let value = param.defaultValue;
    if (this.contain(param.dataType, "string")) {
      value = '';
    } else if (this.contain(param.dataType, "number")) {
      value = 0;
    } else if (this.contain(param.dataType, "boolean")) {
      value = false;
    } else if (this.contain(param.dataType, "array")) {
      value = [];
    } else if (this.contain(param.dataType, "object")) {
      value = {};
    }
    return value;
  }


  /**
   * 字符串是否包含某子字符串
   * @param str 字符串
   * @param subStr 子字符串
   */
  private contain(str, subStr): boolean {
    return str && str.indexOf(subStr) > -1;
  }

  //格式化json数据
  private fromtJSON(json) {
    return JSON.stringify(json, null, 2);
  }

//发送测试方法
  sendTest() {
    //刷新图片验证码
    if (this.mockShowBlob) {
      this.mockShowResponse = true;
      this.mockBlobUrl = this.mockUrl;
      this.mockBlobUrl = this.mockBlobUrl + "?" + new Date();
      console.log('请求地址： ', this.mockBlobUrl);
    } else {//发送测试
      console.log('请求方式: ', this.mockMethod);
      console.log('请求地址: ', this.mockUrl);
      if (this.contain(this.mockMethod, 'GET')) {
        this.http.get(this.mockUrl).subscribe(data => this.success(data), error => this.error4sendTest(error));
      } else if (this.contain(this.mockMethod, 'POST')) {
        this.http.post(this.mockUrl, this.mockReqParams).subscribe(data => this.success(data), error => this.error4sendTest(error));
      } else if (this.contain(this.mockMethod, 'PUT')) {
        this.http.put(this.mockUrl, this.mockReqParams).subscribe(data => this.success(data), error => this.error4sendTest(error));
      } else if (this.contain(this.mockMethod, 'DELETE')) {
        this.http.delete(this.mockUrl).subscribe(data => this.success(data), error => this.error4sendTest(error));
      }
    }

  }

  /**
   * 请求成功调用方法
   * @param data 响应数据
   */
  private success(data) {
    //登陆后保存token
    if (data && data.data && data.data.token) {
      console.log("设置token", data.data.token);
      localStorage.setItem("token", data.data.token);
    }
    this.mockShowResponse = true;
    console.log(data);
    this.mockResponse = this.fromtJSON(data);
    this.isLogout();
  }


  /**
   * 请求失败调用方法
   * @param error
   */
  private error4sendTest(error) {
    this.error(error);
    this.mockShowResponse = true;
    let msg: any = "";
    if (error.status) {
      msg = "状态码：" + error.status + "\n";
    }
    if (error.url) {
      msg = msg + "请求路径：" + error.url + "\n";
    }
    if (error.message) {
      msg = msg + "提示信息：" + error.message + "\n";
    }
    this.mockResponse = +"出现错误或异常，具体如下：\n\n" + msg + "\n" + this.fromtJSON(error);
  }

  /**
   * 请求失败调用方法
   * @param error
   */
  private error(error) {
    this.messageService.error("数据请求失败，错误如下:" + JSON.stringify(error));
    console.error("数据请求失败，错误如下:" + JSON.stringify(error));
    this.isLogout();
  }

  //是否退出登陆
  isLogout() {
    if (this.contain(this.mockUrl, "/logout")) {
      localStorage.clear();
    }
  }

//退出登陆
  logout() {
    this.sendTest();
  }

//上传文件
  sendFile($event, fileKey: string) {
    this.http.upload(this.mockUrl, $event, fileKey).subscribe(data => this.success(data), error => this.error(error));
  }


  /**
   * 右侧显示文档信息
   */
  showInfo() {
    this.detail = null;
  }


  //-------------编辑信息-------------------

//保存基本信息
  saveInfo(info) {
    this.http.post(apis.updateInfo, info).subscribe(
      data => {
        console.log(data);
      },
      error => {
        this.error(error);
      }
    )
  }

  //编辑接口的 描述信息
  saveDescription(description) {
    let param = {
      id: this.action.id,
      description: description
    }
    this.http.post(apis.updateActionDescription, param).subscribe(
      data => {
        console.log(data);
      },
      error => {
        this.error(error);
      }
    );
  }

//编辑详情的 请求参数
  saveDetailReq(params: any) {
    if (params && params.length > 0) {
      console.log(JSON.stringify(params));
      //循环请求参数 组装后台保存信息的数据结构
      // let update = this.packageParams(params, {detail: {}, classList: {}});
      let update = this.packageParams(params, {});
      let param = {
        methodUUID: this.detail.requestParam.methodUUID,
        update: update
      };
      this.http.post(apis.updateDetail, param).subscribe(
        data => {
          console.log(data);
          this.getDetail(this.action);
        },
        error => {
          this.error(error);
        }
      );

    }
  }


  //修改响应参赛
  saveDetailResp(params) {
    if (params && params.length > 0) {
      console.log(JSON.stringify(params));
      //循环请求参数 组装后台保存信息的数据结构
      // let update = this.packageParams(params, {detail: {}, classList: {}});
      let update = this.packageParams(params, {});
      let param = {
        methodUUID: this.detail.responseParam.methodUUID,
        update: update
      };
      console.log(JSON.stringify(param))
      this.http.post(apis.updateDetail, param).subscribe(
        data => {
          console.log(data);
          this.getDetail(this.action);
        },
        error => {
          this.error(error);
        }
      );

    }
  }

  //组装数据
  private packageParams(params, result) {
    //接口需要修改的信息
    /* 组装该接口详细信息 存储数据结构
    {
       "id-people": {
         "defaultVale": "null",
         "description": "id",
         "required": true
       },
    }*/
    for (let p of params) {
      if (p.list && p.list.length > 0) {
        this.packageParams(p.list, result);
      }
      let key, value;
      if (p.pid == "0") {
        key = p.name + "-null";
      } else {
        key = p.name + "-" + p.pid;
      }
      value = {
        "defaultVale": p.defaultValue,
        "description": p.description,
        "required": p.required,
        "show": p.show
      }
      result[key] = value;
    }//for end
    return result;
  }


  /**
   * 添加一级请求参数
   */
  addFirstRequestParam() {
    this.addParam(false, 0);
  }

  /**
   * 添加参数
   */
  private addParam(isReturn, pid) {
    const param = {
      returnd: isReturn,
      actionId: this.action.id,
      pid: pid,
      edit: true
    };
    this.detail.requestParam.params ? true : this.detail.requestParam.params = [];
    this.detail.requestParam.params.push(param);
  }


}
