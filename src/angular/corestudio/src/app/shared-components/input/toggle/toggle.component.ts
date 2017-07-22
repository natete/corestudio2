import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { InputInfo } from '../input-info';

@Component({
  selector: 'app-toggle',
  templateUrl: './toggle.component.html',
  styleUrls: ['./toggle.component.scss']
})
export class ToggleComponent implements OnInit {

  @Input() inputInfo: InputInfo;
  @Input() group: FormGroup;

  constructor() { }

  ngOnInit() { }

}
