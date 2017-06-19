import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessagePriority } from '../model/message-priority';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit {

  @Input() priority: MessagePriority;
  @Input() text: string;
  @Input() date: Date;
  @Output() onMessageRemoved = new EventEmitter<void>();
  @Output() onMessageRead = new EventEmitter<void>();

  icon: string;

  constructor() { }

  ngOnInit() {
    switch (this.priority) {
      case MessagePriority.INFO:
        this.icon = 'fa-info';
    }
  }

  removeMessage() {
    this.onMessageRemoved.emit();
  }

  readMessage() {
    this.onMessageRead.emit();
  }
}
