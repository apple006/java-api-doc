import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {IndexComponent} from './index/index.component';
import {ApiParamComponent} from './api-param/api-param.component';
import {DragulaModule} from "ng2-dragula";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgZorroAntdModule.forRoot(),
    DragulaModule.forRoot()
  ],
  declarations: [IndexComponent, ApiParamComponent],
  exports: [IndexComponent]
})
export class InterfaceModule {
}
