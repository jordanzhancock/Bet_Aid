import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function HomePage() {
    const [liveScores, setLiveScores] = useState([]);
    // const navigate = useNavigate();
    const accessToken = localStorage.getItem("accessToken");
    useEffect(() => {
        // if(accessToken){
        //     fetchData();
        // }else{
        //     navigate("/login")
        // }
        fetchData();
        
    }, []);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/scores/nba');
            console.log(response.data);
            setLiveScores(response.data);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };
    if(!localStorage.getItem("accessToken")){
        return null;
     }
    
    
    return (
        <>
            <h1>Live NBA Scores</h1>
            <div>
                {liveScores && liveScores.map((game, index) => (
                    <div className="game-card" key={index}>
                        <div>
                            <strong>Home Team:</strong> {game.home_team}
                        </div>
                        <div>
                            <strong>Away Team:</strong> {game.away_team}
                        </div>
                        {game.scores && game.scores.map((score, scoreIndex) => (
                            <div key={scoreIndex}>
                                <div>
                                    <strong>{score.name}:</strong> {score.score}
                                </div>
                            </div>
                        ))}
                    </div>
                ))}
            </div>
        </>
    );
}

export default HomePage;
