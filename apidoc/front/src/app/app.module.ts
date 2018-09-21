import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpService} from "./http-service.service";
import {InterfaceModule} from './apidoc/apidoc.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./auto-intercepter";
import {registerLocaleData} from '@angular/common';
/** 配置 angular i18n **/
import zh from '@angular/common/locales/zh';
import {NgZorroAntdModule, NZ_I18N, zh_CN} from "ng-zorro-antd";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    /** 导入 ng-zorro-antd 模块 **/
    NgZorroAntdModule,
    BrowserModule,
    InterfaceModule,
  ],
  providers: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    HttpService,
    /** 配置 ng-zorro-antd 国际化 **/
    {provide: NZ_I18N, useValue: zh_CN},
    /*配置拦截器*/
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
