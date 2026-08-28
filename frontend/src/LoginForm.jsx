import { useState } from 'react'

function LoginForm({onLoginSuccess}){
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [loginMessage, setLoginMessage] = useState('')
  async function handleLogin(event){
    event.preventDefault()
    const response = await fetch ('http://localhost:8080/api/users/login', 
      {
        method: 'POST',
        headers:
        {
          'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
          username: username,
          password: password
        })
      }
    )
    const result = await response.text()
    setLoginMessage(result)
    if (result === "Login Successful"){onLoginSuccess(username)}
  }
    return(
        <div className="front-page">
            <h2>Login</h2>
            <form onSubmit = {handleLogin}>
            <h3>Username:</h3>
            <input 
             value={username}
             onChange={(event) => setUsername(event.target.value)}/>
             <h3>Password:</h3>
             <input 
            value={password}
             type="password"
            onChange={(event) => setPassword(event.target.value)}/>
            <button type="submit">Login</button>
            </form>
            <p>{loginMessage}</p>
        </div>
    )
}
export default LoginForm