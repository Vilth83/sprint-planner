import { Member } from './member.model';
import { Status } from './status.model';
import { Task } from './task.model';

export class Candidate {
  id: number;
  member: Member;
  task: Task;
  priority: number;
  status: Status;

  constructor() {}
}
