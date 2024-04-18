import React, { useState } from 'react';
import 'antd/dist/reset.css';
import './index.css';
import { Button } from 'antd';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Logout() {
    // const navigate = useNavigate();
    const [logoutMessage, setLogoutMessage] = useState('');

    function handleLogout() {



    let data = JSON.stringify ({
        "accessToken": localStorage.getItem("accessToken")

      });
      
      let config = {
        method: 'post',
        maxBodyLength: Infinity,
        url: 'http://localhost:8080/api/logout',
        headers: { 
          'Content-Type': 'application/json'
        },
        data : data
      };
      
      axios.request(config)
      .then((response) => {
        console.log(JSON.stringify(response.data));
        localStorage.removeItem("accessToken");


      })
      .catch((error) => {
        console.log(error);
      });
    }
    if(!localStorage.getItem("accessToken")){
       return null;
    }

    return (
        <div>
            <h1>Logout</h1>
            <Button type="primary" onClick={handleLogout}>
                Logout
            </Button>
            {logoutMessage && <p>{logoutMessage}</p>} {/* Display the logout message if it exists */}
        </div>
    );
}

export default Logout;
