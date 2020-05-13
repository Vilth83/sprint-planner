export class JobUpdateDto {
  title: string;
  task: string;
  active: boolean;

  constructor(title?: string, task?: string, active?: boolean) {
    this.title = title;
    this.task = task;
    this.active = active;
  }
}
