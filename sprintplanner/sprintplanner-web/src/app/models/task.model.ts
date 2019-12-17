import { Member } from './member.model';

export class Task {

  id: number;
  name: String;
  description: string;
  manager: Member;
}
