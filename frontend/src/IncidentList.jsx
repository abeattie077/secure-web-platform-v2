import { useState, useEffect } from 'react'

function IncidentList({username}){
    const [incidents, setIncidents] = useState([])
    async function loadIncidents(){
        const response = await fetch (`http://localhost:8080/api/incidents/${username}`)
        const data = await response.json()
        console.log(data)
        setIncidents(data)
    }
    useEffect(() => {loadIncidents()}, [username])
    return(
        <div className="incident-dashboard">
            <h2>Security Incidents</h2>
            <table className="incident-table">
                <thead>
                    <tr>
                        <th>Incident ID</th>
                        <th>Username</th>
                        <th>IP Address</th>
                        <th>Date & Time</th>
                        <th>Severity</th>
                    </tr>
                </thead>
            <tbody>
                {incidents.map((incident) => (
                    <tr key={incident.incidentID}>
                        <td>{incident.incidentID}</td>
                        <td>{incident.loginAttempt.usernameUsed}</td>
                        <td>{incident.loginAttempt.IPAddress}</td>
                        <td>{new Date(incident.loginAttempt.dateTime).toLocaleString()}</td>
                        <td className={incident.severityLevel}>{incident.severityLevel}</td>
                    </tr>
                ))}
            </tbody>
            </table>
        </div>
    )
}
export default IncidentList
