import React, { useState, useEffect } from 'react';
import { Card } from 'antd';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LiveBets = () => {
    //const navigate = useNavigate();
    const [sports, setSports] = useState([]);

    useEffect(() => {
        getSport();
    }, []);

    function getSport() {
        let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: 'http://localhost:8080/api/sport',
            headers: {}
        };

        axios.request(config)
            .then((response) => {
                console.log(JSON.stringify(response.data));
                setSports(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }
    if(!localStorage.getItem("accessToken")){
        return null;
     }

    return (
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
            <Card
                title="Live Bets"
                style={{ width: 600 }}
            >
                {sports && sports.map(sport => (
                    <Card key={sport.id} style={{ marginBottom: 16, backgroundColor: '#f0f2f5' }}>
                        <p>Sport Title: {sport.sport_title}</p>
                        <p>Start Time: {sport.commence_time}</p>
                        <p>Home Team: {sport.home_team}</p>
                        <p>Away Team: {sport.away_team}</p>
                        <p>Bookmaker: {sport.bookmakers[0].title}</p>
                        <p>Last Update: {sport.bookmakers[0].last_update}</p>
                    </Card>
                ))}
            </Card>
        </div>
    );
};

export default LiveBets;
