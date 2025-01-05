import { api } from 'src/boot/axios'

const responseHandler = (response) => {
  return response.data
}

const BOARD_LIST_URL = () => `/board/list`

export const getBoardList = async () => {
  try {
    const response = await api.get(BOARD_LIST_URL())
    return responseHandler(response)
  } catch (error) {
    console.error('getBoardList 처리 실패', error)
    throw error
  }
}
