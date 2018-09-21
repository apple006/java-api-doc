import {Component, Input} from '@angular/core';
import {HttpService} from "../../http-service.service";
import {apis} from "../../api";
import {IndexComponent} from "../index/index.component";

@Component({
  selector: 'app-api-param',
  templateUrl: './api-param.component.html',
  styleUrls: ['./api-param.component.scss']
})
export class ApiParamComponent {
  @Input()
  params: any;
  @Input()
  index = 0;

  constructor(private http: HttpService,
              private  indexComponent: IndexComponent) {

  }

  /**
   * 保存参数
   */
  saveParam(param) {
    if (!param.id) {//添加

      delete  param.edit;
      this.http.post(apis.addParam, param).subscribe(
        data => {
          if (data) {
            param.id = data;
            console.log(data);
          }
        },
        error => {
          param.edit = true;
          console.error(error)
        }
      );

    } else {

      delete  param.edit;
      this.http.post(apis.updateParam, param).subscribe(
        data => {
          if (data) {
            console.log(data);
          }
        },
        error => {
          param.edit = true;
          console.error(error)
        }
      );

    }

  }

  /**
   * 删除参数
   */
  deleteParam(id: any) {
    if (id) {

      this.http.delete(apis.deleteParam + "/" + id).subscribe(
        data => {
          console.log(data);
          if (data) {//删除成功后删除页面元素
            for (let i = 0; i < this.params.length; i++) {
              if (this.params[i].id == id) {
                this.params.splice(i, 1);
              }
            }
          }

        },
        error => {
          console.error(error)
        }
      );

    } else {
      for (let i = 0; i < this.params.length; i++) {
        if (!this.params[i].id) {
          this.params.splice(i, 1);
        }
      }
    }


  }

  /**
   * 添加请求参数中的子级参数
   * @param id
   */
  addSubRequestParam(id: any) {
    this.addParam(false, id)
  }

  /**
   * 添加参数
   */
  public addParam(isReturn, pid) {
    const param = {
      returnd: isReturn,
      actionId: this.indexComponent.action.id,
      pid: pid,
      edit: true
    };
    for (let p of this.params) {
      if (p.id == pid) {
        p.list ? true : p.list = [];
        p.list.push(param);
      }
    }
  }
}
