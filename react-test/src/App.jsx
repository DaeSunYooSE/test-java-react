import React, {useState} from "react"
import {createCountFetch, getCountFetch, updateCountFetch} from "./dataFetch"

export const App = () => {
  const [username, setUsername] = useState("")
  const [showCountButton, setShowCountButton] = useState(false)
  const [count, setCount] = useState(0)

  const createCount = async (e) => {
    e.preventDefault()
    await createCountFetch(username)
    const res = await getCountFetch(username)
    setCount(res)
    setShowCountButton(true)
  }

  const updateCount = async (e, val) => {
    e.preventDefault()
    await updateCountFetch(username, val)
    const res = await getCountFetch(username)
    setCount(res)
  }

  return (
      <div>
        <input type={"text"} placeholder={"Username"} value={username}
               onChange={(e) => setUsername(e.target.value)}/>
        <input type={"button"} value={"Count Start"}
               onClick={createCount}/>
        {showCountButton && (
            <div>
              Value : {count}
              <input type={"button"} value={"plus"}
                     onClick={(e) => updateCount(e, 1)}/>
              <input type={"button"} value={"minus"}
                     onClick={(e) => updateCount(e, -1)}/>

            </div>
        )}
      </div>
  )
}

