import { MessagePriority } from './message-priority';

export class Message {
  id: number;
  priority: MessagePriority;
  date: Date;
  body: string;
  read: boolean;


  constructor(rawMessage: any = {}) {
    this.id = rawMessage.id;
    this.priority = rawMessage.priority;
    this.date = new Date(rawMessage.date);
    this.body = rawMessage.body;
    this.read = rawMessage.read;
  }
}
