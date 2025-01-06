import { api } from 'src/boot/axios'

const config = {
  headers: {
    'Content-Type': 'application/json',
  },
}

const responseHandler = (response) => {
  return response.data
}

const BOARD_PAGE_URL = () => `/board/page`
const BOARD_LIST_URL = () => `/board/list`
const BOARD_WRITE_URL = () => `/board/write`
const BOARD_EDIT_URL = (id) => `/board/edit/${id}`
const BOARD_URL = (id) => `/board/${id}`

// 게시물 페이징 조회
export const postBoardPaginationList = async (requestBody) => {
  try {
    const response = await api.post(BOARD_PAGE_URL(), requestBody, config)
    return responseHandler(response)
  } catch (error) {
    console.error('postBoardPaginationList 처리 실패', error)
  }
}

// 게시물 전체 조회 api
export const getBoardList = async () => {
  try {
    const response = await api.get(BOARD_LIST_URL())
    return responseHandler(response)
  } catch (error) {
    console.error('getBoardList 처리 실패', error)
    throw error
  }
}

// 게시물 등록 api
export const postBoard = async (requestBody) => {
  try {
    const response = await api.post(BOARD_WRITE_URL(), requestBody, config)
    return responseHandler(response)
  } catch (error) {
    console.error('postBoard 처리 실패', error)
    throw error
  }
}

// 게시물 수정 api
export const patchBoard = async (id, requestBody) => {
  try {
    const response = await api.patch(BOARD_EDIT_URL(id), requestBody, config)
    return responseHandler(response)
  } catch (error) {
    console.log('patchBoard 처리 실패', error)
    throw error
  }
}

// 게시글 상세 조회 api
export const getBoard = async (id) => {
  try {
    const response = await api.get(BOARD_URL(id))
    console.log(response)
    return responseHandler(response)
  } catch (error) {
    console.log('getBoard 처리 실패', error)
    throw error
  }
}
