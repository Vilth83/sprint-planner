import { Status } from './status.model';

export class CandidateUpdateDto {
  id: number;
  priority: number;
  status: Status;

  constructor(id: number, priority: number, status: Status) {
    this.id = id;
    this.priority = priority;
    this.status = status;
  }
}
