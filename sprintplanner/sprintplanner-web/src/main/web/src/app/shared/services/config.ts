import { HttpHeaders } from '@angular/common/http';

const url = 'http://localhost:8081';
const api = url + '/api';

export const Config = {

  uris: {
    root: url,
    api: api,
    token: url + '/oauth/token',
  },

  endpoints: {
    users : '/users',
    userInfo: '/userInfo',
    project: '/projects',
    members: '/members',
    job: '/jobs',
    current: '/current',
    available: '/available',
    candidates: '/candidates',
    releases: {
      all: '/releases',
      last: '/releases/last'
    },
    tasks: '/tasks',
    reconciliation: {
      branches: '/reconciliations/branches',
      repositories: '/reconciliations/repositories',
      reconciliate: '/reconciliations/reconciliate'
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
    shift: 'shift=',
    currentBranch: '?currentBranch=',
    previousBranch: '&previousBranch=',
    username: '&username=',
    password: '&password='
  },

  shifts: {
    paris: 'paris',
    bangalore: 'bangalore'
  },

  appenders: {
    slash: '/',
    and:'&',
    param: '?'
  }
};
