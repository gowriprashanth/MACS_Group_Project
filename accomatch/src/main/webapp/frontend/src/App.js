import './App.css';
import NavBar from "./Components/NavBar";
import { Home } from "./Components/Pages/Home";
import { Posts } from "./Components/Pages/Posts";
import { Applicants } from "./Components/Pages/Applicants";
import { Contact } from "./Components/Pages/Contact";
 import {useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Login } from './Login';
import { Signup } from './Signup';
import {HouseSeekerCreateApplication} from "./Components/Pages/HouseSeekerCreateApplication";
import{LeaseHolderCreateApplication} from "./Components/Pages/LeaseHolderCreateApplication";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLoginSuccess = () => {
    setIsLoggedIn(true);
  };

  return (
    <Router>
      <main className="App">
        <Routes>
          <Route
            path="/"
            element={<Login onLoginSuccess={handleLoginSuccess} />}
          />
          <Route path="/signup" element={<Signup />} />
        </Routes>
      </main>

      {<NavBar />}

      <div className="pages">
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route path="/posts" element={<Posts />} />
          <Route path="/applicants" element={<Applicants />} />
          <Route path="/contact" element={<Contact />} />
          <Route path="/create" element={<LeaseHolderCreateApplication/>}/>
          <Route path="/createApplicant" element={<HouseSeekerCreateApplication/>}/>
        </Routes>
      </div>
    </Router>
  );
}


export default App;