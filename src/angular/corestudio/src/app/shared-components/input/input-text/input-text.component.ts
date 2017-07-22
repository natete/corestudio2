import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InputInfo } from '../input-info';

@Component({
  selector: 'app-input-text',
  templateUrl: './input-text.component.html',
  styleUrls: ['./input-text.component.scss']
})
export class InputTextComponent implements OnInit {

  @Input() inputInfo: InputInfo;
  @Input() group: FormGroup;

  constructor() { }

  ngOnInit() { }

}
