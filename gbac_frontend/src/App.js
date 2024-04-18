import { Navigate } from 'react-router-dom';
import './App.css';
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import HomePage from './pages/HomePage';
import LiveBets from './pages/LiveBets';
import BetHistory from './pages/BetHistory';
import Login from "./pages/Login"
import Logout from './pages/Logout';
import { useEffect } from 'react';

export default function App() {
  const accessToken = localStorage.getItem("accessToken");
  
  return (
    <>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/homepage">Home</Link>
            </li>
            <li>
              <Link to="/livebets">Live Bets</Link>
            </li>
            <li>
              <Link to="/bethistory">Bet History</Link>
            </li>
            <li>
              <Link to="/login">Login</Link>
            </li>
            <li>
              <Link to="/logout">Logout</Link>
            </li>
          </ul>
        </nav>

        <Routes>
        <Route
        path="/*"
        element={ accessToken
          ? <HomePage />
          : <Navigate to="/login" replace />
        }
      />
          <Route path="/homepage" element={<HomePage />} />
          <Route path="/livebets" element={<LiveBets />} />
          <Route path="/login" element={<Login />} />
          <Route path="/bethistory" element={<BetHistory />} />
          <Route path="/logout" element={<Logout />} />
        </Routes>
      </div>
    </>
  );
}
