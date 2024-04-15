import logo from './logo.svg';
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import Login from "./pages/Login"
import HomePage from './pages/HomePage';
import LiveBets from './pages/LiveBets';
import BetHistory from './pages/BetHistory';


// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }'

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" >
          <Route path="homepage" element={<HomePage />} />
          <Route path="livebets" element={<LiveBets />} />
          <Route path="login" element={<Login />} />
          <Route path="bethistory" element={<BetHistory />} />
          {/*<Route path="blogs" element={<Blogs />} />
          <Route path="contact" element={<Contact />} />*/}
          {/* <Route path="*" element={<HomePage />} />  */}
        </Route>
      </Routes>
    </BrowserRouter>
  );
}



