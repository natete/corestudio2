import { Component, Input, OnInit } from '@angular/core';
import { InputInfo } from './input-info';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {

  @Input() inputInfo: InputInfo;
  @Input() group: FormGroup;

  constructor() { }

  ngOnInit() { }

}
