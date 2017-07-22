import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InputInfo } from '../input-info';

@Component({
  selector: 'app-input-textarea',
  templateUrl: './input-textarea.component.html',
  styleUrls: ['./input-textarea.component.scss']
})
export class InputTextAreaComponent implements OnInit {

  @Input() inputInfo: InputInfo;
  @Input() group: FormGroup;

  constructor() { }

  ngOnInit() { }

}
