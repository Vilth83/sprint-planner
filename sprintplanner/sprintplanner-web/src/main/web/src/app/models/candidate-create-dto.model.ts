import { IdDto } from './IdDto.model';

export class CandidateCreateDto {
  member: IdDto;
  task: IdDto;

  constructor(member:IdDto, task: IdDto) {
    this.member = member;
    this.task = task;
  }
}
