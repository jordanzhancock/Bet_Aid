import React, {useState, useEffect} from "react";
import axios from "axios";

function LiveBets(){

    const [sports, setSports] = useState(JSON);

    useEffect(() => {
        getSport();
        }, []);
      

    function getSport(){
    let config = {
      method: 'get',
      maxBodyLength: Infinity,
      url: 'http://localhost:8080/api/sport',
      headers: { }
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

    return(
    <>
    <h1>Live Bets</h1>
    <div>
        {sports.map(function(sport) {
      return (
        <>
        <div>
          Sport Title:  {sport.sport_title}
        </div>
        <div>
            Start Time: {sport.commence_time}
        </div>
        <div>
            Home Team: {sport.home_team}
        </div>
        <div>
            Away Team: {sport.away_team}
        </div>
        <div>
            Bookmaker: {sport.bookmakers[0].title}
        </div>
        <div>
            Last Update: {sport.bookmakers[0].last_update}
        </div>

        </>
      )
    })}
    </div>
    </>
    );
    

}

export default LiveBets;