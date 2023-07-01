import './App.css';
import NavBar from "./components/NavBar";
import { Home } from "./components/Pages/Home";
import { Posts } from "./components/Pages/Posts";
import { Applicants } from "./components/Pages/Applicants";
import { Contact } from "./components/Pages/Contact";
 import {useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Login } from './Login';
import { Signup } from './Signup';

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
        </Routes>
      </div>
    </Router>
  );
}


export default App;