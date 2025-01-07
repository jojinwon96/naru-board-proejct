import { ref } from 'vue'
import type { Post } from '@/types/post'

export const usePost = () => {
  let post: Post | null = null

  const getPost = () => post

  const setPost = (newPost: Post) => {
    post = newPost
  }

  const fetchPost = async () => {
    try {
      const response = await fetch('https://jsonplaceholder.typicode.com/posts/1')
      const data = await response.json()
      setPost(data)
    } catch (error) {
      console.error('fetchPost 호출 실패:', error)
    }
  }

  return { getPost, setPost, fetchPost }
}
