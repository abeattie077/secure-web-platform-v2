import { useState } from 'react'
import './App.css'
import LoginForm from './LoginForm.jsx'
import RegisterForm from './RegisterForm.jsx'
import IncidentList from './IncidentList.jsx'

function App() {
  const [loggedIn, setLoggedIn] = useState(false)
  const [loggedInUsername, setLoggedInUsername] = useState('')
  if (loggedIn){
    return(
      <div>
        <h1><a href="/">Secure Access Monitor</a></h1>
        <button className="logout-button" onClick={() => setLoggedIn(false)}>Logout</button>
        <IncidentList username={loggedInUsername}/>
      </div>
    )
  }
  return (
    <div>
      <h1><a href="/">Secure Access Monitor</a></h1>
      <LoginForm onLoginSuccess={(username) => {setLoggedIn(true); setLoggedInUsername(username);}} />
      <RegisterForm />
    </div>
  )
}
export default App
