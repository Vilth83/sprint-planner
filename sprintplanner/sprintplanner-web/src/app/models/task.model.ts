import { Member } from './member.model';

export class Task {

  id: number;
  name: string;
  description: string;
  manager: Member;
}
