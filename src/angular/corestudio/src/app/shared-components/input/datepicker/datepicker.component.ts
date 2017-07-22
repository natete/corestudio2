import { Component, Input, OnInit, Renderer, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InputInfo } from '../input-info';
import { MdDatepicker } from '@angular/material';

@Component({
  selector: 'app-datepicker',
  templateUrl: './datepicker.component.html',
  styleUrls: ['./datepicker.component.scss']
})
export class DatepickerComponent implements OnInit {

  @Input() inputInfo: InputInfo;
  @Input() group: FormGroup;
  @ViewChild(MdDatepicker) dp: MdDatepicker<Date>;

  constructor(private renderer: Renderer) { }

  ngOnInit() { }

  openDatepicker() {
    this.dp.open();
  }
}
