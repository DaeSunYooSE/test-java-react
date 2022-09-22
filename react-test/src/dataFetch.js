import axios from "axios"

const COUNT_API = "/api/counter"
const COUNT_MINUS_API = "/api/counter/minus"
const COUNT_PLUS_API = "/api/counter/plus"

export const createCountFetch = async (username) => {
  try {
    await axios.post(COUNT_API, {username}, {
      headers: {'Content-Type': 'application/json'},
    })
  } catch (e) {
    console.error(e)
  }
}

export const updateCountFetch = async (username, value) => {
  try {
    const api = value == 1 ? COUNT_PLUS_API : COUNT_MINUS_API
    await axios.put(api, {username}, {
      headers: {'Content-Type': 'application/json'},
    })
  } catch (e) {
    console.error(e)
  }
}

export const getCountFetch = async (username) => {
  try {
    const res = await axios.get(`${COUNT_API}/${username}`)
    return res.data['counterNum']
  } catch (e) {
    console.error(e)
  }
}