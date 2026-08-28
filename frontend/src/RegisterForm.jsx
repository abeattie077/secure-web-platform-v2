import { useState } from 'react'

function RegisterForm(){
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [requestedUsername, setRequestedUsername] = useState('')
  const [requestedPassword, setRequestedPassword] = useState('')
  const [registerMessage, setRegisterMessage] = useState('')
  async function createAccount(event){
    event.preventDefault()
    const response = await fetch ('http://localhost:8080/api/users/register', 
      {
        method: 'POST',
        headers:
        {
          'Content-Type' : 'application/json'
        },
        body: JSON.stringify({
          firstName: firstName,
          lastName: lastName,
          username: requestedUsername,
          password: requestedPassword
        })
      }
    )
    const result = await response.text()
    setRegisterMessage(result)
  }
    return(
        <div className="front-page">
            <h2>Create Account</h2>
            <form onSubmit = {createAccount}>
            <h3>First Name:</h3>
            <input 
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}/>
            <h3>Last Name:</h3>
            <input 
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}/>
            <h3>Requested Username:</h3>
            <input 
            value={requestedUsername}
            onChange={(event) => setRequestedUsername(event.target.value)}/>
            <h3>Requested Password:</h3>
            <input 
            value={requestedPassword}
            type="password"
            onChange={(event) => setRequestedPassword(event.target.value)}/>
            <button type="submit">Create Account</button>
            </form>
            <p>{registerMessage}</p>
        </div>
    )
}
export default RegisterForm