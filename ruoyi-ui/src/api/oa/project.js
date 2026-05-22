import request from '@/utils/request'

// 查询项目管理列表
export function listProject(query) {
  return request({
    url: '/oa/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目管理详细
export function getProject(projectId) {
  return request({
    url: '/oa/project/' + projectId,
    method: 'get'
  })
}

// 新增项目管理
export function addProject(data) {
  return request({
    url: '/oa/project',
    method: 'post',
    data: data
  })
}

// 修改项目管理
export function updateProject(data) {
  return request({
    url: '/oa/project',
    method: 'put',
    data: data
  })
}

// 删除项目管理
export function delProject(projectId) {
  return request({
    url: '/oa/project/' + projectId,
    method: 'delete'
  })
}

// 查询项目里程碑（按 M0-M6 分组）
export function getMilestones(projectId) {
  return request({
    url: '/oa/project/milestone/' + projectId,
    method: 'get'
  })
}

// 更新里程碑任务
export function updateMilestoneTask(data) {
  return request({
    url: '/oa/project/milestone/task',
    method: 'put',
    data: data
  })
}

// 推进到下一里程碑
export function advanceMilestone(projectId) {
  return request({
    url: '/oa/project/milestone/advance/' + projectId,
    method: 'post'
  })
}
