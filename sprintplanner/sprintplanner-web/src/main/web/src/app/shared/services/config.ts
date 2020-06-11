import { HttpHeaders } from '@angular/common/http';

const url = 'http://localhost:8081';
const api = url + '/api';

export const Config = {

  uris: {
    root: url,
    api: api,
    token: url + '/oauth/token'
  },

  endpoints: {
    project: '/projects',
    members: '/members',
    job: '/jobs',
    releases: {
      all: '/releases',
      last: '/releases/last'
    },
    tasks: '/tasks',
    reconciliation: {
      reconciliate: '/reconciliations/reconciliate'
    },
    github: {
      branches: '/github/branches'
    }
  },

  httpOptions: {
    json: { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) },
    formUrlEncoded: { headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' }) },
    headerToken: 'Authorization'
  },
  token: {
    cookieFieldName: 'sprintplanner_cookie',
    type: 'Bearer ',
    header: 'Authorization'
  },
  grantType: {
    password: 'grant_type=password',
    refresh: 'grant_type=refresh_token'
  },
  cookies: {
    cookieFieldName: 'sprintplanner_user',
  },
  clientId: '&client_id=sprintplanner-web',
  refreshToken: "&refresh_token=",
  title: 'sprintplanner: handle your releases',

  params: {
    releaser: "?task=releaser",
    support: "?task=support",
    tester: "?task=tester",
    taskName: '?taskName=',
    shift: '&shift=',
    currentBranch: '?currentBranch=',
    previousBranch: '&previousBranch='
  },

  shifts: {
    paris: 'paris',
    bangalore: 'bangalore'
  }
};
