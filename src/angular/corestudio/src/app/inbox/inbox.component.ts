import { Component, OnInit } from '@angular/core';
import { InboxService } from './inbox.service';
import { Message } from './model/message';

@Component({
  selector: 'app-inbox',
  templateUrl: './inbox.component.html',
  styleUrls: ['./inbox.component.scss']
})
export class InboxPageComponent implements OnInit {

  messages: Message[] = [];

  constructor(private inboxService: InboxService) { }

  ngOnInit() {
    this.inboxService.getMessages()
        .subscribe(
            messages => this.messages = messages
        );
  }

  removeMessage(event: any, messageId: number) {
    console.log(messageId);
  }

  readMessage(event: any, messageId: number) {
    console.log(messageId);
  }
}
