import React, {useState, useEffect} from "react";
import axios from "axios";

function BetHistory(){

    const [bets, setBets] = useState(JSON);

    useEffect(() => {
        getBet();
        }, []);
      

    function getBet(){
    let config = {
      method: 'get',
      maxBodyLength: Infinity,
      url: 'http://localhost:8080/api/bet/history/1',
      headers: { }
    };
    
    axios.request(config)
    .then((response) => {
      console.log(JSON.stringify(response.data));
      setBets(response.data);
      console.log("BETS: "+bets);
    })
    .catch((error) => {
      console.log(error);
    });
}

    return(
    <>
    <h1>Bet History</h1>
    <div>
        {bets.map(function(bet) {
      return (
        <>
        <div>
          Bet ID:  {bet.betId}
        </div>
        <div>
            UserId {bet.userId}
        </div>
        <div>
            Amount: {bet.amount}
        </div>
        <div>
            Bet Type: {bet.betType}
        </div>
        <div>
            Description: {bet.description}
        </div>
        <div>
            Profit/Loss: {bet.profit}
        </div>

        </>
      )
    })
}
    </div>
    </>
    );
    

}

export default BetHistory;